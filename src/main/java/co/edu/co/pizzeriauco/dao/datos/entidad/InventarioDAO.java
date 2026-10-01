package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.dao.datos.ActualizarDAO;
import co.edu.co.pizzeriauco.dao.datos.ConsultarDAO;
import co.edu.co.pizzeriauco.dao.datos.CrearDAO;
import co.edu.co.pizzeriauco.entidad.InventarioEntidad;

import java.util.UUID;

//crear: el inventario NO lo registra una persona; lo crea negocio desde el codigo
//cuando llega el primer lote de un producto interno que todavia no tiene inventario
public interface InventarioDAO extends CrearDAO<InventarioEntidad>, ConsultarDAO<InventarioEntidad, UUID>,
        ActualizarDAO<InventarioEntidad, UUID> {
}
