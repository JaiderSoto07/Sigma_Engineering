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

    public InventarioDto() {
        setId(id);
        setCantidadTotal(BigDecimal.ZERO);
        setProductoInterno(new ProductoInternoDto());
        setUnidadMedidaInventario(new UnidadMedidaDto());
        setStockMinimo(BigDecimal.ZERO);
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
        this.cantidadTotal = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidadTotal, BigDecimal.ZERO);
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
        this.stockMinimo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(stockMinimo, BigDecimal.ZERO);
    }
}
