package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteDto {

    private UUID id;
    private LocalDate fechaVencimiento;
    private boolean disponible;
    private ProductoInternoDto productoInterno;
    private BigDecimal cantidad;
    private BigDecimal saldo;
    private UnidadMedidaDto unidadMedidaInventario;
    private int numeroLote;

    public LoteDto() {
        setId(UtilId.VALOR_DEFECTO);
        setFechaVencimiento(UtilFecha.FECHA_POR_DEFECTO);
        setDisponible(false);
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setSaldo(BigDecimal.ZERO);
        setUnidadMedidaInventario(new UnidadMedidaDto());
        setNumeroLote(UtilNumero.cero);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
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
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(saldo, BigDecimal.ZERO);
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
        this.numeroLote = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(numeroLote, UtilNumero.cero);
    }
}
