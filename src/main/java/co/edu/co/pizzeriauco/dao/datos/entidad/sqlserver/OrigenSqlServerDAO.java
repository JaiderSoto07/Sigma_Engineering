package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.OrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.OrigenEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class OrigenSqlServerDAO extends SqlDAO implements OrigenDAO {

    public OrigenSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public OrigenEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrigenEntidad> consultarPorFiltro(OrigenEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrigenEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }
}
