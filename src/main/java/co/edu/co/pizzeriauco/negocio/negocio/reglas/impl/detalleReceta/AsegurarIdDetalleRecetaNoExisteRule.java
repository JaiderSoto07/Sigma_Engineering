package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;

import java.util.UUID;

public class AsegurarIdDetalleRecetaNoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarIdDetalleRecetaNoExisteRule();

    private AsegurarIdDetalleRecetaNoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idDetalleReceta = (UUID) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var detalleRecetaEncontrado = daoFactory.obtenerDetalleRecetaDAO().consultarPorId(idDetalleReceta);

        if (!UtilId.VALOR_DEFECTO.equals(detalleRecetaEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.ID_DETALLE_RECETA_YA_EXISTE;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
