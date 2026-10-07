package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;

import java.util.UUID;

public class AsegurarNombreRecetaNoExisteRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarNombreRecetaNoExisteRule();

    private AsegurarNombreRecetaNoExisteRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var idProducto = (UUID) datos[0];
        var daoFactory = (DAOFactory) datos[1];

        var productoReceta = daoFactory.obtenerProductoDAO().consultarPorId(idProducto);

        var filtro = new ProductoEntidad.Builder()
                .nombre(productoReceta.getNombre())
                .tamano(new TamanoEntidad.Builder().id(productoReceta.getTamano().getId()).build())
                .build();

        var resultado = daoFactory.obtenerProductoDAO().consultarPorFiltro(filtro);

        for (var producto : resultado) {
            if (!productoReceta.getId().equals(producto.getId())) {
                var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.EXISTE_OTRA_RECETA_CON_EL_MISMO_NOMBRE;
                throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
            }
        }
    }
}
