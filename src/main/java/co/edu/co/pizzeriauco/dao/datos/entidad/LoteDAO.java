package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.LoteEntidad;

import java.util.UUID;

public interface LoteDAO extends CrearDAO<LoteEntidad>, ConsultarDAO<LoteEntidad, UUID>,
        ActualizarDAO<LoteEntidad, UUID> {
}
