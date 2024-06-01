package com.fahorro.recetas.helper;

import com.fahorro.recetas.client.CallmedApiService;
import com.fahorro.recetas.dto.request.ClaveCliente;
import com.fahorro.recetas.dto.request.DataRequest;
import com.fahorro.recetas.dto.request.MedicamentosApi;
import com.fahorro.recetas.dto.request.valreceta.RecetaCallmed;
import com.fahorro.recetas.exception.CallmedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fahorro.recetas.utils.Constants.*;
import static com.fahorro.recetas.utils.Constants.NUR;

@ApplicationScoped
public class CallmedHelper {

    private static final Logger log = LoggerFactory.getLogger(CallmedHelper.class);

    private final ObjectMapper objectMapper;

    @Inject
    @RestClient
    CallmedApiService callmedApiService;

    @ConfigProperty(name = "callmed.user")
    String user;
    @ConfigProperty(name = "callmed.password")
    String password;


    @Inject
    public CallmedHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public void loginClaveCliente(DataRequest data) throws CallmedException {
        JSONObject clienteJson = new JSONObject();

        try {
            log.info("Iniciando proceso login clave cliente Callmed");

            JSONObject loginJson = new JSONObject();
            loginJson.put("Usuario", user);
            loginJson.put("Contra", password);
            String loginPayload = loginJson.toString();

            log.debug("Payload de token: {}", loginPayload);

            String tokenResponse = callmedApiService.login(loginPayload);
            log.info("Token obtenido");

            clienteJson.put(FOLIO, data.getNur());
            clienteJson.put(TOKEN, tokenResponse.replace("\"", ""));
            log.debug("JSON para solicitud de clave cliente: {}", clienteJson);

            String clienteResponse = callmedApiService.claveCliente(clienteJson.toString());
            log.info("Respuesta recibida para solicitud de clave cliente ::: {}", clienteResponse);

            ClaveCliente claveCliente = objectMapper.readValue(clienteResponse, ClaveCliente.class);

            if (!claveCliente.isSuccess()) {
                String message = claveCliente.getMessage() + SEPARATOR + NUR + data.getNur();
                log.warn("Clave cliente no exitosa para NUR: {} - Mensaje: {}", data.getNur(), claveCliente.getMessage());
                throw new CallmedException(message, 404);
            }

            data.setClaveCliente(claveCliente);
            data.setToken(tokenResponse);

            log.info("{} {} :: Clave cliente obtenida y procesada con éxito.", NUR, data.getNur());
        } catch (CallmedException e) {
            log.error("CallmedException capturada para NUR: {} - Error: {}", data.getNur(), e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{} {} :: CLAVE CLIENTE NO ENCONTRADA EN CALLMED: {} - Error: {}", NUR, data.getNur(), clienteJson, e.getMessage(), e);
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }

    public void consultaReceta(DataRequest data) throws CallmedException {
        JSONObject recetaJson = new JSONObject();

        try {
            log.info("Iniciando consulta de receta Callmed");

            recetaJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            recetaJson.put(FOLIO, data.getNur());
            recetaJson.put(TOKEN, data.getToken());

            log.debug("JSON de consulta de receta enviado: {}", recetaJson);

            String clienteResponse = callmedApiService.valReceta(recetaJson.toString());
            log.info("Respuesta recibida de la consulta de receta ::: {}", clienteResponse);

            RecetaCallmed recetaCallmed = objectMapper.readValue(clienteResponse, RecetaCallmed.class);

            if (!recetaCallmed.isSuccess()) {
                String message = recetaCallmed.getMessage() + SEPARATOR + NUR + data.getNur();
                log.warn("Consulta de receta no exitosa para NUR: {} - Mensaje: {}", data.getNur(), recetaCallmed.getMessage());
                throw new CallmedException(message, 404);
            }

            data.setRecetaCallmed(recetaCallmed);

            log.info("{} {} :: Receta obtenida y procesada con éxito.", NUR, data.getNur());
        } catch (CallmedException e) {
            log.error("CallmedException capturada durante la consulta de receta para NUR: {} - Error: {}", data.getNur(), e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{} {} :: RECETA NO ENCONTRADA EN CALLMED: {} - Error: {}", NUR, data.getNur(), recetaJson, e.getMessage(), e);
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }

    public void consultaMedicamentos(DataRequest data) throws CallmedException {
        JSONObject medicamentosJson = new JSONObject();

        try {
            log.info("Iniciando consulta de medicamentos Callmed");

            medicamentosJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            medicamentosJson.put(FOLIO, data.getNur());
            medicamentosJson.put(TOKEN, data.getToken());

            log.debug("JSON de consulta de medicamentos enviado: {}", medicamentosJson);

            String clienteResponse = callmedApiService.valMedicamento(medicamentosJson.toString());
            log.info("Respuesta recibida de la consulta de medicamentos ::: {}", clienteResponse);

            MedicamentosApi medicamentosApi = objectMapper.readValue(clienteResponse, MedicamentosApi.class);

            if (!medicamentosApi.isSuccess()) {
                String message = medicamentosApi.getMessage() + SEPARATOR + NUR + data.getNur();
                log.warn("Consulta de medicamentos no exitosa para NUR: {} - Mensaje: {}", data.getNur(), medicamentosApi.getMessage());
                throw new CallmedException(message, 404);
            }

            data.setMedicamentos(medicamentosApi);

            log.info("{} {} :: Medicamentos obtenidos y procesados con éxito.", NUR, data.getNur());
        } catch (CallmedException e) {
            log.error("CallmedException capturada durante la consulta de medicamentos para NUR: {} - Error: {}", data.getNur(), e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("{} {} :: MEDICAMENTOS NO ENCONTRADOS EN CALLMED: {} - Error: {}", NUR, data.getNur(), medicamentosJson, e.getMessage(), e);
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }

}
