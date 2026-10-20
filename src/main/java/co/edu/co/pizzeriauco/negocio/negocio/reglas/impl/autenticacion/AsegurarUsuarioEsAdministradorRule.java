package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion;

import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;

public class AsegurarUsuarioEsAdministradorRule implements Rule<String> {

    public static final String ROL_ADMINISTRADOR = "Administrador";

    private static final Rule<String> instancia = new AsegurarUsuarioEsAdministradorRule();

    private AsegurarUsuarioEsAdministradorRule() {
    }

    public static final Rule<String> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(String... datos) {
        var rol = datos[0];

        if (!ROL_ADMINISTRADOR.equals(rol)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.USUARIO_SIN_PERMISO_PARA_INGRESAR;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
