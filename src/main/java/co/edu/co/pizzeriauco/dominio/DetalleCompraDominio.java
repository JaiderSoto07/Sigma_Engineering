package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class DetalleCompraDominio {

    private UUID id;
    private ProductoInternoDominio productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDominio unidadMedida;
    private BigDecimal precioCompra;
    private LocalDate fechaVencimiento;
    private CompraDominio compra;

    private DetalleCompraDominio(Builder builder) {
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

    public ProductoInternoDominio getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaDominio getUnidadMedida() {
        return unidadMedida;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public CompraDominio getCompra() {
        return compra;
    }

    public static class Builder {

        private UUID id;
        private ProductoInternoDominio productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaDominio unidadMedida;
        private BigDecimal precioCompra;
        private LocalDate fechaVencimiento;
        private CompraDominio compra;

        public Builder() {
            id = UtilId.valorDefecto(id);
            productoInterno = new ProductoInternoDominio.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaDominio.Builder().build();
            precioCompra = BigDecimal.ZERO;
            fechaVencimiento = UtilFecha.ValorPorDefecto(1, 1, 1000);
            compra = new CompraDominio.Builder().build();
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder productoInterno(ProductoInternoDominio productoInterno) {
            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoDominio.Builder().build()
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

        public Builder unidadMedida(UnidadMedidaDominio unidadMedida) {
            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaDominio.Builder().build()
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

        public Builder compra(CompraDominio compra) {
            this.compra = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            compra,
                            new CompraDominio.Builder().build()
                    );
            return this;
        }

        public DetalleCompraDominio build() {
            return new DetalleCompraDominio(this);
        }
    }
}