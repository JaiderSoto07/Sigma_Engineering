package co.edu.co.pizzeriauco.negocio.fachada;

import co.edu.co.pizzeriauco.dto.ProductoDto;

import java.util.List;

public interface ProductoFachada {

    List<ProductoDto> consultarActivos();

}
