package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.entidad.InventarioEntidad;

import java.util.List;
import java.util.UUID;

public class InventarioSqlServerDAO implements InventarioDAO {

    @Override
    public void crear(InventarioEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public InventarioEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<InventarioEntidad> consultarPorFiltro(InventarioEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<InventarioEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, InventarioEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
