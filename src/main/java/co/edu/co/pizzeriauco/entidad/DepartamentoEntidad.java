package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class DepartamentoEntidad {

    private UUID id;
    private String nombre;
    private PaisEntidad pais;

    private DepartamentoEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.pais = builder.pais;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public PaisEntidad getPais() {
        return pais;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private PaisEntidad pais;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            pais = new PaisEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre =
                    UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder pais(PaisEntidad pais) {
            this.pais =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            pais,
                            new PaisEntidad.Builder().build()
                    );
            return this;
        }

        public DepartamentoEntidad build() {
            return new DepartamentoEntidad(this);
        }
    }
}
