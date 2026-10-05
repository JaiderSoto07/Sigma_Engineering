package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteEntidad {

    private UUID id;
    private LocalDate fechaVencimiento;
    //se calcula solo: esta disponible si todavia le queda saldo
    private boolean disponible;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private BigDecimal saldo;
    private UnidadMedidaEntidad unidadMedidaInventario;
    private int numeroLote;

    private LoteEntidad(Builder builder) {
        this.id = builder.id;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.saldo = builder.saldo;
        this.disponible = UtilNumero.mayorQue(saldo, BigDecimal.ZERO);
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

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public UnidadMedidaEntidad getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public static class Builder {

        private UUID id;
        private LocalDate fechaVencimiento;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private BigDecimal saldo;
        private UnidadMedidaEntidad unidadMedidaInventario;
        private int numeroLote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fechaVencimiento = UtilFecha.FECHA_POR_DEFECTO;
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            saldo = BigDecimal.ZERO;
            unidadMedidaInventario = new UnidadMedidaEntidad.Builder().build();
            numeroLote = 0;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilFecha.valorDefecto(fechaVencimiento);
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
                UnidadMedidaEntidad unidadMedidaInventario) {

            this.unidadMedidaInventario = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedidaInventario,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder numeroLote(int numeroLote) {
            this.numeroLote = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(numeroLote, UtilNumero.cero);
            return this;
        }

        public LoteEntidad build() {
            return new LoteEntidad(this);
        }
    }
}
