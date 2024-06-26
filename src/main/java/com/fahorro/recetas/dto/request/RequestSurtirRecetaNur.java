package com.fahorro.recetas.dto.request;

public class RequestSurtirRecetaNur
{
    private String nur;
    private Integer numeroSucursal;
    private String nombreSucursal;
    private Integer numeroCaja;
    private Integer numeroCajero;
    private Integer numSubCliente;
    private Integer numCliente;
    private String medico;
    private String medicoCedula;

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

    public Integer getNumeroSucursal() {
        return numeroSucursal;
    }

    public void setNumeroSucursal(Integer numeroSucursal) {
        this.numeroSucursal = numeroSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Integer getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(Integer numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public Integer getNumeroCajero() {
        return numeroCajero;
    }

    public void setNumeroCajero(Integer numeroCajero) {
        this.numeroCajero = numeroCajero;
    }

    public Integer getNumSubCliente() {
        return numSubCliente;
    }

    public void setNumSubCliente(Integer numSubCliente) {
        this.numSubCliente = numSubCliente;
    }

    public Integer getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(Integer numCliente) {
        this.numCliente = numCliente;
    }

    public String getNur() {
        return nur;
    }

    public void setNur(String nur) {
        this.nur = nur;
    }
}
