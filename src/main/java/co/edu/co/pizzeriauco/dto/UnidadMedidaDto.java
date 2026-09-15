package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class UnidadMedidaDto {

    private UUID id;
    private String unidadMedida;
    private String tipoMedida;

    public UnidadMedidaDto() {
        setId(id);
        setUnidadMedida(UtilTexto.vacia);
        setTipoMedida(UtilTexto.vacia);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(unidadMedida);
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(tipoMedida);
    }
}
