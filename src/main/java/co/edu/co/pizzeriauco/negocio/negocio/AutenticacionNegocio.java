package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.UsuarioDominio;

public interface AutenticacionNegocio {

    UsuarioDominio iniciarSesion(UsuarioDominio credenciales);

}
