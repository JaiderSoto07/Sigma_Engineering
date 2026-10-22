package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.ProductoInternoDto;
import co.edu.co.pizzeriauco.negocio.fachada.ProductoInternoFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.ProductoInternoFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productosInternos")
public class ProductoInternoControlador extends AbstractRestController {

    private ProductoInternoFachada fachada = new ProductoInternoFachadaImpl();

    @GetMapping
    public ResponseEntity<RespuestaOk<ProductoInternoDto>> consultarActivos(){
        var productosInternos = fachada.consultarActivos();
        RespuestaOk<ProductoInternoDto> respuesta = RespuestaOk.crear(productosInternos);
        return ResponseEntity.ok(respuesta);
    }

}
