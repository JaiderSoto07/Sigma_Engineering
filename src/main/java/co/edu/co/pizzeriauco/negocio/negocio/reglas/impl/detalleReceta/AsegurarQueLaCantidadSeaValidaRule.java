package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;

import java.math.BigDecimal;

public class AsegurarQueLaCantidadSeaValidaRule implements Rule<Object> {
    private static final BigDecimal CANTIDAD_MAXIMA = new BigDecimal("10000");

    private static final Rule<Object> instancia = new AsegurarQueLaCantidadSeaValidaRule();

    private AsegurarQueLaCantidadSeaValidaRule() {
    }

    public static final Rule<Object> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(Object... datos) {
        var cantidad= (BigDecimal) datos[0];
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
