package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDominio {

    private UUID id;
    private String nombre;
    private TipoProductoDominio tipoProducto;
    private TamanoDominio tamano;
    private boolean productoInterno;
    private ProductoInternoDominio productoInternoAsociado;
    private BigDecimal precio;
    private boolean activo;

    private ProductoDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.tipoProducto = builder.tipoProducto;
        this.tamano = builder.tamano;
        this.productoInternoAsociado = builder.productoInternoAsociado;
        this.productoInterno = builder.productoInterno;
        this.precio = builder.precio;
        this.activo = builder.activo;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoProductoDominio getTipoProducto() {
        return tipoProducto;
    }

    public TamanoDominio getTamano() {
        return tamano;
    }

    public boolean isProductoInterno() {
        return productoInterno;
    }

    public ProductoInternoDominio getProductoInternoAsociado() {
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
        private TipoProductoDominio tipoProducto;
        private TamanoDominio tamano;
        private boolean productoInterno;
        private ProductoInternoDominio productoInternoAsociado;
        private BigDecimal precio;
        private boolean activo;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            tipoProducto = new TipoProductoDominio.Builder().build();
            tamano = new TamanoDominio.Builder().build();
            productoInterno = false;
            productoInternoAsociado = new ProductoInternoDominio.Builder().build();
            precio = BigDecimal.ZERO;
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

        public Builder tipoProducto(TipoProductoDominio tipoProducto) {
            this.tipoProducto =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tipoProducto,
                            new TipoProductoDominio.Builder().build()
                    );
            return this;
        }

        public Builder tamano(TamanoDominio tamano) {
            this.tamano =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            tamano,
                            new TamanoDominio.Builder().build()
                    );
            return this;
        }

        public Builder productoInterno(boolean productoInterno) {
            this.productoInterno = productoInterno;
            return this;
        }

        public Builder productoInternoAsociado(ProductoInternoDominio productoInternoAsociado) {
            this.productoInternoAsociado =
                    UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(
                            productoInternoAsociado,
                            new ProductoInternoDominio.Builder().build()
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

        public ProductoDominio build() {
            return new ProductoDominio(this);
        }
    }
}
