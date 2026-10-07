package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;

import java.util.UUID;

public class AsegurarProductoInternoNoRepetidoEnRecetaRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarProductoInternoNoRepetidoEnRecetaRule();

    private AsegurarProductoInternoNoRepetidoEnRecetaRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idProducto = (UUID) datos[0];
        var idProductoInterno = (UUID) datos[1];
        var daoFactory = (DAOFactory) datos[2];

        var filtro = new DetalleRecetaEntidad.Builder()
                .producto(new ProductoEntidad.Builder().id(idProducto).build())
                .productoInterno(new ProductoInternoEntidad.Builder().id(idProductoInterno).build())
                .build();

        var resultado = daoFactory.obtenerDetalleRecetaDAO().consultarPorFiltro(filtro);

        if (!resultado.isEmpty()) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_YA_REGISTRADO_EN_RECETA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
