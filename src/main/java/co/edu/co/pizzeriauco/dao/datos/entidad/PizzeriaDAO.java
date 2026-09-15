package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.PizzeriaEntidad;

import java.util.UUID;

public interface PizzeriaDAO extends CrearDAO<PizzeriaEntidad>, ConsultarDAO<PizzeriaEntidad, UUID>,
        ActualizarDAO<PizzeriaEntidad, UUID>, EliminarDAO<UUID> {
}
