package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.UbicacionDAO;
import co.edu.co.pizzeriauco.entidad.UbicacionEntidad;

import java.util.List;
import java.util.UUID;

public class UbicacionSqlServerDAO implements UbicacionDAO {

    @Override
    public void crear(UbicacionEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public UbicacionEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UbicacionEntidad> consultarPorFiltro(UbicacionEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UbicacionEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, UbicacionEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
