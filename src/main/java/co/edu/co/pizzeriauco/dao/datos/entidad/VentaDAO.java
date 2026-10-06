package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.util.UUID;

public interface VentaDAO extends CrearDAO<VentaEntidad>, ConsultarDAO<VentaEntidad, UUID> {
}
