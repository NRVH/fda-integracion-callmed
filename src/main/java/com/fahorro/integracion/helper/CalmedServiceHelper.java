package com.fahorro.integracion.helper;

import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.dto.request.valreceta.Receta;
import com.fahorro.integracion.dto.response.LineItem;
import com.fahorro.integracion.dto.response.Medicamento;
import com.fahorro.integracion.dto.response.RecetaGeneral;
import com.fahorro.integracion.exception.CallmedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CalmedServiceHelper
{
    private static final Logger log = Logger.getLogger(CalmedServiceHelper.class);

    public RecetaGeneral buildData(DataRequest data)
    {
        RecetaGeneral recetaGeneral = new RecetaGeneral();

        List<LineItem> lineItemList = new ArrayList<>();
        LineItem lineItem = new LineItem();

        Receta receta = data.getRootReceta().getReceta();
        Medicamento med = data.getMedicamentos().getMedicamento();

        recetaGeneral.setEspecialidad(receta.getDoctorEspecialidad().trim());
        recetaGeneral.setFechaReceta(receta.getFechaConsulta().trim());
        recetaGeneral.setMedico(receta.getNombreMedico().trim());
        recetaGeneral.setNumeroPaciente(receta.getClavePaciente().trim());
        recetaGeneral.setPaciente(receta.getNombrePaciente().trim());
        recetaGeneral.setFamiliar(receta.getFamiliar().trim());
        recetaGeneral.setIcd(med.getIcd10().trim());

        lineItem.setEAN(med.getEan().trim());
        lineItem.setMedicamento(med);
        lineItem.setCantidad(med.getCantidad().trim());
        lineItem.setIndicaciones(med.getIndicaciones().trim());
        lineItem.setNur(data.getMedicamentos().getMedicamento().getNur().trim());
        lineItem.setNumeroAutorizacion(data.getRootReceta().getReceta().getAutorizacion().trim());

        lineItemList.add(lineItem);
        recetaGeneral.setLineItem(lineItemList);

        return recetaGeneral;
    }

    public <T> T fromJson(String json, Class<T> type) throws CallmedException {
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(json, type);
        } catch (Exception e) {
            throw new CallmedException("Error al deserializar JSON a " + type.getSimpleName(), 500, e);
        }
    }

    public void handleException(String nur, Exception e) throws CallmedException {
        if (e instanceof ProcessingException) {
            Throwable cause = e.getCause();
            if (cause instanceof SocketTimeoutException) {
                log.error("Timeout al comunicarse con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Timeout de comunicación con Api Winstondata", 504, e); // 504 Gateway Timeout
            } else if (cause instanceof ConnectException) {
                log.error("No se pudo conectar con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Servicio Api Winstondata no disponible", 503, e); // 503 Service Unavailable
            } else {
                log.error("Error de procesamiento al comunicarse con Api Winstondata para NUR: " + nur, e);
                throw new CallmedException("Error de comunicación con Api Winstondata", 500, e); // 500 Internal Server Error
            }
        } else if (e instanceof WebApplicationException w) {
            Response response = w.getResponse();
            Response.StatusType statusInfo = response.getStatusInfo();
            log.error("Respuesta HTTP " + statusInfo.getStatusCode() + ": " + statusInfo.getReasonPhrase() +
                    " al comunicarse con Api Winstondata para NUR: " + nur, e);
            throw new CallmedException("Error HTTP " + statusInfo.getStatusCode() + " desde Callmed", response.getStatus(), e);
        } else {
            log.error("Error inesperado al comunicarse con Callmed para NUR: " + nur, e);
            throw new CallmedException("Error inesperado en Callmed", 500, e); // 500 Internal Server Error
        }
    }
}
