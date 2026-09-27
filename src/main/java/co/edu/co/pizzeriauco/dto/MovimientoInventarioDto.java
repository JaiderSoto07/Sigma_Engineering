package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioDto {

    private UUID id;
    private TipoMovimientoDto tipoMovimiento;
    private OrigenDto origen;
    private String codigoOperacion;
    private ProductoInternoDto productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaDto unidadMedida;
    private LocalDate fechaMovimiento;
    private UUID idLote;

    public MovimientoInventarioDto() {
        setId(id);
        setTipoMovimiento(new TipoMovimientoDto());
        setOrigen(new OrigenDto());
        setCodigoOperacion(UtilTexto.vacia);
        setProductoInterno(new ProductoInternoDto());
        setCantidad(BigDecimal.ZERO);
        setUnidadMedida(new UnidadMedidaDto());
        setFechaMovimiento(UtilFecha.FECHA_POR_DEFECTO);
        setIdLote(idLote);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public TipoMovimientoDto getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoDto tipoMovimiento) {
        this.tipoMovimiento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoMovimiento, new TipoMovimientoDto());
    }

    public OrigenDto getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenDto origen) {
        this.origen = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(origen, new OrigenDto());
    }

    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(String codigoOperacion) {
        this.codigoOperacion = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(codigoOperacion);
    }

    public ProductoInternoDto getProductoInterno() {
        return productoInterno;
    }

    public void setProductoInterno(ProductoInternoDto productoInterno) {
        this.productoInterno = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(productoInterno, new ProductoInternoDto());
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);
    }

    public UnidadMedidaDto getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaDto unidadMedida) {
        this.unidadMedida = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(unidadMedida, new UnidadMedidaDto());
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = UtilFecha.valorDefecto(fechaMovimiento);
    }

    public UUID getIdLote() {
        return idLote;
    }

    public void setIdLote(UUID idLote) {
        this.idLote = UtilId.valorDefecto(idLote);
    }
}
