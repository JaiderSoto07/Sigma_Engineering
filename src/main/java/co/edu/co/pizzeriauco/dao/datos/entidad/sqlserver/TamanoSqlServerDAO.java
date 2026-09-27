package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.TamanoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class TamanoSqlServerDAO extends SqlDAO implements TamanoDAO {

    public TamanoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(TamanoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public TamanoEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TamanoEntidad> consultarPorFiltro(TamanoEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TamanoEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, TamanoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
