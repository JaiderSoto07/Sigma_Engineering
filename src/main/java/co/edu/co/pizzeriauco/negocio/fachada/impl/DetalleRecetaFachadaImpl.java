package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.negocio.fachada.DetalleRecetaFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.DetalleRecetaDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.DetalleRecetaNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.impl.DetalleRecetaNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

public class DetalleRecetaFachadaImpl implements DetalleRecetaFachada {

    private DAOFactory daoFactory;
    private DetalleRecetaNegocio detalleRecetaNegocio;

    public DetalleRecetaFachadaImpl() {
        daoFactory = DAOFactory.obtenerFactoria();
        detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);
    }

    @Override
    public void registrarInformacionNuevoDetalleReceta(DetalleRecetaDto datos) {

        daoFactory.iniciarTransaccion();

        try {
            var detalleRecetaDominio = DetalleRecetaDtoAssembler.getInstance().convertirADominio(datos);
            detalleRecetaNegocio.registrarInformacionNuevoDetalleReceta(detalleRecetaDominio);
            daoFactory.confirmarTransaccion();

        } catch (PizzeriaExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_REGISTRANDO_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
