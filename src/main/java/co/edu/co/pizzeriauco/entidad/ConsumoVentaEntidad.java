package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

//cada renglon de venta se abre en los insumos de su receta: un consumo por insumo, cada uno con su codigo
public class ConsumoVentaEntidad {

    private UUID id;
    private DetalleVentaEntidad detalleVenta;
    private ProductoInternoEntidad productoInterno;
    //cantidad vendida por la cantidad de la receta, en la unidad de la receta
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    //codigo de la operacion: lo comparte con sus movimientos de salida
    private TipoMovimientoEntidad tipoMovimiento;

    private ConsumoVentaEntidad(Builder builder) {
        this.id = builder.id;
        this.detalleVenta = builder.detalleVenta;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.tipoMovimiento = builder.tipoMovimiento;
    }

    public UUID getId() {
        return id;
    }

    public DetalleVentaEntidad getDetalleVenta() {
        return detalleVenta;
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

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public static class Builder {

        private UUID id;
        private DetalleVentaEntidad detalleVenta;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private TipoMovimientoEntidad tipoMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            detalleVenta = new DetalleVentaEntidad.Builder().build();
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder detalleVenta(DetalleVentaEntidad detalleVenta) {
            this.detalleVenta = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            detalleVenta,
                            new DetalleVentaEntidad.Builder().build()
                    );
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
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
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

        public Builder tipoMovimiento(TipoMovimientoEntidad tipoMovimiento) {
            this.tipoMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoEntidad.Builder().build()
                    );
            return this;
        }

        public ConsumoVentaEntidad build() {
            return new ConsumoVentaEntidad(this);
        }
    }
}
