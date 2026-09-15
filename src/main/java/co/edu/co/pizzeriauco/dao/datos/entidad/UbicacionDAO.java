package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.UbicacionEntidad;

import java.util.UUID;

public interface UbicacionDAO extends CrearDAO<UbicacionEntidad>, ConsultarDAO<UbicacionEntidad, UUID>,
        ActualizarDAO<UbicacionEntidad, UUID>, EliminarDAO<UUID> {
}
