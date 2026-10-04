package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

//lo que la persona saca por la alerta de vencimiento del lote; no genera codigo ni movimiento
public class SalidaLoteEntidad {

    private UUID id;
    //un lote se saca una sola vez y completo
    private LoteEntidad lote;
    //el saldo que tenia el lote justo antes de sacarlo (unidad del lote)
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;

    private SalidaLoteEntidad(Builder builder) {
        this.id = builder.id;
        this.lote = builder.lote;
        this.cantidad = builder.cantidad;
        this.fechaMovimiento = builder.fechaMovimiento;
    }

    public UUID getId() {
        return id;
    }

    public LoteEntidad getLote() {
        return lote;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public static class Builder {

        private UUID id;
        private LoteEntidad lote;
        private BigDecimal cantidad;
        private LocalDate fechaMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            lote = new LoteEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            fechaMovimiento = UtilFecha.FECHA_POR_DEFECTO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder lote(LoteEntidad lote) {
            this.lote = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            lote,
                            new LoteEntidad.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        public Builder fechaMovimiento(LocalDate fechaMovimiento) {
            this.fechaMovimiento = UtilFecha.valorDefecto(fechaMovimiento);
            return this;
        }

        public SalidaLoteEntidad build() {
            return new SalidaLoteEntidad(this);
        }
    }
}
