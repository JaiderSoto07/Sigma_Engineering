package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.UnidadMedidaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class UnidadMedidaSqlServerDAO extends SqlDAO implements UnidadMedidaDAO {

    public UnidadMedidaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public UnidadMedidaEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UnidadMedidaEntidad> consultarPorFiltro(UnidadMedidaEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UnidadMedidaEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
