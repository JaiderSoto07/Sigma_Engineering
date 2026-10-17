package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class UnidadMedidaEntidadAssembler implements EntidadAssembler<UnidadMedidaDominio, UnidadMedidaEntidad> {
    private static final EntidadAssembler<UnidadMedidaDominio, UnidadMedidaEntidad> INSTANCIA = new UnidadMedidaEntidadAssembler();

    private UnidadMedidaEntidadAssembler(){};

    public static EntidadAssembler<UnidadMedidaDominio, UnidadMedidaEntidad> getInstance(){
        return INSTANCIA;
    }



    @Override
    public UnidadMedidaEntidad convertirAEntidad(UnidadMedidaDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new UnidadMedidaDominio.Builder().build());
        return new UnidadMedidaEntidad.Builder()
                .id(dominioSeguro.getId())
                .unidadMedida(dominioSeguro.getUnidadMedida())
                .tipoMedida(dominioSeguro.getTipoMedida())
                .build();

    }

    @Override
    public UnidadMedidaDominio convertirADominio(UnidadMedidaEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new UnidadMedidaEntidad.Builder().build());
        return new UnidadMedidaDominio.Builder()
                .id(entidadSegura.getId())
                .unidadMedida(entidadSegura.getUnidadMedida())
                .tipoMedida(entidadSegura.getTipoMedida())
                .build();

    }

    @Override
    public List<UnidadMedidaDominio> convertirADominio(List<UnidadMedidaEntidad> listaEntidades) {
        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaEntidades, new ArrayList<UnidadMedidaEntidad>());
        var listaDominio = new ArrayList<UnidadMedidaDominio>();
        for(var entidad: listaSegura){
            listaDominio.add(convertirADominio(entidad));
        }
        return listaDominio;
    }
}
