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
    //se calcula solo: cantidad por el precio del producto en el momento de la venta
    private BigDecimal subtotal;

    public DetalleVentaDto() {
        setId(UtilId.VALOR_DEFECTO);
        setVenta(new VentaDto());
        setCantidad(0);
        setProducto(new ProductoDto());
        setPrecioProducto(BigDecimal.ZERO);
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
        calcularSubtotal();
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
        calcularSubtotal();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    //cada vez que cambia la cantidad o el precio se vuelve a calcular el subtotal
    private void calcularSubtotal() {
        var precio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(precioProducto, BigDecimal.ZERO);
        this.subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
    }
}
