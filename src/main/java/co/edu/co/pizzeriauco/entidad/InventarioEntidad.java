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

    private InventarioEntidad(Builder builder) {
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

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public UnidadMedidaEntidad getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public BigDecimal getStockMinimo() {
        return stockMinimo;
    }


    public static class Builder {

        private UUID id;
        private BigDecimal cantidadTotal;
        private ProductoInternoEntidad productoInterno;
        private UnidadMedidaEntidad unidadMedidaInventario;
        private BigDecimal stockMinimo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            cantidadTotal = BigDecimal.ZERO;
            productoInterno =
                    new ProductoInternoEntidad.Builder().build();
            unidadMedidaInventario =
                    new UnidadMedidaEntidad.Builder().build();
            stockMinimo = BigDecimal.ZERO;
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
            this.stockMinimo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(stockMinimo, BigDecimal.ZERO);
            return this;
        }


        public InventarioEntidad build() {
            return new InventarioEntidad(this);
        }
    }
}
