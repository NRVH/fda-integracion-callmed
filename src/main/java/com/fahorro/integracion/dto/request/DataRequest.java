package com.fahorro.integracion.dto.request;

import com.fahorro.integracion.dto.*;
import com.fahorro.integracion.dto.request.valreceta.RecetaCallmed;

public class DataRequest
{
    private String nur;
    private String codigoSucursal;
    private String token;
    private String idConvenio;
    private ClaveCliente claveCliente;
    private RecetaCallmed recetaCallmed;
    private MedicamentosApi medicamentosApi;
    private ConvenioResponseDTO convenioEntidad;
    private ClienteApiResponseDTO clienteEntidad;
    private SubClienteApiResponseDTO subClienteEntidad;
    private ProductoApiResponseDTO productoEntidad;

    public String getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(String idConvenio) {
        this.idConvenio = idConvenio;
    }

    public RecetaCallmed getRecetaCallmed() {
        return recetaCallmed;
    }

    public void setRecetaCallmed(RecetaCallmed recetaCallmed) {
        this.recetaCallmed = recetaCallmed;
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
