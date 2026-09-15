package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteDto {

    private UUID id;
    private LocalDate fechaIngreso;
    private LocalDate fechaVencimiento;
    private boolean disponible;
    private ProductoInternoDto productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedidaInventario;
    private int numeroLote;

    public LoteDto() {
        setId(id);
        setFechaIngreso(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setFechaVencimiento(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setDisponible(false);
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedidaInventario(new UnidadMedidaDto());
        setNumeroLote(0);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaIngreso, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaVencimiento, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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

    public UnidadMedidaDto getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public void setUnidadMedidaInventario(UnidadMedidaDto unidadMedidaInventario) {
        this.unidadMedidaInventario = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedidaInventario, new UnidadMedidaDto());
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = UtilNumero.menorQue(numeroLote, 0) ? 0 : numeroLote;
    }
}
