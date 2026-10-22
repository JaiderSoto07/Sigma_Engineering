package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.TamanoDto;
import co.edu.co.pizzeriauco.negocio.fachada.TamanoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.TamanoDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.impl.TamanoNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

import java.util.List;

public class TamanoFachadaImpl implements TamanoFachada {

    @Override
    public List<TamanoDto> consultarTodos() {
        var daoFactory = DAOFactory.obtenerFactoria();
        var tamanoNegocio = new TamanoNegocioImpl(daoFactory);

        try {
            var tamanosEncontrados = tamanoNegocio.consultarTodos();
            return TamanoDtoAssembler.getInstance().convertirADTO(tamanosEncontrados);
        } catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.TamanoFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_TODOS_LOS_TAMANOS;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
