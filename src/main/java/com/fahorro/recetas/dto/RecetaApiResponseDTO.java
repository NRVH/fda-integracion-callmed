package com.fahorro.recetas.dto;

import java.math.BigDecimal;

public class RecetaApiResponseDTO {
    private String uuid;
    private int estatus;
    private String detalles;
    private EntidadDTO entidad;

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDetalles() {
        return detalles;
    }

    public EntidadDTO getEntidad() {
        return entidad;
    }

    public int getEstatus() {
        return estatus;
    }

    public String getUuid() {
        return uuid;
    }

    public static class EntidadDTO {
        private String id;
        private String nombre;
        private DataJsonDTO dataJson;

        public void setDataJson(DataJsonDTO dataJson) {
            this.dataJson = dataJson;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public DataJsonDTO getDataJson() {
            return dataJson;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public static class DataJsonDTO {
            private String nombre;
            private String descripcion;
            private String fechaCreacion;
            private String ultimaModificacion;
            private String idConvenio;
            private String nombreMedico;
            private String cedulaMedico;
            private String nombreDelPaciente;
            private BigDecimal totalReceta;
            private BigDecimal montoCopago;
            private String idSucursal;
            private String razonNoSurtir;
            private boolean estatus;
            private String estatusStr;


            public String getEstatusStr() {
                return estatusStr;
            }

            public void setEstatusStr(String estatusStr) {
                this.estatusStr = estatusStr;
            }

            public void setCedulaMedico(String cedulaMedico) {
                this.cedulaMedico = cedulaMedico;
            }

            public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
            }

            public void setEstatus(boolean estatus) {
                this.estatus = estatus;
            }

            public void setFechaCreacion(String fechaCreacion) {
                this.fechaCreacion = fechaCreacion;
            }

            public void setIdConvenio(String idConvenio) {
                this.idConvenio = idConvenio;
            }

            public void setIdSucursal(String idSucursal) {
                this.idSucursal = idSucursal;
            }

            public void setMontoCopago(BigDecimal montoCopago) {
                this.montoCopago = montoCopago;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public void setNombreDelPaciente(String nombreDelPaciente) {
                this.nombreDelPaciente = nombreDelPaciente;
            }

            public void setNombreMedico(String nombreMedico) {
                this.nombreMedico = nombreMedico;
            }

            public void setRazonNoSurtir(String razonNoSurtir) {
                this.razonNoSurtir = razonNoSurtir;
            }

            public void setTotalReceta(BigDecimal totalReceta) {
                this.totalReceta = totalReceta;
            }

            public void setUltimaModificacion(String ultimaModificacion) {
                this.ultimaModificacion = ultimaModificacion;
            }

            public String getCedulaMedico() {
                return cedulaMedico;
            }

            public String getDescripcion() {
                return descripcion;
            }

            public String getFechaCreacion() {
                return fechaCreacion;
            }

            public String getIdConvenio() {
                return idConvenio;
            }

            public String getIdSucursal() {
                return idSucursal;
            }

            public BigDecimal getMontoCopago() {
                return montoCopago;
            }

            public String getNombre() {
                return nombre;
            }

            public String getNombreDelPaciente() {
                return nombreDelPaciente;
            }

            public String getNombreMedico() {
                return nombreMedico;
            }

            public String getRazonNoSurtir() {
                return razonNoSurtir;
            }

            public BigDecimal getTotalReceta() {
                return totalReceta;
            }

            public String getUltimaModificacion() {
                return ultimaModificacion;
            }

            public boolean isEstatus() {
                return estatus;
            }
        }
    }
}
