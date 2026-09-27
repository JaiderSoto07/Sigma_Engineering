package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class MovimientoInventarioEntidad {

    private UUID id;
    private TipoMovimientoEntidad tipoMovimiento;
    private OrigenEntidad origen;
    //codigo de la operacion que causo el movimiento (compra, venta o cambio)
    private String codigoOperacion;
    private ProductoInternoEntidad productoInterno;
    private BigDecimal cantidad;
    private UnidadMedidaEntidad unidadMedida;
    private LocalDate fechaMovimiento;
    //solo el id del lote afectado: el Lote ya guarda su movimiento de entrada como objeto
    //y si los dos se guardaran como objeto, cada uno crearia al otro sin fin
    private UUID idLote;

    private MovimientoInventarioEntidad(Builder builder) {
        this.id = builder.id;
        this.tipoMovimiento = builder.tipoMovimiento;
        this.origen = builder.origen;
        this.codigoOperacion = builder.codigoOperacion;
        this.productoInterno = builder.productoInterno;
        this.cantidad = builder.cantidad;
        this.unidadMedida = builder.unidadMedida;
        this.fechaMovimiento = builder.fechaMovimiento;
        this.idLote = builder.idLote;
    }

    public UUID getId() {
        return id;
    }

    public TipoMovimientoEntidad getTipoMovimiento() {
        return tipoMovimiento;
    }

    public OrigenEntidad getOrigen() {
        return origen;
    }

    public String getCodigoOperacion() {
        return codigoOperacion;
    }

    public ProductoInternoEntidad getProductoInterno() {
        return productoInterno;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public UnidadMedidaEntidad getUnidadMedida() {
        return unidadMedida;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public UUID getIdLote() {
        return idLote;
    }

    public static class Builder {

        private UUID id;
        private TipoMovimientoEntidad tipoMovimiento;
        private OrigenEntidad origen;
        private String codigoOperacion;
        private ProductoInternoEntidad productoInterno;
        private BigDecimal cantidad;
        private UnidadMedidaEntidad unidadMedida;
        private LocalDate fechaMovimiento;
        private UUID idLote;

        public Builder() {
            id = UtilId.valorDefecto(id);
            tipoMovimiento = new TipoMovimientoEntidad.Builder().build();
            origen = new OrigenEntidad.Builder().build();
            codigoOperacion = UtilTexto.vacia;
            productoInterno = new ProductoInternoEntidad.Builder().build();
            cantidad = BigDecimal.ZERO;
            unidadMedida = new UnidadMedidaEntidad.Builder().build();
            fechaMovimiento = UtilFecha.FECHA_POR_DEFECTO;
            idLote = UtilId.valorDefecto(idLote);
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder tipoMovimiento(
                TipoMovimientoEntidad tipoMovimiento) {

            this.tipoMovimiento = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoMovimiento,
                            new TipoMovimientoEntidad.Builder().build()
                    );

            return this;
        }

        public Builder origen(OrigenEntidad origen) {
            this.origen = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            origen,
                            new OrigenEntidad.Builder().build()
                    );

            return this;
        }

        public Builder codigoOperacion(String codigoOperacion) {
            this.codigoOperacion = UtilTexto.getUtilTexto()
                    .quitarEspaciosEnBlanco(codigoOperacion);
            return this;
        }

        public Builder productoInterno(
                ProductoInternoEntidad productoInterno) {

            this.productoInterno = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInterno,
                            new ProductoInternoEntidad.Builder().build()
                    );

            return this;
        }

        public Builder cantidad(BigDecimal cantidad) {
            this.cantidad = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(cantidad, BigDecimal.ZERO);

            return this;
        }

        public Builder unidadMedida(
                UnidadMedidaEntidad unidadMedida) {

            this.unidadMedida = UtilObjeto
                    .obtenerValorDefectoSiValorOriginalEsNulo(
                            unidadMedida,
                            new UnidadMedidaEntidad.Builder().build()
                    );

            return this;
        }

        public Builder fechaMovimiento(LocalDate fechaMovimiento) {
            this.fechaMovimiento = UtilFecha.valorDefecto(fechaMovimiento);

            return this;
        }

        public Builder idLote(UUID idLote) {
            this.idLote = UtilId.valorDefecto(idLote);
            return this;
        }

        public MovimientoInventarioEntidad build() {
            return new MovimientoInventarioEntidad(this);
        }
    }
}