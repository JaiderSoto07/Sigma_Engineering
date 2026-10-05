package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoDto {

    private UUID id;
    private String nombre;
    private boolean perecedero;
    private Integer vidaUtil;
    private UnidadMedidaDto tipoMedida;
    //true = se puede usar en compras y recetas; false = desactivado (descontinuado), se conserva su historial
    private boolean activo;

    public ProductoInternoDto() {
        setId(UtilId.VALOR_DEFECTO);
        setNombre(UtilTexto.vacia);
        setPerecedero(false);
        setVidaUtil(UtilNumero.cero);
        setTipoMedida(new UnidadMedidaDto());
        setActivo(true);
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
