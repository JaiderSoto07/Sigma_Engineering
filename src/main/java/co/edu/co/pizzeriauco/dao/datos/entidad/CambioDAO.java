package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.dao.datos.EliminarDAO;
import co.edu.co.pizzeriauco.entidad.CambioEntidad;

import java.util.UUID;

//actualizar y eliminar solo mientras el lote que creo el cambio este intacto (saldo = cantidad),
//igual que un renglon de compra
public interface CambioDAO extends CrearDAO<CambioEntidad>, ConsultarDAO<CambioEntidad, UUID>,
        ActualizarDAO<CambioEntidad, UUID>, EliminarDAO<UUID> {
}
