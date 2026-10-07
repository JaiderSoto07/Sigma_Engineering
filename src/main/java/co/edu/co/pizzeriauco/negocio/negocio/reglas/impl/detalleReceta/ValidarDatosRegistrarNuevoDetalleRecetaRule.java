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

    private static final BigDecimal CANTIDAD_MAXIMA = new BigDecimal("10000");
    private static final int CANTIDAD_MAXIMA_DECIMALES = 4;

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
        validarUnidadMedida(dominio.getUnidadMedida().getId());
        validarCantidad(dominio.getCantidad());
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

    private void validarUnidadMedida(UUID idUnidadMedida) {
        if (UtilId.VALOR_DEFECTO.equals(idUnidadMedida)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.UNIDAD_MEDIDA_DETALLE_RECETA_OBLIGATORIA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
    private void validarCantidad(BigDecimal cantidad) {
        if (!UtilNumero.mayorQue(cantidad, BigDecimal.ZERO)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.CANTIDAD_DETALLE_RECETA_OBLIGATORIA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
        if (!UtilNumero.menorIgual(cantidad, CANTIDAD_MAXIMA)) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaNegocioImpl.CANTIDAD_DETALLE_RECETA_FUERA_DE_RANGO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }
}
