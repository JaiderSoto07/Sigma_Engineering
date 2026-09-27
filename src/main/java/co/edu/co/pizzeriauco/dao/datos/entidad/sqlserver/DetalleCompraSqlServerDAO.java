package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleCompraEntidad;

import java.sql.Connection;
import java.util.UUID;

public class DetalleCompraSqlServerDAO extends SqlDAO implements DetalleCompraDAO {

    public DetalleCompraSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleCompraEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void actualizar(UUID id, DetalleCompraEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
