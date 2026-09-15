package co.edu.co.pizzeriauco.crosscuting.utilitario;

public class UtilTexto {
    //The objective of this class is proporcionar operacioes comunes para trabajar con String
    //Una clase de utilidad busca centralizar operaciones que se repiten y darles un comportamiento Consistente

    //Solamente la propia clase puede puede acceder directamente a ese variable
    //va a guardar un objeto de utiltexto
    //Static lo que hace es que cada objeto pueda compartir ese valor pero ese valor va a ser el mismo en toda mi clase
    private static UtilTexto instancia;

    //puedo acceder a esta variable desde otras clases , hay un solo valor por vacio (por eso el static)
    //y es de tipo string
    public static String vacia = "";

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

    public boolean esVacia(String texto) {
        if(esNula(texto)){
            texto= vacia;
        }
        return vacia.equals(texto);
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