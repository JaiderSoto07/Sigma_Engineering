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
                .producto(convertirProductoAEntidad(dominioSeguro.getProducto()))
                .productoInterno(convertirProductoInternoAEntidad(dominioSeguro.getProductoInterno()))
                .cantidad(dominioSeguro.getCantidad())
                .unidadMedida(convertirUnidadMedidaAEntidad(dominioSeguro.getUnidadMedida()))
                .build();
    }


    @Override
    public DetalleRecetaDominio convertirADominio(DetalleRecetaEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new DetalleRecetaEntidad.Builder().build());
        return new DetalleRecetaDominio.Builder()
                .id(entidadSegura.getId())
                .producto(convertirProductoADominio(entidadSegura.getProducto()))
                .productoInterno(convertirProductoInternoADominio(entidadSegura.getProductoInterno()))
                .cantidad(entidadSegura.getCantidad())
                .unidadMedida(convertirUnidadMedidaADominio(entidadSegura.getUnidadMedida()))
                .build();
    }

    private ProductoEntidad convertirProductoAEntidad(ProductoDominio producto) {
        return new ProductoEntidad.Builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .tipoProducto(new TipoProductoEntidad.Builder()
                        .id(producto.getTipoProducto().getId())
                        .nombre(producto.getTipoProducto().getNombre())
                        .build())
                .tamano(new TamanoEntidad.Builder()
                        .id(producto.getTamano().getId())
                        .tamano(producto.getTamano().getTamano())
                        .build())
                .productoInternoAsociado(convertirProductoInternoAEntidad(producto.getProductoInternoAsociado()))
                .precio(producto.getPrecio())
                .activo(producto.isActivo())
                .build();
    }

    private ProductoDominio convertirProductoADominio(ProductoEntidad producto) {
        return new ProductoDominio.Builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .tipoProducto(new TipoProductoDominio.Builder()
                        .id(producto.getTipoProducto().getId())
                        .nombre(producto.getTipoProducto().getNombre())
                        .build())
                .tamano(new TamanoDominio.Builder()
                        .id(producto.getTamano().getId())
                        .tamano(producto.getTamano().getTamano())
                        .build())
                .productoInternoAsociado(convertirProductoInternoADominio(producto.getProductoInternoAsociado()))
                .precio(producto.getPrecio())
                .activo(producto.isActivo())
                .build();
    }

    private ProductoInternoEntidad convertirProductoInternoAEntidad(ProductoInternoDominio productoInterno) {
        return new ProductoInternoEntidad.Builder()
                .id(productoInterno.getId())
                .nombre(productoInterno.getNombre())
                .perecedero(productoInterno.isPerecedero())
                .vidaUtil(productoInterno.getVidaUtil())
                .tipoMedida(convertirUnidadMedidaAEntidad(productoInterno.getTipoMedida()))
                .activo(productoInterno.isActivo())
                .build();
    }

    private ProductoInternoDominio convertirProductoInternoADominio(ProductoInternoEntidad productoInterno) {
        return new ProductoInternoDominio.Builder()
                .id(productoInterno.getId())
                .nombre(productoInterno.getNombre())
                .perecedero(productoInterno.isPerecedero())
                .vidaUtil(productoInterno.getVidaUtil())
                .tipoMedida(convertirUnidadMedidaADominio(productoInterno.getTipoMedida()))
                .activo(productoInterno.isActivo())
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
