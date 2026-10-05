package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoDto {

    private UUID id;
    private String nombre;
    private TipoProductoDto tipoProducto;
    private TamanoDto tamano;
    private boolean productoInterno;
    private ProductoInternoDto productoInternoAsociado;
    private BigDecimal precio;
    private boolean activo;

    public ProductoDto() {
        setId(UtilId.VALOR_DEFECTO);
        setNombre(UtilTexto.vacia);
        setTipoProducto(new TipoProductoDto());
        setTamano(new TamanoDto());
        setProductoInternoAsociado(new ProductoInternoDto());
        setPrecio(BigDecimal.ZERO);
        setActivo(true);
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

    public ProductoInternoDto getProductoInternoAsociado() {
        return productoInternoAsociado;
    }

    public void setProductoInternoAsociado(ProductoInternoDto productoInternoAsociado) {
        this.productoInternoAsociado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(productoInternoAsociado, new ProductoInternoDto());
        this.productoInterno = !UtilId.VALOR_DEFECTO.equals(this.productoInternoAsociado.getId());
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = UtilNumero.obtenerValorDefectoSiEsNuloONegativo(precio, BigDecimal.ZERO);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
