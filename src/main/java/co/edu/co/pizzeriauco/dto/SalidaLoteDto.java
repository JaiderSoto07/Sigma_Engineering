package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class SalidaLoteDto {

    private UUID id;
    //un lote se saca una sola vez y completo
    private LoteDto lote;
    //el saldo que tenia el lote justo antes de sacarlo (unidad del lote)
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;

    public SalidaLoteDto() {
        setId(UtilId.VALOR_DEFECTO);
        setLote(new LoteDto());
        setCantidad(BigDecimal.ZERO);
        setFechaMovimiento(UtilFecha.FECHA_POR_DEFECTO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public LoteDto getLote() {
        return lote;
    }

    public void setLote(LoteDto lote) {
        this.lote = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(lote, new LoteDto());
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = UtilFecha.valorDefecto(fechaMovimiento);
    }
}
