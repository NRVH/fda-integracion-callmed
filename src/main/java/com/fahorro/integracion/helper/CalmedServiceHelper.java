package com.fahorro.integracion.helper;

import com.fahorro.integracion.dto.*;
import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.dto.request.valreceta.Receta;
import com.fahorro.integracion.dto.response.LineItem;
import com.fahorro.integracion.dto.response.Medicamento;
import com.fahorro.integracion.dto.response.RecetaGeneral;
import com.fahorro.integracion.dto.response.TipoCopago;
import com.fahorro.integracion.exception.CallmedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class CalmedServiceHelper
{
    private static final Logger log = Logger.getLogger(CalmedServiceHelper.class);

    public RecetaGeneral buildData(DataRequest data,
                                   RecetaApiResponseDTO.EntidadDTO.DataJsonDTO recetaJson,
                                   ConvenioResponseDTO.EntidadDTO.DataJsonDTO convenioJson,
                                   ClienteApiResponseDTO.EntidadDTO clienteEntidad,
                                   ProductoApiResponseDTO.EntidadDTO.DataJsonDTO productoJson)
    {
        RecetaGeneral recetaGeneral = new RecetaGeneral();

        List<LineItem> lineItemList = new ArrayList<>();
        LineItem lineItem = new LineItem();

        Receta receta = data.getRootReceta().getReceta();
        Medicamento med = data.getMedicamentos().getMedicamento();
        TipoCopago tipoCopago = new TipoCopago();

        recetaGeneral.setCliente(clienteEntidad.getDataJson().getNombre() + " " + clienteEntidad.getDataJson().getApellido());
        recetaGeneral.setNumeroCliente(String.valueOf(clienteEntidad.getDataJson().getNumeroCliente()));
        recetaGeneral.setSubCliente();
        recetaGeneral.setNumeroSubCliente();

        tipoCopago.setMonto(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago") ?
                String.valueOf(convenioJson.getMontoCopago()) : null);

        tipoCopago.setPorcentaje(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago") ?
                String.valueOf(convenioJson.getPorcentajeCopago()) : null);

        recetaGeneral.setTieneCopago(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago"));
        recetaGeneral.setTipoPrecios(clienteEntidad.getDataJson().getTipoDePrecio());

        recetaGeneral.setPagoContado(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Contado"));
        recetaGeneral.setNumeroProveedorReceta("12");
        recetaGeneral.setEspecialidad(receta.getDoctorEspecialidad().trim());
        recetaGeneral.setFechaReceta(receta.getFechaConsulta().trim());
        recetaGeneral.setMedico(receta.getNombreMedico().trim());
        recetaGeneral.setMedicoCedula(recetaJson.getCedulaMedico().trim());
        recetaGeneral.setNumeroPaciente(receta.getClavePaciente().trim());
        recetaGeneral.setPaciente(receta.getNombrePaciente().trim());
        recetaGeneral.setProveedorReceta("Callmed");
        recetaGeneral.setRazonNoSurtir(recetaJson.getRazonNoSurtir());

        recetaGeneral.setFamiliar(receta.getFamiliar().trim());
        recetaGeneral.setIcd(med.getIcd10().trim());


        lineItem.setEAN(med.getEan().trim());
        lineItem.setMedicamento(med.getMedicamento());
        lineItem.setEstatus(productoJson.getEstatus());
        lineItem.setDivision(productoJson.getDivision());
        lineItem.setCategoria(productoJson.getCategoria());
        lineItem.setSubcategoria(productoJson.getSubcategoria());
        lineItem.setSegmento(productoJson.getSegmento());
        lineItem.setClaseTerapeutica(productoJson.getClaseTerapeutica());
        lineItem.setSustanciaActiva(productoJson.getSustanciaActiva());
        lineItem.setSustanciaActiva(productoJson.getSustanciaActiva());
        lineItem.setLaboratorio(productoJson.getLaboratorio());
        lineItem.setIva(productoJson.getImpuestoIVAPorcentaje());
        lineItem.setControlado(!Objects.isNull(productoJson.getControlado()));
        lineItem.setAntibiotico(!Objects.isNull(productoJson.getAntibiotico()));
        lineItem.setUnidadMe(productoJson.getUnidadDeMedida());
        lineItem.setDepartamento(productoJson.getDepartamento());
        lineItem.setPrecio_unitario_bruto(productoJson.getPrecioUnitario());
        lineItem.setPrecio_unitario_neto(productoJson.getPrecioUnitario());
        lineItem.setCantidad(med.getCantidad().trim());
        lineItem.setIndicaciones(med.getIndicaciones());
        lineItem.setNur(data.getNur());
        lineItem.setSePuedeSurtir(true);
        lineItem.setRazonParaNoSurtir(recetaGeneral.getRazonNoSurtir());
        lineItem.setNumeroAutorizacion(data.getRootReceta().getReceta().getAutorizacion().trim());

        lineItemList.add(lineItem);

        recetaGeneral.setTipoCopago(tipoCopago);
        recetaGeneral.setLineItem(lineItemList);

        return recetaGeneral;
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
