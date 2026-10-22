package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.negocio.fachada.DetalleRecetaFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.DetalleRecetaDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.DetalleRecetaEntidadAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.impl.DetalleRecetaNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleRecetaFachadaImpl implements DetalleRecetaFachada {

    @Override
    public void registrarInformacionNuevoDetalleReceta(DetalleRecetaDto datos) {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

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

    @Override
    public void modificarCantidadIngrediente(UUID id, DetalleRecetaDto datos) {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

        daoFactory.iniciarTransaccion();
        try {
            var detalleRecetaDominio = DetalleRecetaDtoAssembler.getInstance().convertirADominio(datos);
            detalleRecetaNegocio.modificarCantidadIngrediente(id,detalleRecetaDominio);
            daoFactory.confirmarTransaccion();

        } catch (PizzeriaExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_MODIFICANDO_CANTIDAD_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void retirarIngredienteDeReceta(UUID id) {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

        daoFactory.iniciarTransaccion();
        try {
            detalleRecetaNegocio.retirarIngredienteDeReceta(id);
            daoFactory.confirmarTransaccion();

        } catch (PizzeriaExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_RETIRANDO_INGREDIENTE_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }



    @Override
    public List<DetalleRecetaDto> consultarPorFiltro(DetalleRecetaDto filtro) {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

        try {
            var filtroDomino = DetalleRecetaDtoAssembler.getInstance().convertirADominio(filtro);
            var detallesEncontrados = detalleRecetaNegocio.consultarPorFiltro(filtroDomino);
            return DetalleRecetaDtoAssembler.getInstance().convertirADTO(detallesEncontrados);
        }catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_POR_FILTRO_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<DetalleRecetaDto> consultarRecetasDeProductosActivos() {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

        try {
            //aqui los tengo en dominio los quiero convertir a dto
            var detallesEncontrados = detalleRecetaNegocio.consultarRecetasDeProductosActivos();
            return DetalleRecetaDtoAssembler.getInstance().convertirADTO(detallesEncontrados);
        }catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PRODUCTOS_ACTIVOS_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<DetalleRecetaDto> consultarRecetasDeProductosInactivos() {
        var daoFactory = DAOFactory.obtenerFactoria();
        var detalleRecetaNegocio = new DetalleRecetaNegocioImpl(daoFactory);

        try {
            //aqui los tengo en dominio los quiero convertir a dto
            var detallesEncontrados = detalleRecetaNegocio.consultarRecetasDeProductosInactivos();
            return DetalleRecetaDtoAssembler.getInstance().convertirADTO(detallesEncontrados);
        }catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PRODUCTOS_INACTIVOS_DETALLE_RECETA;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
