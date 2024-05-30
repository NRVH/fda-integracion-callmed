package com.fahorro.integracion.dto;

public class SubClienteApiResponseDTO {

    private String uuid;
    private int estatus;
    private String detalles;
    private EntidadDTO entidad;

    public static class EntidadDTO {
        private Long id;
        private String nombre;
        private EntidadDTO.DataJsonDTO dataJson;

        public static class DataJsonDTO {
            private Integer idSubcliente;
            private Integer idCliente;
            private String nombre;
            private String personaJuridica;
            private String numeroSubcliente;
            private String prefijoSubcliente;
            private String direccion;
            private String rfc;
            private String telefono;
            private String correoElectronico;
            private String razonSocial;
            private String ldpos;

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

            public String getNombre() {
                return nombre;
            }

            public void setNombre(String nombre) {
                this.nombre = nombre;
            }

            public String getPersonaJuridica() {
                return personaJuridica;
            }

            public void setPersonaJuridica(String personaJuridica) {
                this.personaJuridica = personaJuridica;
            }

            public String getNumeroSubcliente() {
                return numeroSubcliente;
            }

            public void setNumeroSubcliente(String numeroSubcliente) {
                this.numeroSubcliente = numeroSubcliente;
            }

            public String getPrefijoSubcliente() {
                return prefijoSubcliente;
            }

            public void setPrefijoSubcliente(String prefijoSubcliente) {
                this.prefijoSubcliente = prefijoSubcliente;
            }

            public String getDireccion() {
                return direccion;
            }

            public void setDireccion(String direccion) {
                this.direccion = direccion;
            }

            public String getRfc() {
                return rfc;
            }

            public void setRfc(String rfc) {
                this.rfc = rfc;
            }

            public String getTelefono() {
                return telefono;
            }

            public void setTelefono(String telefono) {
                this.telefono = telefono;
            }

            public String getCorreoElectronico() {
                return correoElectronico;
            }

            public void setCorreoElectronico(String correoElectronico) {
                this.correoElectronico = correoElectronico;
            }

            public String getRazonSocial() {
                return razonSocial;
            }

            public void setRazonSocial(String razonSocial) {
                this.razonSocial = razonSocial;
            }

            public String getLdpos() {
                return ldpos;
            }

            public void setLdpos(String ldpos) {
                this.ldpos = ldpos;
            }
        }

        // Getters and Setters for EntidadDTO
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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
}
