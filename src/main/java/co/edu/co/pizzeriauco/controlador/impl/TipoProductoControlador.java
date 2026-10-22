package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.TipoProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.TipoProductoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.TipoProductoFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipo-productos")
public class TipoProductoControlador extends AbstractRestController {

    private TipoProductoFachada fachada = new TipoProductoFachadaImpl();

    @GetMapping
    public ResponseEntity<RespuestaOk<TipoProductoDto>> consultarTodos(){
        var tiposProducto = fachada.consultarTodos();
        RespuestaOk<TipoProductoDto> respuesta = RespuestaOk.crear(tiposProducto);
        return ResponseEntity.ok(respuesta);
    }
}
