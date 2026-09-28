package co.edu.co.pizzeriauco.dao.factoria.impl;

//prueba manual: abre la conexion contra la base Pizzeria y la cierra
public class ConexionSqlServer {

    public static void main(String[] args) {

        SqlServerDAOFactory factory = new SqlServerDAOFactory();

        System.out.println("¡Conexión exitosa!");

        factory.cerrarConexion();
    }
}
