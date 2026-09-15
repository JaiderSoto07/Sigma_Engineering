package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class InventarioEntidad {

    private UUID id;
    private BigDecimal cantidadTotal;
    private ProductoInternoEntidad productoInterno;
    private UnidadMedidaEntidad unidadMedidaInventario;
    private BigDecimal stockMinimo;
    private PizzeriaEntidad pizzeria;

    private InventarioEntidad(Builder builder) {
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

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public UnidadMedidaEntidad getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }

    public PizzeriaEntidad getPizzeria() {
        return pizzeria;
    }

    public static class Builder {

        private UUID id;
        private BigDecimal cantidadTotal;
        private ProductoInternoEntidad productoInterno;
        private UnidadMedidaEntidad unidadMedidaInventario;
        private BigDecimal stockMinimo;
        private PizzeriaEntidad pizzeria;

        public Builder() {
            id = UtilId.valorDefecto(id);
            cantidadTotal = BigDecimal.ZERO;
            productoInterno =
                    new ProductoInternoEntidad.Builder().build();
            unidadMedidaInventario =
                    new UnidadMedidaEntidad.Builder().build();
            stockMinimo = BigDecimal.ZERO;
            pizzeria =
                    new PizzeriaEntidad.Builder().build();
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
                ProductoInternoEntidad productoInterno) {

            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoEntidad.Builder().build()
                    );
            return this;
        }

        public Builder unidadMedidaInventario(
                UnidadMedidaEntidad unidadMedidaInventario) {

            this.unidadMedidaInventario = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedidaInventario,
                            new UnidadMedidaEntidad.Builder().build()
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

        public Builder pizzeria(PizzeriaEntidad pizzeria) {
            this.pizzeria = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            pizzeria,
                            new PizzeriaEntidad.Builder().build()
                    );
            return this;
        }

        public InventarioEntidad build() {
            return new InventarioEntidad(this);
        }
    }
}
