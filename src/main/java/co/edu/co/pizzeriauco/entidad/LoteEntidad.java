package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class LoteEntidad {

    private UUID id;
    private LocalDate fechaIngreso;
    private LocalDate fechaVencimiento;
    private boolean disponible;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedidaInventario;
    private int numeroLote;

    private LoteEntidad(Builder builder) {
        this.id = builder.id;
        this.fechaIngreso = builder.fechaIngreso;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.disponible = builder.disponible;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedidaInventario = builder.unidadMedidaInventario;
        this.numeroLote = builder.numeroLote;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
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

    public UnidadMedidaEntidad getUnidadMedidaInventario() {
        return unidadMedidaInventario;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public static class Builder {

        private UUID id;
        private LocalDate fechaIngreso;
        private LocalDate fechaVencimiento;
        private boolean disponible;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedidaInventario;
        private int numeroLote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fechaIngreso = UtilFecha.ValorPorDefecto(1, 1, 1000);
            fechaVencimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
            disponible = false;
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedidaInventario = new UnidadMedidaEntidad.Builder().build();
            numeroLote = 0;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder fechaIngreso(LocalDate fechaIngreso) {
            this.fechaIngreso = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaIngreso,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public Builder fechaVencimiento(LocalDate fechaVencimiento) {
            this.fechaVencimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaVencimiento,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public Builder disponible(boolean disponible) {
            this.disponible = disponible;
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
            var cantidadSaneada = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            cantidad,
                            BigDecimal.ZERO
                    );
            this.cantidad = UtilNumero.menorQue(cantidadSaneada, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : cantidadSaneada;
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
            this.numeroLote = UtilNumero.menorQue(numeroLote, 0) ? 0 : numeroLote;
            return this;
        }

        public LoteEntidad build() {
            return new LoteEntidad(this);
        }
    }
}
