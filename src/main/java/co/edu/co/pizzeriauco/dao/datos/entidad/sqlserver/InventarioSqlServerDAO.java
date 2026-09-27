package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.InventarioEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class InventarioSqlServerDAO extends SqlDAO implements InventarioDAO {

    public InventarioSqlServerDAO(Connection conexion) {
        super(conexion);
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
