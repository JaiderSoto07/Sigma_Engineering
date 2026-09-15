package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.MovimientoInventarioEntidad;

import java.util.UUID;

public interface MovimientoInventarioDAO extends CrearDAO<MovimientoInventarioEntidad>, ConsultarDAO<MovimientoInventarioEntidad, UUID> {
}
