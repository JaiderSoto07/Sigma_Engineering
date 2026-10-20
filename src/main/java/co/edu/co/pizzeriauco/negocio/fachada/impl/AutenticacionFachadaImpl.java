package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.CredencialesDto;
import co.edu.co.pizzeriauco.dto.UsuarioDto;
import co.edu.co.pizzeriauco.negocio.fachada.AutenticacionFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.CredencialesDtoAssembler;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.UsuarioDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.AutenticacionNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.impl.AutenticacionNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

public class AutenticacionFachadaImpl implements AutenticacionFachada {

    private DAOFactory daoFactory;
    private AutenticacionNegocio autenticacionNegocio;

    public AutenticacionFachadaImpl() {
        daoFactory = DAOFactory.obtenerFactoria();
        autenticacionNegocio = new AutenticacionNegocioImpl(daoFactory);
    }

    @Override
    public UsuarioDto iniciarSesion(CredencialesDto credenciales) {
        try {
            var credencialesDominio = CredencialesDtoAssembler.getInstance().convertirADominio(credenciales);
            var usuarioAutenticado = autenticacionNegocio.iniciarSesion(credencialesDominio);
            return UsuarioDtoAssembler.getInstance().convertirADTO(usuarioAutenticado);
        } catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_INICIANDO_SESION;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
