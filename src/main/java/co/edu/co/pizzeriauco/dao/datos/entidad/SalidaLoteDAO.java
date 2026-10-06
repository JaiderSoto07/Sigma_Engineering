package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.SalidaLoteEntidad;

import java.util.UUID;

public interface SalidaLoteDAO extends CrearDAO<SalidaLoteEntidad>, ConsultarDAO<SalidaLoteEntidad, UUID> {
}
