package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class HistoricoPrecioDto {

    private UUID id;
    private ProductoDto producto;
    private BigDecimal precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public HistoricoPrecioDto() {
        setId(id);
        setProducto(new ProductoDto());
        setPrecio(BigDecimal.ZERO);
        setFechaInicio(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setFechaFin(UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(producto, new ProductoDto());
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        var precioSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(precio, BigDecimal.ZERO);
        this.precio = UtilNumero.menorQue(precioSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : precioSaneado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaInicio, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaFin, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }
}
