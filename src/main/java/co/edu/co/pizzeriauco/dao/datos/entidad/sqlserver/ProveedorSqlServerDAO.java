package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.util.List;
import java.util.UUID;

public class ProveedorSqlServerDAO implements ProveedorDAO {

    @Override
    public void crear(ProveedorEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public ProveedorEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProveedorEntidad> consultarPorFiltro(ProveedorEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProveedorEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, ProveedorEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
