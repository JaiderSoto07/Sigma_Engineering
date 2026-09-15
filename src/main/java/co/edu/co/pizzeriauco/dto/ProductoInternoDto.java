package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoDto {

    private UUID id;
    private String nombre;
    private String perecedero;
    private int vidaUtil;
    private UnidadMedidaDto tipoMedida;

    public ProductoInternoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setPerecedero(UtilTexto.vacia);
        setVidaUtil(0);
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

    public String getPerecedero() {
        return perecedero;
    }

    public void setPerecedero(String perecedero) {
        this.perecedero = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(perecedero);
    }

    public int getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(int vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public UnidadMedidaDto getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(UnidadMedidaDto tipoMedida) {
        this.tipoMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMedida, new UnidadMedidaDto());
    }
}
