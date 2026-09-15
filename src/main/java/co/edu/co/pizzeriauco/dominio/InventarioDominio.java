package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class InventarioDominio {

    private UUID id;
    private BigDecimal cantidadTotal;
    private ProductoInternoDominio productoInterno;
    private UnidadMedidaDominio unidadMedidaInventario;
    private BigDecimal stockMinimo;
    private PizzeriaDominio pizzeria;

    private InventarioDominio(Builder builder) {
        this.id = builder.id;
        this.cantidadTotal = builder.cantidadTotal;
        this.productoInterno = builder.productoInterno;
        this.unidadMedidaInventario = builder.unidadMedidaInventario;
        this.stockMinimo = builder.stockMinimo;
        this.pizzeria = builder.pizzeria;
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

    public PizzeriaDominio getPizzeria() {
        return pizzeria;
    }

    public static class Builder {

        private UUID id;
        private BigDecimal cantidadTotal;
        private ProductoInternoDominio productoInterno;
        private UnidadMedidaDominio unidadMedidaInventario;
        private BigDecimal stockMinimo;
        private PizzeriaDominio pizzeria;

        public Builder() {
            id = UtilId.valorDefecto(id);
            cantidadTotal = BigDecimal.ZERO;
            productoInterno =
                    new ProductoInternoDominio.Builder().build();
            unidadMedidaInventario =
                    new UnidadMedidaDominio.Builder().build();
            stockMinimo = BigDecimal.ZERO;
            pizzeria =
                    new PizzeriaDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder cantidadTotal(BigDecimal cantidadTotal) {
            var cantidadTotalSaneada = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            cantidadTotal,
                            BigDecimal.ZERO
                    );
            this.cantidadTotal = UtilNumero.menorQue(cantidadTotalSaneada, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : cantidadTotalSaneada;
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
            var stockMinimoSaneado = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            stockMinimo,
                            BigDecimal.ZERO
                    );
            this.stockMinimo = UtilNumero.menorQue(stockMinimoSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : stockMinimoSaneado;
            return this;
        }

        public Builder pizzeria(PizzeriaDominio pizzeria) {
            this.pizzeria = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            pizzeria,
                            new PizzeriaDominio.Builder().build()
                    );
            return this;
        }

        public InventarioDominio build() {
            return new InventarioDominio(this);
        }
    }
}