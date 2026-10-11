package co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta;

import co.edu.co.pizzeriauco.negocio.negocio.reglas.Rule;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;

import java.math.BigDecimal;

public class AsegurarQueLaCantidadSeaValidaRule implements Rule<BigDecimal> {
    private static final BigDecimal CANTIDAD_MAXIMA = new BigDecimal("10000");

    private static final Rule<BigDecimal> instancia = new AsegurarQueLaCantidadSeaValidaRule();

    private AsegurarQueLaCantidadSeaValidaRule() {
    }

    public static final Rule<BigDecimal> obtenerInstancia() {
        return instancia;
    }

    @Override
    public void ejecutar(BigDecimal... datos) {
        var cantidad=  datos[0];
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
