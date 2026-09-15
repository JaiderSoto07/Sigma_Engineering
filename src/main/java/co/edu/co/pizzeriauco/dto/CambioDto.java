package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

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

    public CambioDto() {
        setId(id);
        setProductoCambio(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setFechaVencimiento(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setFechaCambio(UtilFecha.ValorPorDefecto(1, 1, 1000));
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
        var cantidadSaneada = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(cantidad, BigDecimal.ZERO);
        this.cantidad = UtilNumero.menorQue(cantidadSaneada, BigDecimal.ZERO) ? BigDecimal.ZERO : cantidadSaneada;
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
        this.fechaVencimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaVencimiento, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaCambio, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }
}
