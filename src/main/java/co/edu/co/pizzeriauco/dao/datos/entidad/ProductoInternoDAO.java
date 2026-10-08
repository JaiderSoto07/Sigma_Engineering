package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarActivosDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;

import java.util.UUID;

public interface ProductoInternoDAO extends CrearDAO<ProductoInternoEntidad>, ConsultarDAO<ProductoInternoEntidad, UUID>,
        ConsultarActivosDAO<ProductoInternoEntidad>, ActualizarDAO<ProductoInternoEntidad, UUID>, EliminarDAO<UUID> {
}
