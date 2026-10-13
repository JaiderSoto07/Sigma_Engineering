package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;

import java.util.List;

public interface ProductoInternoNegocio {

    List<ProductoInternoDominio> consultarActivos();

}
