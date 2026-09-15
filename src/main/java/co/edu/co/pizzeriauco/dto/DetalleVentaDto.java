package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleVentaDto {

    private UUID id;
    private VentaDto venta;
    private int cantidad;
    private ProductoDto producto;
    private BigDecimal precioProducto;
    private BigDecimal subtotal;

    public DetalleVentaDto() {
        setId(id);
        setVenta(new VentaDto());
        setCantidad(0);
        setProducto(new ProductoDto());
        setPrecioProducto(BigDecimal.ZERO);
        setSubtotal(BigDecimal.ZERO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public VentaDto getVenta() {
        return venta;
    }

    public void setVenta(VentaDto venta) {
        this.venta = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(venta, new VentaDto());
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = UtilNumero.menorQue(cantidad, 0) ? 0 : cantidad;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(producto, new ProductoDto());
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        var precioProductoSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(precioProducto, BigDecimal.ZERO);
        this.precioProducto = UtilNumero.menorQue(precioProductoSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : precioProductoSaneado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        var subtotalSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(subtotal, BigDecimal.ZERO);
        this.subtotal = UtilNumero.menorQue(subtotalSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : subtotalSaneado;
    }
}
