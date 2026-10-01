package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoInternoEntidad {

    private UUID id;
    private String nombre;
    private boolean perecedero;
    //no es obligatoria, por eso es Integer y su valor por defecto es 0
    private Integer vidaUtil;
    private UnidadMedidaEntidad tipoMedida;
    //true = se puede usar en compras y recetas; false = desactivado (descontinuado), se conserva su historial
    private boolean activo;

    private ProductoInternoEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.perecedero = builder.perecedero;
        this.vidaUtil = builder.vidaUtil;
        this.tipoMedida = builder.tipoMedida;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public Integer getVidaUtil() {
        return vidaUtil;
    }

    public UnidadMedidaEntidad getTipoMedida() {
        return tipoMedida;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private boolean perecedero;
        private Integer vidaUtil;
        private UnidadMedidaEntidad tipoMedida;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            perecedero = false;
            vidaUtil = UtilNumero.cero;
            tipoMedida = new UnidadMedidaEntidad.Builder().build();
            //todo producto interno nace activo
            activo = true;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder perecedero(boolean perecedero) {
            this.perecedero = perecedero;
            return this;
        }

        public Builder vidaUtil(Integer vidaUtil) {
            this.vidaUtil = UtilNumero.obtenerValorDefecto(vidaUtil, UtilNumero.cero);
            return this;
        }

        public Builder tipoMedida(UnidadMedidaEntidad tipoMedida) {
            this.tipoMedida =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public ProductoInternoEntidad build() {
            return new ProductoInternoEntidad(this);
        }
    }
}
