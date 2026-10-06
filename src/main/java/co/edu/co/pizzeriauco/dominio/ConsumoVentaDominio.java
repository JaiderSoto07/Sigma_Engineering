package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsumoVentaDominio {

    private UUID id;
    private DetalleVentaDominio detalleVenta;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDominio unidadMedida;
    private TipoMovimientoDominio tipoMovimiento;

    private ConsumoVentaDominio(Builder builder) {
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

    public DetalleVentaDominio getDetalleVenta() {
        return detalleVenta;
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

    public TipoMovimientoDominio getTipoMovimiento() {
        return tipoMovimiento;
    }

    public static class Builder {

        private UUID id;
        private DetalleVentaDominio detalleVenta;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaDominio unidadMedida;
        private TipoMovimientoDominio tipoMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            detalleVenta = new DetalleVentaDominio.Builder().build();
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaDominio.Builder().build();
            tipoMovimiento = new TipoMovimientoDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder detalleVenta(DetalleVentaDominio detalleVenta) {
            this.detalleVenta = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            detalleVenta,
                            new DetalleVentaDominio.Builder().build());
            return this;
        }

        public Builder productoInterno(ProductoInternoDominio productoInterno) {
            this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build());
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        public Builder unidadMedida(UnidadMedidaDominio unidadMedida) {
            this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaDominio.Builder().build());
            return this;
        }

        public Builder tipoMovimiento(TipoMovimientoDominio tipoMovimiento) {
            this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoDominio.Builder().build());
            return this;
        }

        public ConsumoVentaDominio build() {
            return new ConsumoVentaDominio(this);
        }
    }
}
