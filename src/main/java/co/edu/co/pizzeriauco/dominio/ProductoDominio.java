package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoDominio {

    private UUID id;
    private String nombre;
    private HistoricoPrecioDominio historicoPrecio;

    private ProductoDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.historicoPrecio = builder.historicoPrecio;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public HistoricoPrecioDominio getHistoricoPrecio() {
        return historicoPrecio;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private HistoricoPrecioDominio historicoPrecio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            historicoPrecio =
                    new HistoricoPrecioDominio.Builder().build();
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

        public Builder historicoPrecio(
                HistoricoPrecioDominio historicoPrecio) {

            this.historicoPrecio =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            historicoPrecio,
                            new HistoricoPrecioDominio.Builder().build()
                    );

            return this;
        }

        public ProductoDominio build() {
            return new ProductoDominio(this);
        }
    }
}