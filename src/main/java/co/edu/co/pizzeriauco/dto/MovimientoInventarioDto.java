package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioDto {

    private UUID id;
    private ClaseMovimientoDto claseMovimiento;
    //codigo de la operacion que causo el movimiento: lo comparte con su renglon de compra, consumo de venta o cambio
    //(la categoria de origen se sabe por el codigo)
    private TipoMovimientoDto tipoMovimiento;
    private BigDecimal cantidad;
    private LocalDate fechaMovimiento;
    //entrada: el lote que se crea con esa compra o cambio; salida: el lote del que se saca
    private LoteDto lote;

    public MovimientoInventarioDto() {
        setId(UtilId.VALOR_DEFECTO);
        setClaseMovimiento(new ClaseMovimientoDto());
        setTipoMovimiento(new TipoMovimientoDto());
        setCantidad(BigDecimal.ZERO);
        setFechaMovimiento(UtilFecha.FECHA_POR_DEFECTO);
        setLote(new LoteDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public ClaseMovimientoDto getClaseMovimiento() {
        return claseMovimiento;
    }

    public void setClaseMovimiento(ClaseMovimientoDto claseMovimiento) {
        this.claseMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(claseMovimiento, new ClaseMovimientoDto());
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
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

    public LoteDto getLote() {
        return lote;
    }

    public void setLote(LoteDto lote) {
        this.lote = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(lote, new LoteDto());
    }
}
