package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.dto.ProductoDto;
import co.edu.co.pizzeriauco.negocio.fachada.impl.DetalleRecetaFachadaImpl;
import co.edu.co.pizzeriauco.negocio.fachada.impl.ProductoFachadaImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoControlador extends AbstractRestController {
    @PostMapping
    public String registrarInformacionNuevoProducto( @RequestBody ProductoDto datos){
        var fachada = new ProductoFachadaImpl();
        fachada.registrarInformacionNuevoProducto(datos);
        return "Producto registrado exitosamente";
    }

    @GetMapping
    public String consultarActivos(){
        return "Solicitud exitosa";
    }

}
