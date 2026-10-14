package co.edu.co.pizzeriauco.controlador.respuesta;

import java.util.List;

public class RespuestaError<D> extends Respuesta<D> {

    protected RespuestaError(List<D> datos, List<String> mensajes) {
        super(datos, mensajes, EstadoRespuesta.ERROR);
    }
    //voy a crear diferentes metodos dependiedo con lo que me llegue
    public static RespuestaError<String> crear(final String mensaje){
        return new RespuestaError<>(List.of(), List.of(mensaje));
    }
    public static <D> RespuestaError<D> crear(List<D> datos , List<String> mensajes){
        return new RespuestaError<>(datos, mensajes);
    }
}
