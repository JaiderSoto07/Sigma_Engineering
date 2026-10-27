package co.edu.co.pizzeriauco.controlador.impl;

import co.edu.co.pizzeriauco.controlador.AbstractRestController;
import co.edu.co.pizzeriauco.controlador.respuesta.RespuestaOk;
import co.edu.co.pizzeriauco.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionControlador extends AbstractRestController {

    private static final String ROL_ADMINISTRADOR = "Administrador";

    @GetMapping
    public ResponseEntity<RespuestaOk<UsuarioDto>> consultarUsuarioActual(@AuthenticationPrincipal OidcUser datosUsuario){
        var usuario = new UsuarioDto();

        usuario.setId(UUID.nameUUIDFromBytes(datosUsuario.getSubject().getBytes(StandardCharsets.UTF_8)));
        usuario.setUsuario(datosUsuario.getEmail());
        usuario.setRol(ROL_ADMINISTRADOR);

        RespuestaOk<UsuarioDto> respuesta = RespuestaOk.crear(usuario);
        return ResponseEntity.ok(respuesta);
    }
}
