package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.dto.ProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class ProductoDtoAssembler implements DTOAssembler<ProductoDominio, ProductoDto> {

    private static final DTOAssembler<ProductoDominio, ProductoDto> INSTANCIA = new ProductoDtoAssembler();

    private ProductoDtoAssembler() {
    }

    public static DTOAssembler<ProductoDominio, ProductoDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public ProductoDto convertirADTO(ProductoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new ProductoDominio.Builder().build());
        var dto = new ProductoDto();
        dto.setId(dominioSeguro.getId());
        dto.setNombre(dominioSeguro.getNombre());
        dto.setTipoProducto(TipoProductoDtoAssembler.getInstance().convertirADTO(dominioSeguro.getTipoProducto()));
        dto.setTamano(TamanoDtoAssembler.getInstance().convertirADTO(dominioSeguro.getTamano()));
        dto.setProductoInterno(dominioSeguro.isProductoInterno());
        dto.setPrecio(dominioSeguro.getPrecio());
        dto.setActivo(dominioSeguro.isActivo());
        return dto;
    }

    @Override
    public ProductoDominio convertirADominio(ProductoDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ProductoDto());
        return new ProductoDominio.Builder()
                .id(dtoSeguro.getId())
                .nombre(dtoSeguro.getNombre())
                .tipoProducto(TipoProductoDtoAssembler.getInstance().convertirADominio(dtoSeguro.getTipoProducto()))
                .tamano(TamanoDtoAssembler.getInstance().convertirADominio(dtoSeguro.getTamano()))
                .productoInterno(dtoSeguro.isProductoInterno())
                .precio(dtoSeguro.getPrecio())
                .activo(dtoSeguro.isActivo())
                .build();
    }

    @Override
    public List<ProductoDto> convertirADTO(List<ProductoDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<ProductoDominio>());

        var listaDto = new ArrayList<ProductoDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
