package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.CompraEntidad;

import java.util.UUID;

public interface CompraDAO extends CrearDAO<CompraEntidad>, ConsultarDAO<CompraEntidad, UUID>,
        ActualizarDAO<CompraEntidad, UUID> {
}
