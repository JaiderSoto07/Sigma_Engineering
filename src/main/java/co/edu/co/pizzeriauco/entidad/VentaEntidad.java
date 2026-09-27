package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaEntidad {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private String cliente;
    private BigDecimal total;

    private VentaEntidad(Builder builder) {
        this.id = builder.id;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
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
        private String cliente;
        private BigDecimal total;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fecha = UtilFecha.FECHA_POR_DEFECTO;
            hora = LocalTime.MIN;
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
            this.hora = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            hora,
                            LocalTime.MIN
                    );
            return this;
        }

        public Builder cliente(String cliente) {
            //si no me dicen el cliente (nulo o vacio), se usa el cliente por defecto
            var clienteSaneado = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(cliente);
            this.cliente = UtilTexto.getUtilTexto().esVacia(clienteSaneado)
                    ? UtilTexto.CLIENTE_POR_DEFECTO
                    : clienteSaneado;
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