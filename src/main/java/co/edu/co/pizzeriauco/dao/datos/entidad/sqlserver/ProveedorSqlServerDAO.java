package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.sql.Connection;
import java.util.List;
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
    public ProveedorEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProveedorEntidad> consultarPorFiltro(ProveedorEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProveedorEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, ProveedorEntidad entidad) {
        // TODO Auto-generated method stub
    }

    //eliminar = borrar de verdad, solo si no tiene compras (si los tiene, la base no deja);
    //para retirarlo sin perder su historial se usa actualizar con activo = false
    //(delete from proveedor where id_proveedor = ?)
    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
