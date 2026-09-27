package co.edu.co.pizzeriauco.dominio;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDominio {

    private UUID id;
    private String nombre;
    private TipoProductoDominio tipoProducto;
    private TamanoDominio tamano;
    //si el producto es un producto interno (Si / No)
    private boolean productoInterno;
    private BigDecimal precio;

    private ProductoDominio(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.tipoProducto = builder.tipoProducto;
        this.tamano = builder.tamano;
        this.productoInterno = builder.productoInterno;
        this.precio = builder.precio;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public static class Builder {

        private UUID id;
        private String nombre;
        private TipoProductoDominio tipoProducto;
        private TamanoDominio tamano;
        private boolean productoInterno;
        private BigDecimal precio;

        public Builder() {
            id = UtilId.valorDefecto(id);
            nombre = UtilTexto.vacia;
            tipoProducto = new TipoProductoDominio.Builder().build();
            tamano = new TamanoDominio.Builder().build();
            productoInterno = false;
            precio = BigDecimal.ZERO;
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

        public Builder precio(BigDecimal precio) {
            this.precio = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precio, BigDecimal.ZERO);
            return this;
        }

        public ProductoDominio build() {
            return new ProductoDominio(this);
        }
    }
}
