package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioEntidad {

    private UUID id;
    private ClaseMovimientoEntidad claseMovimiento;
    //codigo de la operacion que causo el movimiento: lo comparte con su renglon de compra, consumo de venta o cambio
    //(la categoria de origen se sabe por el codigo)
    private TipoMovimientoEntidad tipoMovimiento;
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;
    //entrada: el lote que se crea con esa compra o cambio; salida: el lote del que se saca
    private LoteEntidad lote;

    private MovimientoInventarioEntidad(Builder builder) {
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

    public ClaseMovimientoEntidad getClaseMovimiento() {
        return claseMovimiento;
    }

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public LoteEntidad getLote() {
        return lote;
    }

    public static class Builder {

        private UUID id;
        private ClaseMovimientoEntidad claseMovimiento;
        private TipoMovimientoEntidad tipoMovimiento;
        private BigDecimal cantidad;
        private LocalDate fechaMovimiento;
        private LoteEntidad lote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            claseMovimiento = new ClaseMovimientoEntidad.Builder().build();
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            fechaMovimiento = UtilFecha.FECHA_POR_DEFECTO;
            lote = new LoteEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder claseMovimiento(
                ClaseMovimientoEntidad claseMovimiento) {

            this.claseMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            claseMovimiento,
                            new ClaseMovimientoEntidad.Builder().build()
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

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);

            return this;
        }

        public Builder fechaMovimiento(LocalDate fechaMovimiento) {
            this.fechaMovimiento = UtilFecha.valorDefecto(fechaMovimiento);

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

        public MovimientoInventarioEntidad build() {
            return new MovimientoInventarioEntidad(this);
        }
    }
}