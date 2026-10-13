package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

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
                .tipoMedida(convertirUnidadMedidaAEntidad(dominioSeguro.getTipoMedida()))
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
                .tipoMedida(convertirUnidadMedidaADominio(entidadSegura.getTipoMedida()))
                .activo(entidadSegura.isActivo())
                .build();
    }

    private UnidadMedidaEntidad convertirUnidadMedidaAEntidad(UnidadMedidaDominio unidadMedida) {
        return new UnidadMedidaEntidad.Builder()
                .id(unidadMedida.getId())
                .unidadMedida(unidadMedida.getUnidadMedida())
                .tipoMedida(unidadMedida.getTipoMedida())
                .build();
    }

    private UnidadMedidaDominio convertirUnidadMedidaADominio(UnidadMedidaEntidad unidadMedida) {
        return new UnidadMedidaDominio.Builder()
                .id(unidadMedida.getId())
                .unidadMedida(unidadMedida.getUnidadMedida())
                .tipoMedida(unidadMedida.getTipoMedida())
                .build();
    }
}
