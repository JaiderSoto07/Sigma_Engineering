package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;

import java.util.UUID;

public class AsegurarQueIngredienteExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarQueIngredienteExisteRule();

    private AsegurarQueIngredienteExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idDetalleReceta = (UUID) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var IngredienteEncontrado = daoFactory.obtenerDetalleRecetaDAO().consultarPorId(idDetalleReceta);

        if (UtilId.VALOR_DEFECTO.equals(IngredienteEncontrado.getId())) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.EL_INGREDIENTE_DE_LA_RECETA_NO_EXISTE;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

}
