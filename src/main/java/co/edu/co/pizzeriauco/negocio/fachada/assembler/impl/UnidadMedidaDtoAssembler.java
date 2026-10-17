package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;


import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.dto.UnidadMedidaDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final  class UnidadMedidaDtoAssembler implements DTOAssembler<UnidadMedidaDominio, UnidadMedidaDto> {
    private static final DTOAssembler<UnidadMedidaDominio, UnidadMedidaDto> INSTANCIA = new UnidadMedidaDtoAssembler();

    private UnidadMedidaDtoAssembler() {
    }

    public static DTOAssembler<UnidadMedidaDominio, UnidadMedidaDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public UnidadMedidaDto convertirADTO(UnidadMedidaDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new UnidadMedidaDominio.Builder().build());
        var dto = new UnidadMedidaDto();
        dto.setId(dominioSeguro.getId());
        dto.setUnidadMedida(dominioSeguro.getUnidadMedida());
        dto.setTipoMedida(dominioSeguro.getTipoMedida());
        return dto;
    }

    @Override
    public UnidadMedidaDominio convertirADominio(UnidadMedidaDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new UnidadMedidaDto());
        return new UnidadMedidaDominio.Builder()
                .id(dtoSeguro.getId())
                .unidadMedida(dtoSeguro.getUnidadMedida())
                .tipoMedida(dtoSeguro.getTipoMedida())
                .build();
    }

    @Override
    public List<UnidadMedidaDto> convertirADTO(List<UnidadMedidaDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<UnidadMedidaDominio>());

        var listaDto = new ArrayList<UnidadMedidaDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
