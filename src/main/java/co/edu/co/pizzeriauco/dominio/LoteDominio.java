package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteDominio {

    private UUID id;
    private LocalDate fechaVencimiento;
    private boolean disponible;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private BigDecimal saldo;
    private UnidadMedidaDominio unidadMedidaInventario;
    private int numeroLote;

    private LoteDominio(Builder builder) {
        this.id = builder.id;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.saldo = builder.saldo;
        this.disponible = builder.disponible;
        this.unidadMedidaInventario = builder.unidadMedidaInventario;
        this.numeroLote = builder.numeroLote;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public ProductoInternoDominio getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public UnidadMedidaDominio getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public static class Builder {

        private UUID id;
        private LocalDate fechaVencimiento;
        private boolean disponible;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private BigDecimal saldo;
        private UnidadMedidaDominio unidadMedidaInventario;
        private int numeroLote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            disponible = false;
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            saldo = BigDecimal.ZERO;
            unidadMedidaInventario = new UnidadMedidaDominio.Builder().build();
            numeroLote = UtilNumero.cero;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
            return this;
        }

        public Builder disponible(boolean disponible) {
            this.disponible = disponible;
            return this;
        }

        public Builder productoInterno(ProductoInternoDominio productoInterno) {

            this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build());
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        public Builder saldo(BigDecimal saldo) {
            this.saldo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(saldo, BigDecimal.ZERO);
            return this;
        }

        public Builder unidadMedidaInventario(UnidadMedidaDominio unidadMedidaInventario) {

            this.unidadMedidaInventario = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedidaInventario,
                            new UnidadMedidaDominio.Builder().build());
            return this;
        }

        public Builder numeroLote(int numeroLote) {
            this.numeroLote = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(numeroLote, UtilNumero.cero);
            return this;
        }

        public LoteDominio build() {
            return new LoteDominio(this);
        }
    }
}
