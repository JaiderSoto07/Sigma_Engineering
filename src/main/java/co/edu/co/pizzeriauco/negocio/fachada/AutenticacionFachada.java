package co.edu.co.pizzeriauco.negocio.fachada;

import co.edu.co.pizzeriauco.dto.CredencialesDto;
import co.edu.co.pizzeriauco.dto.UsuarioDto;

public interface AutenticacionFachada {

    UsuarioDto iniciarSesion(CredencialesDto credenciales);

}
