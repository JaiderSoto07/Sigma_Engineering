package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

public final class TipoProductoEntidadAssembler implements EntidadAssembler<TipoProductoDominio, TipoProductoEntidad> {

    private static final EntidadAssembler<TipoProductoDominio, TipoProductoEntidad> INSTANCIA = new TipoProductoEntidadAssembler();

    private TipoProductoEntidadAssembler() {
    }

    public static EntidadAssembler<TipoProductoDominio, TipoProductoEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public TipoProductoEntidad convertirAEntidad(TipoProductoDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new TipoProductoDominio.Builder().build());
        return new TipoProductoEntidad.Builder()
                .id(dominioSeguro.getId())
                .nombre(dominioSeguro.getNombre())
                .build();
    }

    @Override
    public TipoProductoDominio convertirADominio(TipoProductoEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new TipoProductoEntidad.Builder().build());
        return new TipoProductoDominio.Builder()
                .id(entidadSegura.getId())
                .nombre(entidadSegura.getNombre())
                .build();
    }
}
