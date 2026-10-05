package co.edu.co.pizzeriauco.transversal.utilitario;

import java.time.LocalDate;


public class UtilFecha {

    //fecha por defecto cuando no me dan una fecha (01/01/1000)
    public static final LocalDate FECHA_POR_DEFECTO = LocalDate.of(1000, 1, 1);

    private UtilFecha(){
    }

    // aseguro que la fecha nunca sea nula
    public static LocalDate valorDefecto(LocalDate fecha){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fecha, FECHA_POR_DEFECTO);
    }

}
