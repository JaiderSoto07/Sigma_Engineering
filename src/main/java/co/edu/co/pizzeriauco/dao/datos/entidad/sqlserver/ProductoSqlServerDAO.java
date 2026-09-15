package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;

import java.util.List;
import java.util.UUID;

public class ProductoSqlServerDAO implements ProductoDAO {

    @Override
    public void crear(ProductoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public ProductoEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductoEntidad> consultarPorFiltro(ProductoEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProductoEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, ProductoEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
