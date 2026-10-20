package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.util.UUID;

public class UsuarioEntidad {

    private UUID id;
    private String usuario;
    private String clave;
    private String rol;
    private boolean activo;

    private UsuarioEntidad(Builder builder) {
        this.id = builder.id;
        this.usuario = builder.usuario;
        this.clave = builder.clave;
        this.rol = builder.rol;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getRol() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {

        private UUID id;
        private String usuario;
        private String clave;
        private String rol;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            usuario = UtilTexto.vacia;
            clave = UtilTexto.vacia;
            rol = UtilTexto.vacia;
            activo = true;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder usuario(String usuario) {
            this.usuario = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(usuario);
            return this;
        }

        public Builder clave(String clave) {
            this.clave = UtilTexto.obtenerValorDefecto(clave);
            return this;
        }

        public Builder rol(String rol) {
            this.rol = UtilTexto.getUtilTexto().primeraLetraMayuscula(rol);
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public UsuarioEntidad build() {
            return new UsuarioEntidad(this);
        }
    }
}
