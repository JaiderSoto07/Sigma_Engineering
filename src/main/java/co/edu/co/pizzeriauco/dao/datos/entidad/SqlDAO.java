package co.edu.co.pizzeriauco.dao.datos.entidad;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilSql;

import java.sql.Connection;

//de esta clase heredan todos los DAO de base de datos SQL
//el Factory les pasa la conexion que ya abrio
public abstract class SqlDAO {

    private Connection conexion;

    protected SqlDAO(Connection conexion) {
        setConexion(conexion);
    }

    //antes de guardar la conexion me aseguro de que exista y este abierta
    private void setConexion(Connection conexion) {
        UtilSql.asegurarConexionAbierta(conexion);
        this.conexion = conexion;
    }

    protected Connection getConexion() {
        return conexion;
    }
}
