package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class SalidaLoteDominio {

    private UUID id;
    private LoteDominio lote;
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;

    private SalidaLoteDominio(Builder builder) {
        this.id = builder.id;
        this.lote = builder.lote;
        this.cantidad = builder.cantidad;
        this.fechaMovimiento = builder.fechaMovimiento;
    }

    public UUID getId() {
        return id;
    }

    public LoteDominio getLote() {
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
        private LoteDominio lote;
        private BigDecimal cantidad;
        private LocalDate fechaMovimiento;

        public Builder() {
            id = UtilId.valorDefecto(id);
            lote = new LoteDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            fechaMovimiento = UtilFecha.FECHA_POR_DEFECTO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder lote(LoteDominio lote) {
            this.lote = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            lote,
                            new LoteDominio.Builder().build());
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

        public SalidaLoteDominio build() {
            return new SalidaLoteDominio(this);
        }
    }
}
