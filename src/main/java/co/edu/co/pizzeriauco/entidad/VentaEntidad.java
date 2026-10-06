package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaEntidad {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private String factura;
    private String cliente;
    private BigDecimal total;

    private VentaEntidad(Builder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.factura = builder.factura;
        this.cliente = builder.cliente;
        this.total = builder.total;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getFactura() {
        return factura;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public static class Builder {

        private UUID id;
        private LocalDate fecha;
        private LocalTime hora;
        private String factura;
        private String cliente;
        private BigDecimal total;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fecha = UtilFecha.FECHA_POR_DEFECTO;
            hora = LocalTime.MIN;
            factura = UtilTexto.vacia;
            cliente = UtilTexto.CLIENTE_POR_DEFECTO;
            total = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = UtilFecha.valorDefecto(fecha);
            return this;
        }

        public Builder hora(LocalTime hora) {
            this.hora = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            hora,
                            LocalTime.MIN);
            return this;
        }

        public Builder factura(String factura) {
            this.factura = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(factura);
            return this;
        }

        public Builder cliente(String cliente) {
            var clienteLimpio = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(cliente);
            this.cliente = UtilTexto.getUtilTexto().esVacia(clienteLimpio)
                    ? UtilTexto.CLIENTE_POR_DEFECTO
                    : clienteLimpio;
            return this;
        }

        public Builder total(BigDecimal total) {
            this.total = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(total, BigDecimal.ZERO);
            return this;
        }

        public VentaEntidad build() {
            return new VentaEntidad(this);
        }
    }
}