package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.PizzeriaDAO;
import co.edu.co.pizzeriauco.entidad.PizzeriaEntidad;

import java.util.List;
import java.util.UUID;

public class PizzeriaSqlServerDAO implements PizzeriaDAO {

    @Override
    public void crear(PizzeriaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public PizzeriaEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PizzeriaEntidad> consultarPorFiltro(PizzeriaEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PizzeriaEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, PizzeriaEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
