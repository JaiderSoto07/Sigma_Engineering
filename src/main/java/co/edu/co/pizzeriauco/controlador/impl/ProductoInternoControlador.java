package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.negocio.fachada.impl.DetalleRecetaFachadaImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productosInternos")
public class ProductoInternoControlador extends AbstractRestController {

    @GetMapping
    public String consultarActivos(){
        return "Solicitud exitosa";
    }

}
