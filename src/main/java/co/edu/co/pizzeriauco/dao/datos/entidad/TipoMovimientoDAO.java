package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;

import java.util.UUID;

public interface TipoMovimientoDAO extends CrearDAO<TipoMovimientoEntidad>, ConsultarDAO<TipoMovimientoEntidad, UUID> {
}
