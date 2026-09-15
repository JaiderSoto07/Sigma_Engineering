package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.AdministradorEntidad;

import java.util.UUID;

public interface AdministradorDAO extends CrearDAO<AdministradorEntidad>, ConsultarDAO<AdministradorEntidad, UUID>,
        ActualizarDAO<AdministradorEntidad, UUID>, EliminarDAO<UUID> {
}
