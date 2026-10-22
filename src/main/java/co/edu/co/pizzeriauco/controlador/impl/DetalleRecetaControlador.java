package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;
import co.edu.co.pizzeriauco.negocio.fachada.DetalleRecetaFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.DetalleRecetaFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/detallerecetas")
public class DetalleRecetaControlador extends AbstractRestController {

    private DetalleRecetaFachada fachada = new DetalleRecetaFachadaImpl();

    @GetMapping("/dummy")
    public DetalleRecetaDto obtenerDummy(){
        return new DetalleRecetaDto();
    }

    @PostMapping
    public ResponseEntity<RespuestaOk<String>> registrarInformacionNuevoDetalleReceta(@RequestBody DetalleRecetaDto datos){
        fachada.registrarInformacionNuevoDetalleReceta(datos);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Detalle de receta registrado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaOk<String>> modificarCantidadIngrediente(@PathVariable UUID id, @RequestBody DetalleRecetaDto datos){
        fachada.modificarCantidadIngrediente(id, datos);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Detalle de receta modificado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaOk<String>> retirarIngredienteDeReceta(@PathVariable UUID id){
        fachada.retirarIngredienteDeReceta(id);
        RespuestaOk<String> respuesta = RespuestaOk.crear("Detalle de receta eliminado exitosamente");
        return ResponseEntity.ok(respuesta);
    }

    //....../api/v1/detallerecetas
    //....../api/v1/detallerecetas?producto.id=valor
    //....../api/v1/detallerecetas?productoInterno.nombre=Queso
    @GetMapping
    public ResponseEntity<RespuestaOk<DetalleRecetaDto>> consultarPorFiltro(DetalleRecetaDto filtro){
        var detalles = fachada.consultarPorFiltro(filtro);
        RespuestaOk<DetalleRecetaDto> respuesta = RespuestaOk.crear(detalles);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/activos")
    public ResponseEntity<RespuestaOk<DetalleRecetaDto>> consultarRecetasDeProductosActivos(){
        var detalles = fachada.consultarRecetasDeProductosActivos();
        RespuestaOk<DetalleRecetaDto> respuesta = RespuestaOk.crear(detalles);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<RespuestaOk<DetalleRecetaDto>> consultarRecetasDeProductosInactivos(){
        var detalles = fachada.consultarRecetasDeProductosInactivos();
        RespuestaOk<DetalleRecetaDto> respuesta = RespuestaOk.crear(detalles);
        return ResponseEntity.ok(respuesta);
    }
}
