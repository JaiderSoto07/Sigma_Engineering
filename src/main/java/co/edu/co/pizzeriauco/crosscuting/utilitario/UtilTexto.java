package co.edu.co.pizzeriauco.crosscuting.utilitario;

public class UtilTexto {

    private static UtilTexto instancia;

    //puedo acceder a esta variable desde otras clases , hay un solo valor por vacio (por eso el static)
    //y es de tipo string
    public static final String vacia = "";

    //cliente por defecto de la venta cuando no me dicen quien compro (10 digitos, como en el Excel)
    public static final String CLIENTE_POR_DEFECTO = "2222222222";



    //Solo la propia clase puede llamar este constructor , es el que dice creemos un objeto de tipo UtilTexto
    private UtilTexto() {
    }

    //estamos declarando un metodo , el cual es una accion que el objeto va a poder hacer
    //Static para poder llamar directamente este metodo desde afuera sin necesidad de nates a ver creado
    // un objeto

    public static UtilTexto getUtilTexto() {
        //Solo un objeto a la vez puede entrar a crear la variable isntancia
        //Por eso el synchronized para asegurar con exito la creacion de instancia
        //que debe ser la misma para cada objeto
            synchronized (UtilTexto.class) {
                //pregunta si instancia es nula ent se crea si no , la retorn
                if (UtilObjeto.esNulo(instancia)) {
                    instancia = new UtilTexto();
                }
            }
            return instancia;
    }


    public boolean esNula(String texto) {
        return UtilObjeto.esNulo(texto);
    }

    //un texto con solo espacios tambien se considera vacio
    public boolean esVacia(String texto) {
        return vacia.equals(quitarEspaciosEnBlanco(texto));
    }

    public boolean esCorreoValido(String correo) {
        return quitarEspaciosEnBlanco(correo)
                .matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }


    public static String obtenerValorDefecto(String valor, String valorDefecto) {
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }
    //cuando no me ingresan el valor por defecto , ent el valor defecto va a ser vacia
    public  String obtenerValorDefecto(String valor){
        return obtenerValorDefecto(valor,vacia);
    }
    //aqui se hace ese valor por defecto para que si el valor es null , esto me devuelva vacia

    public String quitarEspaciosEnBlanco(String valor){
        return obtenerValorDefecto(valor).trim();
    }

    //quita espacios y deja la primera letra en mayuscula y el resto en minuscula
    //asi "pizza", "PIZZA" o " pIzZa " se guardan igual: "Pizza"
    public String primeraLetraMayuscula(String valor){
        var valorSanitizado = quitarEspaciosEnBlanco(valor).toLowerCase();
        return esVacia(valorSanitizado)
                ? vacia
                : valorSanitizado.substring(0, 1).toUpperCase() + valorSanitizado.substring(1);
    }

    public int obtenerLongitudCadena(String valor){
        return obtenerValorDefecto(valor).length();
    }
     //la persona entre por consola y dice si si o si no
    public int obtenerLongitudCadena(String valor, boolean quitarEspaciosEnBlanco){

        return quitarEspaciosEnBlanco ?
                obtenerLongitudCadena(quitarEspaciosEnBlanco(valor)): obtenerLongitudCadena(valor);
    }

     //condición ? si_es_true : si_es_false
    public boolean longitudCadenaEsValida(String valor, int longitudInicial , int longitudFinal, boolean quitarEspaciosEnBlanco){

        var valorSanitizado = quitarEspaciosEnBlanco ? quitarEspaciosEnBlanco(valor): valor;

        return obtenerLongitudCadena(valorSanitizado)>= longitudInicial && obtenerLongitudCadena(valorSanitizado)<=longitudFinal;
    }

}