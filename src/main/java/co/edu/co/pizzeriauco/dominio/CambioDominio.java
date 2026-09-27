package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CambioDominio {

    private UUID id;
    private ProductoInternoDominio productoCambio;
    private BigDecimal cantidad;
    private UnidadMedidaDominio unidadMedida;
    private LocalDate fechaVencimiento;
    private LocalDate fechaCambio;

    private CambioDominio(Builder builder) {
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

    public ProductoInternoDominio getProductoCambio() {
        return productoCambio;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaDominio getUnidadMedida() {
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
        private ProductoInternoDominio productoCambio;
        private BigDecimal cantidad;
        private UnidadMedidaDominio unidadMedida;
        private LocalDate fechaVencimiento;
        private LocalDate fechaCambio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoCambio = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaDominio.Builder().build();
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            fechaCambio = UtilFecha.FECHA_POR_DEFECTO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoCambio(ProductoInternoDominio productoCambio) {
            this.productoCambio = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoCambio,
                            new ProductoInternoDominio.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        public Builder unidadMedida(UnidadMedidaDominio unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaDominio.Builder().build()
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

        public CambioDominio build() {
            return new CambioDominio(this);
        }
    }
}