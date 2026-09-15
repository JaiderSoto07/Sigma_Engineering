package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;

import java.util.List;
import java.util.UUID;

public class DetalleRecetaSqlServerDAO implements DetalleRecetaDAO {

    @Override
    public void crear(DetalleRecetaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public DetalleRecetaEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DetalleRecetaEntidad> consultarPorFiltro(DetalleRecetaEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DetalleRecetaEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, DetalleRecetaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
