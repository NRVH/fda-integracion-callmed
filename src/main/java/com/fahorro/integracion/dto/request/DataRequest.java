package com.fahorro.integracion.dto.request;

import com.fahorro.integracion.dto.request.valreceta.RootReceta;

public class DataRequest
{
    private String nur;
    private String token;
    private ClaveCliente claveCliente;
    private RootReceta rootReceta;
    private MedicamentosApi medicamentosApi;

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
