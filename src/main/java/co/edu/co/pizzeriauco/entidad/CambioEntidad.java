package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

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

    private CambioEntidad(Builder builder) {
        this.id = builder.id;
        this.productoCambio = builder.productoCambio;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.fechaCambio = builder.fechaCambio;
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

    public static class Builder {

        private UUID id;
        private ProductoInternoEntidad productoCambio;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private LocalDate fechaVencimiento;
        private LocalDate fechaCambio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoCambio = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            fechaVencimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
            fechaCambio = UtilFecha.ValorPorDefecto(1, 1, 1000);
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

        public Builder unidadMedida(UnidadMedidaEntidad unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaVencimiento,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public Builder fechaCambio(LocalDate fechaCambio) {
            this.fechaCambio = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaCambio,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public CambioEntidad build() {
            return new CambioEntidad(this);
        }
    }
}
