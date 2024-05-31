package com.fahorro.integracion.dto;

public class SucursalApiResponseDTO {

    private String uuid;
    private int estatus;
    private String detalles;
    private EntidadDTO entidad;
    private Object listEntidad;


    public static class EntidadDTO {
        private String id;
        private String nombre;
        private DataJsonDTO dataJson;
        private Object listDataJson;


        public static class DataJsonDTO {
            private int tiendaId;
            private String tienda;
            private String fechaApertura;
            private String fechaClausura;
            private int regionId;
            private String region;
            private int plazaId;
            private String plaza;
            private int divisionId;
            private String division;
            private int ciudadOperativaId;
            private String ciudadOperativa;
            private String fechaUltimaModificacion;
            private boolean activo;


            public int getTiendaId() {
                return tiendaId;
            }

            public void setTiendaId(int tiendaId) {
                this.tiendaId = tiendaId;
            }

            public String getTienda() {
                return tienda;
            }

            public void setTienda(String tienda) {
                this.tienda = tienda;
            }

            public String getFechaApertura() {
                return fechaApertura;
            }

            public void setFechaApertura(String fechaApertura) {
                this.fechaApertura = fechaApertura;
            }

            public String getFechaClausura() {
                return fechaClausura;
            }

            public void setFechaClausura(String fechaClausura) {
                this.fechaClausura = fechaClausura;
            }

            public int getRegionId() {
                return regionId;
            }

            public void setRegionId(int regionId) {
                this.regionId = regionId;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public int getPlazaId() {
                return plazaId;
            }

            public void setPlazaId(int plazaId) {
                this.plazaId = plazaId;
            }

            public String getPlaza() {
                return plaza;
            }

            public void setPlaza(String plaza) {
                this.plaza = plaza;
            }

            public int getDivisionId() {
                return divisionId;
            }

            public void setDivisionId(int divisionId) {
                this.divisionId = divisionId;
            }

            public String getDivision() {
                return division;
            }

            public void setDivision(String division) {
                this.division = division;
            }

            public int getCiudadOperativaId() {
                return ciudadOperativaId;
            }

            public void setCiudadOperativaId(int ciudadOperativaId) {
                this.ciudadOperativaId = ciudadOperativaId;
            }

            public String getCiudadOperativa() {
                return ciudadOperativa;
            }

            public void setCiudadOperativa(String ciudadOperativa) {
                this.ciudadOperativa = ciudadOperativa;
            }

            public String getFechaUltimaModificacion() {
                return fechaUltimaModificacion;
            }

            public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
                this.fechaUltimaModificacion = fechaUltimaModificacion;
            }

            public boolean isActivo() {
                return activo;
            }

            public void setActivo(boolean activo) {
                this.activo = activo;
            }
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

        public Object getListDataJson() {
            return listDataJson;
        }

        public void setListDataJson(Object listDataJson) {
            this.listDataJson = listDataJson;
        }
    }

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

    public Object getListEntidad() {
        return listEntidad;
    }

    public void setListEntidad(Object listEntidad) {
        this.listEntidad = listEntidad;
    }
}