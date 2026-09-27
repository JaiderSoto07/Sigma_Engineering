package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.util.UUID;

public interface TipoProductoDAO extends CrearDAO<TipoProductoEntidad>, ConsultarDAO<TipoProductoEntidad, UUID>,
        ActualizarDAO<TipoProductoEntidad, UUID>, EliminarDAO<UUID> {
}
