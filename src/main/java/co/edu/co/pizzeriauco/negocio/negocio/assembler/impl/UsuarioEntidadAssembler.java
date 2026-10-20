package co.edu.co.pizzeriauco.negocio.negocio.assembler.impl;

import co.edu.co.pizzeriauco.dominio.UsuarioDominio;
import co.edu.co.pizzeriauco.entidad.UsuarioEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.EntidadAssembler;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

    public final class UsuarioEntidadAssembler implements EntidadAssembler<UsuarioDominio, UsuarioEntidad> {

    private static final EntidadAssembler<UsuarioDominio, UsuarioEntidad> INSTANCIA = new UsuarioEntidadAssembler();

    private UsuarioEntidadAssembler() {
    }

    public static EntidadAssembler<UsuarioDominio, UsuarioEntidad> getInstance() {
        return INSTANCIA;
    }

    @Override
    public UsuarioEntidad convertirAEntidad(UsuarioDominio dominio) {
        var dominioSeguro = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(dominio, new UsuarioDominio.Builder().build());
        return new UsuarioEntidad.Builder()
                .id(dominioSeguro.getId())
                .usuario(dominioSeguro.getUsuario())
                .clave(dominioSeguro.getClave())
                .rol(dominioSeguro.getRol())
                .activo(dominioSeguro.isActivo())
                .build();
    }

    @Override
    public UsuarioDominio convertirADominio(UsuarioEntidad entidad) {
        var entidadSegura = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(entidad, new UsuarioEntidad.Builder().build());
        return new UsuarioDominio.Builder()
                .id(entidadSegura.getId())
                .usuario(entidadSegura.getUsuario())
                .rol(entidadSegura.getRol())
                .activo(entidadSegura.isActivo())
                .build();
    }
}
