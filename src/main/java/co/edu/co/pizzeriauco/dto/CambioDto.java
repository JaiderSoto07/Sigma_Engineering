package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CambioDto {

    private UUID id;
    private ProductoInternoDto productoCambio;
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private LocalDate fechaVencimiento;
    private LocalDate fechaCambio;
    //codigo de la operacion: lo comparte con su movimiento de entrada
    private TipoMovimientoDto tipoMovimiento;

    public CambioDto() {
        setId(UtilId.VALOR_DEFECTO);
        setProductoCambio(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setFechaVencimiento(UtilFecha.FECHA_POR_DEFECTO);
        setFechaCambio(UtilFecha.FECHA_POR_DEFECTO);
        setTipoMovimiento(new TipoMovimientoDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public ProductoInternoDto getProductoCambio() {
        return productoCambio;
    }

    public void setProductoCambio(ProductoInternoDto productoCambio) {
        this.productoCambio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(productoCambio, new ProductoInternoDto());
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
    }

    public UnidadMedidaDto getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDto unidadMedida) {
        this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedida, new UnidadMedidaDto());
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = UtilFecha.valorDefecto(fechaCambio);
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
    }
}
