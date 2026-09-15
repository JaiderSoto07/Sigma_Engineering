package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ClienteDominio {

    private UUID id;
    private String nombre;
    private String apellido;
    private String documento;
    private String telefono;

    private ClienteDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.documento = builder.documento;
        this.telefono = builder.telefono;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private String apellido;
        private String documento;
        private String telefono;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            apellido = UtilTexto.vacia;
            documento = UtilTexto.vacia;
            telefono = UtilTexto.vacia;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(apellido);
            return this;
        }

        public Builder documento(String documento) {
            this.documento =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(documento);
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(telefono);
            return this;
        }

        public ClienteDominio build() {
            return new ClienteDominio(this);
        }
    }
}