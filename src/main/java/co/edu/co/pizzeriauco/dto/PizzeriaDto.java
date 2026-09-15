package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.time.LocalTime;
import java.util.UUID;

public class PizzeriaDto {

    private UUID id;
    private String nombre;
    private String nit;
    private String correo;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private String telefono;
    private UbicacionDto ubicacion;

    public PizzeriaDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setNit(UtilTexto.vacia);
        setCorreo(UtilTexto.vacia);
        setHoraApertura(LocalTime.MIN);
        setHoraCierre(LocalTime.MIN);
        setTelefono(UtilTexto.vacia);
        setUbicacion(new UbicacionDto());
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nit);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(correo);
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(horaApertura, LocalTime.MIN);
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(horaCierre, LocalTime.MIN);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(telefono);
    }

    public UbicacionDto getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionDto ubicacion) {
        this.ubicacion = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(ubicacion, new UbicacionDto());
    }
}
