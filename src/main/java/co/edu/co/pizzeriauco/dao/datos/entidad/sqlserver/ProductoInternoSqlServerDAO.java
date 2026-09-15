package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;

import java.util.List;
import java.util.UUID;

public class ProductoInternoSqlServerDAO implements ProductoInternoDAO {

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

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
