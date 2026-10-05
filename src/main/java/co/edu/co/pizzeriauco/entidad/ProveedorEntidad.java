package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.util.UUID;

public class ProveedorEntidad {

    private UUID id;
    private String nombreEmpresa;
    private String nit;
    private String contacto;
    //true = se le puede comprar; false = desactivado (retirado), se conserva su historial de compras
    private boolean activo;

    private ProveedorEntidad(Builder builder) {
        this.id = builder.id;
        this.nombreEmpresa = builder.nombreEmpresa;
        this.nit = builder.nit;
        this.contacto = builder.contacto;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getNit() {
        return nit;
    }

    public String getContacto() {
        return contacto;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {

        private UUID id;
        private String nombreEmpresa;
        private String nit;
        private String contacto;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombreEmpresa = UtilTexto.vacia;
            nit = UtilTexto.vacia;
            contacto = UtilTexto.vacia;
            //todo proveedor nace activo
            activo = true;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombreEmpresa(String nombreEmpresa) {
            this.nombreEmpresa = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(nombreEmpresa);
            return this;
        }

        //NIT de la empresa (ej. 890904478-6) o cedula si el proveedor es persona natural
        public Builder nit(String nit) {
            this.nit = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(nit);
            return this;
        }

        public Builder contacto(String contacto) {
            this.contacto = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(contacto);
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public ProveedorEntidad build() {
            return new ProveedorEntidad(this);
        }
    }
}
