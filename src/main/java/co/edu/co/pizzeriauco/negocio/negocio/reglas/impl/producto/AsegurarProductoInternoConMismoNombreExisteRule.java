package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.producto;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;

public class AsegurarProductoInternoConMismoNombreExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarProductoInternoConMismoNombreExisteRule();

    private AsegurarProductoInternoConMismoNombreExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var nombre = (String) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var filtro = new ProductoInternoEntidad.Builder().nombre(nombre).build();

        var resultado = daoFactory.obtenerProductoInternoDAO().consultarPorFiltro(filtro);

        if (resultado.isEmpty()) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.PRODUCTO_INTERNO_PRODUCTO_NO_EXISTE;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
