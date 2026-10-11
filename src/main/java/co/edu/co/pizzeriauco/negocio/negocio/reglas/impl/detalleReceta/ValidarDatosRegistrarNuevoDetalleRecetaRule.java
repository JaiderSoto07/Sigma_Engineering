package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;

import java.math.BigDecimal;
import java.util.UUID;

public class ValidarDatosRegistrarNuevoDetalleRecetaRule implements Rule<DetalleRecetaDominio> {

    private static final Rule<DetalleRecetaDominio> instancia = new ValidarDatosRegistrarNuevoDetalleRecetaRule();

    private ValidarDatosRegistrarNuevoDetalleRecetaRule() {
    }

    public static final Rule<DetalleRecetaDominio> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(DetalleRecetaDominio... datos) {
        var dominio = datos[0];

        validarObligatoriedadDatos(dominio);
        validarProducto(dominio.getProducto().getId());
        validarProductoInterno(dominio.getProductoInterno().getId());
        AsegurarQueLaCantidadSeaValidaRule.obtenerInstancia().ejecutar(dominio.getCantidad());
    }

    private void validarObligatoriedadDatos(DetalleRecetaDominio dominio) {
        if (UtilObjeto.esNulo(dominio)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.DATOS_DETALLE_RECETA_OBLIGATORIOS;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarProducto(UUID idProducto) {
        if (UtilId.VALOR_DEFECTO.equals(idProducto)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_DETALLE_RECETA_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarProductoInterno(UUID idProductoInterno) {
        if (UtilId.VALOR_DEFECTO.equals(idProductoInterno)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_DETALLE_RECETA_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

}
