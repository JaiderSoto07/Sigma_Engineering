package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.dto.ProductoInternoDto;
import co.edu.co.pizzeriauco.dto.UnidadMedidaDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class ProductoInternoDtoAssembler implements DTOAssembler<ProductoInternoDominio, ProductoInternoDto> {

    private static final DTOAssembler<ProductoInternoDominio, ProductoInternoDto> INSTANCIA = new ProductoInternoDtoAssembler();

    private ProductoInternoDtoAssembler() {
    }

    public static DTOAssembler<ProductoInternoDominio, ProductoInternoDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public ProductoInternoDto convertirADTO(ProductoInternoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new ProductoInternoDominio.Builder().build());
        var dto = new ProductoInternoDto();
        dto.setId(dominioSeguro.getId());
        dto.setNombre(dominioSeguro.getNombre());
        dto.setPerecedero(dominioSeguro.isPerecedero());
        dto.setVidaUtil(dominioSeguro.getVidaUtil());
        dto.setTipoMedida(convertirUnidadMedidaADTO(dominioSeguro.getTipoMedida()));
        dto.setActivo(dominioSeguro.isActivo());
        return dto;
    }

    @Override
    public ProductoInternoDominio convertirADominio(ProductoInternoDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new ProductoInternoDto());
        return new ProductoInternoDominio.Builder()
                .id(dtoSeguro.getId())
                .nombre(dtoSeguro.getNombre())
                .perecedero(dtoSeguro.isPerecedero())
                .vidaUtil(dtoSeguro.getVidaUtil())
                .tipoMedida(convertirUnidadMedidaADominio(dtoSeguro.getTipoMedida()))
                .activo(dtoSeguro.isActivo())
                .build();
    }

    @Override
    public List<ProductoInternoDto> convertirADTO(List<ProductoInternoDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<ProductoInternoDominio>());

        var listaDto = new ArrayList<ProductoInternoDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
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
