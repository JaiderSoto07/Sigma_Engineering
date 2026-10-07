package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;

import java.util.UUID;

public class AsegurarProductoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarProductoExisteRule();

    private AsegurarProductoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idProducto = (UUID) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var productoEncontrado = daoFactory.obtenerProductoDAO().consultarPorId(idProducto);

        if (UtilId.VALOR_DEFECTO.equals(productoEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_DETALLE_RECETA_NO_EXISTE;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
