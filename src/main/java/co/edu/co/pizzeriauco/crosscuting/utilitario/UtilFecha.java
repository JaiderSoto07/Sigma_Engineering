package co.edu.co.pizzeriauco.crosscuting.utilitario;

import java.time.LocalDate;


public class UtilFecha {
    private UtilFecha(){
    }

    // como es te metodo es de la clase numero recordemos que ya nos va a devolver un tipo Number ent debo poner el double
    public static boolean DiaPosible(int variable ){
        return UtilNumero.obtenerValorDefecto(variable).doubleValue()>0 && UtilNumero.obtenerValorDefecto(variable).doubleValue() <32;
    }
    public static boolean MesPosible(int variable ){
        return UtilNumero.obtenerValorDefecto(variable).doubleValue()>0 && UtilNumero.obtenerValorDefecto(variable).doubleValue() <13;
    }

    //ahora si el año es posible
    public static boolean YearPosible(int year){
        return UtilNumero.obtenerValorDefecto(year).doubleValue() >=1000 && UtilNumero.obtenerValorDefecto(year).doubleValue() <= 2100;
    }
    public static boolean FechaValida(int dia, int mes,int year){
        return DiaPosible(dia) && MesPosible(mes) && YearPosible(year);
    }
    public static LocalDate ValorPorDefecto(int dia, int mes, int year){
         return FechaValida(dia, mes, year)
             ? LocalDate.of(year, mes, dia) : LocalDate.of(1000,1,1);

    }





}

