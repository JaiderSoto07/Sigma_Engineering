package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class InventarioDominio {

    private UUID id;
    private BigDecimal cantidadTotal;
    private ProductoInternoDominio productoInterno;
    private UnidadMedidaDominio unidadMedidaInventario;
    private BigDecimal stockMinimo;

    private InventarioDominio(Builder builder) {
        this.id = builder.id;
        this.cantidadTotal = builder.cantidadTotal;
        this.productoInterno = builder.productoInterno;
        this.unidadMedidaInventario = builder.unidadMedidaInventario;
        this.stockMinimo = builder.stockMinimo;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getCantidadTotal() {
        return cantidadTotal;
    }

    public ProductoInternoDominio getProductoInterno() {
        return productoInterno;
    }

    public UnidadMedidaDominio getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    //el inventario es la suma de los saldos de todos los lotes de ese producto interno
    //los lotes de otros productos no se cuentan
    public static BigDecimal calcularCantidadTotal(ProductoInternoDominio productoInterno, List<LoteDominio> lotes) {
        var productoSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                productoInterno, new ProductoInternoDominio.Builder().build());
        var lotesSaneados = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                lotes, List.<LoteDominio>of());

        var cantidadTotal = BigDecimal.ZERO;
        for (var lote : lotesSaneados) {
            if (!UtilObjeto.esNulo(lote)
                    && lote.getProductoInterno().getId().equals(productoSaneado.getId())) {
                cantidadTotal = cantidadTotal.add(lote.getSaldo());
            }
        }
        return cantidadTotal;
    }

    public static class Builder {

        private UUID id;
        private BigDecimal cantidadTotal;
        private ProductoInternoDominio productoInterno;
        private UnidadMedidaDominio unidadMedidaInventario;
        private BigDecimal stockMinimo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            cantidadTotal = BigDecimal.ZERO;
            productoInterno =
                    new ProductoInternoDominio.Builder().build();
            unidadMedidaInventario =
                    new UnidadMedidaDominio.Builder().build();
            stockMinimo = UtilNumero.STOCK_MINIMO_POR_DEFECTO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder cantidadTotal(BigDecimal cantidadTotal) {
            this.cantidadTotal = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidadTotal, BigDecimal.ZERO);
            return this;
        }

        public Builder productoInterno(
                ProductoInternoDominio productoInterno) {

            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build()
                    );
            return this;
        }

        public Builder unidadMedidaInventario(
                UnidadMedidaDominio unidadMedidaInventario) {

            this.unidadMedidaInventario = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedidaInventario,
                            new UnidadMedidaDominio.Builder().build()
                    );
            return this;
        }

        public Builder stockMinimo(BigDecimal stockMinimo) {
            this.stockMinimo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(stockMinimo, UtilNumero.STOCK_MINIMO_POR_DEFECTO);
            return this;
        }

        public InventarioDominio build() {
            return new InventarioDominio(this);
        }
    }
}