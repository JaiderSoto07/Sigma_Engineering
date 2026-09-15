package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioDominio {

    private UUID id;
    private TipoMovimientoDominio tipoMovimiento;
    private OrigenDominio origen;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDominio unidadMedida;
    private LocalDate fechaMovimiento;

    private MovimientoInventarioDominio(Builder builder) {
        this.id = builder.id;
        this.tipoMovimiento = builder.tipoMovimiento;
        this.origen = builder.origen;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.fechaMovimiento = builder.fechaMovimiento;
    }

    public UUID getId() {
        return id;
    }

    public TipoMovimientoDominio getTipoMovimiento() {
        return tipoMovimiento;
    }

    public OrigenDominio getOrigen() {
        return origen;
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

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public static class Builder {

        private UUID id;
        private TipoMovimientoDominio tipoMovimiento;
        private OrigenDominio origen;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaDominio unidadMedida;
        private LocalDate fechaMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            tipoMovimiento = new TipoMovimientoDominio.Builder().build();
            origen = new OrigenDominio.Builder().build();
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaDominio.Builder().build();
            fechaMovimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder tipoMovimiento(
                TipoMovimientoDominio tipoMovimiento) {

            this.tipoMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoDominio.Builder().build()
                    );

            return this;
        }

        public Builder origen(OrigenDominio origen) {
            this.origen = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            origen,
                            new OrigenDominio.Builder().build()
                    );

            return this;
        }

        public Builder productoInterno(
                ProductoInternoDominio productoInterno) {

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

        public Builder unidadMedida(
                UnidadMedidaDominio unidadMedida) {

            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaDominio.Builder().build()
                    );

            return this;
        }

        public Builder fechaMovimiento(LocalDate fechaMovimiento) {
            this.fechaMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaMovimiento,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );

            return this;
        }

        public MovimientoInventarioDominio build() {
            return new MovimientoInventarioDominio(this);
        }
    }
}