package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;

import java.util.UUID;

//sin actualizar ni eliminar: los cambios de cantidad se hacen en pantalla antes de registrar la venta;
//una vez registrada (confirmada) sus renglones quedan bloqueados
public interface DetalleVentaDAO extends CrearDAO<DetalleVentaEntidad>, ConsultarDAO<DetalleVentaEntidad, UUID> {
}
