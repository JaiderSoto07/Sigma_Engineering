package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.HistoricoPrecioEntidad;

import java.sql.Connection;

public class HistoricoPrecioSqlServerDAO extends SqlDAO implements HistoricoPrecioDAO {

    public HistoricoPrecioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(HistoricoPrecioEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
