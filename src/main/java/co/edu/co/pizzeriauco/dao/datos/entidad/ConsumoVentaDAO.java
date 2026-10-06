package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.ConsumoVentaEntidad;

import java.util.UUID;

public interface ConsumoVentaDAO extends CrearDAO<ConsumoVentaEntidad>, ConsultarDAO<ConsumoVentaEntidad, UUID> {
}
