package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.LoteDAO;
import co.edu.co.pizzeriauco.entidad.LoteEntidad;

import java.util.List;
import java.util.UUID;

public class LoteSqlServerDAO implements LoteDAO {

    @Override
    public void crear(LoteEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public LoteEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LoteEntidad> consultarPorFiltro(LoteEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LoteEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, LoteEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
