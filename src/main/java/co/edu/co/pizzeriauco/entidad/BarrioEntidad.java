package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class BarrioEntidad {

    private UUID id;
    private String nombre;
    private CiudadEntidad ciudad;

    private BarrioEntidad(Builder builder) {
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

    public CiudadEntidad getCiudad() {
        return ciudad;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private CiudadEntidad ciudad;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            ciudad = new CiudadEntidad.Builder().build();
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

        public Builder ciudad(CiudadEntidad ciudad) {
            this.ciudad =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            ciudad,
                            new CiudadEntidad.Builder().build()
                    );
            return this;
        }

        public BarrioEntidad build() {
            return new BarrioEntidad(this);
        }
    }
}
