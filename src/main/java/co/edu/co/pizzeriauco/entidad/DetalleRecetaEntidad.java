package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleRecetaEntidad {

    private UUID id;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private ProductoEntidad producto;

    private DetalleRecetaEntidad(Builder builder) {
        this.id = builder.id;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.producto = builder.producto;
    }

    public UUID getId() {
        return id;
    }

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public ProductoEntidad getProducto() {
        return producto;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private ProductoEntidad producto;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            producto = new ProductoEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoInterno(ProductoInternoEntidad productoInterno) {
            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoEntidad.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            var cantidadSaneada = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            cantidad,
                            BigDecimal.ZERO
                    );
            this.cantidad = UtilNumero.menorQue(cantidadSaneada, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : cantidadSaneada;
            return this;
        }

        public Builder unidadMedida(UnidadMedidaEntidad unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder producto(ProductoEntidad producto) {
            this.producto = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            producto,
                            new ProductoEntidad.Builder().build()
                    );
            return this;
        }

        public DetalleRecetaEntidad build() {
            return new DetalleRecetaEntidad(this);
        }
    }
}
