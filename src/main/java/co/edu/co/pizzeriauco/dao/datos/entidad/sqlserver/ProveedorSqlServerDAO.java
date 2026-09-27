package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.sql.Connection;
import java.util.UUID;

public class ProveedorSqlServerDAO extends SqlDAO implements ProveedorDAO {

    public ProveedorSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProveedorEntidad entidad) {
        // TODO Auto-generated method stub
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
