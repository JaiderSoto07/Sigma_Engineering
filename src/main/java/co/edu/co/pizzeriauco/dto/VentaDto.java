package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaDto {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private ClienteDto cliente;
    private BigDecimal total;

    public VentaDto() {
        setId(id);
        setFecha(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setHora(LocalTime.MIN);
        setCliente(new ClienteDto());
        setTotal(BigDecimal.ZERO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fecha, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(hora, LocalTime.MIN);
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(cliente, new ClienteDto());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        var totalSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(total, BigDecimal.ZERO);
        this.total = UtilNumero.menorQue(totalSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : totalSaneado;
    }
}
