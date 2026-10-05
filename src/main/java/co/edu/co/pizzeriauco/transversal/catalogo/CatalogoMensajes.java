package co.edu.co.pizzeriauco.transversal.catalogo;

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



    public static class UnidadMedidaSqlServerDAO {

        private UnidadMedidaSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_UNIDAD_MEDIDA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la unidad de medida deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_UNIDAD_MEDIDA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la unidad de medida deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_UNIDAD_MEDIDA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las unidades de medida. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_UNIDAD_MEDIDA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las unidades de medida. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_UNIDADES_MEDIDA = "Se ha presentado un problema tratando de consultar todas las unidades de medida. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_UNIDADES_MEDIDA = "Se ha presentado un problema NO CONTROLADO tratando de consultar todas las unidades de medida. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ClaseMovimientoSqlServerDAO {

        private ClaseMovimientoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CLASE_MOVIMIENTO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la clase de movimiento deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CLASE_MOVIMIENTO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la clase de movimiento deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CLASE_MOVIMIENTO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las clases de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CLASE_MOVIMIENTO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las clases de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_CLASES_MOVIMIENTO = "Se ha presentado un problema tratando de consultar todas las clases de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_CLASES_MOVIMIENTO = "Se ha presentado un problema NO CONTROLADO tratando de consultar todas las clases de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class CategoriaOrigenSqlServerDAO {

        private CategoriaOrigenSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CATEGORIA_ORIGEN_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la categoria de origen deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CATEGORIA_ORIGEN_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la categoria de origen deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CATEGORIA_ORIGEN_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las categorias de origen. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CATEGORIA_ORIGEN_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las categorias de origen. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_CATEGORIAS_ORIGEN = "Se ha presentado un problema tratando de consultar todas las categorias de origen. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_CATEGORIAS_ORIGEN = "Se ha presentado un problema NO CONTROLADO tratando de consultar todas las categorias de origen. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class TipoMovimientoSqlServerDAO {

        private TipoMovimientoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_TIPO_MOVIMIENTO = "Se ha presentado un problema tratando de registrar el codigo de la operacion. Por favor verifique que la categoria de origen exista, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TIPO_MOVIMIENTO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el codigo de la operacion. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_MOVIMIENTO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el tipo de movimiento deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_MOVIMIENTO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el tipo de movimiento deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_MOVIMIENTO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los tipos de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_MOVIMIENTO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los tipos de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TIPOS_MOVIMIENTO = "Se ha presentado un problema tratando de consultar todos los tipos de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TIPOS_MOVIMIENTO = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los tipos de movimiento. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class TipoProductoSqlServerDAO {

        private TipoProductoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_TIPO_PRODUCTO = "Se ha presentado un problema tratando de registrar el tipo de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TIPO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el tipo de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_PRODUCTO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el tipo de producto deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_PRODUCTO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el tipo de producto deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_PRODUCTO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los tipos de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_PRODUCTO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los tipos de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TIPOS_PRODUCTO = "Se ha presentado un problema tratando de consultar todos los tipos de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TIPOS_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los tipos de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_TIPO_PRODUCTO = "Se ha presentado un problema tratando de actualizar el tipo de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_TIPO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el tipo de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_TIPO_PRODUCTO = "Se ha presentado un problema tratando de eliminar el tipo de producto. Por favor verifique que el tipo de producto no tenga productos asociados, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_TIPO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el tipo de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class TamanoSqlServerDAO {

        private TamanoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_TAMANO = "Se ha presentado un problema tratando de registrar el tamano. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TAMANO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el tamano. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TAMANO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el tamano deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TAMANO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el tamano deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TAMANO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los tamanos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TAMANO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los tamanos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TAMANOS = "Se ha presentado un problema tratando de consultar todos los tamanos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TAMANOS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los tamanos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_TAMANO = "Se ha presentado un problema tratando de actualizar el tamano. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_TAMANO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el tamano. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_TAMANO = "Se ha presentado un problema tratando de eliminar el tamano. Por favor verifique que el tamano no tenga productos asociados, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_TAMANO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el tamano. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ProveedorSqlServerDAO {

        private ProveedorSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_PROVEEDOR = "Se ha presentado un problema tratando de registrar el proveedor. Por favor verifique que no exista otro proveedor con el mismo nombre o NIT, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PROVEEDOR = "Se ha presentado un problema NO CONTROLADO tratando de registrar el proveedor. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PROVEEDOR_POR_ID = "Se ha presentado un problema tratando de consultar la informacion del proveedor deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PROVEEDOR_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion del proveedor deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PROVEEDOR_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los proveedores. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PROVEEDOR_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los proveedores. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PROVEEDORES = "Se ha presentado un problema tratando de consultar todos los proveedores. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PROVEEDORES = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los proveedores. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PROVEEDOR = "Se ha presentado un problema tratando de actualizar el proveedor. Por favor verifique que no exista otro proveedor con el mismo nombre o NIT, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PROVEEDOR = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el proveedor. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_PROVEEDOR = "Se ha presentado un problema tratando de eliminar el proveedor. Por favor verifique que el proveedor no tenga compras registradas (si las tiene, puede desactivarlo), intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PROVEEDOR = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el proveedor. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ProductoInternoSqlServerDAO {

        private ProductoInternoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO_INTERNO = "Se ha presentado un problema tratando de registrar el producto interno. Por favor verifique que la unidad de medida exista y que no haya otro producto interno con el mismo nombre, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO_INTERNO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el producto interno. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_INTERNO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el producto interno. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_INTERNO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el producto interno. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_INTERNO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los productos internos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_INTERNO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los productos internos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PRODUCTOS_INTERNOS = "Se ha presentado un problema tratando de consultar todos los productos internos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PRODUCTOS_INTERNOS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los productos internos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO_INTERNO = "Se ha presentado un problema tratando de actualizar el producto interno. Por favor verifique que la unidad de medida exista y que no haya otro producto interno con el mismo nombre, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO_INTERNO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el producto interno. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO_INTERNO = "Se ha presentado un problema tratando de eliminar el producto interno. Por favor verifique que el producto interno no tenga compras, lotes, movimientos ni recetas (si los tiene, puede desactivarlo), intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO_INTERNO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el producto interno. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class CompraSqlServerDAO {

        private CompraSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_COMPRA = "Se ha presentado un problema tratando de registrar la compra. Por favor verifique que el proveedor exista y que esta factura de este proveedor no este ya registrada, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de registrar la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_COMPRA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_COMPRA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_COMPRA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las compras. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_COMPRA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las compras. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_COMPRAS = "Se ha presentado un problema tratando de consultar todos las compras. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_COMPRAS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos las compras. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_COMPRA = "Se ha presentado un problema tratando de actualizar la compra. Por favor verifique que el proveedor exista y que esta factura de este proveedor no este ya registrada, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de actualizar la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_COMPRA = "Se ha presentado un problema tratando de eliminar la compra. Por favor verifique que la compra no tenga renglones registrados, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de eliminar la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class DetalleCompraSqlServerDAO {

        private DetalleCompraSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_COMPRA = "Se ha presentado un problema tratando de registrar el renglon de la compra. Por favor verifique que la compra, el producto interno y la unidad de medida existan y que el producto no este repetido en la compra con la misma fecha de vencimiento, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de registrar el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_COMPRA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_COMPRA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_COMPRA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los renglones de compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_COMPRA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los renglones de compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_COMPRA = "Se ha presentado un problema tratando de consultar todos los renglones de compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los renglones de compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_COMPRA = "Se ha presentado un problema tratando de actualizar el renglon de la compra. Por favor verifique que la compra, el producto interno y la unidad de medida existan y que el producto no este repetido en la compra con la misma fecha de vencimiento, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_COMPRA = "Se ha presentado un problema tratando de eliminar el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_COMPRA = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el renglon de la compra. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ProductoSqlServerDAO {

        private ProductoSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO = "Se ha presentado un problema tratando de registrar el producto. Por favor verifique que el tipo de producto y el tamano existan y que no haya otro producto con el mismo nombre y tamano, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PRODUCTOS = "Se ha presentado un problema tratando de consultar todos los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PRODUCTOS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los productos. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO = "Se ha presentado un problema tratando de actualizar el producto. Por favor verifique que el tipo de producto y el tamano existan y que no haya otro producto con el mismo nombre y tamano, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO = "Se ha presentado un problema tratando de eliminar el producto. Por favor verifique que el producto no tenga ventas registradas (si las tiene, puede desactivarlo), intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class DetalleRecetaSqlServerDAO {

        private DetalleRecetaSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de registrar el ingrediente de la receta. Por favor verifique que el producto, el producto interno y la unidad de medida existan y que el ingrediente no este repetido en la receta, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de registrar el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los ingredientes de las recetas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los ingredientes de las recetas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_RECETA = "Se ha presentado un problema tratando de consultar todos los ingredientes de las recetas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los ingredientes de las recetas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de actualizar el ingrediente de la receta. Por favor verifique que el producto, el producto interno y la unidad de medida existan y que el ingrediente no este repetido en la receta, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_RECETA = "Se ha presentado un problema tratando de eliminar el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_RECETA = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el ingrediente de la receta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class LoteSqlServerDAO {

        private LoteSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_LOTE = "Se ha presentado un problema tratando de registrar el lote. Por favor verifique que el producto interno y la unidad de medida existan, que el numero de lote no este repetido para ese insumo y que el saldo este entre cero y la cantidad del lote, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_LOTE = "Se ha presentado un problema NO CONTROLADO tratando de registrar el lote. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_LOTE_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el lote. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_LOTE_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el lote. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_LOTE_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_LOTE_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_LOTES = "Se ha presentado un problema tratando de consultar todos los lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_LOTES = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_LOTE = "Se ha presentado un problema tratando de actualizar el lote. Por favor verifique que el movimiento, el producto interno y la unidad de medida existan, que el numero de lote no este repetido para ese insumo y que el saldo este entre cero y la cantidad del lote, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_LOTE = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el lote. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class InventarioSqlServerDAO {

        private InventarioSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_INVENTARIO = "Se ha presentado un problema tratando de registrar el inventario. Por favor verifique que el producto interno y la unidad existan, que el insumo no tenga ya un inventario y que la cantidad no quede negativa, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_INVENTARIO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_INVENTARIO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_INVENTARIO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_INVENTARIO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los inventarios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_INVENTARIO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los inventarios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_INVENTARIOS = "Se ha presentado un problema tratando de consultar todos los inventarios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_INVENTARIOS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los inventarios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_INVENTARIO = "Se ha presentado un problema tratando de actualizar el inventario. Por favor verifique que el producto interno y la unidad existan, que el insumo no tenga ya un inventario y que la cantidad no quede negativa, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_INVENTARIO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class HistoricoPrecioSqlServerDAO {

        private HistoricoPrecioSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_HISTORICO_PRECIO = "Se ha presentado un problema tratando de registrar el precio del producto. Por favor verifique que el producto exista, que no tenga otro precio que empiece el mismo dia y que el precio este entre 1 y 1.000.000, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_HISTORICO_PRECIO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el precio del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_HISTORICO_PRECIO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el precio del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_HISTORICO_PRECIO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el precio del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_HISTORICO_PRECIO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de el historial de precios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_HISTORICO_PRECIO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el historial de precios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_HISTORICOS_PRECIO = "Se ha presentado un problema tratando de consultar todos el historial de precios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_HISTORICOS_PRECIO = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos el historial de precios. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_HISTORICO_PRECIO = "Se ha presentado un problema tratando de actualizar el precio del producto. Por favor verifique que el producto exista, que no tenga otro precio que empiece el mismo dia y que el precio este entre 1 y 1.000.000, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_HISTORICO_PRECIO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el precio del producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class CambioSqlServerDAO {

        private CambioSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_CAMBIO = "Se ha presentado un problema tratando de registrar el cambio de producto. Por favor verifique que el producto interno y la unidad existan, que no haya otro cambio del mismo insumo en la misma fecha y que la fecha de vencimiento sea posterior a la del cambio, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_CAMBIO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CAMBIO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CAMBIO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CAMBIO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los cambios de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CAMBIO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los cambios de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_CAMBIOS = "Se ha presentado un problema tratando de consultar todos los cambios de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_CAMBIOS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los cambios de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_CAMBIO = "Se ha presentado un problema tratando de actualizar el cambio de producto. Por favor verifique que el producto interno y la unidad existan, que no haya otro cambio del mismo insumo en la misma fecha y que la fecha de vencimiento sea posterior a la del cambio, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_CAMBIO = "Se ha presentado un problema NO CONTROLADO tratando de actualizar el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_ELIMINANDO_CAMBIO = "Se ha presentado un problema tratando de eliminar el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_CAMBIO = "Se ha presentado un problema NO CONTROLADO tratando de eliminar el cambio de producto. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class VentaSqlServerDAO {

        private VentaSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_VENTA = "Se ha presentado un problema tratando de registrar la venta. Por favor verifique que el numero de factura no este repetido, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_VENTA = "Se ha presentado un problema NO CONTROLADO tratando de registrar la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_VENTA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_VENTA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_VENTA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_VENTA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_VENTAS = "Se ha presentado un problema tratando de consultar todos las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_VENTAS = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class DetalleVentaSqlServerDAO {

        private DetalleVentaSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_VENTA = "Se ha presentado un problema tratando de registrar el renglon de la venta. Por favor verifique que la venta y el producto existan y que el producto no este repetido en la venta, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_VENTA = "Se ha presentado un problema NO CONTROLADO tratando de registrar el renglon de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_VENTA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el renglon de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_VENTA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el renglon de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_VENTA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los renglones de venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_VENTA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los renglones de venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_VENTA = "Se ha presentado un problema tratando de consultar todos los renglones de venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_VENTA = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los renglones de venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class ConsumoVentaSqlServerDAO {

        private ConsumoVentaSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_CONSUMO_VENTA = "Se ha presentado un problema tratando de registrar el consumo de insumos de la venta. Por favor verifique que el renglon de venta, el insumo, la unidad de medida y el codigo existan y que el insumo no este repetido en el renglon, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_CONSUMO_VENTA = "Se ha presentado un problema NO CONTROLADO tratando de registrar el consumo de insumos de la venta. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CONSUMO_VENTA_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el consumo de insumos deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CONSUMO_VENTA_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el consumo de insumos deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_CONSUMO_VENTA_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los consumos de insumos de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CONSUMO_VENTA_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los consumos de insumos de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_CONSUMOS_VENTA = "Se ha presentado un problema tratando de consultar todos los consumos de insumos de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_CONSUMOS_VENTA = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los consumos de insumos de las ventas. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class SalidaLoteSqlServerDAO {

        private SalidaLoteSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_SALIDA_LOTE = "Se ha presentado un problema tratando de registrar la salida del lote. Por favor verifique que el lote exista y que no se haya sacado antes, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_SALIDA_LOTE = "Se ha presentado un problema NO CONTROLADO tratando de registrar la salida del lote. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_SALIDA_LOTE_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de la salida de lote deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_SALIDA_LOTE_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de la salida de lote deseada. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_SALIDA_LOTE_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de las salidas de lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_SALIDA_LOTE_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de las salidas de lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_SALIDAS_LOTE = "Se ha presentado un problema tratando de consultar todas las salidas de lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_SALIDAS_LOTE = "Se ha presentado un problema NO CONTROLADO tratando de consultar todas las salidas de lotes. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }

    public static class MovimientoInventarioSqlServerDAO {

        private MovimientoInventarioSqlServerDAO() {
        }

        public static final String USUARIO_ERROR_PROBLEMA_CREANDO_MOVIMIENTO_INVENTARIO = "Se ha presentado un problema tratando de registrar el movimiento de inventario. Por favor verifique que la clase de movimiento, el codigo y el lote existan, intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_MOVIMIENTO_INVENTARIO = "Se ha presentado un problema NO CONTROLADO tratando de registrar el movimiento de inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_ID = "Se ha presentado un problema tratando de consultar la informacion de el movimiento de inventario deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_ID = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de el movimiento de inventario deseado. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_FILTRO = "Se ha presentado un problema tratando de consultar la informacion de los movimientos de inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_FILTRO = "Se ha presentado un problema NO CONTROLADO tratando de consultar la informacion de los movimientos de inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_MOVIMIENTOS_INVENTARIO = "Se ha presentado un problema tratando de consultar todos los movimientos de inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
        public static final String USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_MOVIMIENTOS_INVENTARIO = "Se ha presentado un problema NO CONTROLADO tratando de consultar todos los movimientos de inventario. Por favor intente de nuevo y si el problema persiste contacte al administrador de la aplicacion y reporte la novedad";
    }
}
