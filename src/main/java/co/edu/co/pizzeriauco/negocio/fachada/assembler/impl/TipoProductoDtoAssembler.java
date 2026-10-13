package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.dto.TipoProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class TipoProductoDtoAssembler implements DTOAssembler<TipoProductoDominio, TipoProductoDto> {

    private static final DTOAssembler<TipoProductoDominio, TipoProductoDto> INSTANCIA = new TipoProductoDtoAssembler();

    private TipoProductoDtoAssembler() {
    }

    public static DTOAssembler<TipoProductoDominio, TipoProductoDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public TipoProductoDto convertirADTO(TipoProductoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new TipoProductoDominio.Builder().build());
        var dto = new TipoProductoDto();
        dto.setId(dominioSeguro.getId());
        dto.setNombre(dominioSeguro.getNombre());
        return dto;
    }

    @Override
    public TipoProductoDominio convertirADominio(TipoProductoDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new TipoProductoDto());
        return new TipoProductoDominio.Builder()
                .id(dtoSeguro.getId())
                .nombre(dtoSeguro.getNombre())
                .build();
    }

    @Override
    public List<TipoProductoDto> convertirADTO(List<TipoProductoDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<TipoProductoDominio>());

        var listaDto = new ArrayList<TipoProductoDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
