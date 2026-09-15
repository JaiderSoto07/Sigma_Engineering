package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;

import java.util.UUID;

public interface DetalleVentaDAO extends CrearDAO<DetalleVentaEntidad>, ConsultarDAO<DetalleVentaEntidad, UUID> {
}
