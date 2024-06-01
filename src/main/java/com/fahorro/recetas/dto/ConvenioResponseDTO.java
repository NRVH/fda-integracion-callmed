package com.fahorro.recetas.dto;

public class ConvenioResponseDTO {
    private String uuid;
    private int estatus;
    private String detalles;
    private EntidadDTO entidad;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public EntidadDTO getEntidad() {
        return entidad;
    }

    public void setEntidad(EntidadDTO entidad) {
        this.entidad = entidad;
    }

    public static class EntidadDTO {
        private String id;
        private String nombre;
        private DataJsonDTO dataJson;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public DataJsonDTO getDataJson() {
            return dataJson;
        }

        public void setDataJson(DataJsonDTO dataJson) {
            this.dataJson = dataJson;
        }

        public static class DataJsonDTO {
            private Integer id;
            private String descripcion;
            private Integer idSubcliente;
            private Integer idCliente;
            private Integer idIntegrador;
            private Integer idGrupo;
            private String vigencia;
            private Integer idTipoPago;
            private Double montoCopago;
            private Double porcentajeCopago;
            private String displayUrl;
            private Boolean activo;
            private String fechaCreacion;
            private String fechaUltimaModificacion;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getDescripcion() {
                return descripcion;
            }

            public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
            }

            public Integer getIdSubcliente() {
                return idSubcliente;
            }

            public void setIdSubcliente(Integer idSubcliente) {
                this.idSubcliente = idSubcliente;
            }

            public Integer getIdCliente() {
                return idCliente;
            }

            public void setIdCliente(Integer idCliente) {
                this.idCliente = idCliente;
            }

            public Integer getIdIntegrador() {
                return idIntegrador;
            }

            public void setIdIntegrador(Integer idIntegrador) {
                this.idIntegrador = idIntegrador;
            }

            public Integer getIdGrupo() {
                return idGrupo;
            }

            public void setIdGrupo(Integer idGrupo) {
                this.idGrupo = idGrupo;
            }

            public String getVigencia() {
                return vigencia;
            }

            public void setVigencia(String vigencia) {
                this.vigencia = vigencia;
            }

            public Integer getIdTipoPago() {
                return idTipoPago;
            }

            public void setIdTipoPago(Integer idTipoPago) {
                this.idTipoPago = idTipoPago;
            }

            public Double getMontoCopago() {
                return montoCopago;
            }

            public void setMontoCopago(Double montoCopago) {
                this.montoCopago = montoCopago;
            }

            public Double getPorcentajeCopago() {
                return porcentajeCopago;
            }

            public void setPorcentajeCopago(Double porcentajeCopago) {
                this.porcentajeCopago = porcentajeCopago;
            }

            public String getDisplayUrl() {
                return displayUrl;
            }

            public void setDisplayUrl(String displayUrl) {
                this.displayUrl = displayUrl;
            }

            public Boolean getActivo() {
                return activo;
            }

            public void setActivo(Boolean activo) {
                this.activo = activo;
            }

            public String getFechaCreacion() {
                return fechaCreacion;
            }

            public void setFechaCreacion(String fechaCreacion) {
                this.fechaCreacion = fechaCreacion;
            }

            public String getFechaUltimaModificacion() {
                return fechaUltimaModificacion;
            }

            public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
                this.fechaUltimaModificacion = fechaUltimaModificacion;
            }
        }
    }
}
