package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.HistoricoPrecioEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class HistoricoPrecioSqlServerDAO extends SqlDAO implements HistoricoPrecioDAO {

    public HistoricoPrecioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(HistoricoPrecioEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public HistoricoPrecioEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HistoricoPrecioEntidad> consultarPorFiltro(HistoricoPrecioEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<HistoricoPrecioEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, HistoricoPrecioEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
