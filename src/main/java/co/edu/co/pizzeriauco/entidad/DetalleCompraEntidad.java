package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DetalleCompraEntidad {

    private UUID id;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private BigDecimal precioCompra;
    private LocalDate fechaVencimiento;
    private CompraEntidad compra;

    private DetalleCompraEntidad(Builder builder) {
        this.id = builder.id;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.precioCompra = builder.precioCompra;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.compra = builder.compra;
    }

    public UUID getId() {
        return id;
    }

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public CompraEntidad getCompra() {
        return compra;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private BigDecimal precioCompra;
        private LocalDate fechaVencimiento;
        private CompraEntidad compra;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            precioCompra = BigDecimal.ZERO;
            fechaVencimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
            compra = new CompraEntidad.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoInterno(ProductoInternoEntidad productoInterno) {
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

        public Builder unidadMedida(UnidadMedidaEntidad unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );
            return this;
        }

        public Builder precioCompra(BigDecimal precioCompra) {
            var precioCompraSaneado = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            precioCompra,
                            BigDecimal.ZERO
                    );
            this.precioCompra = UtilNumero.menorQue(precioCompraSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : precioCompraSaneado;
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

        public Builder compra(CompraEntidad compra) {
            this.compra = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            compra,
                            new CompraEntidad.Builder().build()
                    );
            return this;
        }

        public DetalleCompraEntidad build() {
            return new DetalleCompraEntidad(this);
        }
    }
}
