package co.edu.co.pizzeriauco.crosscuting.catalogo;

//cada clase interna agrupa los mensajes de un tema
//un buen mensaje dice que fallo, por que y que hacer
public class CatalogoMensajes {

    private CatalogoMensajes() {
    }

    public static class UtilSql {

        private UtilSql() {
        }
        public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA = "Se ha presentado un problema tratando de validar si la conexion contra la fuente de informacion en la cual se iba a tratar de llevar a cabo la operacion deseada estaba o no abierta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_CONEXION_SQL_ESTA_ABIERTA = "Se ha presentado un problema NO CONTROLADO tratando de validar si la conexion contra la fuente de informacion en la cual se iba a tratar de llevar a cabo la operacion deseada estaba o no abierta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = "Se ha presentado un problema tratando de validar si la conexion contra la fuente de informacion estaba en un estado consistente al tratar de llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_VALIDANDO_SI_TRANSACCION_SQL_ESTA_INICIADA = "Se ha presentado un problema NO CONTROLADO tratando de validar si la conexion contra la fuente de informacion estaba en un estado consistente al tratar de llevar a cabo la operacion deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_INICIANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de iniciar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_INICIANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de iniciar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONFIRMANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de confirmar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONFIRMANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de confirmar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CANCELANDO_TRANSACCION_SQL = "Se ha presentado un problema tratando de cancelar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CANCELANDO_TRANSACCION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de cancelar la transaccion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CERRANDO_CONEXION_SQL = "Se ha presentado un problema tratando de cerrar la conexion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CERRANDO_CONEXION_SQL = "Se ha presentado un problema NO CONTROLADO tratando de cerrar la conexion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_CONEXION_SQL_NO_ESTA_ABIERTA = "No es posible continuar con la operacion deseada debido a que la conexion contra la fuente de informacion esta vacia o se encuentra cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_NO_ES_POSIBLE_INICIAR_TRANSACCION_SQL = "No es posible continuar con la operacion deseada debido a que la conexion contra la fuente de informacion se encuentra en un estado inconsistente porque esta cerrada, esta vacia o porque la transaccion ya fue iniciada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_NO_ES_POSIBLE_CONFIRMAR_TRANSACCION_SQL = "No es posible confirmar los cambios de la operacion deseada debido a que la conexion contra la fuente de informacion esta vacia, esta cerrada o porque la transaccion no fue iniciada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_NO_ES_POSIBLE_CANCELAR_TRANSACCION_SQL = "No es posible deshacer los cambios de la operacion deseada debido a que la conexion contra la fuente de informacion esta vacia, esta cerrada o porque la transaccion no fue iniciada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_NO_ES_POSIBLE_CERRAR_CONEXION_SQL = "No es posible cerrar la conexion contra la fuente de informacion debido a que esta vacia o ya se encuentra cerrada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class SqlServerDAOFactory {

        private SqlServerDAOFactory() {
        }

        //problema concreto (SQLException) y problema no controlado (Exception) al abrir la conexion
        public static final String USUARIO_ERROR_PROBLEMA_ABRIENDO_CONEXION_SQL_SERVER = "Se ha presentado un problema tratando de abrir la conexion contra la fuente de informacion. Por favor verifique que SQL Server este encendido, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ABRIENDO_CONEXION_SQL_SERVER = "Se ha presentado un problema NO CONTROLADO tratando de abrir la conexion contra la fuente de informacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class DetalleRecetaSqlServerDAO {

        private DetalleRecetaSqlServerDAO() {
        }

        //problema concreto (SQLException) y problema no controlado (Exception) de cada operacion
        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de registrar el ingrediente en la receta del producto. Por favor verifique que el producto, el producto interno y la unidad de medida existan, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de registrar el ingrediente en la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de consultar la informacion de la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de actualizar el ingrediente de la receta del producto. Por favor verifique que el producto, el producto interno y la unidad de medida existan, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el ingrediente de la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de eliminar el ingrediente de la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el ingrediente de la receta del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ProductoSqlServerDAO {

        private ProductoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO = "Se ha presentado un problema tratando de registrar el producto. Por favor verifique que el tipo de producto y el tamano existan, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO = "Se ha presentado un problema tratando de consultar la informacion de los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO = "Se ha presentado un problema tratando de actualizar el producto. Por favor verifique que el tipo de producto y el tamano existan, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO = "Se ha presentado un problema tratando de eliminar el producto. Por favor verifique que el producto no tenga ventas ni historico de precios registrados, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

}
