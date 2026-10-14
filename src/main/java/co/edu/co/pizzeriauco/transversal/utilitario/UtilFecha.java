package co.edu.co.pizzeriauco.transversal.utilitario;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class UtilFecha {

    //fecha por defecto cuando no me dan una fecha (01/01/1000)
    public static final LocalDate FECHA_POR_DEFECTO = LocalDate.of(1000, 1, 1);

    private UtilFecha(){
    }

    // aseguro que la fecha nunca sea nula
    public static LocalDate valorDefecto(LocalDate fecha){
        return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(fecha, FECHA_POR_DEFECTO);
    }
    public static LocalDateTime fechaActual(){
        return LocalDateTime.now(ZoneId.of("America/Bogota"));
    }

    public static long fechaParaMillins(){
        return Instant.now().toEpochMilli();
    }

}
