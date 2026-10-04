package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.util.UUID;

//es el codigo de cada operacion: lo comparten el renglon que la genero y sus movimientos
public class TipoMovimientoDominio {

    private UUID id;
    private CategoriaOrigenDominio categoriaOrigen;

    private TipoMovimientoDominio(Builder builder) {
        this.id = builder.id;
        this.categoriaOrigen = builder.categoriaOrigen;
    }

    public UUID getId() {
        return id;
    }

    public CategoriaOrigenDominio getCategoriaOrigen() {
        return categoriaOrigen;
    }

    public static class Builder {

        private UUID id;
        private CategoriaOrigenDominio categoriaOrigen;

        public Builder() {
            id = UtilId.valorDefecto(id);
            categoriaOrigen = new CategoriaOrigenDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder categoriaOrigen(CategoriaOrigenDominio categoriaOrigen) {
            this.categoriaOrigen = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            categoriaOrigen,
                            new CategoriaOrigenDominio.Builder().build()
                    );
            return this;
        }

        public TipoMovimientoDominio build() {
            return new TipoMovimientoDominio(this);
        }
    }
}
