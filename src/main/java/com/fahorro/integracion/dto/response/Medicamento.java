package com.fahorro.integracion.dto.response;

import jakarta.json.bind.annotation.JsonbProperty;

public class Medicamento {
    @JsonbProperty("Cantidad")
    private String cantidad;

    @JsonbProperty("EAN")
    private String ean;

    @JsonbProperty("EANEquivalente")
    private String eanEquivalente;

    @JsonbProperty("FechaConsulta")
    private String fechaConsulta;

    @JsonbProperty("Folio")
    private String folio;

    @JsonbProperty("ICD10")
    private String icd10;

    @JsonbProperty("Indicaciones")
    private String indicaciones;

    @JsonbProperty("Medicamento")
    private String medicamento;

    @JsonbProperty("NUR")
    private String nur;

    @JsonbProperty("PacienteName")
    private String pacienteName;

    @JsonbProperty("Status")
    private String status;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getEanEquivalente() {
        return eanEquivalente;
    }

    public void setEanEquivalente(String eanEquivalente) {
        this.eanEquivalente = eanEquivalente;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getIcd10() {
        return icd10;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getNur() {
        return nur;
    }

    public void setNur(String nur) {
        this.nur = nur;
    }

    public String getPacienteName() {
        return pacienteName;
    }

    public void setPacienteName(String pacienteName) {
        this.pacienteName = pacienteName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

