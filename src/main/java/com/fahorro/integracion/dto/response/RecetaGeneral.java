package com.fahorro.integracion.dto.response;

import java.util.List;

public class RecetaGeneral {
    private Boolean cliente;
    private String subCliente;
    private String numeroCliente;
    private String numeroSubCliente;
    private Boolean tieneCopago;
    private TipoCopago tipoCopago;
    private String tipoPrecios;
    private Boolean pagoContado;
    private String numeroProveedorReceta;
    private String mensajeParaSurtir;
    private String imagenParaSurtir;
    private Integer ldpos;
    private String especialidad;
    private String fechaReceta;
    private String medico;
    private String medicoCedula;
    private String numeroPreAutorizacion;
    private String numeroPaciente;
    private String paciente;
    private String proveedorReceta;
    private String razonNoSurtir;
    private Boolean sePuedeSurtir;
    private String copago;
    private Integer copiaTantos;
    private String diagnostico;
    private String elegibilidad;
    private String familiar;
    private String icd;
    private String nomina;
    private String numeroDiagnostico;
    private String tipoNip;
    private Boolean requiereNip;
    private String recetaGrupo;
    private String tipoReceta;
    private Boolean ventaValida;
    private List<LineItem> lineItem;

    public Boolean getCliente() {
        return cliente;
    }

    public void setCliente(Boolean cliente) {
        this.cliente = cliente;
    }

    public String getSubCliente() {
        return subCliente;
    }

    public void setSubCliente(String subCliente) {
        this.subCliente = subCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNumeroSubCliente() {
        return numeroSubCliente;
    }

    public void setNumeroSubCliente(String numeroSubCliente) {
        this.numeroSubCliente = numeroSubCliente;
    }

    public Boolean getTieneCopago() {
        return tieneCopago;
    }

    public void setTieneCopago(Boolean tieneCopago) {
        this.tieneCopago = tieneCopago;
    }

    public TipoCopago getTipoCopago() {
        return tipoCopago;
    }

    public void setTipoCopago(TipoCopago tipoCopago) {
        this.tipoCopago = tipoCopago;
    }

    public String getTipoPrecios() {
        return tipoPrecios;
    }

    public void setTipoPrecios(String tipoPrecios) {
        this.tipoPrecios = tipoPrecios;
    }

    public Boolean getPagoContado() {
        return pagoContado;
    }

    public void setPagoContado(Boolean pagoContado) {
        this.pagoContado = pagoContado;
    }

    public String getNumeroProveedorReceta() {
        return numeroProveedorReceta;
    }

    public void setNumeroProveedorReceta(String numeroProveedorReceta) {
        this.numeroProveedorReceta = numeroProveedorReceta;
    }

    public String getMensajeParaSurtir() {
        return mensajeParaSurtir;
    }

    public void setMensajeParaSurtir(String mensajeParaSurtir) {
        this.mensajeParaSurtir = mensajeParaSurtir;
    }

    public String getImagenParaSurtir() {
        return imagenParaSurtir;
    }

    public void setImagenParaSurtir(String imagenParaSurtir) {
        this.imagenParaSurtir = imagenParaSurtir;
    }

    public Integer getLdpos() {
        return ldpos;
    }

    public void setLdpos(Integer ldpos) {
        this.ldpos = ldpos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechaReceta() {
        return fechaReceta;
    }

    public void setFechaReceta(String fechaReceta) {
        this.fechaReceta = fechaReceta;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getMedicoCedula() {
        return medicoCedula;
    }

    public void setMedicoCedula(String medicoCedula) {
        this.medicoCedula = medicoCedula;
    }

    public String getNumeroPreAutorizacion() {
        return numeroPreAutorizacion;
    }

    public void setNumeroPreAutorizacion(String numeroPreAutorizacion) {
        this.numeroPreAutorizacion = numeroPreAutorizacion;
    }

    public String getNumeroPaciente() {
        return numeroPaciente;
    }

    public void setNumeroPaciente(String numeroPaciente) {
        this.numeroPaciente = numeroPaciente;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getProveedorReceta() {
        return proveedorReceta;
    }

    public void setProveedorReceta(String proveedorReceta) {
        this.proveedorReceta = proveedorReceta;
    }

    public String getRazonNoSurtir() {
        return razonNoSurtir;
    }

    public void setRazonNoSurtir(String razonNoSurtir) {
        this.razonNoSurtir = razonNoSurtir;
    }

    public Boolean getSePuedeSurtir() {
        return sePuedeSurtir;
    }

    public void setSePuedeSurtir(Boolean sePuedeSurtir) {
        this.sePuedeSurtir = sePuedeSurtir;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public Integer getCopiaTantos() {
        return copiaTantos;
    }

    public void setCopiaTantos(Integer copiaTantos) {
        this.copiaTantos = copiaTantos;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getElegibilidad() {
        return elegibilidad;
    }

    public void setElegibilidad(String elegibilidad) {
        this.elegibilidad = elegibilidad;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getIcd() {
        return icd;
    }

    public void setIcd(String icd) {
        this.icd = icd;
    }

    public String getNomina() {
        return nomina;
    }

    public void setNomina(String nomina) {
        this.nomina = nomina;
    }

    public String getNumeroDiagnostico() {
        return numeroDiagnostico;
    }

    public void setNumeroDiagnostico(String numeroDiagnostico) {
        this.numeroDiagnostico = numeroDiagnostico;
    }

    public String getTipoNip() {
        return tipoNip;
    }

    public void setTipoNip(String tipoNip) {
        this.tipoNip = tipoNip;
    }

    public Boolean getRequiereNip() {
        return requiereNip;
    }

    public void setRequiereNip(Boolean requiereNip) {
        this.requiereNip = requiereNip;
    }

    public String getRecetaGrupo() {
        return recetaGrupo;
    }

    public void setRecetaGrupo(String recetaGrupo) {
        this.recetaGrupo = recetaGrupo;
    }

    public String getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(String tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public Boolean getVentaValida() {
        return ventaValida;
    }

    public void setVentaValida(Boolean ventaValida) {
        this.ventaValida = ventaValida;
    }

    public List<LineItem> getLineItem() {
        return lineItem;
    }

    public void setLineItem(List<LineItem> lineItem) {
        this.lineItem = lineItem;
    }
}
