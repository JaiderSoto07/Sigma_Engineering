package co.edu.co.pizzeriauco.crosscuting.utilitario;

public class UtilNumero {
    public static int cero=0;
    private UtilNumero(){

    }
    //n extiende de number
    public static <n extends Number> n obtenerValorDefecto(n valor, n valorDefecto){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }

    public static <n extends Number> Number obtenerValorDefecto(n valor){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, cero);
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
    public static <n extends Number> boolean ValorEntreUnRangoYOtroSinIncluirlos(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() < obtenerValorDefecto(numeroTres).doubleValue();
    }

    public static <n extends Number> boolean ValorEntreUnRangoIncluyeElPrimero(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >=  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() < obtenerValorDefecto(numeroTres).doubleValue();
    }
    public static <n extends Number> boolean ValorEntreUnRangoIncluyeElUltimo(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() <= obtenerValorDefecto(numeroTres).doubleValue();
    }
    public static <n extends Number> boolean ValorEntreUnRangoIncluyendoAmbos(n numeroUno, n numeroDos , n numeroTres){
        return obtenerValorDefecto(numeroDos).doubleValue() >=  obtenerValorDefecto(numeroUno).doubleValue()  && obtenerValorDefecto(numeroDos).doubleValue() <= obtenerValorDefecto(numeroTres).doubleValue();
    }



    //utilitarios de fecha , del identificador unico universal
    //Modelo de dominio refinado




}
