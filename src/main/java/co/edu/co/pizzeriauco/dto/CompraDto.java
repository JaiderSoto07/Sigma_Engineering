package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CompraDto {

    private UUID id;
    private ProveedorDto proveedor;
    private LocalDate fechaCompra;
    private String numeroFactura;
    private BigDecimal total;

    public CompraDto() {
        setId(id);
        setProveedor(new ProveedorDto());
        setFechaCompra(UtilFecha.ValorPorDefecto(1, 1, 1000));
        setNumeroFactura(UtilTexto.vacia);
        setTotal(BigDecimal.ZERO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public ProveedorDto getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDto proveedor) {
        this.proveedor = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(proveedor, new ProveedorDto());
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fechaCompra, UtilFecha.ValorPorDefecto(1, 1, 1000));
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(numeroFactura);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        var totalSaneado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(total, BigDecimal.ZERO);
        this.total = UtilNumero.menorQue(totalSaneado, BigDecimal.ZERO) ? BigDecimal.ZERO : totalSaneado;
    }
}
