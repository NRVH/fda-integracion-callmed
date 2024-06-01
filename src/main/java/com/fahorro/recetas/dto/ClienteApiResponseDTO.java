package com.fahorro.recetas.dto;

public class ClienteApiResponseDTO {
    private String uuid;
    private int estatus;
    private String detalles;
    private EntidadDTO entidad;

    public static class EntidadDTO {
        private Long id;
        private String nombre;
        private DataJsonDTO dataJson;

        public static class DataJsonDTO {
            private Long id;
            private String nombre;
            private String apellido;
            private String direccion;
            private String correoElectronico;
            private String telefono;
            private String personaJuridica;
            private String razonSocial;
            private int numeroCliente;
            private String rfc;
            private String tipoDePrecio;
            private String tipoDeCliente;
            private String tipoPagoCliente;

            public String getTipoDeCliente() {
                return tipoDeCliente;
            }

            public void setTipoDeCliente(String tipoDeCliente) {
                this.tipoDeCliente = tipoDeCliente;
            }

            public String getTipoPagoCliente() {
                return tipoPagoCliente;
            }

            public void setTipoPagoCliente(String tipoPagoCliente) {
                this.tipoPagoCliente = tipoPagoCliente;
            }

            public String getTipoDePrecio() {
                return tipoDePrecio;
            }

            public void setTipoDePrecio(String tipoDePrecio) {
                this.tipoDePrecio = tipoDePrecio;
            }

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

            public String getApellido() {
                return apellido;
            }

            public void setApellido(String apellido) {
                this.apellido = apellido;
            }

            public String getDireccion() {
                return direccion;
            }

            public void setDireccion(String direccion) {
                this.direccion = direccion;
            }

            public String getCorreoElectronico() {
                return correoElectronico;
            }

            public void setCorreoElectronico(String correoElectronico) {
                this.correoElectronico = correoElectronico;
            }

            public String getTelefono() {
                return telefono;
            }

            public void setTelefono(String telefono) {
                this.telefono = telefono;
            }

            public String getPersonaJuridica() {
                return personaJuridica;
            }

            public void setPersonaJuridica(String personaJuridica) {
                this.personaJuridica = personaJuridica;
            }

            public String getRazonSocial() {
                return razonSocial;
            }

            public void setRazonSocial(String razonSocial) {
                this.razonSocial = razonSocial;
            }

            public int getNumeroCliente() {
                return numeroCliente;
            }

            public void setNumeroCliente(int numeroCliente) {
                this.numeroCliente = numeroCliente;
            }

            public String getRfc() {
                return rfc;
            }

            public void setRfc(String rfc) {
                this.rfc = rfc;
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

    // Getters and Setters for ClienteApiResponseDTO
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
