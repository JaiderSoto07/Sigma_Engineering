package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.UsuarioDominio;
import co.edu.co.pizzeriauco.dto.UsuarioDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioDtoAssembler implements DTOAssembler<UsuarioDominio, UsuarioDto> {

    private static final DTOAssembler<UsuarioDominio, UsuarioDto> INSTANCIA = new UsuarioDtoAssembler();

    private UsuarioDtoAssembler() {
    }

    public static DTOAssembler<UsuarioDominio, UsuarioDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public UsuarioDto convertirADTO(UsuarioDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new UsuarioDominio.Builder().build());
        var dto = new UsuarioDto();
        dto.setId(dominioSeguro.getId());
        dto.setUsuario(dominioSeguro.getUsuario());
        dto.setRol(dominioSeguro.getRol());
        return dto;
    }

    @Override
    public UsuarioDominio convertirADominio(UsuarioDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new UsuarioDto());
        return new UsuarioDominio.Builder()
                .id(dtoSeguro.getId())
                .usuario(dtoSeguro.getUsuario())
                .rol(dtoSeguro.getRol())
                .build();
    }

    @Override
    public List<UsuarioDto> convertirADTO(List<UsuarioDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<UsuarioDominio>());

        var listaDto = new ArrayList<UsuarioDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
