package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class BarrioDto {

    private UUID id;
    private String nombre;
    private CiudadDto ciudad;

    public BarrioDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setCiudad(new CiudadDto());
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

    public CiudadDto getCiudad() {
        return ciudad;
    }

    public void setCiudad(CiudadDto ciudad) {
        this.ciudad = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(ciudad, new CiudadDto());
    }
}
