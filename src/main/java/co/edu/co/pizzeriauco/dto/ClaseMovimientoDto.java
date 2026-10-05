package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.util.UUID;

public class ClaseMovimientoDto {

    private UUID id;
    private String nombre;

    public ClaseMovimientoDto() {
        setId(UtilId.VALOR_DEFECTO);
        setNombre(UtilTexto.vacia);
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
        this.nombre = UtilTexto.getUtilTexto().primeraLetraMayuscula(nombre);
    }
}
