package com.fahorro.integracion.dto.request;

import jakarta.json.bind.annotation.JsonbProperty;

public class ClaveCliente {
    @JsonbProperty("Success")
    private boolean success;

    @JsonbProperty("Code")
    private String code;

    @JsonbProperty("Message")
    private String message;

    @JsonbProperty("ClaveCliente")
    private String claveCliente;

    @JsonbProperty("Subcliente")
    private String subcliente;

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

    public String getClaveCliente() {
        return claveCliente;
    }

    public void setClaveCliente(String claveCliente) {
        this.claveCliente = claveCliente;
    }

    public String getSubcliente() {
        return subcliente;
    }

    public void setSubcliente(String subcliente) {
        this.subcliente = subcliente;
    }

    @Override
    public String toString() {
        return "ClienteInfo{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", claveCliente='" + claveCliente + '\'' +
                ", subcliente='" + subcliente + '\'' +
                '}';
    }
}
