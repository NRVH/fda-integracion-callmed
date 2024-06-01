package com.fahorro.recetas.dto.request.valreceta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecetaCallmed {
    @JsonProperty("Success")
    private boolean success;

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Medicamento")
    private Object medicamento;

    @JsonProperty("Receta")
    private Receta receta;

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

    public Object getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Object medicamento) {
        this.medicamento = medicamento;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
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
