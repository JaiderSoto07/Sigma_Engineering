package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class ConsumoVentaDto {

    private UUID id;
    private DetalleVentaDto detalleVenta;
    private ProductoInternoDto productoInterno;
    //cantidad vendida por la cantidad de la receta, en la unidad de la receta
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    //codigo de la operacion: lo comparte con sus movimientos de salida
    private TipoMovimientoDto tipoMovimiento;

    public ConsumoVentaDto() {
        setId(id);
        setDetalleVenta(new DetalleVentaDto());
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setTipoMovimiento(new TipoMovimientoDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public DetalleVentaDto getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(DetalleVentaDto detalleVenta) {
        this.detalleVenta = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(detalleVenta, new DetalleVentaDto());
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
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
    }

    public UnidadMedidaDto getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDto unidadMedida) {
        this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedida, new UnidadMedidaDto());
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
    }
}
