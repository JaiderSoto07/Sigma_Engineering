package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.ProductoInternoDto;
import co.edu.co.pizzeriauco.negocio.fachada.ProductoInternoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.ProductoInternoDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.ProductoInternoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.impl.ProductoInternoNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

import java.util.List;

public class ProductoInternoFachadaImpl implements ProductoInternoFachada {

    private DAOFactory daoFactory;
    private ProductoInternoNegocio productoInternoNegocio;

    public ProductoInternoFachadaImpl() {
        daoFactory = DAOFactory.obtenerFactoria();
        productoInternoNegocio = new ProductoInternoNegocioImpl(daoFactory);
    }

    @Override
    public List<ProductoInternoDto> consultarActivos() {
        try {
            var productosInternosEncontrados = productoInternoNegocio.consultarActivos();
            return ProductoInternoDtoAssembler.getInstance().convertirADTO(productosInternosEncontrados);
        } catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoInternoFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PRODUCTOS_INTERNOS_ACTIVOS;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
