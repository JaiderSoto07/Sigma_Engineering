package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;

import java.util.ArrayList;
import java.util.List;

public final class DetalleRecetaEntidadAssembler implements EntidadAssembler<DetalleRecetaDominio, DetalleRecetaEntidad> {

    private static final EntidadAssembler<DetalleRecetaDominio, DetalleRecetaEntidad> INSTANCIA = new DetalleRecetaEntidadAssembler();

    private DetalleRecetaEntidadAssembler() {
    }

    public static EntidadAssembler<DetalleRecetaDominio, DetalleRecetaEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public DetalleRecetaEntidad convertirAEntidad(DetalleRecetaDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new DetalleRecetaDominio.Builder().build());
        return new DetalleRecetaEntidad.Builder()
                .id(dominioSeguro.getId())
                .producto(ProductoEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getProducto()))
                .productoInterno(ProductoInternoEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getProductoInterno()))
                .cantidad(dominioSeguro.getCantidad())
                .unidadMedida(UnidadMedidaEntidadAssembler.getInstance().convertirAEntidad(dominioSeguro.getUnidadMedida()))
                .build();
    }


    @Override
    public DetalleRecetaDominio convertirADominio(DetalleRecetaEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new DetalleRecetaEntidad.Builder().build());
        return new DetalleRecetaDominio.Builder()
                .id(entidadSegura.getId())
                .producto(ProductoEntidadAssembler.getInstance().convertirADominio(entidadSegura.getProducto()))
                .productoInterno(ProductoInternoEntidadAssembler.getInstance().convertirADominio(entidadSegura.getProductoInterno()))
                .cantidad(entidadSegura.getCantidad())
                .unidadMedida(UnidadMedidaEntidadAssembler.getInstance().convertirADominio(entidadSegura.getUnidadMedida()))
                .build();
    }

    @Override
    public List<DetalleRecetaDominio> convertirADominio(List<DetalleRecetaEntidad> listaEntidades) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaEntidades, new ArrayList<DetalleRecetaEntidad>());
        var listaDominio = new ArrayList<DetalleRecetaDominio>();
        for (var entidad : listaSegura) {
            listaDominio.add(convertirADominio(entidad));
        }
        return listaDominio;
    }

}
