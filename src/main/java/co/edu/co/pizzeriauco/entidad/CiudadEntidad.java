package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class CiudadEntidad {

    private UUID id;
    private String nombre;
    private DepartamentoEntidad departamento;

    private CiudadEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.departamento = builder.departamento;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoEntidad getDepartamento() {
        return departamento;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private DepartamentoEntidad departamento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            departamento = new DepartamentoEntidad.Builder().build();
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

        public Builder departamento(DepartamentoEntidad departamento) {
            this.departamento =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            departamento,
                            new DepartamentoEntidad.Builder().build()
                    );
            return this;
        }

        public CiudadEntidad build() {
            return new CiudadEntidad(this);
        }
    }
}
