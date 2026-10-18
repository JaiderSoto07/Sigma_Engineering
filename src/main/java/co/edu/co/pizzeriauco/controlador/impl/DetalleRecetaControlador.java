package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.negocio.fachada.impl.DetalleRecetaFachadaImpl;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/detallerecetas")
public class DetalleRecetaControlador extends AbstractRestController {
    @PostMapping
    public String registrarInformacionNuevoDetalleReceta( @RequestBody DetalleRecetaDto datos){
        var fachada = new DetalleRecetaFachadaImpl();
        fachada.registrarInformacionNuevoDetalleReceta(datos);
        return "Detalle de receta registrado exitosamente";
    }
    @PutMapping("/{id}")
    public  String modificarCantidadIngrediente (@PathVariable UUID id, @RequestBody DetalleRecetaDto datos){
        var fachada = new DetalleRecetaFachadaImpl();
        fachada.modificarCantidadIngrediente(id, datos);
        return "Detalle de receta modificado exitosamente";
    }
    @DeleteMapping("/{id}")
    public  String retirarIngredienteDeReceta(@PathVariable UUID id){
        var fachada = new DetalleRecetaFachadaImpl();
        fachada.retirarIngredienteDeReceta(id);
        return "Detalle de receta  eliminado exitosamente";
    }

    @GetMapping("/filtro")
    public String consultarPorFiltro(DetalleRecetaDto filtro){
        return "Solicitud exitosa";
    }
    @GetMapping("/activos")
    public String consultarRecetasDeProductosActivos(){
        return "Solicitud exitosa";
    }
    @GetMapping("/inactivos")
    public String consultarRecetasDeProductosInactivos(){
        return "Solicitud exitosa";
    }
}
