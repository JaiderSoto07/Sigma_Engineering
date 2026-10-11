package co.edu.co.pizzeriauco.negocio.negocio;

import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;

import java.util.List;
import java.util.UUID;

public interface DetalleRecetaNegocio {

    void registrarInformacionNuevoDetalleReceta(DetalleRecetaDominio datos);
    void modificarCantidadIngrediente(UUID id , DetalleRecetaDominio datos);
    void retirarIngredienteDeReceta(UUID id);
    List<DetalleRecetaDominio> consultarPorFiltro(DetalleRecetaDominio filtro);
    List<DetalleRecetaDominio> consultarRecetasDeProductosActivos();
    List<DetalleRecetaDominio> consultarRecetasDeProductosInactivos();

}
