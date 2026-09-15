package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleVentaDominio {

    private UUID id;
    private VentaDominio venta;
    private int cantidad;
    private ProductoDominio producto;
    private BigDecimal precioProducto;
    private BigDecimal subtotal;

    private DetalleVentaDominio(Builder builder) {
        this.id = builder.id;
        this.venta = builder.venta;
        this.cantidad = builder.cantidad;
        this.producto = builder.producto;
        this.precioProducto = builder.precioProducto;
        this.subtotal = builder.subtotal;
    }

    public UUID getId() {
        return id;
    }

    public VentaDominio getVenta() {
        return venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ProductoDominio getProducto() {
        return producto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public static class Builder {

        private UUID id;
        private VentaDominio venta;
        private int cantidad;
        private ProductoDominio producto;
        private BigDecimal precioProducto;
        private BigDecimal subtotal;

        public Builder() {
            id = UtilId.valorDefecto(id);
            venta = new VentaDominio.Builder().build();
            cantidad = 0;
            producto = new ProductoDominio.Builder().build();
            precioProducto = BigDecimal.ZERO;
            subtotal = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder venta(VentaDominio venta) {
            this.venta = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            venta,
                            new VentaDominio.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(int cantidad) {
            this.cantidad = UtilNumero.menorQue(cantidad, 0) ? 0 : cantidad;
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

        public Builder precioProducto(BigDecimal precioProducto) {
            var precioProductoSaneado = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            precioProducto,
                            BigDecimal.ZERO
                    );
            this.precioProducto = UtilNumero.menorQue(precioProductoSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : precioProductoSaneado;
            return this;
        }

        public Builder subtotal(BigDecimal subtotal) {
            var subtotalSaneado = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            subtotal,
                            BigDecimal.ZERO
                    );
            this.subtotal = UtilNumero.menorQue(subtotalSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : subtotalSaneado;
            return this;
        }

        public DetalleVentaDominio build() {
            return new DetalleVentaDominio(this);
        }
    }
}