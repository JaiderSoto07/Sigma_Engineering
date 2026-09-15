package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.ClienteDAO;
import co.edu.co.pizzeriauco.entidad.ClienteEntidad;

import java.util.List;
import java.util.UUID;

public class ClienteSqlServerDAO implements ClienteDAO {

    @Override
    public void crear(ClienteEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public ClienteEntidad consultarPorId(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ClienteEntidad> consultarPorFiltro(ClienteEntidad filtro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ClienteEntidad> consultarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void actualizar(UUID id, ClienteEntidad entidad) {
        // TODO Auto-generated method stub
    }

    @Override
    public void eliminar(UUID id) {
        // TODO Auto-generated method stub
    }
}
