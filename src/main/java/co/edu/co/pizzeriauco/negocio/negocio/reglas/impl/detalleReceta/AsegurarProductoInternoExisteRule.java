package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;

import java.util.UUID;

public class AsegurarProductoInternoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarProductoInternoExisteRule();

    private AsegurarProductoInternoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idProductoInterno = (UUID) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var productoInternoEncontrado = daoFactory.obtenerProductoInternoDAO().consultarPorId(idProductoInterno);

        if (UtilId.VALOR_DEFECTO.equals(productoInternoEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_DETALLE_RECETA_NO_EXISTE;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
