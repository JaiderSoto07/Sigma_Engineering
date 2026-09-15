package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.ClienteEntidad;

import java.util.UUID;

public interface ClienteDAO extends CrearDAO<ClienteEntidad>, ConsultarDAO<ClienteEntidad, UUID>,
        ActualizarDAO<ClienteEntidad, UUID>, EliminarDAO<UUID> {
}
