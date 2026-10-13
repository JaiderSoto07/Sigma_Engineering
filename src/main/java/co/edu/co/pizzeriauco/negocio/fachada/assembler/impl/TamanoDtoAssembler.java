package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.dto.TamanoDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class TamanoDtoAssembler implements DTOAssembler<TamanoDominio, TamanoDto> {

    private static final DTOAssembler<TamanoDominio, TamanoDto> INSTANCIA = new TamanoDtoAssembler();

    private TamanoDtoAssembler() {
    }

    public static DTOAssembler<TamanoDominio, TamanoDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public TamanoDto convertirADTO(TamanoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new TamanoDominio.Builder().build());
        var dto = new TamanoDto();
        dto.setId(dominioSeguro.getId());
        dto.setTamano(dominioSeguro.getTamano());
        return dto;
    }

    @Override
    public TamanoDominio convertirADominio(TamanoDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new TamanoDto());
        return new TamanoDominio.Builder()
                .id(dtoSeguro.getId())
                .tamano(dtoSeguro.getTamano())
                .build();
    }

    @Override
    public List<TamanoDto> convertirADTO(List<TamanoDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<TamanoDominio>());

        var listaDto = new ArrayList<TamanoDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
