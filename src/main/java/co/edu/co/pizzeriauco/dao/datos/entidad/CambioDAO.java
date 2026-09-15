package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.CambioEntidad;

import java.util.UUID;

public interface CambioDAO extends CrearDAO<CambioEntidad>, ConsultarDAO<CambioEntidad, UUID> {
}
