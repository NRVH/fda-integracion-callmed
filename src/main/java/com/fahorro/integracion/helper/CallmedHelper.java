package com.fahorro.integracion.helper;

import com.fahorro.integracion.client.CallmedApiService;
import com.fahorro.integracion.dto.request.ClaveCliente;
import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.dto.request.MedicamentosApi;
import com.fahorro.integracion.dto.request.valreceta.RootReceta;
import com.fahorro.integracion.exception.CallmedException;
import com.fahorro.integracion.service.RecetaNurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.fahorro.integracion.utils.Constants.*;
import static com.fahorro.integracion.utils.Constants.NUR;

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

        try
        {
            JSONObject loginJson = new JSONObject();
            loginJson.put("Usuario", user);
            loginJson.put("Contra", password);
            String loginPayload = loginJson.toString();

            String tokenResponse = callmedApiService.login(loginPayload);
            clienteJson.put(FOLIO, data.getNur());
            clienteJson.put(TOKEN, tokenResponse.replace("\"", ""));

            String clienteResponse = callmedApiService.claveCliente(clienteJson.toString());
            ClaveCliente claveCliente = objectMapper.readValue(clienteResponse, ClaveCliente.class);


            if (!claveCliente.isSuccess()) {
                String message = claveCliente.getMessage() + SEPARATOR + NUR + data.getNur();
                throw new CallmedException(message, 404);
            }

            data.setClaveCliente(claveCliente);
            data.setToken(tokenResponse);

            log.info(String.format("%s %s :: Clave cliente obtenida y procesada con éxito.", NUR, data.getNur()));
        }
        catch (CallmedException e)
        {
            throw e;
        }
        catch (Exception e) {
            log.error(String.format("%s %s :: CLAVE CLIENTE NO ENCONTRADA EN CALLMED: %s", NUR, data.getNur(), clienteJson), e);
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }

    public void consultaReceta(DataRequest data) throws CallmedException {

        JSONObject recetaJson = new JSONObject();

        try
        {
            recetaJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            recetaJson.put(FOLIO, data.getNur());
            recetaJson.put(TOKEN, data.getToken());

            String clienteResponse = callmedApiService.valReceta(recetaJson.toString());
            RootReceta rootReceta =  objectMapper.readValue(clienteResponse, RootReceta.class);

            if (!rootReceta.isSuccess()) {
                String message = rootReceta.getMessage() + SEPARATOR + NUR + data.getNur();
                throw new CallmedException(message, 404);
            }

            data.setRootReceta(rootReceta);

            log.info(String.format("%s %s :: Receta obtenida y procesada con éxito.", NUR, data.getNur()));
        }
        catch (CallmedException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            log.error(String.format("%s %s :: RECETA NO ENCONTRADA EN CALLMED: %s", NUR, data.getNur(), recetaJson));
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }

    public void consultaMedicamentos(DataRequest data) throws CallmedException {

        JSONObject medicamentosJson = new JSONObject();

        try
        {
            medicamentosJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            medicamentosJson.put(FOLIO, data.getNur());
            medicamentosJson.put(TOKEN, data.getToken());

            String clienteResponse = callmedApiService.valMedicamento(medicamentosJson.toString());
            MedicamentosApi medicamentosApi =  objectMapper.readValue(clienteResponse, MedicamentosApi.class);

            if (!medicamentosApi.isSuccess()) {
                String message = medicamentosApi.getMessage() + SEPARATOR + NUR + data.getNur();
                throw new CallmedException(message, 404);
            }

            data.setMedicamentos(medicamentosApi);

            log.info(String.format("%s %s :: Medicamentos obtenidos y procesados con éxito.", NUR, data.getNur()));
        }
        catch (CallmedException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            log.error(String.format("%s %s :: MEDICAMENTOS NO ENCONTRADOS EN CALLMED: %s", NUR, data.getNur(), medicamentosJson));
            ExceptionHelper.handleException(data.getNur(), e);
        }
    }
}
