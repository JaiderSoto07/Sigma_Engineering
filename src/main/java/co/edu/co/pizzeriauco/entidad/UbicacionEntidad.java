package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class UbicacionEntidad {

    private UUID id;
    private String direccion;
    private BarrioEntidad barrio;

    private UbicacionEntidad(Builder builder) {
        this.id = builder.id;
        this.direccion = builder.direccion;
        this.barrio = builder.barrio;
    }

    public UUID getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public BarrioEntidad getBarrio() {
        return barrio;
    }

    public static class Builder {

        private UUID id;
        private String direccion;
        private BarrioEntidad barrio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            direccion = UtilTexto.vacia;
            barrio = new BarrioEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(direccion);
            return this;
        }

        public Builder barrio(BarrioEntidad barrio) {
            this.barrio = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            barrio,
                            new BarrioEntidad.Builder().build()
                    );
            return this;
        }

        public UbicacionEntidad build() {
            return new UbicacionEntidad(this);
        }
    }
}
