package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.producto;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;

import java.util.UUID;

public class AsegurarProductoNoRepetidoRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarProductoNoRepetidoRule();

    private AsegurarProductoNoRepetidoRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var nombre = (String) datos[0];
        var idTamano = (UUID) datos[1];
        var daoFactory = (DAOFactory) datos[2];

        var filtro = new ProductoEntidad.Builder()
                .nombre(nombre)
                .tamano(new TamanoEntidad.Builder().id(idTamano).build())
                .build();

        var resultado = daoFactory.obtenerProductoDAO().consultarPorFiltro(filtro);

        if (!resultado.isEmpty()) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.PRODUCTO_YA_REGISTRADO_CON_NOMBRE_Y_TAMANO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
