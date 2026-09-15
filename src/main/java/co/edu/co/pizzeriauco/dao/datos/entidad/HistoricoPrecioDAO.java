package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.HistoricoPrecioEntidad;

import java.util.UUID;

public interface HistoricoPrecioDAO extends CrearDAO<HistoricoPrecioEntidad>, ConsultarDAO<HistoricoPrecioEntidad, UUID> {
}
