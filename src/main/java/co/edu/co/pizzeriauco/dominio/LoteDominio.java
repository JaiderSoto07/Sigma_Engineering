package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteDominio {

    private UUID id;
    //movimiento de entrada que origina el lote; su origen dice si vino de una compra o de un cambio
    private MovimientoInventarioDominio movimientoInventario;
    private LocalDate fechaVencimiento;
    private boolean disponible;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private BigDecimal saldo;
    private UnidadMedidaDominio unidadMedidaInventario;
    private int numeroLote;

    private LoteDominio(Builder builder) {
        this.id = builder.id;
        this.movimientoInventario = builder.movimientoInventario;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.disponible = builder.disponible;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.saldo = builder.saldo;
        this.unidadMedidaInventario = builder.unidadMedidaInventario;
        this.numeroLote = builder.numeroLote;
    }

    public UUID getId() {
        return id;
    }

    public MovimientoInventarioDominio getMovimientoInventario() {
        return movimientoInventario;
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
        private MovimientoInventarioDominio movimientoInventario;
        private LocalDate fechaVencimiento;
        private boolean disponible;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private BigDecimal saldo;
        private UnidadMedidaDominio unidadMedidaInventario;
        private int numeroLote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            movimientoInventario = new MovimientoInventarioDominio.Builder().build();
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            disponible = false;
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            saldo = BigDecimal.ZERO;
            unidadMedidaInventario = new UnidadMedidaDominio.Builder().build();
            numeroLote = 0;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder movimientoInventario(
                MovimientoInventarioDominio movimientoInventario) {

            this.movimientoInventario = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            movimientoInventario,
                            new MovimientoInventarioDominio.Builder().build()
                    );
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

        public Builder productoInterno(
                ProductoInternoDominio productoInterno) {

            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build()
                    );
            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
            return this;
        }

        //lo que todavia queda del lote
        public Builder saldo(BigDecimal saldo) {
            this.saldo = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(saldo, BigDecimal.ZERO);
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

        public Builder numeroLote(int numeroLote) {
            this.numeroLote = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(numeroLote, UtilNumero.cero);
            return this;
        }

        public LoteDominio build() {
            return new LoteDominio(this);
        }
    }
}
