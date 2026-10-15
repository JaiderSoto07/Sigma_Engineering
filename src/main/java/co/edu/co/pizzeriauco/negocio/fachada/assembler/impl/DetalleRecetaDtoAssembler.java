package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
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
        dto.setProducto(ProductoDtoAssembler.getInstance().convertirADTO(dominioSeguro.getProducto()));
        dto.setProductoInterno(ProductoInternoDtoAssembler.getInstance().convertirADTO(dominioSeguro.getProductoInterno()));
        dto.setCantidad(dominioSeguro.getCantidad());
        dto.setUnidadMedida(UnidadMedidaDtoAssembler.getInstance().convertirADTO(dominioSeguro.getUnidadMedida()));
        return dto;
    }

    @Override
    public DetalleRecetaDominio convertirADominio(DetalleRecetaDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new DetalleRecetaDto());
        return new DetalleRecetaDominio.Builder()
                .id(dtoSeguro.getId())
                .producto(ProductoDtoAssembler.getInstance().convertirADominio(dtoSeguro.getProducto()))
                .productoInterno(ProductoInternoDtoAssembler.getInstance().convertirADominio(dtoSeguro.getProductoInterno()))
                .cantidad(dtoSeguro.getCantidad())
                .unidadMedida(UnidadMedidaDtoAssembler.getInstance().convertirADominio(dtoSeguro.getUnidadMedida()))
                .build();
    }

    @Override
    public List<DetalleRecetaDto> convertirADTO(List<DetalleRecetaDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<DetalleRecetaDominio>());

        var listaDto = new ArrayList<DetalleRecetaDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }

}
