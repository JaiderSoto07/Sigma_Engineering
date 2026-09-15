package co.edu.co.pizzeriauco.crosscuting.utilitario;

public class UtilObjeto {
    //solo la misma clase puede crear objetos de este tipo
    private UtilObjeto(){

    }
    //public que se puede llamar desde otras clases este metodo , esta accion
    // y static que se puede llamar directamente sin necesidad de crear un objeto de la clase primero

    // <O> significa que esta clase puede recibir diferentes tipos de parametros
    // O puede ser un int , puede ser un str
    // esta funcion se creo para no escribir cada vez objeto == null?

    public static <O> boolean esNulo(O objeto){
        return objeto == null; // retorna si el objeto es nulo o no con true or false
    }


    //public static , se puede llamar desde otras clases directamente el metodo sin necesidad de crear
    //un objeto de esta clase antes
    //<O> O , digo voy a recibir un tipo <O>  de parametro en esta clase, este parametro es generico , puede
    //ser int , str ect y cuando digo O luego , e spor que digo qeu la funcion me va a devolver un parametro de ese tipo
    //ya luego se crean las variables valor y valor por defecto , se les pone O al inicio por que van a ser de ese tipo
    public static <O> O obtenerValorDefectoSiValorOriginalEsNulo(O valor , O valorDefecto){
        //Aqui pregunta si valor es nulo , si true  ent retorna el valor por defecto
        // si es false ent retorna el mismo valor
        return esNulo(valor) ? valorDefecto : valor;
    }
}
