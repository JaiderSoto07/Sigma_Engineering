package co.edu.co.pizzeriauco.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoEntidad {

    private UUID id;
    private String nombre;
    private TipoProductoEntidad tipoProducto;
    private TamanoEntidad tamano;
    //se calcula solo: es producto interno si tiene un insumo de bodega asociado
    private boolean productoInterno;
    //insumo de bodega que se vende directo (ej. la bebida); vacio si se vende por receta
    private ProductoInternoEntidad productoInternoAsociado;
    private BigDecimal precio;
    //true = esta en el menu; false = desactivado (retirado de la venta), se conserva su historial
    private boolean activo;

    private ProductoEntidad(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.tipoProducto = builder.tipoProducto;
        this.tamano = builder.tamano;
        this.productoInternoAsociado = builder.productoInternoAsociado;
        this.productoInterno = !UtilId.VALOR_DEFECTO.equals(productoInternoAsociado.getId());
        this.precio = builder.precio;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProductoEntidad getTipoProducto() {
        return tipoProducto;
    }

    public TamanoEntidad getTamano() {
        return tamano;
    }

    public boolean isProductoInterno() {
        return productoInterno;
    }

    public ProductoInternoEntidad getProductoInternoAsociado() {
        return productoInternoAsociado;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private TipoProductoEntidad tipoProducto;
        private TamanoEntidad tamano;
        private ProductoInternoEntidad productoInternoAsociado;
        private BigDecimal precio;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            tipoProducto = new TipoProductoEntidad.Builder().build();
            tamano = new TamanoEntidad.Builder().build();
            productoInternoAsociado = new ProductoInternoEntidad.Builder().build();
            precio = BigDecimal.ZERO;
            //todo producto nace activo (en el menu)
            activo = true;
        }

        public Builder id(UUID id) {
            this.id = UtilId.valorDefecto(id);
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre =
                    UtilTexto.getUtilTexto()
                            .quitarEspaciosEnBlanco(nombre);
            return this;
        }

        public Builder tipoProducto(TipoProductoEntidad tipoProducto) {
            this.tipoProducto =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoProducto,
                            new TipoProductoEntidad.Builder().build()
                    );
            return this;
        }

        public Builder tamano(TamanoEntidad tamano) {
            this.tamano =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tamano,
                            new TamanoEntidad.Builder().build()
                    );
            return this;
        }

        public Builder productoInternoAsociado(ProductoInternoEntidad productoInternoAsociado) {
            this.productoInternoAsociado =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInternoAsociado,
                            new ProductoInternoEntidad.Builder().build()
                    );
            return this;
        }

        public Builder precio(BigDecimal precio) {
            this.precio = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precio, BigDecimal.ZERO);
            return this;
        }

        public Builder activo(boolean activo) {
            this.activo = activo;
            return this;
        }

        public ProductoEntidad build() {
            return new ProductoEntidad(this);
        }
    }
}
