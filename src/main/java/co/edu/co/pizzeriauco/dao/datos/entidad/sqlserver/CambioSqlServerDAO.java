package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.entidad.CambioEntidad;

import java.util.List;
import java.util.UUID;

public class CambioSqlServerDAO implements CambioDAO {

    @Override
    public void crear(CambioEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public CambioEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CambioEntidad> consultarPorFiltro(CambioEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CambioEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }
}
