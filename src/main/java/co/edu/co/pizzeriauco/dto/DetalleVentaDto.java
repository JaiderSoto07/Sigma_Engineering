package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

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
        setId(UtilId.VALOR_DEFECTO);
        setVenta(new VentaDto());
        setCantidad(UtilNumero.cero);
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
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, UtilNumero.cero);
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
        this.precioProducto = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precioProducto, BigDecimal.ZERO);
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(subtotal, BigDecimal.ZERO);
    }
}
