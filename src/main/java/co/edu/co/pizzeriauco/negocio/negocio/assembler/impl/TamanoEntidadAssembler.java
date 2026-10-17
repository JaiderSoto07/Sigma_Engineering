package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.util.ArrayList;
import java.util.List;

public final class TamanoEntidadAssembler implements EntidadAssembler<TamanoDominio, TamanoEntidad> {

    private static final EntidadAssembler<TamanoDominio, TamanoEntidad> INSTANCIA = new TamanoEntidadAssembler();

    private TamanoEntidadAssembler() {
    }

    public static EntidadAssembler<TamanoDominio, TamanoEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public TamanoEntidad convertirAEntidad(TamanoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new TamanoDominio.Builder().build());
        return new TamanoEntidad.Builder()
                .id(dominioSeguro.getId())
                .tamano(dominioSeguro.getTamano())
                .build();
    }

    @Override
    public TamanoDominio convertirADominio(TamanoEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new TamanoEntidad.Builder().build());
        return new TamanoDominio.Builder()
                .id(entidadSegura.getId())
                .tamano(entidadSegura.getTamano())
                .build();
    }

    @Override
    public List<TamanoDominio> convertirADominio(List<TamanoEntidad> listaEntidades) {

        var listaSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(listaEntidades, new ArrayList<TamanoEntidad>());
        var listaDominio = new ArrayList<TamanoDominio>();
        for (var entidad : listaSegura) {
            listaDominio.add(convertirADominio(entidad));
        }
        return listaDominio;
    }
}
