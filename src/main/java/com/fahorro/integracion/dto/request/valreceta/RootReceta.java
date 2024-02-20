package com.fahorro.integracion.dto.request.valreceta;

import jakarta.json.bind.annotation.JsonbProperty;

public class RootReceta {
    @JsonbProperty("Success")
    private boolean success;

    @JsonbProperty("Code")
    private String code;

    @JsonbProperty("Message")
    private String message;

    @JsonbProperty("Medicamento")
    private Object medicamento;

    @JsonbProperty("Receta")
    private Receta receta;

    @JsonbProperty("Direcciones")
    private Object direcciones;

    @JsonbProperty("DireccionID")
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
