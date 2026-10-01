package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.util.UUID;

//sin actualizar ni eliminar: una venta registrada ya desconto inventario y uso un numero de factura,
//por eso no se modifica ni se borra (si hace falta deshacerla, sera una futura "Anular venta")
public interface VentaDAO extends CrearDAO<VentaEntidad>, ConsultarDAO<VentaEntidad, UUID> {
}
