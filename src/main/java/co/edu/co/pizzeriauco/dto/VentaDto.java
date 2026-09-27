package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaDto {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private String cliente;
    private BigDecimal total;

    public VentaDto() {
        setId(id);
        setFecha(UtilFecha.FECHA_POR_DEFECTO);
        setHora(LocalTime.MIN);
        setCliente(UtilTexto.CLIENTE_POR_DEFECTO);
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
        this.fecha = UtilFecha.valorDefecto(fecha);
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(hora, LocalTime.MIN);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        //si no me dicen el cliente (nulo o vacio), se usa el cliente por defecto
        var clienteSaneado = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(cliente);
        this.cliente = UtilTexto.getUtilTexto().esVacia(clienteSaneado) ? UtilTexto.CLIENTE_POR_DEFECTO : clienteSaneado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(total, BigDecimal.ZERO);
    }
}
