package com.fahorro.recetas.service;

import com.fahorro.recetas.dto.ClienteApiResponseDTO;
import com.fahorro.recetas.dto.request.*;
import com.fahorro.recetas.dto.response.RecetaGeneral;
import com.fahorro.recetas.exception.CallmedException;
import com.fahorro.recetas.helper.CallmedHelper;
import com.fahorro.recetas.helper.RecetaNurHelper;
import com.fahorro.recetas.helper.RecetaNurSurtirHelper;
import com.fahorro.recetas.helper.RespuestaGeneralHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.xml.bind.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RecetaNurService
{
    private static final Logger log = LoggerFactory.getLogger(RecetaNurService.class);

    private final RespuestaGeneralHelper respuestaGeneralHelper;
    private final ObjectMapper objectMapper;
    private final DataRequest data;
    private final RecetaNurHelper recetaNurHelper;
    private final RecetaNurSurtirHelper recetaNurSurtirHelper;
    private final CallmedHelper callmedHelper;
    private final GuardarRecetaAsync guardarRecetaAsync;


    @Inject
    public RecetaNurService(RespuestaGeneralHelper respuestaGeneralHelper,
                            ObjectMapper objectMapper,
                            RecetaNurHelper recetaNurHelper,
                            RecetaNurSurtirHelper recetaNurSurtirHelper,
                            CallmedHelper callmedHelper, GuardarRecetaAsync guardarRecetaAsync) {
        this.respuestaGeneralHelper = respuestaGeneralHelper;
        this.objectMapper = objectMapper;
        this.recetaNurHelper = recetaNurHelper;
        this.recetaNurSurtirHelper = recetaNurSurtirHelper;
        this.callmedHelper = callmedHelper;
        this.guardarRecetaAsync = guardarRecetaAsync;
        this.data = new DataRequest();
    }

    public String processRecetaNur(String nur, String codigoSucursal) throws Exception {
        log.info("Iniciando el procesamiento de Receta NUR para NUR: {}, Código de Sucursal: {}", nur, codigoSucursal);

        data.setNur(nur);
        data.setCodigoSucursal(codigoSucursal);

        // Login y obtención de la clave del cliente
        try {
            callmedHelper.loginClaveCliente(data);
            log.info("Login y clave de cliente completados con éxito");
        } catch (Exception e) {
            log.error("Error durante el login y obtención de clave del cliente para NUR: {}", nur, e);
            throw e;
        }

        // Consulta de la receta
        try {
            callmedHelper.consultaReceta(data);
            log.info("Consulta de receta completada con éxito para NUR: {}", nur);
        } catch (Exception e) {
            log.error("Error durante la consulta de receta para NUR: {}", nur, e);
            throw e;
        }

        // Consulta de medicamentos
        try {
            callmedHelper.consultaMedicamentos(data);
            log.info("Consulta de medicamentos completada con éxito para NUR: {}", nur);
        } catch (Exception e) {
            log.error("Error durante la consulta de medicamentos para NUR: {}", nur, e);
            throw e;
        }

        // Procesamiento de la receta
        try {
            recetaNurHelper.recetaNurHelperProcess(data);
            log.info("Procesamiento de Receta NUR completado con éxito para NUR: {}", nur);
        } catch (Exception e) {
            log.error("Error durante el procesamiento de la receta NUR para NUR: {}", nur, e);
            throw e;
        }

        // Finalización y generación de respuesta
        try {
            String response = objectMapper.writeValueAsString(respuestaGeneralHelper.buildData(data));
            guardarRecetaAsync.asyncMethod(data);
            log.info("Receta NUR generada y guardada asincrónicamente con éxito para NUR: {}", nur);
            return response;
        } catch (Exception e) {
            log.error("Error al armar JSON de Receta General {} ::: {} ::: {}", RecetaGeneral.class.getSimpleName(), 500, e.getMessage());
            throw new CallmedException("Error al armar JSON de Receta General " + RecetaGeneral.class.getSimpleName(), 500, e);
        }
    }


    public String processSurtirRecetaNur(RequestSurtirRecetaNur recetaNur) throws Exception {
        log.info("Iniciando el proceso de surtir receta NUR para receta: {}", recetaNur);
        data.setRecetaNur(recetaNur);

        String idReceta;

        try
        {
            idReceta = recetaNurSurtirHelper.recetaNurSurtirProcess(data);
            log.info("Receta NUR procesada con éxito, OrderId generado: {}", idReceta);
        }
        catch (Exception e)
        {
            log.error("Error al procesar la receta NUR: {}", e.getMessage(), e);
            throw new Exception("Error al procesar surtir receta NUR", e);
        }

        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();
            jsonObject.putNull("autorizacion");
            jsonObject.put("orderid", idReceta);

            String jsonResponse = objectMapper.writeValueAsString(jsonObject);
            log.info("Respuesta JSON para surtir receta NUR generada con éxito");
            String prettyJsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            log.info(prettyJsonResponse);
            return jsonResponse;

        } catch (Exception e)
        {
            log.error("Error al generar la respuesta JSON para surtir receta NUR, OrderId generado {}: {}", idReceta, e.getMessage(), e);
            throw new CallmedException("Error al generar respuesta surtir receta NUR, OrderId generado " + idReceta, 500, e);
        }
    }

    public String processSurtirRecetaNurNumeroCliente(RequestSurtirRecetaNur recetaNur) throws Exception {

        log.info("Iniciando el procesamiento de Surtir Receta NUR Numero Cliente");
        data.setRecetaNur(recetaNur);

        validateEssentialFields(recetaNur);

        String idReceta;
        try
        {
            idReceta = recetaNurSurtirHelper.recetaNurSurtirProcess(data);
            log.info("Receta NUR/Numero Cliente procesada con éxito, OrderId generado: {}", idReceta);
        } catch (Exception e)
        {
            log.error("Error al procesar la receta NUR para NUR: {}, Error: {}", recetaNur.getNur(), e.getMessage(), e);
            throw new Exception("Error al procesar la receta NUR", e);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode jsonObject = objectMapper.createObjectNode();
            ClienteApiResponseDTO.EntidadDTO.DataJsonDTO clienteJson = data.getClienteEntidad().getEntidad().getDataJson();

            if (clienteJson.getTipoDePrecio().equals("Convenio")) {
                jsonObject.putNull("autorizacion");
            } else {
                jsonObject.put("tipoPagoCliente", clienteJson.getTipoPagoCliente());
            }
            jsonObject.put("orderid", idReceta);
            log.debug("JSON de respuesta construido con éxito");

            String jsonResponse = objectMapper.writeValueAsString(jsonObject);
            log.info("Respuesta JSON generada con éxito para NUR");
            String prettyJsonResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            log.info(prettyJsonResponse);
            return jsonResponse;
        } catch (Exception e) {
            log.error("Error al generar respuesta JSON para Surtir Receta NUR/Numero Cliente, OrderId: {}, Error: {}", idReceta, e.getMessage(), e);
            throw new CallmedException("Error al generar respuesta surtir receta NUR, OrderId generado " + idReceta, 500, e);
        }
    }

    public void validateEssentialFields(RequestSurtirRecetaNur recetaNur) throws ValidationException {
        if (recetaNur.getNur() == null || recetaNur.getNur().trim().isEmpty()) {
            throw new ValidationException("El NUR es requerido y no puede ser vacío.");
        }
        if (recetaNur.getNumSubCliente() == null) {
            throw new ValidationException("El número de subcliente es requerido.");
        }
    }
}
