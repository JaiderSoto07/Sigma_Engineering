package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class HistoricoPrecioDominio {

    private UUID id;
    private ProductoDominio producto;
    private BigDecimal precio;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    private HistoricoPrecioDominio(Builder builder) {
        this.id = builder.id;
        this.producto = builder.producto;
        this.precio = builder.precio;
        this.fechaInicio = builder.fechaInicio;
        this.fechaFin = builder.fechaFin;
    }

    public UUID getId() {
        return id;
    }

    public ProductoDominio getProducto() {
        return producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public static class Builder {

        private UUID id;
        private ProductoDominio producto;
        private BigDecimal precio;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;

        public Builder() {
            id = UtilId.valorDefecto(id);
            producto = new ProductoDominio.Builder().build();
            precio = BigDecimal.ZERO;
            fechaInicio = UtilFecha.ValorPorDefecto(1, 1, 1000);
            fechaFin = UtilFecha.ValorPorDefecto(1, 1, 1000);
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder producto(ProductoDominio producto) {
            this.producto =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            producto,
                            new ProductoDominio.Builder().build()
                    );
            return this;
        }

        public Builder precio(BigDecimal precio) {
            var precioSaneado =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            precio,
                            BigDecimal.ZERO
                    );
            this.precio = UtilNumero.menorQue(precioSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : precioSaneado;
            return this;
        }

        public Builder fechaInicio(LocalDate fechaInicio) {
            this.fechaInicio =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaInicio,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public Builder fechaFin(LocalDate fechaFin) {
            this.fechaFin =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaFin,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public HistoricoPrecioDominio build() {
            return new HistoricoPrecioDominio(this);
        }
    }
}