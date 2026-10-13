package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;

import java.util.List;

public interface TipoProductoNegocio {

    List<TipoProductoDominio> consultarTodos();

}
