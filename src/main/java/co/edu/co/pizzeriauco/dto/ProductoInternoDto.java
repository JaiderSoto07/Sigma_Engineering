package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoDto {

    private UUID id;
    private String nombre;
    private boolean perecedero;
    private Integer vidaUtil;
    private UnidadMedidaDto tipoMedida;

    public ProductoInternoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setPerecedero(false);
        setVidaUtil(UtilNumero.cero);
        setTipoMedida(new UnidadMedidaDto());
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

    public boolean isPerecedero() {
        return perecedero;
    }

    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(Integer vidaUtil) {
        this.vidaUtil = UtilNumero.obtenerValorDefecto(vidaUtil, UtilNumero.cero);
    }

    public UnidadMedidaDto getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(UnidadMedidaDto tipoMedida) {
        this.tipoMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMedida, new UnidadMedidaDto());
    }
}
