package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;

import java.util.UUID;

public interface ProductoDAO extends CrearDAO<ProductoEntidad>, ConsultarDAO<ProductoEntidad, UUID>,
        ActualizarDAO<ProductoEntidad, UUID>, EliminarDAO<UUID> {
}
