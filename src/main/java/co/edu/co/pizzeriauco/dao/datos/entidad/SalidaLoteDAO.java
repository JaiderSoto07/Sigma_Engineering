package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.SalidaLoteEntidad;

import java.util.UUID;

//sin actualizar ni eliminar: es el registro de algo que ya paso (si se borrara, el lote quedaria en 0 sin explicacion)
public interface SalidaLoteDAO extends CrearDAO<SalidaLoteEntidad>, ConsultarDAO<SalidaLoteEntidad, UUID> {
}
