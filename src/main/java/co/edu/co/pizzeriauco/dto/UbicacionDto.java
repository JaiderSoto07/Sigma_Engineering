package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class UbicacionDto {

    private UUID id;
    private String direccion;
    private BarrioDto barrio;

    public UbicacionDto() {
        setId(id);
        setDireccion(UtilTexto.vacia);
        setBarrio(new BarrioDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(direccion);
    }

    public BarrioDto getBarrio() {
        return barrio;
    }

    public void setBarrio(BarrioDto barrio) {
        this.barrio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(barrio, new BarrioDto());
    }
}
