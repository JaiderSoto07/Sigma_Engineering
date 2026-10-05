package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class VentaDto {

    private UUID id;
    private LocalDate fecha;
    private LocalTime hora;
    private String factura;
    private String cliente;
    private BigDecimal total;

    public VentaDto() {
        setId(UtilId.VALOR_DEFECTO);
        setFecha(UtilFecha.FECHA_POR_DEFECTO);
        setHora(LocalTime.MIN);
        setFactura(UtilTexto.vacia);
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

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(factura);
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        var clienteLimpio = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(cliente);
        this.cliente = UtilTexto.getUtilTexto().esVacia(clienteLimpio) ? UtilTexto.CLIENTE_POR_DEFECTO : clienteLimpio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(total, BigDecimal.ZERO);
    }
}
