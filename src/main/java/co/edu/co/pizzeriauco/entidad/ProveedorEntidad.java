package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProveedorEntidad {

    private UUID id;
    private String nombreEmpresa;
    private String contacto;

    private ProveedorEntidad(Builder builder) {
        this.id = builder.id;
        this.nombreEmpresa = builder.nombreEmpresa;
        this.contacto = builder.contacto;
    }

    public UUID getId() {
        return id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public static class Builder {

        private UUID id;
        private String nombreEmpresa;
        private String contacto;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombreEmpresa = UtilTexto.vacia;
            contacto = UtilTexto.vacia;
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

        public Builder contacto(String contacto) {
            this.contacto = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(contacto);
            return this;
        }

        public ProveedorEntidad build() {
            return new ProveedorEntidad(this);
        }
    }
}
