package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoSqlServerDAO extends SqlDAO implements ProductoDAO {

    //consulta base: trae el producto con los datos de su tipo de producto y de su tamano
    private static final String SENTENCIA_CONSULTA_BASE =
            "select p.id_producto, p.nombre, p.id_producto_interno, p.precio, p.activo, "
                    + "tp.id_tipo_producto, tp.nombre as nombre_tipo_producto, "
                    + "t.id_tamano, t.tamano "
                    + "from producto p "
                    + "inner join tipo_producto tp on tp.id_tipo_producto = p.id_tipo_producto "
                    + "inner join tamano t on t.id_tamano = p.id_tamano";

    public ProductoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProductoEntidad entidad) {

        var sentenciaSql = "insert into producto (id_producto, nombre, id_tipo_producto, id_tamano, "
                + "id_producto_interno, precio) values (?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getNombre());
            sentencia.setObject(3, entidad.getTipoProducto().getId());
            sentencia.setObject(4, entidad.getTamano().getId());
            //producto_interno no se escribe: la base la calcula con id_producto_interno
            sentencia.setObject(5, idProductoInternoAsociado(entidad));
            sentencia.setBigDecimal(6, entidad.getPrecio());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //si no existe devuelve el producto por defecto (id 00000000-...), nunca nulo
    @Override
    public ProductoEntidad consultarPorId(UUID id) {
        var filtro = new ProductoEntidad.Builder().id(id).build();
        var resultados = consultarPorFiltro(filtro);
        return resultados.isEmpty() ? new ProductoEntidad.Builder().build() : resultados.get(0);
    }

    //solo filtra por los datos que vengan diferentes al valor por defecto
    //productoInterno no se usa como filtro porque un boolean no tiene valor "sin definir"
    @Override
    public List<ProductoEntidad> consultarPorFiltro(ProductoEntidad filtro) {

        var sentenciaSql = new StringBuilder(SENTENCIA_CONSULTA_BASE).append(" where 1 = 1");
        var parametros = new ArrayList<Object>();

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql.append(" and p.id_producto = ?");
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql.append(" and p.nombre = ?");
            parametros.add(filtro.getNombre());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoProducto().getId())) {
            sentenciaSql.append(" and p.id_tipo_producto = ?");
            parametros.add(filtro.getTipoProducto().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTamano().getId())) {
            sentenciaSql.append(" and p.id_tamano = ?");
            parametros.add(filtro.getTamano().getId());
        }
        if (UtilNumero.mayorQue(filtro.getPrecio(), BigDecimal.ZERO)) {
            sentenciaSql.append(" and p.precio = ?");
            parametros.add(filtro.getPrecio());
        }

        var resultados = new ArrayList<ProductoEntidad>();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }
            try (var resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    resultados.add(ObjetoProductoSql(resultado));
                }
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return resultados;
    }

    //un filtro con todos los valores por defecto no agrega condiciones, por eso trae todo
    @Override
    public List<ProductoEntidad> consultarTodos() {
        return consultarPorFiltro(new ProductoEntidad.Builder().build());
    }

    @Override
    public void actualizar(UUID id, ProductoEntidad entidad) {

        //tambien guarda activo: desactivar un producto (retirarlo del menu) es actualizarlo con activo = false
        var sentenciaSql = "update producto set nombre = ?, id_tipo_producto = ?, id_tamano = ?, "
                + "id_producto_interno = ?, precio = ?, activo = ? where id_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getNombre());
            sentencia.setObject(2, entidad.getTipoProducto().getId());
            sentencia.setObject(3, entidad.getTamano().getId());
            sentencia.setObject(4, idProductoInternoAsociado(entidad));
            sentencia.setBigDecimal(5, entidad.getPrecio());
            sentencia.setBoolean(6, entidad.isActivo());
            sentencia.setObject(7, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //eliminar = borrar de verdad (para retirarlo del menu se usa actualizar con activo = false);
    //la receta se borra en cascada; si el producto tiene ventas la base no deja eliminarlo,
    //y negocio debe borrar antes su historial de precios en la misma transaccion
    @Override
    public void eliminar(UUID id) {

        var sentenciaSql = "delete from producto where id_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //arma la entidad con la fila actual: el producto con su tipo de producto y su tamano
    private ProductoEntidad ObjetoProductoSql(ResultSet resultado) throws SQLException {

        var tipoProducto = new TipoProductoEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                .nombre(resultado.getString("nombre_tipo_producto"))
                .build();

        var tamano = new TamanoEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_tamano")))
                .tamano(resultado.getString("tamano"))
                .build();

        return new ProductoEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_producto")))
                .nombre(resultado.getString("nombre"))
                .tipoProducto(tipoProducto)
                .tamano(tamano)
                .productoInternoAsociado(ObjetoProductoInternoAsociadoSql(resultado))
                .precio(resultado.getBigDecimal("precio"))
                .activo(resultado.getBoolean("activo"))
                .build();
    }

    //la llave foranea no acepta el id por defecto (00000000-...), por eso sin insumo asociado se guarda null
    private UUID idProductoInternoAsociado(ProductoEntidad entidad) {
        return entidad.isProductoInterno() ? entidad.getProductoInternoAsociado().getId() : null;
    }

    //si la columna viene null el producto no tiene insumo asociado (se vende por receta)
    private ProductoInternoEntidad ObjetoProductoInternoAsociadoSql(ResultSet resultado) throws SQLException {
        var idProductoInterno = resultado.getString("id_producto_interno");
        return new ProductoInternoEntidad.Builder()
                .id(idProductoInterno == null ? null : UUID.fromString(idProductoInterno))
                .build();
    }
}
