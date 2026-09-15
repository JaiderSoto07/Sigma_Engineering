package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoEntidad {

    private UUID id;
    private String nombre;
    private HistoricoPrecioEntidad historicoPrecio;

    private ProductoEntidad(Builder builder) {
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

    public HistoricoPrecioEntidad getHistoricoPrecio() {
        return historicoPrecio;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private HistoricoPrecioEntidad historicoPrecio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            historicoPrecio =
                    new HistoricoPrecioEntidad.Builder().build();
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
                HistoricoPrecioEntidad historicoPrecio) {

            this.historicoPrecio =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            historicoPrecio,
                            new HistoricoPrecioEntidad.Builder().build()
                    );

            return this;
        }

        public ProductoEntidad build() {
            return new ProductoEntidad(this);
        }
    }
}
