package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.AdministradorDAO;
import co.edu.co.pizzeriauco.entidad.AdministradorEntidad;

import java.util.List;
import java.util.UUID;

public class AdministradorSqlServerDAO implements AdministradorDAO {

    @Override
    public void crear(AdministradorEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public AdministradorEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AdministradorEntidad> consultarPorFiltro(AdministradorEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AdministradorEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, AdministradorEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
