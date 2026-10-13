package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

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
}
