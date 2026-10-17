package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.*;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;

import java.util.UUID;

public interface DetalleRecetaDAO extends CrearDAO<DetalleRecetaEntidad>, ConsultarDAO<DetalleRecetaEntidad, UUID>, ConsultarActivosDAO<DetalleRecetaEntidad>,ConsultarInactivosDAO<DetalleRecetaEntidad>,
        ActualizarDAO<DetalleRecetaEntidad, UUID>, EliminarDAO<UUID> {
}
