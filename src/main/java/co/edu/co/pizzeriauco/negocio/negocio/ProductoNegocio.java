package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.ProductoDominio;

import java.util.List;

public interface ProductoNegocio {

    void registrarInformacionNuevoProducto(ProductoDominio datos);
    List<ProductoDominio> consultarActivos();

}
