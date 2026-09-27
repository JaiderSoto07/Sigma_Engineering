package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.DetalleCompraEntidad;

import java.util.UUID;

public interface DetalleCompraDAO extends CrearDAO<DetalleCompraEntidad>, ActualizarDAO<DetalleCompraEntidad, UUID>,
        EliminarDAO<UUID> {
}
