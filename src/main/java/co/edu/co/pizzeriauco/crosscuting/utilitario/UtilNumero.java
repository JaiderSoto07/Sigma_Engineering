package co.edu.co.pizzeriauco.crosscuting.utilitario;

import java.math.BigDecimal;

public class UtilNumero {
    public static final int cero = 0;

    //stock minimo con el que nace un inventario (1 kg, 1 l o 1 und segun el insumo);
    //luego el administrador lo ajusta con "Establecer stock minimo"
    public static final BigDecimal STOCK_MINIMO_POR_DEFECTO = BigDecimal.ONE;
    private UtilNumero(){

    }
    //n extiende de number
    public static <n extends Number> n obtenerValorDefecto(n valor, n valorDefecto){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }

    public static <n extends Number> Number obtenerValorDefecto(n valor){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, cero);
    }

    //si el valor es nulo o negativo, devuelvo el valor por defecto
    //asi no se repite en cada clase la misma pregunta
    public static <n extends Number> n obtenerValorDefectoSiEsNuloONegativo(n valor, n valorDefecto){
        return UtilObjeto.esNulo(valor) || valor.doubleValue() < 0 ? valorDefecto : valor;
    }

    // doubleValue() dice no importa que tipo especifico de Number tengas dame su valor como double
    //para asi poderlo comparar
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

    //valor que esta entre un rango y otro
    //el valor por defecto para que en caso
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



    //utilitarios de fecha , del identificador unico universal
    //Modelo de dominio refinado




}
