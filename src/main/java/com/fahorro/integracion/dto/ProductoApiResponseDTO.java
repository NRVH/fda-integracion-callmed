package com.fahorro.integracion.dto;

import java.time.LocalDateTime;

public class ProductoApiResponseDTO {
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
            private String nombre;
            private String descripcion;
            private String grupoDeMarca;
            private String controlado;
            private String refrigerado;
            private String antibiotico;
            private String requiereReceta;
            private String farmaco;
            private String gramaje;
            private String unidadDeMedida;
            private String claseTerapeutica;
            private String sustanciaActiva;
            private String laboratorio;
            private String fraccion;
            private String orin;
            private String productCode;
            private String estatus;
            private boolean activo;
            private String division;
            private String categoria;
            private String subcategoria;
            private String segmento;
            private String departamento;
            private String impuestoIEPSCategorizacion;
            private double impuestoIEPSPorcentaje;
            private String impuestoIVARegion;
            private double impuestoIVAPorcentaje;
            private String impuestoIVACompraventa;
            private LocalDateTime ultimaModificacion;
            private LocalDateTime fechaCreacion;
            private String displayURL;
            private double precioUnitario;
            private String itemLevelRMS;
            private String tranLevelRMS;

            public String getNombre() {
                return nombre;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public String getDescripcion() {
                return descripcion;
            }

            public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
            }

            public String getGrupoDeMarca() {
                return grupoDeMarca;
            }

            public void setGrupoDeMarca(String grupoDeMarca) {
                this.grupoDeMarca = grupoDeMarca;
            }

            public String getControlado() {
                return controlado;
            }

            public void setControlado(String controlado) {
                this.controlado = controlado;
            }

            public String getRefrigerado() {
                return refrigerado;
            }

            public void setRefrigerado(String refrigerado) {
                this.refrigerado = refrigerado;
            }

            public String getAntibiotico() {
                return antibiotico;
            }

            public void setAntibiotico(String antibiotico) {
                this.antibiotico = antibiotico;
            }

            public String getRequiereReceta() {
                return requiereReceta;
            }

            public void setRequiereReceta(String requiereReceta) {
                this.requiereReceta = requiereReceta;
            }

            public String getFarmaco() {
                return farmaco;
            }

            public void setFarmaco(String farmaco) {
                this.farmaco = farmaco;
            }

            public String getGramaje() {
                return gramaje;
            }

            public void setGramaje(String gramaje) {
                this.gramaje = gramaje;
            }

            public String getUnidadDeMedida() {
                return unidadDeMedida;
            }

            public void setUnidadDeMedida(String unidadDeMedida) {
                this.unidadDeMedida = unidadDeMedida;
            }

            public String getClaseTerapeutica() {
                return claseTerapeutica;
            }

            public void setClaseTerapeutica(String claseTerapeutica) {
                this.claseTerapeutica = claseTerapeutica;
            }

            public String getSustanciaActiva() {
                return sustanciaActiva;
            }

            public void setSustanciaActiva(String sustanciaActiva) {
                this.sustanciaActiva = sustanciaActiva;
            }

            public String getLaboratorio() {
                return laboratorio;
            }

            public void setLaboratorio(String laboratorio) {
                this.laboratorio = laboratorio;
            }

            public String getFraccion() {
                return fraccion;
            }

            public void setFraccion(String fraccion) {
                this.fraccion = fraccion;
            }

            public String getOrin() {
                return orin;
            }

            public void setOrin(String orin) {
                this.orin = orin;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getEstatus() {
                return estatus;
            }

            public void setEstatus(String estatus) {
                this.estatus = estatus;
            }

            public boolean isActivo() {
                return activo;
            }

            public void setActivo(boolean activo) {
                this.activo = activo;
            }

            public String getDivision() {
                return division;
            }

            public void setDivision(String division) {
                this.division = division;
            }

            public String getCategoria() {
                return categoria;
            }

            public void setCategoria(String categoria) {
                this.categoria = categoria;
            }

            public String getSubcategoria() {
                return subcategoria;
            }

            public void setSubcategoria(String subcategoria) {
                this.subcategoria = subcategoria;
            }

            public String getSegmento() {
                return segmento;
            }

            public void setSegmento(String segmento) {
                this.segmento = segmento;
            }

            public String getDepartamento() {
                return departamento;
            }

            public void setDepartamento(String departamento) {
                this.departamento = departamento;
            }

            public String getImpuestoIEPSCategorizacion() {
                return impuestoIEPSCategorizacion;
            }

            public void setImpuestoIEPSCategorizacion(String impuestoIEPSCategorizacion) {
                this.impuestoIEPSCategorizacion = impuestoIEPSCategorizacion;
            }

            public double getImpuestoIEPSPorcentaje() {
                return impuestoIEPSPorcentaje;
            }

            public void setImpuestoIEPSPorcentaje(double impuestoIEPSPorcentaje) {
                this.impuestoIEPSPorcentaje = impuestoIEPSPorcentaje;
            }

            public String getImpuestoIVARegion() {
                return impuestoIVARegion;
            }

            public void setImpuestoIVARegion(String impuestoIVARegion) {
                this.impuestoIVARegion = impuestoIVARegion;
            }

            public double getImpuestoIVAPorcentaje() {
                return impuestoIVAPorcentaje;
            }

            public void setImpuestoIVAPorcentaje(double impuestoIVAPorcentaje) {
                this.impuestoIVAPorcentaje = impuestoIVAPorcentaje;
            }

            public String getImpuestoIVACompraventa() {
                return impuestoIVACompraventa;
            }

            public void setImpuestoIVACompraventa(String impuestoIVACompraventa) {
                this.impuestoIVACompraventa = impuestoIVACompraventa;
            }

            public LocalDateTime getUltimaModificacion() {
                return ultimaModificacion;
            }

            public void setUltimaModificacion(LocalDateTime ultimaModificacion) {
                this.ultimaModificacion = ultimaModificacion;
            }

            public LocalDateTime getFechaCreacion() {
                return fechaCreacion;
            }

            public void setFechaCreacion(LocalDateTime fechaCreacion) {
                this.fechaCreacion = fechaCreacion;
            }

            public String getDisplayURL() {
                return displayURL;
            }

            public void setDisplayURL(String displayURL) {
                this.displayURL = displayURL;
            }

            public double getPrecioUnitario() {
                return precioUnitario;
            }

            public void setPrecioUnitario(double precioUnitario) {
                this.precioUnitario = precioUnitario;
            }

            public String getItemLevelRMS() {
                return itemLevelRMS;
            }

            public void setItemLevelRMS(String itemLevelRMS) {
                this.itemLevelRMS = itemLevelRMS;
            }

            public String getTranLevelRMS() {
                return tranLevelRMS;
            }

            public void setTranLevelRMS(String tranLevelRMS) {
                this.tranLevelRMS = tranLevelRMS;
            }
        }
    }
}
