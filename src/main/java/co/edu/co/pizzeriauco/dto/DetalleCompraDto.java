package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DetalleCompraDto {

    private UUID id;
    private ProductoInternoDto productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private BigDecimal precioCompra;
    private LocalDate fechaVencimiento;
    private CompraDto compra;
    private TipoMovimientoDto tipoMovimiento;

    public DetalleCompraDto() {
        setId(UtilId.VALOR_DEFECTO);
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setPrecioCompra(BigDecimal.ZERO);
        setFechaVencimiento(UtilFecha.FECHA_POR_DEFECTO);
        setCompra(new CompraDto());
        setTipoMovimiento(new TipoMovimientoDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
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

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precioCompra, BigDecimal.ZERO);
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
    }

    public CompraDto getCompra() {
        return compra;
    }

    public void setCompra(CompraDto compra) {
        this.compra = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(compra, new CompraDto());
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
    }
}
