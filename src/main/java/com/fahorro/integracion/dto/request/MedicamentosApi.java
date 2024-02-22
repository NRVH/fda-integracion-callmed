package com.fahorro.integracion.dto.request;

import com.fahorro.integracion.dto.response.Medicamento;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicamentosApi {
    @JsonProperty("Success")
    private boolean success;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Medicamento")
    private Medicamento medicamento;

    @JsonProperty("Receta")
    private Object receta;

    @JsonProperty("Direcciones")
    private Object direcciones;

    @JsonProperty("DireccionID")
    private Object direccionID;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Object getReceta() {
        return receta;
    }

    public void setReceta(Object receta) {
        this.receta = receta;
    }

    public Object getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Object direcciones) {
        this.direcciones = direcciones;
    }

    public Object getDireccionID() {
        return direccionID;
    }

    public void setDireccionID(Object direccionID) {
        this.direccionID = direccionID;
    }
}
