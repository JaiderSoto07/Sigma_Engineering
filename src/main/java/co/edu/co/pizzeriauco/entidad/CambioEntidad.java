package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CambioEntidad {

    private UUID id;
    private ProductoInternoEntidad productoCambio;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private LocalDate fechaVencimiento;
    private LocalDate fechaCambio;
    //codigo de la operacion: lo comparte con su movimiento de entrada
    private TipoMovimientoEntidad tipoMovimiento;

    private CambioEntidad(Builder builder) {
        this.id = builder.id;
        this.productoCambio = builder.productoCambio;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.fechaCambio = builder.fechaCambio;
        this.tipoMovimiento = builder.tipoMovimiento;
    }

    public UUID getId() {
        return id;
    }

    public ProductoInternoEntidad getProductoCambio() {
        return productoCambio;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoEntidad productoCambio;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private LocalDate fechaVencimiento;
        private LocalDate fechaCambio;
        private TipoMovimientoEntidad tipoMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoCambio = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            fechaCambio = UtilFecha.FECHA_POR_DEFECTO;
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoCambio(ProductoInternoEntidad productoCambio) {
            this.productoCambio = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoCambio,
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

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
            return this;
        }

        public Builder fechaCambio(LocalDate fechaCambio) {
            this.fechaCambio = UtilFecha.valorDefecto(fechaCambio);
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

        public CambioEntidad build() {
            return new CambioEntidad(this);
        }
    }
}
