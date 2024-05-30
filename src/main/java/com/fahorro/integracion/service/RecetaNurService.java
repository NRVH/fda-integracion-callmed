package com.fahorro.integracion.service;

import com.fahorro.integracion.client.*;
import com.fahorro.integracion.dto.*;
import com.fahorro.integracion.dto.request.*;
import com.fahorro.integracion.dto.request.valreceta.RootReceta;
import com.fahorro.integracion.dto.response.RecetaGeneral;
import com.fahorro.integracion.exception.CallmedException;
import com.fahorro.integracion.exception.ExcepcionSucursalNoEncontrada;
import com.fahorro.integracion.helper.CalmedServiceHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.fahorro.integracion.utils.Constants.*;
@ApplicationScoped
public class RecetaNurService
{
    private static final Logger log = LoggerFactory.getLogger(RecetaNurService.class);

    private final CallmedClient callmedClient;
    private final CalmedServiceHelper helperCallmed;
    private final ObjectMapper objectMapper;
    private DataRequest data;

    @ConfigProperty(name = "callmed.user")
    String user;
    @ConfigProperty(name = "callmed.password")
    String password;

    @Inject
    @RestClient
    RecetaApiService recetaApiService;

    @Inject
    @RestClient
    SucursalApiService sucursalApiService;

    @Inject
    @RestClient
    ConvenioApiService convenioApiService;

    @Inject
    @RestClient
    ClienteApiService clienteApiService;

    @Inject
    @RestClient
    ProductoApiService productoApiService;

    @Inject
    public RecetaNurService(@RestClient CallmedClient callmedClient, CalmedServiceHelper helperCallmed, ObjectMapper objectMapper) {
        this.callmedClient = callmedClient;
        this.helperCallmed = helperCallmed;
        this.objectMapper = objectMapper;
        data = new DataRequest();
    }

    public String processRecetaNur(String nur, String codigoSucursal) throws Exception {

        log.info("Validando la petición con NUR ::: {}, SUCURSAL ::: {}", nur, codigoSucursal);
        data.setNur(nur);
        RecetaApiResponseDTO receta = recetaApiService.getRecetaByNur(nur);
        SucursalApiResponseDTO sucursal = sucursalApiService.getSucursalById(codigoSucursal);


        if (Objects.isNull(receta)) {
            log.info("NUR ::: {} :: Receta no encontrada con el nur proporcionado", nur);
            throw new ExcepcionSucursalNoEncontrada("NUR: " + nur + " :: Receta no encontrada con el NUR proporcionado");
        }

        if (Objects.isNull(sucursal)) {
            log.info("NUR ::: {} , Sucursal no encontrada ::: {}", nur, codigoSucursal);
            throw new ExcepcionSucursalNoEncontrada("No se encontro la sucursal numero " + codigoSucursal + " en la base de datos.");
        }

        String idConvenio = receta.getEntidad().getDataJson().getIdConvenio();
        ConvenioResponseDTO convenio = convenioApiService.getConvenio(idConvenio);
        ConvenioProductoResponseDTO convenioProducto = productoApiService.getProductoByConvenio(idConvenio);

        if (Objects.isNull(convenio.getEntidad().getDataJson())) {
            log.info("idConvenio ::: {} Convenio no encontrado con el idConvenio de receta", idConvenio);
            throw new ExcepcionSucursalNoEncontrada("idConvenio: " + idConvenio + " :: Convenio no encontrado con el idConvenio de receta");
        }

        if (Objects.isNull(convenioProducto.getEntidad().getListDataJson())) {
            log.info("idConvenio ::: {} Producto no encontrado con el idConvenio de receta", idConvenio);
            throw new ExcepcionSucursalNoEncontrada("idConvenio: " + idConvenio + " :: Producto no encontrado con el idConvenio de receta");
        }

        String idCliente = convenio.getEntidad().getDataJson().getIdCliente().toString();
        String ean = convenioProducto.getEntidad().getListDataJson().get(0).getProductCode();

        ClienteApiResponseDTO cliente = clienteApiService.getClienteById(idCliente);
        ProductoApiResponseDTO producto = productoApiService.getProductoByEAN(ean);

        if (Objects.isNull(cliente.getEntidad().getDataJson())) {
            log.info("Cliente no encontrado con idCliente ::: {} ", idCliente);
            throw new ExcepcionSucursalNoEncontrada("Cliente no encontrado con id :" + idCliente);
        }

        if (Objects.isNull(producto.getEntidad().getDataJson())) {
            log.info("Producto no encontrado con EAN ::: {}", ean);
            throw new ExcepcionSucursalNoEncontrada("EAN: " + ean + " :: Producto no encontrado");
        }


        loginClaveCliente();
        consultaReceta();
        consultaMedicamentos();


        try
        {
            return objectMapper.writeValueAsString(helperCallmed.buildData(data,
                    receta.getEntidad().getDataJson(),
                    convenio.getEntidad().getDataJson(),
                    cliente.getEntidad(),
                    producto.getEntidad().getDataJson()));
        }
        catch (Exception e) {
            throw new CallmedException("Error al serializar a JSON " + RecetaGeneral.class.getSimpleName(), 500, e);
        }
    }

    private void loginClaveCliente() throws CallmedException {
        JSONObject clienteJson = new JSONObject();

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
            helperCallmed.handleException(data.getNur(), e);
        }
    }
}
