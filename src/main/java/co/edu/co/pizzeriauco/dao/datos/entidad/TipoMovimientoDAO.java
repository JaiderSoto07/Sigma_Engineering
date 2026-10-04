package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;

import java.util.UUID;

//sin actualizar ni eliminar: un codigo nunca cambia y se conserva aunque se borre el renglon que lo genero
public interface TipoMovimientoDAO extends CrearDAO<TipoMovimientoEntidad>, ConsultarDAO<TipoMovimientoEntidad, UUID> {
}
