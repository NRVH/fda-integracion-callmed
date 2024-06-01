package com.fahorro.recetas.dto.request.valreceta;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Receta {
    @JsonProperty("Autorizacion")
    private String autorizacion;

    @JsonProperty("Folio")
    private String folio;

    @JsonProperty("NUR")
    private String nur;

    @JsonProperty("FechaConsulta")
    private String fechaConsulta;

    @JsonProperty("NombreMedico")
    private String nombreMedico;

    @JsonProperty("ClaveMedico")
    private String claveMedico;

    @JsonProperty("ConsultorioID")
    private String consultorioID;

    @JsonProperty("ConsultorioNombre")
    private String consultorioNombre;

    @JsonProperty("FolioConsultorio")
    private String folioConsultorio;

    @JsonProperty("DoctorEspecialidad")
    private String doctorEspecialidad;

    @JsonProperty("NombrePaciente")
    private String nombrePaciente;

    @JsonProperty("ClavePaciente")
    private String clavePaciente;

    @JsonProperty("Familiar")
    private String familiar;

    @JsonProperty("EdadPaciente")
    private String edadPaciente;

    @JsonProperty("SexoPaciente")
    private String sexoPaciente;

    @JsonProperty("Status")
    private String status;

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNur() {
        return nur;
    }

    public void setNur(String nur) {
        this.nur = nur;
    }

    public String getFechaConsulta() {
        return fechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getClaveMedico() {
        return claveMedico;
    }

    public void setClaveMedico(String claveMedico) {
        this.claveMedico = claveMedico;
    }

    public String getConsultorioID() {
        return consultorioID;
    }

    public void setConsultorioID(String consultorioID) {
        this.consultorioID = consultorioID;
    }

    public String getConsultorioNombre() {
        return consultorioNombre;
    }

    public void setConsultorioNombre(String consultorioNombre) {
        this.consultorioNombre = consultorioNombre;
    }

    public String getFolioConsultorio() {
        return folioConsultorio;
    }

    public void setFolioConsultorio(String folioConsultorio) {
        this.folioConsultorio = folioConsultorio;
    }

    public String getDoctorEspecialidad() {
        return doctorEspecialidad;
    }

    public void setDoctorEspecialidad(String doctorEspecialidad) {
        this.doctorEspecialidad = doctorEspecialidad;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getClavePaciente() {
        return clavePaciente;
    }

    public void setClavePaciente(String clavePaciente) {
        this.clavePaciente = clavePaciente;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getEdadPaciente() {
        return edadPaciente;
    }

    public void setEdadPaciente(String edadPaciente) {
        this.edadPaciente = edadPaciente;
    }

    public String getSexoPaciente() {
        return sexoPaciente;
    }

    public void setSexoPaciente(String sexoPaciente) {
        this.sexoPaciente = sexoPaciente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
