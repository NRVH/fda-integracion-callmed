package com.fahorro.integracion.dto.request.valreceta;

import jakarta.json.bind.annotation.JsonbProperty;

public class Receta {
    @JsonbProperty("Autorizacion")
    private String autorizacion;

    @JsonbProperty("Folio")
    private String folio;

    @JsonbProperty("NUR")
    private String nur;

    @JsonbProperty("FechaConsulta")
    private String fechaConsulta;

    @JsonbProperty("NombreMedico")
    private String nombreMedico;

    @JsonbProperty("ClaveMedico")
    private String claveMedico;

    @JsonbProperty("ConsultorioID")
    private String consultorioID;

    @JsonbProperty("ConsultorioNombre")
    private String consultorioNombre;

    @JsonbProperty("FolioConsultorio")
    private String folioConsultorio;

    @JsonbProperty("DoctorEspecialidad")
    private String doctorEspecialidad;

    @JsonbProperty("NombrePaciente")
    private String nombrePaciente;

    @JsonbProperty("ClavePaciente")
    private String clavePaciente;

    @JsonbProperty("Familiar")
    private String familiar;

    @JsonbProperty("EdadPaciente")
    private String edadPaciente;

    @JsonbProperty("SexoPaciente")
    private String sexoPaciente;

    @JsonbProperty("Status")
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
