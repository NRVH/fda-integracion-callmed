package com.fahorro.integracion.service;

import com.fahorro.integracion.client.RecetaApiService;
import com.fahorro.integracion.dto.ConvenioResponseDTO;
import com.fahorro.integracion.dto.ProductoApiResponseDTO;
import com.fahorro.integracion.dto.RecetaApiResponseDTO;
import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.dto.request.valreceta.Receta;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class GuardarRecetaAsync {

    private static final Logger log = LoggerFactory.getLogger(GuardarRecetaAsync.class);

    @Inject
    @RestClient
    RecetaApiService recetaApiService;

    @Asynchronous
    public CompletionStage<Void> asyncMethod(DataRequest data) {
        return CompletableFuture.runAsync(() -> {
            RecetaApiResponseDTO.EntidadDTO.DataJsonDTO recetaRequest = new RecetaApiResponseDTO.EntidadDTO.DataJsonDTO();
            ConvenioResponseDTO.EntidadDTO.DataJsonDTO convenioJson = data.getConvenioEntidad().getEntidad().getDataJson();
            ProductoApiResponseDTO.EntidadDTO.DataJsonDTO productoJson = data.getProductoEntidad().getEntidad().getDataJson();

            Receta recetaCallmed = data.getRecetaCallmed().getReceta();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
            LocalDateTime now = LocalDateTime.now();
            String formattedDate = now.format(formatter);

            recetaRequest.setNombre(recetaCallmed.getNombrePaciente());
            recetaRequest.setDescripcion("");
            recetaRequest.setFechaCreacion(formattedDate);
            recetaRequest.setUltimaModificacion(formattedDate);
            recetaRequest.setIdConvenio(data.getIdConvenio());
            recetaRequest.setNombreMedico(recetaCallmed.getNombreMedico());
            recetaRequest.setCedulaMedico(recetaCallmed.getClaveMedico().trim());
            recetaRequest.setNombreDelPaciente(recetaCallmed.getNombrePaciente());
            recetaRequest.setTotalReceta(BigDecimal.valueOf(productoJson.getPrecioUnitario()));
            recetaRequest.setMontoCopago(BigDecimal.valueOf(convenioJson.getMontoCopago()));
            recetaRequest.setIdSucursal(data.getCodigoSucursal());
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
                    } else {
                        log.warn("La receta no fue guardada. Detalles: {}", rootNode.path("detalles").asText());
                    }
                } else {
                    log.error("Error en la operación: HTTP status {}", response.getStatus());
                }
            } catch (Exception e) {
                log.error("Error al procesar la respuesta: {}", e.getMessage(), e);
            }
        });
    }

}
