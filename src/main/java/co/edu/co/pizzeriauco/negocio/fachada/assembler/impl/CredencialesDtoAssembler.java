package co.edu.co.pizzeriauco.negocio.fachada.assembler.impl;

import co.edu.co.pizzeriauco.dominio.UsuarioDominio;
import co.edu.co.pizzeriauco.dto.CredencialesDto;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.DTOAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class CredencialesDtoAssembler implements DTOAssembler<UsuarioDominio, CredencialesDto> {

    private static final DTOAssembler<UsuarioDominio, CredencialesDto> INSTANCIA = new CredencialesDtoAssembler();

    private CredencialesDtoAssembler() {
    }

    public static DTOAssembler<UsuarioDominio, CredencialesDto> getInstance() {
        return INSTANCIA;
    }

    @Override
    public CredencialesDto convertirADTO(UsuarioDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new UsuarioDominio.Builder().build());
        var dto = new CredencialesDto();
        dto.setUsuario(dominioSeguro.getUsuario());
        return dto;
    }

    @Override
    public UsuarioDominio convertirADominio(CredencialesDto dto) {
        var dtoSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dto, new CredencialesDto());
        return new UsuarioDominio.Builder()
                .usuario(dtoSeguro.getUsuario())
                .clave(dtoSeguro.getClave())
                .build();
    }

    @Override
    public List<CredencialesDto> convertirADTO(List<UsuarioDominio> listaDominios) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaDominios, new ArrayList<UsuarioDominio>());

        var listaDto = new ArrayList<CredencialesDto>();
        for (var dominio : listaSegura) {
            listaDto.add(convertirADTO(dominio));
        }
        return listaDto;
    }
}
