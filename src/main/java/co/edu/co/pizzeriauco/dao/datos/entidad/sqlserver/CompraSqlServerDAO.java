package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.CompraDAO;
import co.edu.co.pizzeriauco.entidad.CompraEntidad;

import java.util.List;
import java.util.UUID;

public class CompraSqlServerDAO implements CompraDAO {

    @Override
    public void crear(CompraEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public CompraEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CompraEntidad> consultarPorFiltro(CompraEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CompraEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, CompraEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
