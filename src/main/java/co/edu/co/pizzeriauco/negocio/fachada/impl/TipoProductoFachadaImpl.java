package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.TipoProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.TipoProductoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.TipoProductoDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.TipoProductoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.impl.TipoProductoNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

import java.util.List;

public class TipoProductoFachadaImpl implements TipoProductoFachada {

    private DAOFactory daoFactory;
    private TipoProductoNegocio tipoProductoNegocio;

    public TipoProductoFachadaImpl() {
        daoFactory = DAOFactory.obtenerFactoria();
        tipoProductoNegocio = new TipoProductoNegocioImpl(daoFactory);
    }

    @Override
    public List<TipoProductoDto> consultarTodos() {
        try {
            var tiposProductoEncontrados = tipoProductoNegocio.consultarTodos();
            return TipoProductoDtoAssembler.getInstance().convertirADTO(tiposProductoEncontrados);
        } catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.TipoProductoFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_TODOS_LOS_TIPOS_PRODUCTO;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
