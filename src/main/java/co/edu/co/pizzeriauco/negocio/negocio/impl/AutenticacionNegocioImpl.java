package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.UsuarioDominio;
import co.edu.co.pizzeriauco.entidad.UsuarioEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.AutenticacionNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.UsuarioEntidadAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion.AsegurarCredencialesValidasRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion.AsegurarUsuarioEsAdministradorRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion.ValidarDatosCredencialesRule;

public class AutenticacionNegocioImpl implements AutenticacionNegocio {

    private final DAOFactory daoFactory;

    public AutenticacionNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public UsuarioDominio iniciarSesion(UsuarioDominio credenciales) {
        ValidarDatosCredencialesRule.obtenerInstancia().ejecutar(credenciales);
        AsegurarCredencialesValidasRule.obtenerInstancia().ejecutar(credenciales.getUsuario(), credenciales.getClave(), daoFactory);

        var filtro = new UsuarioEntidad.Builder().usuario(credenciales.getUsuario()).build();
        var usuarioEncontrado = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro).get(0);

        AsegurarUsuarioEsAdministradorRule.obtenerInstancia().ejecutar(usuarioEncontrado.getRol());

        return UsuarioEntidadAssembler.getInstance().convertirADominio(usuarioEncontrado);
    }

}
