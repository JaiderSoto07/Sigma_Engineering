package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.entidad.UsuarioEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilClave;

public class AsegurarCredencialesValidasRule implements Rule<Object> {

    private static final Rule<Object> instancia = new AsegurarCredencialesValidasRule();

    private AsegurarCredencialesValidasRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var usuario = (String) datos[0];
        var clave = (String) datos[1];
        var daoFactory = (DAOFactory) datos[2];

        var filtro = new UsuarioEntidad.Builder().usuario(usuario).build();
        var resultado = daoFactory.obtenerUsuarioDAO().consultarPorFiltro(filtro);

        if (resultado.isEmpty()) {
            lanzarCredencialesInvalidas();
        }

        var usuarioEncontrado = resultado.get(0);
        if (!usuarioEncontrado.isActivo() || !UtilClave.coincide(clave, usuarioEncontrado.getClave())) {
            lanzarCredencialesInvalidas();
        }
    }

    private void lanzarCredencialesInvalidas() {
        var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.CREDENCIALES_NO_VALIDAS;
        throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
    }
}
