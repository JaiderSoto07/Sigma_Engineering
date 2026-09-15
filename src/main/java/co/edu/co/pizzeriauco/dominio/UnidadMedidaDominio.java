package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class UnidadMedidaDominio {

    private UUID id;
    private String unidadMedida;
    private String tipoMedida;

    private UnidadMedidaDominio(Builder builder) {
        this.id = builder.id;
        this.unidadMedida = builder.unidadMedida;
        this.tipoMedida = builder.tipoMedida;
    }

    public UUID getId() {
        return id;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public static class Builder {

        private UUID id;
        private String unidadMedida;
        private String tipoMedida;

        public Builder() {
            id = UtilId.valorDefecto(id);
            unidadMedida = UtilTexto.vacia;
            tipoMedida = UtilTexto.vacia;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder unidadMedida(String unidadMedida) {
            this.unidadMedida =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(unidadMedida);
            return this;
        }

        public Builder tipoMedida(String tipoMedida) {
            this.tipoMedida =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(tipoMedida);
            return this;
        }

        public UnidadMedidaDominio build() {
            return new UnidadMedidaDominio(this);
        }
    }
}