package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleRecetaDominio {

    private UUID id;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDominio unidadMedida;
    private ProductoDominio producto;

    private DetalleRecetaDominio(Builder builder) {
        this.id = builder.id;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.producto = builder.producto;
    }

    public UUID getId() {
        return id;
    }

    public ProductoInternoDominio getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaDominio getUnidadMedida() {
        return unidadMedida;
    }

    public ProductoDominio getProducto() {
        return producto;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaDominio unidadMedida;
        private ProductoDominio producto;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaDominio.Builder().build();
            producto = new ProductoDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoInterno(ProductoInternoDominio productoInterno) {
            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build()
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

        public Builder unidadMedida(UnidadMedidaDominio unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaDominio.Builder().build()
                    );
            return this;
        }

        public Builder producto(ProductoDominio producto) {
            this.producto = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            producto,
                            new ProductoDominio.Builder().build()
                    );
            return this;
        }

        public DetalleRecetaDominio build() {
            return new DetalleRecetaDominio(this);
        }
    }
}