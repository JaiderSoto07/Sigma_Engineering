package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.MovimientoInventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.MovimientoInventarioEntidad;

import java.sql.Connection;

public class MovimientoInventarioSqlServerDAO extends SqlDAO implements MovimientoInventarioDAO {

    public MovimientoInventarioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(MovimientoInventarioEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
