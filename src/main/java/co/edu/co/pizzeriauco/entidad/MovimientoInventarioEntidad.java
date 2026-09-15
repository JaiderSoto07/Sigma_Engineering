package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioEntidad {

    private UUID id;
    private TipoMovimientoEntidad tipoMovimiento;
    private OrigenEntidad origen;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private LocalDate fechaMovimiento;

    private MovimientoInventarioEntidad(Builder builder) {
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

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public OrigenEntidad getOrigen() {
        return origen;
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

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public static class Builder {

        private UUID id;
        private TipoMovimientoEntidad tipoMovimiento;
        private OrigenEntidad origen;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private LocalDate fechaMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
            origen = new OrigenEntidad.Builder().build();
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            fechaMovimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder tipoMovimiento(
                TipoMovimientoEntidad tipoMovimiento) {

            this.tipoMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoEntidad.Builder().build()
                    );

            return this;
        }

        public Builder origen(OrigenEntidad origen) {
            this.origen = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            origen,
                            new OrigenEntidad.Builder().build()
                    );

            return this;
        }

        public Builder productoInterno(
                ProductoInternoEntidad productoInterno) {

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

        public Builder unidadMedida(
                UnidadMedidaEntidad unidadMedida) {

            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build()
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

        public MovimientoInventarioEntidad build() {
            return new MovimientoInventarioEntidad(this);
        }
    }
}
