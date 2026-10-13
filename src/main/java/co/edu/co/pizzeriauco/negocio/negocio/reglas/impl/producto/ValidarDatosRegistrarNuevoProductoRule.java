package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.producto;

import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.math.BigDecimal;
import java.util.UUID;

public class ValidarDatosRegistrarNuevoProductoRule implements Rule<ProductoDominio> {

    private static final Rule<ProductoDominio> instancia = new ValidarDatosRegistrarNuevoProductoRule();

    private ValidarDatosRegistrarNuevoProductoRule() {
    }

    public static final Rule<ProductoDominio> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(ProductoDominio... datos) {
        var dominio = datos[0];

        validarObligatoriedadDatos(dominio);
        validarNombre(dominio.getNombre());
        validarTipoProducto(dominio.getTipoProducto().getId());
        validarTamano(dominio.getTamano().getId());
        validarPrecio(dominio.getPrecio());
    }

    private void validarObligatoriedadDatos(ProductoDominio dominio) {
        if (UtilObjeto.esNulo(dominio)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.DATOS_PRODUCTO_OBLIGATORIOS;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarNombre(String nombre) {
        if (UtilTexto.getUtilTexto().esVacia(nombre)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.NOMBRE_PRODUCTO_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
        if (!UtilTexto.getUtilTexto().longitudCadenaEsValida(nombre, 1, 40, true)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.NOMBRE_PRODUCTO_LONGITUD_NO_VALIDA;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarTipoProducto(UUID idTipoProducto) {
        if (UtilId.VALOR_DEFECTO.equals(idTipoProducto)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.TIPO_PRODUCTO_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarTamano(UUID idTamano) {
        if (UtilId.VALOR_DEFECTO.equals(idTamano)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.TAMANO_PRODUCTO_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

    private void validarPrecio(BigDecimal precio) {
        if (!UtilNumero.mayorQue(precio, BigDecimal.ZERO)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.PRECIO_PRODUCTO_OBLIGATORIO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
        if (!UtilNumero.menorIgual(precio, 1000000)) {
            var mensajeUsuario = CatalogoMensajes.ProductoNegocioImpl.PRECIO_PRODUCTO_FUERA_DE_RANGO;
            throw PizzeriaNegocioExcepcion.crear(mensajeUsuario);
        }
    }

}
