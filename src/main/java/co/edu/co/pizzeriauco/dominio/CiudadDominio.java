package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class CiudadDominio {

    private UUID id;
    private String nombre;
    private DepartamentoDominio departamento;

    private CiudadDominio(Builder builder) {
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

    public DepartamentoDominio getDepartamento() {
        return departamento;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private DepartamentoDominio departamento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            departamento = new DepartamentoDominio.Builder().build();
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

        public Builder departamento(DepartamentoDominio departamento) {
            this.departamento =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            departamento,
                            new DepartamentoDominio.Builder().build()
                    );
            return this;
        }

        public CiudadDominio build() {
            return new CiudadDominio(this);
        }
    }
}