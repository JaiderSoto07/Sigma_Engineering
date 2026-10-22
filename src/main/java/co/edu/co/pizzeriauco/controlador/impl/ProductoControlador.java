package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.ProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.ProductoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.ProductoFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoControlador extends AbstractRestController {

    private ProductoFachada fachada = new ProductoFachadaImpl();

    @GetMapping
    public ResponseEntity<RespuestaOk<ProductoDto>> consultarActivos(){
        var productos = fachada.consultarActivos();
        RespuestaOk<ProductoDto> respuesta = RespuestaOk.crear(productos);
        return ResponseEntity.ok(respuesta);
    }

}
