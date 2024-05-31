package com.fahorro.integracion.service;

import com.fahorro.integracion.dto.request.*;
import com.fahorro.integracion.dto.response.RecetaGeneral;
import com.fahorro.integracion.exception.CallmedException;
import com.fahorro.integracion.helper.CallmedHelper;
import com.fahorro.integracion.helper.RecetaNurHelper;
import com.fahorro.integracion.helper.RespuestaGeneralHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
    private final CallmedHelper callmedHelper;
    private final GuardarRecetaAsync guardarRecetaAsync;


    @Inject
    public RecetaNurService(RespuestaGeneralHelper respuestaGeneralHelper,
                            ObjectMapper objectMapper,
                            RecetaNurHelper recetaNurHelper,
                            CallmedHelper callmedHelper, GuardarRecetaAsync guardarRecetaAsync) {
        this.respuestaGeneralHelper = respuestaGeneralHelper;
        this.objectMapper = objectMapper;
        this.recetaNurHelper = recetaNurHelper;
        this.callmedHelper = callmedHelper;
        this.guardarRecetaAsync = guardarRecetaAsync;
        this.data = new DataRequest();
    }

    public String processRecetaNur(String nur, String codigoSucursal) throws Exception {

        data.setNur(nur);
        data.setCodigoSucursal(codigoSucursal);

        callmedHelper.loginClaveCliente(data);
        callmedHelper.consultaReceta(data);
        callmedHelper.consultaMedicamentos(data);

        recetaNurHelper.recetaNurHelperProcess(data);
        try
        {
            String response = objectMapper.writeValueAsString(respuestaGeneralHelper.buildData(data));
            guardarRecetaAsync.asyncMethod(data);

            return response;
        }
        catch (Exception e)
        {
            log.error("Error al armar JSON de Receta General {} ::: {} ::: {}", RecetaGeneral.class.getSimpleName(), 500, e.getMessage());
            throw new CallmedException("Error al armar JSON de Receta General " + RecetaGeneral.class.getSimpleName(), 500, e);
        }
    }

    public String processSurtirRecetaNur(String nur, RequestSurtirRecetaNur recetaNur) throws Exception {

        data.setNur(nur);
        //data.setCodigoSucursal(codigoSucursal);

        callmedHelper.loginClaveCliente(data);
        callmedHelper.consultaReceta(data);
        callmedHelper.consultaMedicamentos(data);

        recetaNurHelper.recetaNurHelperProcess(data);
        try
        {
            return objectMapper.writeValueAsString(respuestaGeneralHelper.buildData(data));
        }
        catch (Exception e)
        {
            log.error("Error al armar JSON de Receta General {} ::: {} ::: {}", RecetaGeneral.class.getSimpleName(), 500, e.getMessage());
            throw new CallmedException("Error al armar JSON de Receta General " + RecetaGeneral.class.getSimpleName(), 500, e);
        }
    }
}
