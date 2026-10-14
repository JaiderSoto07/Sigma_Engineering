package co.edu.co.pizzeriauco.controlador.respuesta;

import java.util.List;

public class RespuestaOk<D> extends Respuesta<D>{

    protected RespuestaOk(List<D> datos, List<String> mensajes) {
        super(datos, mensajes, EstadoRespuesta.OK);
    }
    public static <D> RespuestaOk<D> crear(List<D> datos, List<String> mensajes){
        return new RespuestaOk<>(datos,mensajes);
    }
    public static <D> RespuestaOk<D> crear(List<D> datos){
        return new RespuestaOk<>(datos,List.of());
    }
    public static <D> RespuestaOk<D> crear(D dato){
        return new RespuestaOk<>(List.of(dato), List.of());
    }
    public static <D> RespuestaOk<D> crear(){
        return new RespuestaOk<>(List.of(), List.of());
    }


}
