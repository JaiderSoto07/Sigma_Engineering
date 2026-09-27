package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;

import java.util.UUID;

public interface ProductoDAO extends CrearDAO<ProductoEntidad>, EliminarDAO<UUID> {
}
