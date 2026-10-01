package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleCompraEntidad;

import java.sql.Connection;
import java.util.List;
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
    public DetalleCompraEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DetalleCompraEntidad> consultarPorFiltro(DetalleCompraEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DetalleCompraEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
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
