package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class ProductoEntidadAssembler implements EntidadAssembler<ProductoDominio, ProductoEntidad> {

    private static final EntidadAssembler<ProductoDominio, ProductoEntidad> INSTANCIA = new ProductoEntidadAssembler();

    private ProductoEntidadAssembler() {
    }

    public static EntidadAssembler<ProductoDominio, ProductoEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public ProductoEntidad convertirAEntidad(ProductoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new ProductoDominio.Builder().build());
        return new ProductoEntidad.Builder()
                .id(dominioSeguro.getId())
                .nombre(dominioSeguro.getNombre())
                .tipoProducto(TipoProductoEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getTipoProducto()))
                .tamano(TamanoEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getTamano()))
                .productoInterno(dominioSeguro.isProductoInterno())
                .precio(dominioSeguro.getPrecio())
                .activo(dominioSeguro.isActivo())
                .build();
    }

    @Override
    public ProductoDominio convertirADominio(ProductoEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ProductoEntidad.Builder().build());
        return new ProductoDominio.Builder()
                .id(entidadSegura.getId())
                .nombre(entidadSegura.getNombre())
                .tipoProducto(TipoProductoEntidadAssembler.getInstance().convertirADominio(entidadSegura.getTipoProducto()))
                .tamano(TamanoEntidadAssembler.getInstance().convertirADominio(entidadSegura.getTamano()))
                .productoInterno(entidadSegura.isProductoInterno())
                .precio(entidadSegura.getPrecio())
                .activo(entidadSegura.isActivo())
                .build();
    }

    @Override
    public List<ProductoDominio> convertirADominio(List<ProductoEntidad> listaEntidades) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaEntidades, new ArrayList<ProductoEntidad>());
        var listaDominio = new ArrayList<ProductoDominio>();
        for (var entidad : listaSegura) {
            listaDominio.add(convertirADominio(entidad));
        }
        return listaDominio;
    }
}
