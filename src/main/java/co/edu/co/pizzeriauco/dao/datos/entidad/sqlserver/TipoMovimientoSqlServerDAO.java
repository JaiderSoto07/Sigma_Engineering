package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class TipoMovimientoSqlServerDAO extends SqlDAO implements TipoMovimientoDAO {

    public TipoMovimientoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public TipoMovimientoEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TipoMovimientoEntidad> consultarPorFiltro(TipoMovimientoEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TipoMovimientoEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }
}
