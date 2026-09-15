package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class BarrioDominio {

    private UUID id;
    private String nombre;
    private CiudadDominio ciudad;

    private BarrioDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.ciudad = builder.ciudad;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public CiudadDominio getCiudad() {
        return ciudad;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private CiudadDominio ciudad;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            ciudad = new CiudadDominio.Builder().build();
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

        public Builder ciudad(CiudadDominio ciudad) {
            this.ciudad =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            ciudad,
                            new CiudadDominio.Builder().build()
                    );
            return this;
        }

        public BarrioDominio build() {
            return new BarrioDominio(this);
        }
    }
}