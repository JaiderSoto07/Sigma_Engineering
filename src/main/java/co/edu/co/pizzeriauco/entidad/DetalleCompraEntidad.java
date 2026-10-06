package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DetalleCompraEntidad {

    private UUID id;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private BigDecimal precioCompra;
    private LocalDate fechaVencimiento;
    private CompraEntidad compra;
    private TipoMovimientoEntidad tipoMovimiento;

    private DetalleCompraEntidad(Builder builder) {
        this.id = builder.id;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.precioCompra = builder.precioCompra;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.compra = builder.compra;
        this.tipoMovimiento = builder.tipoMovimiento;
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

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public CompraEntidad getCompra() {
        return compra;
    }

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private BigDecimal precioCompra;
        private LocalDate fechaVencimiento;
        private CompraEntidad compra;
        private TipoMovimientoEntidad tipoMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            precioCompra = BigDecimal.ZERO;
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            compra = new CompraEntidad.Builder().build();
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoInterno(ProductoInternoEntidad productoInterno) {
            this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoEntidad.Builder().build());
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        public Builder unidadMedida(UnidadMedidaEntidad unidadMedida) {
            this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build());
            return this;
        }

        public Builder precioCompra(BigDecimal precioCompra) {
            this.precioCompra = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precioCompra, BigDecimal.ZERO);
            return this;
        }

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
            return this;
        }

        public Builder compra(CompraEntidad compra) {
            this.compra = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            compra,
                            new CompraEntidad.Builder().build());
            return this;
        }

        public Builder tipoMovimiento(TipoMovimientoEntidad tipoMovimiento) {
            this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoEntidad.Builder().build());
            return this;
        }

        public DetalleCompraEntidad build() {
            return new DetalleCompraEntidad(this);
        }
    }
}
