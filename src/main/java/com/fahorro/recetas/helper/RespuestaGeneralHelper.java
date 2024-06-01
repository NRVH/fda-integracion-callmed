package com.fahorro.recetas.helper;

import com.fahorro.recetas.dto.*;
import com.fahorro.recetas.dto.request.DataRequest;
import com.fahorro.recetas.dto.request.valreceta.Receta;
import com.fahorro.recetas.dto.response.LineItem;
import com.fahorro.recetas.dto.response.Medicamento;
import com.fahorro.recetas.dto.response.RecetaGeneral;
import com.fahorro.recetas.dto.response.TipoCopago;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RespuestaGeneralHelper
{
    public RecetaGeneral buildData(DataRequest data)
    {
        RecetaGeneral recetaGeneral = new RecetaGeneral();

        List<LineItem> lineItemList = new ArrayList<>();
        LineItem lineItem = new LineItem();

        Receta receta = data.getRecetaCallmed().getReceta();
        Medicamento med = data.getMedicamentos().getMedicamento();

        ClienteApiResponseDTO.EntidadDTO clienteEntidad = data.getClienteEntidad().getEntidad();
        SubClienteApiResponseDTO.EntidadDTO.DataJsonDTO subClienteJson = data.getSubClienteEntidad().getEntidad().getDataJson();
        ConvenioResponseDTO.EntidadDTO.DataJsonDTO convenioJson = data.getConvenioEntidad().getEntidad().getDataJson();
        ProductoApiResponseDTO.EntidadDTO.DataJsonDTO productoJson = data.getProductoEntidad().getEntidad().getDataJson();

        TipoCopago tipoCopago = new TipoCopago();

        recetaGeneral.setCliente(clienteEntidad.getDataJson().getNombre() + " " + clienteEntidad.getDataJson().getApellido());
        recetaGeneral.setNumeroCliente(String.valueOf(clienteEntidad.getDataJson().getNumeroCliente()));
        recetaGeneral.setSubCliente(subClienteJson.getNombre());
        recetaGeneral.setNumeroSubCliente(subClienteJson.getNumeroSubcliente());

        tipoCopago.setMonto(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago") ?
                String.valueOf(convenioJson.getMontoCopago()) : null);

        tipoCopago.setPorcentaje(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago") ?
                String.valueOf(convenioJson.getPorcentajeCopago()) : null);

        recetaGeneral.setTieneCopago(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Copago"));
        recetaGeneral.setTipoPrecios(clienteEntidad.getDataJson().getTipoDePrecio());

        recetaGeneral.setPagoContado(clienteEntidad.getDataJson().getTipoPagoCliente().equals("Contado"));
        recetaGeneral.setNumeroProveedorReceta("12");
        recetaGeneral.setLdpos(Integer.valueOf(subClienteJson.getLdpos().trim()));
        recetaGeneral.setEspecialidad(receta.getDoctorEspecialidad().trim());
        recetaGeneral.setFechaReceta(receta.getFechaConsulta().trim());
        recetaGeneral.setMedico(receta.getNombreMedico().trim());
        recetaGeneral.setMedicoCedula(receta.getClaveMedico().trim());
        recetaGeneral.setNumeroPaciente(receta.getClavePaciente().trim());
        recetaGeneral.setPaciente(receta.getNombrePaciente().trim());
        recetaGeneral.setProveedorReceta("Callmed");
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
        lineItem.setNumeroAutorizacion(receta.getAutorizacion().trim());

        lineItemList.add(lineItem);

        recetaGeneral.setTipoCopago(tipoCopago);
        recetaGeneral.setLineItem(lineItemList);

        return recetaGeneral;
    }
}
