package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.VentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.sql.Connection;
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
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
