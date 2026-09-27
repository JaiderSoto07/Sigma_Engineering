package co.edu.co.pizzeriauco.crosscuting.utilitario;

import java.time.DateTimeException;
import java.time.LocalDate;


public class UtilFecha {

    //fecha por defecto cuando no me dan una fecha (01/01/1000)
    public static final LocalDate FECHA_POR_DEFECTO = LocalDate.of(1000, 1, 1);

    private UtilFecha(){
    }

    //asi como UtilId.valorDefecto, aseguro que la fecha nunca sea nula
    public static LocalDate valorDefecto(LocalDate fecha){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fecha, FECHA_POR_DEFECTO);
    }

    public static boolean diaPosible(int dia){
        return dia > 0 && dia < 32;
    }
    public static boolean mesPosible(int mes){
        return mes > 0 && mes < 13;
    }

    //ahora si el año es posible
    public static boolean yearPosible(int year){
        return year >= 1000 && year <= 2100;
    }

    //ademas de los rangos, pregunto si la fecha existe (por ejemplo 31 de febrero no existe)
    public static boolean fechaValida(int dia, int mes, int year){
        if (!(diaPosible(dia) && mesPosible(mes) && yearPosible(year))) {
            return false;
        }
        try {
            LocalDate.of(year, mes, dia);
            return true;
        } catch (DateTimeException exception) {
            return false;
        }
    }

    public static LocalDate valorPorDefecto(int dia, int mes, int year){
        return fechaValida(dia, mes, year)
                ? LocalDate.of(year, mes, dia) : FECHA_POR_DEFECTO;
    }

}
