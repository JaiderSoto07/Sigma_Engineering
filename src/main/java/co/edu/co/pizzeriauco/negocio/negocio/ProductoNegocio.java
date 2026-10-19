package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.ProductoDominio;

import java.util.List;

public interface ProductoNegocio {

    List<ProductoDominio> consultarActivos();

}
