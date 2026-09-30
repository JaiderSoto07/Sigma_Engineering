package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleRecetaSqlServerDAO extends SqlDAO implements DetalleRecetaDAO {

    //consulta base: trae el detalle con los datos del producto, del producto interno y de la unidad de medida
    private static final String SENTENCIA_CONSULTA_BASE =
            "select dr.id_detalle_receta, dr.cantidad, "
                    + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                    + "p.producto_interno, p.precio, "
                    + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, "
                    + "pi.vida_util, pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                    + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                    + "from detalle_receta dr "
                    + "inner join producto p on p.id_producto = dr.id_producto "
                    + "inner join producto_interno pi on pi.id_producto_interno = dr.id_producto_interno "
                    + "inner join unidad_medida um on um.id_unidad_medida = dr.id_unidad_medida";

    public DetalleRecetaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleRecetaEntidad entidad) {

        var sentenciaSql = "insert into detalle_receta (id_detalle_receta, id_producto, id_producto_interno, "
                + "cantidad, id_unidad_medida) values (?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setObject(2, entidad.getProducto().getId());
            sentencia.setObject(3, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setObject(5, entidad.getUnidadMedida().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //si no existe devuelve el detalle por defecto (id 00000000-...), nunca nulo
    @Override
    public DetalleRecetaEntidad consultarPorId(UUID id) {
        var filtro = new DetalleRecetaEntidad.Builder().id(id).build();
        var resultados = consultarPorFiltro(filtro);
        return resultados.isEmpty() ? new DetalleRecetaEntidad.Builder().build() : resultados.get(0);
    }

    //solo filtra por los datos que vengan diferentes al valor por defecto
    @Override
    public List<DetalleRecetaEntidad> consultarPorFiltro(DetalleRecetaEntidad filtro) {

        var sentenciaSql = new StringBuilder(SENTENCIA_CONSULTA_BASE).append(" where 1 = 1");
        var parametros = new ArrayList<Object>();

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql.append(" and dr.id_detalle_receta = ?");
            parametros.add(filtro.getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProducto().getId())) {
            sentenciaSql.append(" and dr.id_producto = ?");
            parametros.add(filtro.getProducto().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoInterno().getId())) {
            sentenciaSql.append(" and dr.id_producto_interno = ?");
            parametros.add(filtro.getProductoInterno().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getUnidadMedida().getId())) {
            sentenciaSql.append(" and dr.id_unidad_medida = ?");
            parametros.add(filtro.getUnidadMedida().getId());
        }
        if (UtilNumero.mayorQue(filtro.getCantidad(), BigDecimal.ZERO)) {
            sentenciaSql.append(" and dr.cantidad = ?");
            parametros.add(filtro.getCantidad());
        }

        var resultados = new ArrayList<DetalleRecetaEntidad>();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql.toString())) {
            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }
            try (var resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    resultados.add(ObjetoDetalleRecetaSql(resultado));
                }
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return resultados;
    }

    //un filtro con todos los valores por defecto no agrega condiciones, por eso trae todo
    @Override
    public List<DetalleRecetaEntidad> consultarTodos() {
        return consultarPorFiltro(new DetalleRecetaEntidad.Builder().build());
    }

    @Override
    public void actualizar(UUID id, DetalleRecetaEntidad entidad) {

        var sentenciaSql = "update detalle_receta set id_producto = ?, id_producto_interno = ?, cantidad = ?, "
                + "id_unidad_medida = ? where id_detalle_receta = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getProducto().getId());
            sentencia.setObject(2, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(3, entidad.getCantidad());
            sentencia.setObject(4, entidad.getUnidadMedida().getId());
            sentencia.setObject(5, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {

        var sentenciaSql = "delete from detalle_receta where id_detalle_receta = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //arma la entidad con la fila actual; del tipo de producto, del tamano y de la medida
    //del producto interno solo se trae el id, el resto se consulta con su propio DAO si se necesita
    private DetalleRecetaEntidad ObjetoDetalleRecetaSql(ResultSet resultado) throws SQLException {

        var producto = new ProductoEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_producto")))
                .nombre(resultado.getString("nombre_producto"))
                .tipoProducto(new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                .tamano(new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                .productoInterno(resultado.getBoolean("producto_interno"))
                .precio(resultado.getBigDecimal("precio"))
                .build();

        var productoInterno = new ProductoInternoEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_producto_interno")))
                .nombre(resultado.getString("nombre_producto_interno"))
                .perecedero(resultado.getBoolean("perecedero"))
                .vidaUtil(resultado.getInt("vida_util"))
                .tipoMedida(new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                .build();

        var unidadMedida = new UnidadMedidaEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                .unidadMedida(resultado.getString("unidad_medida"))
                .tipoMedida(resultado.getString("tipo_medida"))
                .build();

        return new DetalleRecetaEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_detalle_receta")))
                .producto(producto)
                .productoInterno(productoInterno)
                .cantidad(resultado.getBigDecimal("cantidad"))
                .unidadMedida(unidadMedida)
                .build();
    }
}
