package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioDominio {

    private UUID id;
    private ClaseMovimientoDominio claseMovimiento;
    private TipoMovimientoDominio tipoMovimiento;
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;
    private LoteDominio lote;

    private MovimientoInventarioDominio(Builder builder) {
        this.id = builder.id;
        this.claseMovimiento = builder.claseMovimiento;
        this.tipoMovimiento = builder.tipoMovimiento;
        this.cantidad = builder.cantidad;
        this.fechaMovimiento = builder.fechaMovimiento;
        this.lote = builder.lote;
    }

    public UUID getId() {
        return id;
    }

    public ClaseMovimientoDominio getClaseMovimiento() {
        return claseMovimiento;
    }

    public TipoMovimientoDominio getTipoMovimiento() {
        return tipoMovimiento;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public LoteDominio getLote() {
        return lote;
    }

    public static class Builder {

        private UUID id;
        private ClaseMovimientoDominio claseMovimiento;
        private TipoMovimientoDominio tipoMovimiento;
        private BigDecimal cantidad;
        private LocalDate fechaMovimiento;
        private LoteDominio lote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            claseMovimiento = new ClaseMovimientoDominio.Builder().build();
            tipoMovimiento = new TipoMovimientoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            fechaMovimiento = UtilFecha.FECHA_POR_DEFECTO;
            lote = new LoteDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder claseMovimiento(ClaseMovimientoDominio claseMovimiento) {

            this.claseMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            claseMovimiento,
                            new ClaseMovimientoDominio.Builder().build());

            return this;
        }

        public Builder tipoMovimiento(TipoMovimientoDominio tipoMovimiento) {
            this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoDominio.Builder().build());

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

        public Builder lote(LoteDominio lote) {
            this.lote = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            lote,
                            new LoteDominio.Builder().build());
            return this;
        }

        public MovimientoInventarioDominio build() {
            return new MovimientoInventarioDominio(this);
        }
    }
}