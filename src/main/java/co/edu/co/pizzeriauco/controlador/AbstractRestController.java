package co.edu.co.pizzeriauco.controlador;

import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaError;
import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public  abstract class AbstractRestController {

    @ExceptionHandler(PizzeriaExcepcion.class)
    public ResponseEntity<RespuestaError<String>> controlarPizzeriaExcepcion(PizzeriaExcepcion excepcion){
        var respuesta = RespuestaError.crear(excepcion.getMensajeUsuario());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError<String>> controlarExcepcion(Exception excepcion){
        var mensajeRespuesta= CatalogoMensajes.AbstractRestController.USUARIO_ERROR_PROBLEMA_INESPERADO_EN_CONTROLADOR;
        var respuesta = RespuestaError.crear(mensajeRespuesta);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<RespuestaError<String>> controlarNoResourceFoundException(NoResourceFoundException exception){
        var mensajeRespuesta= UtilTexto.getUtilTexto().concatenar("Este recurso no existe :  "  ,exception.getResourcePath() , "  por el metodo: ", exception.getHttpMethod() );
        var respuesta = RespuestaError.crear(mensajeRespuesta);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

}
