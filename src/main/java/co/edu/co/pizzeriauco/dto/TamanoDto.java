package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class TamanoDto {

    private UUID id;
    private String tamano;

    public TamanoDto() {
        setId(id);
        setTamano(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(tamano);
    }
}
