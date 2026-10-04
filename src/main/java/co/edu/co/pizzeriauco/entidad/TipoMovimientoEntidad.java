package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.util.UUID;

//es el codigo de cada operacion: lo comparten el renglon que la genero y sus movimientos
public class TipoMovimientoEntidad {

    private UUID id;
    private CategoriaOrigenEntidad categoriaOrigen;

    private TipoMovimientoEntidad(Builder builder) {
        this.id = builder.id;
        this.categoriaOrigen = builder.categoriaOrigen;
    }

    public UUID getId() {
        return id;
    }

    public CategoriaOrigenEntidad getCategoriaOrigen() {
        return categoriaOrigen;
    }

    public static class Builder {

        private UUID id;
        private CategoriaOrigenEntidad categoriaOrigen;

        public Builder() {
            id = UtilId.valorDefecto(id);
            categoriaOrigen = new CategoriaOrigenEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder categoriaOrigen(CategoriaOrigenEntidad categoriaOrigen) {
            this.categoriaOrigen = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            categoriaOrigen,
                            new CategoriaOrigenEntidad.Builder().build()
                    );
            return this;
        }

        public TipoMovimientoEntidad build() {
            return new TipoMovimientoEntidad(this);
        }
    }
}
