package co.edu.co.pizzeriauco.transversal.utilitario;

public class UtilTexto {

    private static UtilTexto instancia;

    public static final String vacia = "";

    public static final String CLIENTE_POR_DEFECTO = "2222222222";

    private UtilTexto() {
    }

    public static UtilTexto getUtilTexto() {
            synchronized (UtilTexto.class) {
                //pregunta si instancia es nula ent se crea si no , la retorna
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
        return vacia.equals(quitarEspaciosEnBlanco(texto));
    }

    public static String obtenerValorDefecto(String valor, String valorDefecto) {
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(valor, valorDefecto);
    }
    public static String obtenerValorDefecto(String valor){
        return obtenerValorDefecto(valor,vacia);
    }

    public String quitarEspaciosEnBlanco(String valor){
        return obtenerValorDefecto(valor).trim();
    }

    public String primeraLetraMayuscula(String valor){
        var valorSanitizado = quitarEspaciosEnBlanco(valor).toLowerCase();
        return esVacia(valorSanitizado)
                ? vacia
                : valorSanitizado.substring(0, 1).toUpperCase() + valorSanitizado.substring(1);
    }

    public int obtenerLongitudCadena(String valor){
        return obtenerValorDefecto(valor).length();
    }

    public int obtenerLongitudCadena(String valor, boolean quitarEspaciosEnBlanco){

        return quitarEspaciosEnBlanco ?
                obtenerLongitudCadena(quitarEspaciosEnBlanco(valor)): obtenerLongitudCadena(valor);
    }

    public boolean longitudCadenaEsValida(String valor, int longitudInicial , int longitudFinal, boolean quitarEspaciosEnBlanco){

        var valorSanitizado = quitarEspaciosEnBlanco ? quitarEspaciosEnBlanco(valor): valor;

        return obtenerLongitudCadena(valorSanitizado)>= longitudInicial && obtenerLongitudCadena(valorSanitizado)<=longitudFinal;
    }

}