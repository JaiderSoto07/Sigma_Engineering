package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class ProductoInternoSqlServerDAO extends SqlDAO implements ProductoInternoDAO {

    public ProductoInternoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProductoInternoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public ProductoInternoEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductoInternoEntidad> consultarPorFiltro(ProductoInternoEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductoInternoEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, ProductoInternoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    //eliminar = borrar de verdad, solo si no tiene compras, lotes, movimientos o recetas (si los tiene, la base no deja);
    //para retirarlo sin perder su historial se usa actualizar con activo = false
    //(delete from producto_interno where id_producto_interno = ?)
    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
