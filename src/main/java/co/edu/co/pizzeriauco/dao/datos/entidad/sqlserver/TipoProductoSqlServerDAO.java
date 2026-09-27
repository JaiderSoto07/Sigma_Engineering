package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.TipoProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class TipoProductoSqlServerDAO extends SqlDAO implements TipoProductoDAO {

    public TipoProductoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(TipoProductoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public TipoProductoEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TipoProductoEntidad> consultarPorFiltro(TipoProductoEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TipoProductoEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, TipoProductoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
