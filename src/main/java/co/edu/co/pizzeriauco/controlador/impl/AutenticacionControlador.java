package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.CredencialesDto;
import co.edu.co.pizzeriauco.dto.UsuarioDto;
import co.edu.co.pizzeriauco.negocio.fachada.AutenticacionFachada;
import co.edu.co.pizzeriauco.negocio.fachada.impl.AutenticacionFachadaImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionControlador extends AbstractRestController {

    private AutenticacionFachada fachada = new AutenticacionFachadaImpl();

    @PostMapping
    public ResponseEntity<RespuestaOk<UsuarioDto>> iniciarSesion(@RequestBody CredencialesDto credenciales){
        var usuarioAutenticado = fachada.iniciarSesion(credenciales);
        RespuestaOk<UsuarioDto> respuesta = RespuestaOk.crear(usuarioAutenticado);
        return ResponseEntity.ok(respuesta);
    }
}
