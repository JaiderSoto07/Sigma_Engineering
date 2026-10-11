package co.edu.co.pizzeriauco.negocio.fachada;

import co.edu.co.pizzeriauco.dto.DetalleRecetaDto;

import java.util.List;
import java.util.UUID;

public interface DetalleRecetaFachada {

    void registrarInformacionNuevoDetalleReceta(DetalleRecetaDto datos);
    void modificarCantidadIngrediente(UUID id , DetalleRecetaDto datos);
    void retirarIngredienteDeReceta(UUID id);
    List<DetalleRecetaDto> consultarPorFiltro(DetalleRecetaDto filtro);
    List<DetalleRecetaDto> consultarRecetasDeProductosActivos();
    List<DetalleRecetaDto> consultarRecetasDeProductosInactivos();

}
