package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CompraDominio {

    private UUID id;
    private ProveedorDominio proveedor;
    private LocalDate fechaCompra;
    private String numeroFactura;
    private BigDecimal total;

    private CompraDominio(Builder builder) {
        this.id = builder.id;
        this.proveedor = builder.proveedor;
        this.fechaCompra = builder.fechaCompra;
        this.numeroFactura = builder.numeroFactura;
        this.total = builder.total;
    }

    public UUID getId() {
        return id;
    }

    public ProveedorDominio getProveedor() {
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
        private ProveedorDominio proveedor;
        private LocalDate fechaCompra;
        private String numeroFactura;
        private BigDecimal total;

        public Builder() {
            id = UtilId.valorDefecto(id);
            proveedor = new ProveedorDominio.Builder().build();
            fechaCompra = UtilFecha.FECHA_POR_DEFECTO;
            numeroFactura = UtilTexto.vacia;
            total = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder proveedor(ProveedorDominio proveedor) {
            this.proveedor = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            proveedor,
                            new ProveedorDominio.Builder().build()
                    );
            return this;
        }

        public Builder fechaCompra(LocalDate fechaCompra) {
            this.fechaCompra = UtilFecha.valorDefecto(fechaCompra);
            return this;
        }

        public Builder numeroFactura(String numeroFactura) {
            this.numeroFactura = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(numeroFactura);
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(total, BigDecimal.ZERO);
            return this;
        }

        public CompraDominio build() {
            return new CompraDominio(this);
        }
    }
}