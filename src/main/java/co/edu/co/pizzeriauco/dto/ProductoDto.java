package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDto {

    private UUID id;
    private String nombre;
    private TipoProductoDto tipoProducto;
    private TamanoDto tamano;
    private boolean productoInterno;
    private BigDecimal precio;

    public ProductoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setTipoProducto(new TipoProductoDto());
        setTamano(new TamanoDto());
        setProductoInterno(false);
        setPrecio(BigDecimal.ZERO);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }

    public TipoProductoDto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProductoDto tipoProducto) {
        this.tipoProducto = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tipoProducto, new TipoProductoDto());
    }

    public TamanoDto getTamano() {
        return tamano;
    }

    public void setTamano(TamanoDto tamano) {
        this.tamano = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(tamano, new TamanoDto());
    }

    public boolean isProductoInterno() {
        return productoInterno;
    }

    public void setProductoInterno(boolean productoInterno) {
        this.productoInterno = productoInterno;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precio, BigDecimal.ZERO);
    }
}
