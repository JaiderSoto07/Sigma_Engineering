package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.negocio.fachada.impl.ProductoFachadaImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoControlador extends AbstractRestController {

    @GetMapping
    public String consultarActivos(){
        return "Solicitud exitosa";
    }

}
