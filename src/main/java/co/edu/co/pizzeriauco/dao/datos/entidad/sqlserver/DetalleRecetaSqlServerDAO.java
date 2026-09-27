package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;

import java.sql.Connection;
import java.util.UUID;

public class DetalleRecetaSqlServerDAO extends SqlDAO implements DetalleRecetaDAO {

    public DetalleRecetaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleRecetaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actualizar(UUID id, DetalleRecetaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
