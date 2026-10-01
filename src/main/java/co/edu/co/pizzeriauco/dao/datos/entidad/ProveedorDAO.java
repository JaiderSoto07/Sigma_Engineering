package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.util.UUID;

public interface ProveedorDAO extends CrearDAO<ProveedorEntidad>, ConsultarDAO<ProveedorEntidad, UUID>,
        ActualizarDAO<ProveedorEntidad, UUID>, EliminarDAO<UUID> {
}
