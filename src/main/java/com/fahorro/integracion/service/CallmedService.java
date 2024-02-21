package com.fahorro.integracion.service;

import com.fahorro.integracion.client.CallmedClient;
import com.fahorro.integracion.dto.request.ClaveCliente;
import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.dto.request.MedicamentosApi;
import com.fahorro.integracion.dto.request.valreceta.RootReceta;
import com.fahorro.integracion.dto.response.RecetaGeneral;
import com.fahorro.integracion.exception.CallmedException;
import com.fahorro.integracion.helper.CalmedServiceHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.json.JSONObject;

import static com.fahorro.integracion.util.Constants.*;
@ApplicationScoped
public class CallmedService
{
    private static final Logger log = Logger.getLogger(CallmedService.class);
    private final CallmedClient callmedClient;
    private final CalmedServiceHelper helperCallmed;
    DataRequest data;
    @ConfigProperty(name = "callmed.winstondata.user")
    String user;
    @ConfigProperty(name = "callmed.winstondata.password")
    String password;

    @Inject
    public CallmedService(@RestClient CallmedClient callmedClient, CalmedServiceHelper helperCallmed) {
        this.callmedClient = callmedClient;
        this.helperCallmed = helperCallmed;
        data = new DataRequest();
    }

    public String processCallmed(String nur) throws CallmedException {

        loginClaveCliente(nur);
        consultaReceta();
        consultaMedicamentos();

        JsonbConfig config = new JsonbConfig().withNullValues(true);

        try (Jsonb jsonb = JsonbBuilder.create(config))
        {
            RecetaGeneral recetaGeneral = helperCallmed.buildData(data);
            return jsonb.toJson(recetaGeneral);
        }
        catch (Exception e)
        {
            throw new CallmedException("Error al serializar a JSON " + RecetaGeneral.class.getSimpleName(), 500, e);
        }
    }

    private void loginClaveCliente(String nur) throws CallmedException {
        JSONObject clienteJson = new JSONObject();
        data.setNur(nur);

        try
        {
            JSONObject loginJson = new JSONObject();
            loginJson.put("Usuario", user);
            loginJson.put("Contra", password);
            String loginPayload = loginJson.toString();

            String tokenResponse = callmedClient.login(loginPayload);
            clienteJson.put(FOLIO, data.getNur());
            clienteJson.put(TOKEN, tokenResponse.replace("\"", ""));

            String clienteResponse = callmedClient.claveCliente(clienteJson.toString());
            ClaveCliente claveCliente = helperCallmed.fromJson(clienteResponse, ClaveCliente.class);


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
            helperCallmed.handleException(data.getNur(), e);
        }
    }

    private void consultaReceta() throws CallmedException {

        JSONObject recetaJson = new JSONObject();

        try
        {
            recetaJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            recetaJson.put(FOLIO, data.getNur());
            recetaJson.put(TOKEN, data.getToken());

            String clienteResponse = callmedClient.valReceta(recetaJson.toString());
            RootReceta rootReceta =  helperCallmed.fromJson(clienteResponse, RootReceta.class);

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
            helperCallmed.handleException(data.getNur(), e);
        }
    }

    private void consultaMedicamentos() throws CallmedException {

        JSONObject medicamentosJson = new JSONObject();

        try
        {
            medicamentosJson.put("ClaveCliente", data.getClaveCliente().getClaveCliente());
            medicamentosJson.put(FOLIO, data.getNur());
            medicamentosJson.put(TOKEN, data.getToken());

            String clienteResponse = callmedClient.valMedicamento(medicamentosJson.toString());
            MedicamentosApi medicamentosApi =  helperCallmed.fromJson(clienteResponse, MedicamentosApi.class);

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
            helperCallmed.handleException(data.getNur(), e);
        }
    }
}
