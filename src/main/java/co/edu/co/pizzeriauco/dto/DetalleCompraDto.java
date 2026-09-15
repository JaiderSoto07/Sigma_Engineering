package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

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

    public DetalleCompraDto() {
        setId(id);
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setPrecioCompra(BigDecimal.ZERO);
        setFechaVencimiento(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setCompra(new CompraDto());
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
        var cantidadSaneada = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(cantidad, BigDecimal.ZERO);
        this.cantidad = UtilNumero.menorQue(cantidadSaneada, BigDecimal.ZERO) ? BigDecimal.ZERO : cantidadSaneada;
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
        var precioCompraSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(precioCompra, BigDecimal.ZERO);
        this.precioCompra = UtilNumero.menorQue(precioCompraSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : precioCompraSaneado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaVencimiento, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public CompraDto getCompra() {
        return compra;
    }

    public void setCompra(CompraDto compra) {
        this.compra = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(compra, new CompraDto());
    }
}
