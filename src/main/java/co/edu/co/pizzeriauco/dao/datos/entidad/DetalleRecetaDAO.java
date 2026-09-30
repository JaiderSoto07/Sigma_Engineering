package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;

import java.util.UUID;

public interface DetalleRecetaDAO extends CrearDAO<DetalleRecetaEntidad>, ConsultarDAO<DetalleRecetaEntidad, UUID>,
        ActualizarDAO<DetalleRecetaEntidad, UUID>, EliminarDAO<UUID> {
}
