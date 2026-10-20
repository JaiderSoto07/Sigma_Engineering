package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.autenticacion;

import co.edu.co.pizzeriauco.dominio.UsuarioDominio;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

public class ValidarDatosCredencialesRule implements Rule<UsuarioDominio> {

    private static final Rule<UsuarioDominio> instancia = new ValidarDatosCredencialesRule();

    private ValidarDatosCredencialesRule() {
    }

    public static final Rule<UsuarioDominio> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(UsuarioDominio... datos) {
        var dominio = datos[0];

        validarObligatoriedadDatos(dominio);
        validarUsuario(dominio.getUsuario());
        validarClave(dominio.getClave());
    }

    private void validarObligatoriedadDatos(UsuarioDominio dominio) {
        if (UtilObjeto.esNulo(dominio)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.DATOS_CREDENCIALES_OBLIGATORIOS;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarUsuario(String usuario) {
        if (UtilTexto.getUtilTexto().esVacia(usuario)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.USUARIO_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
        if (!UtilTexto.getUtilTexto().longitudCadenaEsValida(usuario, 1, 30, true)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.USUARIO_LONGITUD_NO_VALIDA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarClave(String clave) {
        if (UtilTexto.getUtilTexto().esVacia(clave)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.CLAVE_OBLIGATORIA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
        if (!UtilTexto.getUtilTexto().longitudCadenaEsValida(clave, 1, 72, false)) {
            var mensajeUsuario = CatalogoMensajes.AutenticacionNegocioImpl.CLAVE_LONGITUD_NO_VALIDA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

}
