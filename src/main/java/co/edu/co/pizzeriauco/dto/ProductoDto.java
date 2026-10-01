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
    //se calcula solo: es producto interno si tiene un insumo de bodega asociado
    private boolean productoInterno;
    //insumo de bodega que se vende directo (ej. la bebida); vacio si se vende por receta
    private ProductoInternoDto productoInternoAsociado;
    private BigDecimal precio;
    //true = esta en el menu; false = desactivado (retirado de la venta), se conserva su historial
    private boolean activo;

    public ProductoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setTipoProducto(new TipoProductoDto());
        setTamano(new TamanoDto());
        setProductoInternoAsociado(new ProductoInternoDto());
        setPrecio(BigDecimal.ZERO);
        //todo producto nace activo (en el menu)
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
