package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.HistoricoPrecioEntidad;

import java.util.UUID;

//sin eliminar: el historico de precios nunca se borra
//actualizar solo se usa para cerrar el precio vigente (fecha fin) o corregir el precio de hoy
public interface HistoricoPrecioDAO extends CrearDAO<HistoricoPrecioEntidad>, ConsultarDAO<HistoricoPrecioEntidad, UUID>,
        ActualizarDAO<HistoricoPrecioEntidad, UUID> {
}
