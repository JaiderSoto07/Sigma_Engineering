package co.edu.co.pizzeriauco.transversal.utilitario;

import java.math.BigDecimal;

public class UtilNumero {
    public static final int cero = 0;

    public static final BigDecimal STOCK_MINIMO_POR_DEFECTO = BigDecimal.ONE;
    private UtilNumero(){
    }

    public static <n extends Number> n obtenerValorDefecto(n valor, n valorDefecto){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }

    public static <n extends Number> Number obtenerValorDefecto(n valor){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, cero);
    }

    public static <n extends Number> n obtenerValorDefectoSiEsNuloONegativo(n valor, n valorDefecto){
        return UtilObjeto.esNulo(valor) || valor.doubleValue() < 0 ? valorDefecto : valor;
    }

    public static <n extends Number> boolean mayorQue(n numeroUno, n numeroDos){
        return obtenerValorDefecto(numeroUno).doubleValue() > obtenerValorDefecto(numeroDos).doubleValue();
    }
    public static <n extends Number> boolean menorQue(n numeroUno, n numeroDos){
        return obtenerValorDefecto(numeroUno).doubleValue() < obtenerValorDefecto(numeroDos).doubleValue();
    }
    public static <n extends Number> boolean mayorIgual(n numeroUno, n numeroDos){
        return obtenerValorDefecto(numeroUno).doubleValue() >= obtenerValorDefecto(numeroDos).doubleValue();
    }
    public static <n extends Number> boolean menorIgual(n numeroUno, n numeroDos){
        return obtenerValorDefecto(numeroUno).doubleValue() <= obtenerValorDefecto(numeroDos).doubleValue();
    }
    public static <n extends Number> boolean diferente(n numeroUno, n numeroDos){
        return obtenerValorDefecto(numeroUno).doubleValue() != obtenerValorDefecto(numeroDos).doubleValue();
    }

    public static <n extends Number> boolean valorEntreUnRangoYOtroSinIncluirlos(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() < obtenerValorDefecto(numeroTres).doubleValue();
    }

    public static <n extends Number> boolean valorEntreUnRangoIncluyeElPrimero(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >=  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() < obtenerValorDefecto(numeroTres).doubleValue();
    }
    public static <n extends Number> boolean valorEntreUnRangoIncluyeElUltimo(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() <= obtenerValorDefecto(numeroTres).doubleValue();
    }
    public static <n extends Number> boolean valorEntreUnRangoIncluyendoAmbos(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >=  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() <= obtenerValorDefecto(numeroTres).doubleValue();
    }

}
