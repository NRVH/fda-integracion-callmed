package com.fahorro.integracion.dto.request;

import com.fahorro.integracion.dto.*;
import com.fahorro.integracion.dto.request.valreceta.RootReceta;

public class DataRequest
{
    private String nur;
    private String codigoSucursal;
    private String token;
    private ClaveCliente claveCliente;
    private RootReceta rootReceta;
    private MedicamentosApi medicamentosApi;
    private RecetaApiResponseDTO recetaEntidad;
    private ConvenioResponseDTO convenioEntidad;
    private ClienteApiResponseDTO clienteEntidad;
    private SubClienteApiResponseDTO subClienteEntidad;
    private ProductoApiResponseDTO productoEntidad;

    public RecetaApiResponseDTO getRecetaEntidad() {
        return recetaEntidad;
    }

    public void setRecetaEntidad(RecetaApiResponseDTO recetaEntidad) {
        this.recetaEntidad = recetaEntidad;
    }

    public ConvenioResponseDTO getConvenioEntidad() {
        return convenioEntidad;
    }

    public void setConvenioEntidad(ConvenioResponseDTO convenioEntidad) {
        this.convenioEntidad = convenioEntidad;
    }

    public ClienteApiResponseDTO getClienteEntidad() {
        return clienteEntidad;
    }

    public void setClienteEntidad(ClienteApiResponseDTO clienteEntidad) {
        this.clienteEntidad = clienteEntidad;
    }

    public SubClienteApiResponseDTO getSubClienteEntidad() {
        return subClienteEntidad;
    }

    public void setSubClienteEntidad(SubClienteApiResponseDTO subClienteEntidad) {
        this.subClienteEntidad = subClienteEntidad;
    }

    public ProductoApiResponseDTO getProductoEntidad() {
        return productoEntidad;
    }

    public void setProductoEntidad(ProductoApiResponseDTO productoEntidad) {
        this.productoEntidad = productoEntidad;
    }

    public MedicamentosApi getMedicamentos() {
        return medicamentosApi;
    }

    public void setMedicamentos(MedicamentosApi medicamentosApi) {
        this.medicamentosApi = medicamentosApi;
    }

    public RootReceta getRootReceta() {
        return rootReceta;
    }

    public void setRootReceta(RootReceta rootReceta) {
        this.rootReceta = rootReceta;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public MedicamentosApi getMedicamentosApi() {
        return medicamentosApi;
    }

    public void setMedicamentosApi(MedicamentosApi medicamentosApi) {
        this.medicamentosApi = medicamentosApi;
    }

    public String getNur() {
        return nur;
    }

    public void setNur(String nur) {
        this.nur = nur;
    }

    public String getToken() {
        return token.replace("\"", "");
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ClaveCliente getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(ClaveCliente claveCliente) {
        this.claveCliente = claveCliente;
    }
}
