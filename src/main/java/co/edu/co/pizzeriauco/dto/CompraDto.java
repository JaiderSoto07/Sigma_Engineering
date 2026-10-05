package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

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
        setId(UtilId.VALOR_DEFECTO);
        setProveedor(new ProveedorDto());
        setFechaCompra(UtilFecha.FECHA_POR_DEFECTO);
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
        this.fechaCompra = UtilFecha.valorDefecto(fechaCompra);
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
        this.total = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(total, BigDecimal.ZERO);
    }
}
