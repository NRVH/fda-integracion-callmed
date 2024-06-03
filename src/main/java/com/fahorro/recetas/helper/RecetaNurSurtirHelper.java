package com.fahorro.recetas.helper;

import com.fahorro.recetas.client.ClienteApiService;
import com.fahorro.recetas.client.ConvenioApiService;
import com.fahorro.recetas.client.RecetaApiService;
import com.fahorro.recetas.client.SucursalApiService;
import com.fahorro.recetas.dto.*;
import com.fahorro.recetas.dto.request.DataRequest;
import com.fahorro.recetas.dto.request.RequestSurtirRecetaNur;
import com.fahorro.recetas.exception.ExcepcionControlada;
import com.fahorro.recetas.exception.ExcepcionRespuestaNula;
import com.fahorro.recetas.exception.ExcepcionSucursalNoEncontrada;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@ApplicationScoped
public class RecetaNurSurtirHelper {

    private static final Logger log = LoggerFactory.getLogger(RecetaNurHelper.class);

    @Inject
    @RestClient
    ClienteApiService clienteApiService;

    @Inject
    @RestClient
    ConvenioApiService convenioApiService;

    @Inject
    @RestClient
    SucursalApiService sucursalApiService;

    @Inject
    @RestClient
    RecetaApiService recetaApiService;

    public String recetaNurSurtirProcess(DataRequest data) throws Exception {

        String idCliente = data.getRecetaNur().getNumCliente().toString();

        log.info("Se consultara cliente con numCliente ::: {}", idCliente);

        ClienteApiResponseDTO cliente = clienteApiService.getClienteByNumCliente(idCliente);

        if (Objects.isNull(cliente.getEntidad().getDataJson())) {
            log.info("Cliente no encontrado con idCliente ::: {} ", idCliente);
            throw new ExcepcionSucursalNoEncontrada("Cliente no encontrado con id :" + idCliente);
        }

        ConvenioResponseDTO convenio = convenioApiService.getConvenioByIdCliente(idCliente);

        if (Objects.isNull(convenio.getEntidad().getDataJson())) {
            log.info("Convenio no encontrado con idCliente ::: {} ", convenio);
            throw new ExcepcionSucursalNoEncontrada("Convenio no encontrado con idCliente :" + convenio);
        }

        SucursalApiResponseDTO sucursal = sucursalApiService.getSucursalById(data.getRecetaNur().getNumeroSucursal().toString());


        data.setClienteEntidad(cliente);
        data.setConvenioEntidad(convenio);
        data.setSucursalEntidad(sucursal);

        return guardarReceta(data);
    }

    private String guardarReceta(DataRequest data) throws Exception {
        RecetaApiResponseDTO.EntidadDTO.DataJsonDTO recetaRequest = new RecetaApiResponseDTO.EntidadDTO.DataJsonDTO();

        ConvenioResponseDTO.EntidadDTO convenioEntidad = data.getConvenioEntidad().getEntidad();
        ClienteApiResponseDTO.EntidadDTO.DataJsonDTO clienteJson = data.getClienteEntidad().getEntidad().getDataJson();
        SucursalApiResponseDTO.EntidadDTO sucursalEntidad = data.getSucursalEntidad().getEntidad();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);

        recetaRequest.setNombre(clienteJson.getNombre() + " " + clienteJson.getApellido());
        recetaRequest.setDescripcion("");
        recetaRequest.setFechaCreacion(formattedDate);
        recetaRequest.setUltimaModificacion(formattedDate);
        recetaRequest.setIdConvenio(convenioEntidad.getId());
        recetaRequest.setNombreMedico("");
        recetaRequest.setCedulaMedico("");
        recetaRequest.setNombreDelPaciente(clienteJson.getNombre() + " " + clienteJson.getApellido());
        recetaRequest.setTotalReceta(new BigDecimal(0));
        recetaRequest.setMontoCopago(new BigDecimal(0));
        recetaRequest.setIdSucursal(sucursalEntidad.getId());
        recetaRequest.setRazonNoSurtir("");
        recetaRequest.setEstatus(true);
        recetaRequest.setEstatusStr("Pendiente Surtir");

        try (Response response = recetaApiService.guardarReceta(recetaRequest)) {
            if (response.getStatus() == 200) {
                String responseBody = response.readEntity(String.class);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);

                if (rootNode.path("estatus").asInt() == 200 && "Recurso generado".equals(rootNode.path("detalles").asText())) {
                    log.info("La receta fue guardada exitosamente. ID: {}", rootNode.path("entidad").path("id").asText());
                    return rootNode.path("entidad").path("id").asText();
                } else {
                    log.warn("La receta no fue guardada. Detalles: {}", rootNode.path("detalles").asText());
                    throw new ExcepcionRespuestaNula("La receta no fue guardada. Detalles: " + rootNode.path("detalles").asText());
                }
            } else {
                log.error("Error en la operación: HTTP status {}", response.getStatus());
                throw new ExcepcionControlada("Error en la operación de guardar receta: HTTP status " + response.getStatus());
            }
        } catch (Exception e) {
            log.error("Error al procesar la respuesta: {}", e.getMessage(), e);
            throw new ExcepcionControlada("Error al procesar la respuesta: " + e.getMessage());
        }
    }
}
