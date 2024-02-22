package com.fahorro.integracion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medicamento {
    @JsonProperty("Cantidad")
    private String cantidad;

    @JsonProperty("EAN")
    private String ean;

    @JsonProperty("EANEquivalente")
    private String eanEquivalente;

    @JsonProperty("FechaConsulta")
    private String fechaConsulta;

    @JsonProperty("Folio")
    private String folio;

    @JsonProperty("ICD10")
    private String icd10;

    @JsonProperty("Indicaciones")
    private String indicaciones;

    @JsonProperty("Medicamento")
    private String medicamento;

    @JsonProperty("NUR")
    private String nur;

    @JsonProperty("PacienteName")
    private String pacienteName;

    @JsonProperty("Status")
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

