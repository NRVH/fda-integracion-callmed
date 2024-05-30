package com.fahorro.integracion.dto;


import java.util.List;

public class ConvenioProductoResponseDTO {
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
        private List<DataJsonDTO> listDataJson;

        public List<DataJsonDTO> getListDataJson() {
            return listDataJson;
        }

        public void setListDataJson(List<DataJsonDTO> listDataJson) {
            this.listDataJson = listDataJson;
        }

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
            private int idConvenio;
            private String productCode;
            private double precio;
            private String equivalencias;
            private boolean activo;
            private String fechaCreacion;
            private String fechaUltimaModificacion;

            public int getIdConvenio() {
                return idConvenio;
            }

            public void setIdConvenio(int idConvenio) {
                this.idConvenio = idConvenio;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public double getPrecio() {
                return precio;
            }

            public void setPrecio(double precio) {
                this.precio = precio;
            }

            public String getEquivalencias() {
                return equivalencias;
            }

            public void setEquivalencias(String equivalencias) {
                this.equivalencias = equivalencias;
            }

            public boolean isActivo() {
                return activo;
            }

            public void setActivo(boolean activo) {
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
