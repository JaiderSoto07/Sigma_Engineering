package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioDto {

    private UUID id;
    private TipoMovimientoDto tipoMovimiento;
    private OrigenDto origen;
    private ProductoInternoDto productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private LocalDate fechaMovimiento;

    public MovimientoInventarioDto() {
        setId(id);
        setTipoMovimiento(new TipoMovimientoDto());
        setOrigen(new OrigenDto());
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setFechaMovimiento(UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
    }

    public OrigenDto getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenDto origen) {
        this.origen = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(origen, new OrigenDto());
    }

    public ProductoInternoDto getProductoInterno() {
        return productoInterno;
    }

    public void setProductoInterno(ProductoInternoDto productoInterno) {
        this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(productoInterno, new ProductoInternoDto());
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        var cantidadSaneada = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(cantidad, BigDecimal.ZERO);
        this.cantidad = UtilNumero.menorQue(cantidadSaneada, BigDecimal.ZERO) ? BigDecimal.ZERO : cantidadSaneada;
    }

    public UnidadMedidaDto getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDto unidadMedida) {
        this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedida, new UnidadMedidaDto());
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaMovimiento, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }
}
