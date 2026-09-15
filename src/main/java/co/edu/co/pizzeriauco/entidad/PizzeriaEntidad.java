package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.time.LocalTime;
import java.util.UUID;

public class PizzeriaEntidad {

    private UUID id;
    private String nombre;
    private String nit;
    private String correo;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private String telefono;
    private UbicacionEntidad ubicacion;

    private PizzeriaEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.nit = builder.nit;
        this.correo = builder.correo;
        this.horaApertura = builder.horaApertura;
        this.horaCierre = builder.horaCierre;
        this.telefono = builder.telefono;
        this.ubicacion = builder.ubicacion;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public String getTelefono() {
        return telefono;
    }

    public UbicacionEntidad getUbicacion() {
        return ubicacion;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private String nit;
        private String correo;
        private LocalTime horaApertura;
        private LocalTime horaCierre;
        private String telefono;
        private UbicacionEntidad ubicacion;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            nit = UtilTexto.vacia;
            correo = UtilTexto.vacia;
            horaApertura = LocalTime.MIN;
            horaCierre = LocalTime.MIN;
            telefono = UtilTexto.vacia;
            ubicacion = new UbicacionEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder nit(String nit) {
            this.nit = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(nit);
            return this;
        }

        public Builder correo(String correo) {
            this.correo = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(correo);
            return this;
        }

        public Builder horaApertura(LocalTime horaApertura) {
            this.horaApertura = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            horaApertura,
                            LocalTime.MIN
                    );
            return this;
        }

        public Builder horaCierre(LocalTime horaCierre) {
            this.horaCierre = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            horaCierre,
                            LocalTime.MIN
                    );
            return this;
        }

        public Builder telefono(String telefono) {
            this.telefono = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(telefono);
            return this;
        }

        public Builder ubicacion(UbicacionEntidad ubicacion) {
            this.ubicacion = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            ubicacion,
                            new UbicacionEntidad.Builder().build()
                    );
            return this;
        }

        public PizzeriaEntidad build() {
            return new PizzeriaEntidad(this);
        }
    }
}
