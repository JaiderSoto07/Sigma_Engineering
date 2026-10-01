package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.VentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class VentaSqlServerDAO extends SqlDAO implements VentaDAO {

    public VentaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(VentaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public VentaEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<VentaEntidad> consultarPorFiltro(VentaEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<VentaEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }
}
