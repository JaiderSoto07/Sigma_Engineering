package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class AdministradorDto {

    private UUID id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String celular;
    private PizzeriaDto pizzeria;
    private boolean activo;

    public AdministradorDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setApellido(UtilTexto.vacia);
        setCedula(UtilTexto.vacia);
        setCelular(UtilTexto.vacia);
        setPizzeria(new PizzeriaDto());
        setActivo(false);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(apellido);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(cedula);
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(celular);
    }

    public PizzeriaDto getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(PizzeriaDto pizzeria) {
        this.pizzeria = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pizzeria, new PizzeriaDto());
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
