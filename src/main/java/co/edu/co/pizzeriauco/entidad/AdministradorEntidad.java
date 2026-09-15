package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class AdministradorEntidad {

    private UUID id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String celular;
    private PizzeriaEntidad pizzeria;
    private boolean activo;

    private AdministradorEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.apellido = builder.apellido;
        this.cedula = builder.cedula;
        this.celular = builder.celular;
        this.pizzeria = builder.pizzeria;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCelular() {
        return celular;
    }

    public PizzeriaEntidad getPizzeria() {
        return pizzeria;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private String apellido;
        private String cedula;
        private String celular;
        private PizzeriaEntidad pizzeria;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            apellido = UtilTexto.vacia;
            cedula = UtilTexto.vacia;
            celular = UtilTexto.vacia;
            pizzeria = new PizzeriaEntidad.Builder().build();
            activo = false;
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

        public Builder apellido(String apellido) {
            this.apellido = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(apellido);
            return this;
        }

        public Builder cedula(String cedula) {
            this.cedula = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(cedula);
            return this;
        }

        public Builder celular(String celular) {
            this.celular = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(celular);
            return this;
        }

        public Builder pizzeria(PizzeriaEntidad pizzeria) {
            this.pizzeria = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            pizzeria,
                            new PizzeriaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public AdministradorEntidad build() {
            return new AdministradorEntidad(this);
        }
    }
}
