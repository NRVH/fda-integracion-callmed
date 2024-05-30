package com.fahorro.integracion.helper;

import com.fahorro.integracion.client.*;
import com.fahorro.integracion.dto.*;
import com.fahorro.integracion.dto.request.DataRequest;
import com.fahorro.integracion.exception.ExcepcionSucursalNoEncontrada;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

@ApplicationScoped
public class RecetaNurHelper
{
    private static final Logger log = LoggerFactory.getLogger(RecetaNurHelper.class);

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
    SubClienteApiService subClienteApiService;

    @Inject
    @RestClient
    ProductoApiService productoApiService;


    public void recetaNurHelperProcess(DataRequest data) throws Exception
    {
        log.info("Validando la petición con NUR ::: {}, SUCURSAL ::: {}", data.getNur(), data.getCodigoSucursal());

        RecetaApiResponseDTO receta = recetaApiService.getRecetaByNur(data.getNur());
        SucursalApiResponseDTO sucursal = sucursalApiService.getSucursalById(data.getCodigoSucursal());


        if (Objects.isNull(receta)) {
            log.info("NUR ::: {} :: Receta no encontrada con el nur proporcionado", data.getNur());
            throw new ExcepcionSucursalNoEncontrada("NUR: " + data.getNur() + " :: Receta no encontrada con el NUR proporcionado");
        }

        if (Objects.isNull(sucursal)) {
            log.info("NUR ::: {} , Sucursal no encontrada ::: {}", data.getNur(), data.getCodigoSucursal());
            throw new ExcepcionSucursalNoEncontrada("No se encontro la sucursal numero " + data.getCodigoSucursal() + " en la base de datos.");
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
        SubClienteApiResponseDTO subCliente = subClienteApiService.getSubClienteById(idCliente);
        ProductoApiResponseDTO producto = productoApiService.getProductoByEAN(ean);

        if (Objects.isNull(cliente.getEntidad().getDataJson())) {
            log.info("Cliente no encontrado con idCliente ::: {} ", idCliente);
            throw new ExcepcionSucursalNoEncontrada("Cliente no encontrado con id :" + idCliente);
        }

        if (Objects.isNull(subCliente.getEntidad().getDataJson())) {
            log.info("SubCliente no encontrado con idCliente ::: {} ", idCliente);
            throw new ExcepcionSucursalNoEncontrada("SubCliente no encontrado con id :" + idCliente);
        }

        if (Objects.isNull(producto.getEntidad().getDataJson())) {
            log.info("Producto no encontrado con EAN ::: {}", ean);
            throw new ExcepcionSucursalNoEncontrada("EAN: " + ean + " :: Producto no encontrado");
        }

        data.setRecetaEntidad(receta);
        data.setConvenioEntidad(convenio);
        data.setClienteEntidad(cliente);
        data.setSubClienteEntidad(subCliente);
        data.setProductoEntidad(producto);
    }
}
