package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CambioEntidad;

import java.sql.Connection;

public class CambioSqlServerDAO extends SqlDAO implements CambioDAO {

    public CambioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(CambioEntidad entidad) {
        // TODO Auto-generated method stub
    }
}
