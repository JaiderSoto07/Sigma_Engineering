package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.TamanoDto;
import co.edu.co.pizzeriauco.negocio.fachada.TamanoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.TamanoFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tamanos")
public class TamanoControlador extends AbstractRestController {

    private TamanoFachada fachada = new TamanoFachadaImpl();

    @GetMapping
    public ResponseEntity<RespuestaOk<TamanoDto>> consultarTodos(){
        var tamanos = fachada.consultarTodos();
        RespuestaOk<TamanoDto> respuesta = RespuestaOk.crear(tamanos);
        return ResponseEntity.ok(respuesta);
    }
}
