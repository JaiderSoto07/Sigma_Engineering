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
    //se calcula solo: cantidad por el precio del producto en el momento de la venta
    private BigDecimal subtotal;

    private DetalleVentaDominio(Builder builder) {
        this.id = builder.id;
        this.venta = builder.venta;
        this.cantidad = builder.cantidad;
        this.producto = builder.producto;
        this.precioProducto = builder.precioProducto;
        this.subtotal = precioProducto.multiply(BigDecimal.valueOf(cantidad));
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

        public Builder() {
            id = UtilId.valorDefecto(id);
            venta = new VentaDominio.Builder().build();
            cantidad = 0;
            producto = new ProductoDominio.Builder().build();
            precioProducto = BigDecimal.ZERO;
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
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, UtilNumero.cero);
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
            this.precioProducto = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precioProducto, BigDecimal.ZERO);
            return this;
        }

        public DetalleVentaDominio build() {
            return new DetalleVentaDominio(this);
        }
    }
}