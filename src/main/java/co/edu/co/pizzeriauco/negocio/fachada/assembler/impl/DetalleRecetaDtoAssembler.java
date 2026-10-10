package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.dto.ProductoDto;
import co.edu.co.pizzeriauco.dto.ProductoInternoDto;
import co.edu.co.pizzeriauco.dto.TamanoDto;
import co.edu.co.pizzeriauco.dto.TipoProductoDto;
import co.edu.co.pizzeriauco.dto.UnidadMedidaDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;

import java.util.ArrayList;
import java.util.List;

public final class DetalleRecetaDtoAssembler implements DTOAssembler<DetalleRecetaDominio, DetalleRecetaDto> {

    private static final DTOAssembler<DetalleRecetaDominio, DetalleRecetaDto> INSTANCIA = new DetalleRecetaDtoAssembler();

    private DetalleRecetaDtoAssembler() {
    }

    public static DTOAssembler<DetalleRecetaDominio, DetalleRecetaDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public DetalleRecetaDto convertirADTO(DetalleRecetaDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new DetalleRecetaDominio.Builder().build());
        var dto = new DetalleRecetaDto();
        dto.setId(dominioSeguro.getId());
        dto.setProducto(convertirProductoADTO(dominioSeguro.getProducto()));
        dto.setProductoInterno(convertirProductoInternoADTO(dominioSeguro.getProductoInterno()));
        dto.setCantidad(dominioSeguro.getCantidad());
        dto.setUnidadMedida(convertirUnidadMedidaADTO(dominioSeguro.getUnidadMedida()));
        return dto;
    }

    @Override
    public DetalleRecetaDominio convertirADominio(DetalleRecetaDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new DetalleRecetaDto());
        return new DetalleRecetaDominio.Builder()
                .id(dtoSeguro.getId())
                .producto(convertirProductoADominio(dtoSeguro.getProducto()))
                .productoInterno(convertirProductoInternoADominio(dtoSeguro.getProductoInterno()))
                .cantidad(dtoSeguro.getCantidad())
                .unidadMedida(convertirUnidadMedidaADominio(dtoSeguro.getUnidadMedida()))
                .build();
    }

    @Override
    public List<DetalleRecetaDto> convertirADTO(List<DetalleRecetaDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<DetalleRecetaDominio>());
        return listaSegura.stream().map(this::convertirADTO).toList();
    }

    private ProductoDto convertirProductoADTO(ProductoDominio producto) {
        var tipoProducto = new TipoProductoDto();
        tipoProducto.setId(producto.getTipoProducto().getId());
        tipoProducto.setNombre(producto.getTipoProducto().getNombre());

        var tamano = new TamanoDto();
        tamano.setId(producto.getTamano().getId());
        tamano.setTamano(producto.getTamano().getTamano());

        var dto = new ProductoDto();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setTipoProducto(tipoProducto);
        dto.setTamano(tamano);
        dto.setProductoInterno(producto.isProductoInterno());
        dto.setProductoInternoAsociado(convertirProductoInternoADTO(producto.getProductoInternoAsociado()));
        dto.setPrecio(producto.getPrecio());
        dto.setActivo(producto.isActivo());
        return dto;
    }

    private ProductoDominio convertirProductoADominio(ProductoDto producto) {
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
                .productoInterno(producto.isProductoInterno())
                .productoInternoAsociado(convertirProductoInternoADominio(producto.getProductoInternoAsociado()))
                .precio(producto.getPrecio())
                .activo(producto.isActivo())
                .build();
    }

    private ProductoInternoDto convertirProductoInternoADTO(ProductoInternoDominio productoInterno) {
        var dto = new ProductoInternoDto();
        dto.setId(productoInterno.getId());
        dto.setNombre(productoInterno.getNombre());
        dto.setPerecedero(productoInterno.isPerecedero());
        dto.setVidaUtil(productoInterno.getVidaUtil());
        dto.setTipoMedida(convertirUnidadMedidaADTO(productoInterno.getTipoMedida()));
        dto.setActivo(productoInterno.isActivo());
        return dto;
    }

    private ProductoInternoDominio convertirProductoInternoADominio(ProductoInternoDto productoInterno) {
        return new ProductoInternoDominio.Builder()
                .id(productoInterno.getId())
                .nombre(productoInterno.getNombre())
                .perecedero(productoInterno.isPerecedero())
                .vidaUtil(productoInterno.getVidaUtil())
                .tipoMedida(convertirUnidadMedidaADominio(productoInterno.getTipoMedida()))
                .activo(productoInterno.isActivo())
                .build();
    }

    private UnidadMedidaDto convertirUnidadMedidaADTO(UnidadMedidaDominio unidadMedida) {
        var dto = new UnidadMedidaDto();
        dto.setId(unidadMedida.getId());
        dto.setUnidadMedida(unidadMedida.getUnidadMedida());
        dto.setTipoMedida(unidadMedida.getTipoMedida());
        return dto;
    }

    private UnidadMedidaDominio convertirUnidadMedidaADominio(UnidadMedidaDto unidadMedida) {
        return new UnidadMedidaDominio.Builder()
                .id(unidadMedida.getId())
                .unidadMedida(unidadMedida.getUnidadMedida())
                .tipoMedida(unidadMedida.getTipoMedida())
                .build();
    }
}
