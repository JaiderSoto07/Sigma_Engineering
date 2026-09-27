package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;

import java.util.UUID;

public interface TamanoDAO extends CrearDAO<TamanoEntidad>, ConsultarDAO<TamanoEntidad, UUID>,
        ActualizarDAO<TamanoEntidad, UUID>, EliminarDAO<UUID> {
}
