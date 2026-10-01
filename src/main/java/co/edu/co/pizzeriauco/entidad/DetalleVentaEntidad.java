package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleVentaEntidad {

    private UUID id;
    private VentaEntidad venta;
    private int cantidad;
    private ProductoEntidad producto;
    private BigDecimal precioProducto;
    //se calcula solo: cantidad por el precio del producto en el momento de la venta
    private BigDecimal subtotal;

    private DetalleVentaEntidad(Builder builder) {
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

    public VentaEntidad getVenta() {
        return venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ProductoEntidad getProducto() {
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
        private VentaEntidad venta;
        private int cantidad;
        private ProductoEntidad producto;
        private BigDecimal precioProducto;

        public Builder() {
            id = UtilId.valorDefecto(id);
            venta = new VentaEntidad.Builder().build();
            cantidad = 0;
            producto = new ProductoEntidad.Builder().build();
            precioProducto = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder venta(VentaEntidad venta) {
            this.venta = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            venta,
                            new VentaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(int cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, UtilNumero.cero);
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

        public Builder precioProducto(BigDecimal precioProducto) {
            this.precioProducto = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precioProducto, BigDecimal.ZERO);
            return this;
        }

        public DetalleVentaEntidad build() {
            return new DetalleVentaEntidad(this);
        }
    }
}
