package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class InventarioDto {

    private UUID id;
    private BigDecimal cantidadTotal;
    private ProductoInternoDto productoInterno;
    private UnidadMedidaDto unidadMedidaInventario;
    private BigDecimal stockMinimo;
    private PizzeriaDto pizzeria;

    public InventarioDto() {
        setId(id);
        setCantidadTotal(BigDecimal.ZERO);
        setProductoInterno(new ProductoInternoDto());
        setUnidadMedidaInventario(new UnidadMedidaDto());
        setStockMinimo(BigDecimal.ZERO);
        setPizzeria(new PizzeriaDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public BigDecimal getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(BigDecimal cantidadTotal) {
        var cantidadTotalSaneada = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(cantidadTotal, BigDecimal.ZERO);
        this.cantidadTotal = UtilNumero.menorQue(cantidadTotalSaneada, BigDecimal.ZERO) ? BigDecimal.ZERO : cantidadTotalSaneada;
    }

    public ProductoInternoDto getProductoInterno() {
        return productoInterno;
    }

    public void setProductoInterno(ProductoInternoDto productoInterno) {
        this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(productoInterno, new ProductoInternoDto());
    }

    public UnidadMedidaDto getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public void setUnidadMedidaInventario(UnidadMedidaDto unidadMedidaInventario) {
        this.unidadMedidaInventario = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedidaInventario, new UnidadMedidaDto());
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(BigDecimal stockMinimo) {
        var stockMinimoSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(stockMinimo, BigDecimal.ZERO);
        this.stockMinimo = UtilNumero.menorQue(stockMinimoSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : stockMinimoSaneado;
    }

    public PizzeriaDto getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(PizzeriaDto pizzeria) {
        this.pizzeria = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pizzeria, new PizzeriaDto());
    }
}
