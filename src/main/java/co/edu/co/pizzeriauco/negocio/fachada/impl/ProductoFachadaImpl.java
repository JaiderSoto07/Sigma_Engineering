package co.edu.co.pizzeriauco.negocio.fachada.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dto.ProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.ProductoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.assembler.impl.ProductoDtoAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.ProductoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.impl.ProductoNegocioImpl;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaFachadaExcepcion;

import java.util.List;

public class ProductoFachadaImpl implements ProductoFachada {

    private DAOFactory daoFactory;
    private ProductoNegocio productoNegocio;

    public ProductoFachadaImpl() {
        daoFactory = DAOFactory.obtenerFactoria();
        productoNegocio = new ProductoNegocioImpl(daoFactory);
    }

    @Override
    public void registrarInformacionNuevoProducto(ProductoDto datos) {

        daoFactory.iniciarTransaccion();

        try {
            var productoDominio = ProductoDtoAssembler.getInstance().convertirADominio(datos);
            productoNegocio.registrarInformacionNuevoProducto(productoDominio);
            daoFactory.confirmarTransaccion();

        } catch (PizzeriaExcepcion excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;
        } catch (Exception excepcion) {
            daoFactory.cancelarTransaccion();

            var mensajeUsuario = CatalogoMensajes.ProductoFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_REGISTRANDO_PRODUCTO;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<ProductoDto> consultarActivos() {
        try {
            //aqui los tengo en dominio los quiero convertir a dto
            var productosEncontrados = productoNegocio.consultarActivos();
            return ProductoDtoAssembler.getInstance().convertirADTO(productosEncontrados);
        } catch (PizzeriaExcepcion excepcion) {
            throw excepcion;
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoFachadaImpl.USUARIO_ERROR_PROBLEMA_INESPERADO_CONSULTANDO_PRODUCTOS_ACTIVOS;
            throw PizzeriaFachadaExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
