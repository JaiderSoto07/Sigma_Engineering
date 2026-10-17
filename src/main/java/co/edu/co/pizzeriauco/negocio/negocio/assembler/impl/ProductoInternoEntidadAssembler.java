package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class ProductoInternoEntidadAssembler implements EntidadAssembler<ProductoInternoDominio, ProductoInternoEntidad> {

    private static final EntidadAssembler<ProductoInternoDominio, ProductoInternoEntidad> INSTANCIA = new ProductoInternoEntidadAssembler();

    private ProductoInternoEntidadAssembler() {
    }

    public static EntidadAssembler<ProductoInternoDominio, ProductoInternoEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public ProductoInternoEntidad convertirAEntidad(ProductoInternoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new ProductoInternoDominio.Builder().build());
        return new ProductoInternoEntidad.Builder()
                .id(dominioSeguro.getId())
                .nombre(dominioSeguro.getNombre())
                .perecedero(dominioSeguro.isPerecedero())
                .vidaUtil(dominioSeguro.getVidaUtil())
                .tipoMedida(UnidadMedidaEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getTipoMedida()))
                .activo(dominioSeguro.isActivo())
                .build();
    }

    @Override
    public ProductoInternoDominio convertirADominio(ProductoInternoEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new ProductoInternoEntidad.Builder().build());
        return new ProductoInternoDominio.Builder()
                .id(entidadSegura.getId())
                .nombre(entidadSegura.getNombre())
                .perecedero(entidadSegura.isPerecedero())
                .vidaUtil(entidadSegura.getVidaUtil())
                .tipoMedida(UnidadMedidaEntidadAssembler.getInstance().convertirADominio(entidadSegura.getTipoMedida()))
                .activo(entidadSegura.isActivo())
                .build();
    }

    @Override
    public List<ProductoInternoDominio> convertirADominio(List<ProductoInternoEntidad> listaEntidades) {

        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaEntidades, new ArrayList<ProductoInternoEntidad>());
        var listaDominio = new ArrayList<ProductoInternoDominio>();
        for (var entidad : listaSegura) {
            listaDominio.add(convertirADominio(entidad));
        }
        return listaDominio;
    }

}
