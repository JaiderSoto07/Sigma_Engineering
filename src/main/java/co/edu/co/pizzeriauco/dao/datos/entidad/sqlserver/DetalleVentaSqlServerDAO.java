package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;

import java.sql.Connection;
import java.util.UUID;

public class DetalleVentaSqlServerDAO extends SqlDAO implements DetalleVentaDAO {

    public DetalleVentaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleVentaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actualizar(UUID id, DetalleVentaEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
