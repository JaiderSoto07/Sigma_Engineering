package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaDominio {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private ClienteDominio cliente;
    private BigDecimal total;

    private VentaDominio(Builder builder) {
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

    public ClienteDominio getCliente() {
        return cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public static class Builder {

        private UUID id;
        private LocalDate fecha;
        private LocalTime hora;
        private ClienteDominio cliente;
        private BigDecimal total;

        public Builder() {
            id = UtilId.valorDefecto(id);
            fecha = UtilFecha.ValorPorDefecto(1, 1, 1000);
            hora = LocalTime.MIN;
            cliente = new ClienteDominio.Builder().build();
            total = BigDecimal.ZERO;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            fecha,
                            UtilFecha.ValorPorDefecto(1, 1, 1000)
                    );
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

        public Builder cliente(ClienteDominio cliente) {
            this.cliente = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            cliente,
                            new ClienteDominio.Builder().build()
                    );
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

        public VentaDominio build() {
            return new VentaDominio(this);
        }
    }
}