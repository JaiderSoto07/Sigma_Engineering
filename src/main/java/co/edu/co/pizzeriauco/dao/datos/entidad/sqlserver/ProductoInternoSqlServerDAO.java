package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;

import java.sql.Connection;
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
    public void actualizar(UUID id, ProductoInternoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
