package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;

import java.util.UUID;

public interface DetalleVentaDAO extends CrearDAO<DetalleVentaEntidad>, ActualizarDAO<DetalleVentaEntidad, UUID> {
}
