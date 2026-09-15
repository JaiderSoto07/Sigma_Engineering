package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class TamanoEntidad {

    private UUID id;
    private String tamano;

    private TamanoEntidad(Builder builder) {
        this.id = builder.id;
        this.tamano = builder.tamano;
    }

    public UUID getId() {
        return id;
    }

    public String getTamano() {
        return tamano;
    }

    public static class Builder {

        private UUID id;
        private String tamano;

        public Builder() {
            id = UtilId.valorDefecto(id);
            tamano = UtilTexto.vacia;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder tamano(String tamano) {
            this.tamano =
                    UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(tamano);
            return this;
        }

        public TamanoEntidad build() {
            return new TamanoEntidad(this);
        }
    }
}
