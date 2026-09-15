package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CompraEntidad {

    private UUID id;
    private ProveedorEntidad proveedor;
    private LocalDate fechaCompra;
    private String numeroFactura;
    private BigDecimal total;

    private CompraEntidad(Builder builder) {
        this.id = builder.id;
        this.proveedor = builder.proveedor;
        this.fechaCompra = builder.fechaCompra;
        this.numeroFactura = builder.numeroFactura;
        this.total = builder.total;
    }

    public UUID getId() {
        return id;
    }

    public ProveedorEntidad getProveedor() {
        return proveedor;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public static class Builder {

        private UUID id;
        private ProveedorEntidad proveedor;
        private LocalDate fechaCompra;
        private String numeroFactura;
        private BigDecimal total;

        public Builder() {
            id = UtilId.valorDefecto(id);
            proveedor = new ProveedorEntidad.Builder().build();
            fechaCompra = UtilFecha.ValorPorDefecto(1, 1, 1000);
            numeroFactura = UtilTexto.vacia;
            total = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder proveedor(ProveedorEntidad proveedor) {
            this.proveedor = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            proveedor,
                            new ProveedorEntidad.Builder().build()
                    );
            return this;
        }

        public Builder fechaCompra(LocalDate fechaCompra) {
            this.fechaCompra = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fechaCompra,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
            return this;
        }

        public Builder numeroFactura(String numeroFactura) {
            this.numeroFactura = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(numeroFactura);
            return this;
        }

        public Builder total(BigDecimal total) {
            var totalSaneado = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            total,
                            BigDecimal.ZERO
                    );
            this.total = UtilNumero.menorQue(totalSaneado, BigDecimal.ZERO)
                    ? BigDecimal.ZERO
                    : totalSaneado;
            return this;
        }

        public CompraEntidad build() {
            return new CompraEntidad(this);
        }
    }
}
