package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.InventarioEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventarioSqlServerDAO extends SqlDAO implements InventarioDAO {

    public InventarioSqlServerDAO(Connection conexion) {
        super(conexion);
    }


    @Override
    public void crear(InventarioEntidad entidad) {
        var sentenciaSql = "insert into inventario(id_inventario, id_producto_interno, cantidad_total, id_unidad_medida, stock_minimo) "
                + "values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            sentencia.setObject(1, entidad.getId());
            sentencia.setObject(2, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(3, entidad.getCantidadTotal());
            sentencia.setObject(4, entidad.getUnidadMedidaInventario().getId());
            sentencia.setBigDecimal(5, entidad.getStockMinimo());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public InventarioEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select i.id_inventario, i.cantidad_total, i.stock_minimo, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from inventario as i "
                + "inner join producto_interno as pi on i.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on i.id_unidad_medida = um.id_unidad_medida "
                + "where i.id_inventario = ?";
        var inventarioEncontrado = new InventarioEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                inventarioEncontrado = new InventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_inventario")))
                        .productoInterno(productoInterno)
                        .cantidadTotal(resultado.getBigDecimal("cantidad_total"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .stockMinimo(resultado.getBigDecimal("stock_minimo"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_INVENTARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_INVENTARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return inventarioEncontrado;
    }

    @Override
    public List<InventarioEntidad> consultarPorFiltro(InventarioEntidad filtro) {
        var inventariosEncontrados = new ArrayList<InventarioEntidad>();
        var sentenciaSql = "select i.id_inventario, i.cantidad_total, i.stock_minimo, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from inventario as i "
                + "inner join producto_interno as pi on i.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on i.id_unidad_medida = um.id_unidad_medida "
                + "where 1=1";
        var parametros = new ArrayList<Object>();

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and i.id_inventario = ?";
            parametros.add(filtro.getId());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getProductoInterno().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProductoInterno().getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getProductoInterno().getNombre());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getUnidadMedidaInventario().getId())) {
            sentenciaSql = sentenciaSql + " and um.id_unidad_medida = ?";
            parametros.add(filtro.getUnidadMedidaInventario().getId());
        }

        sentenciaSql = sentenciaSql + " order by pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();

            while (resultado.next()) {

                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var inventario = new InventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_inventario")))
                        .productoInterno(productoInterno)
                        .cantidadTotal(resultado.getBigDecimal("cantidad_total"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .stockMinimo(resultado.getBigDecimal("stock_minimo"))
                        .build();
                inventariosEncontrados.add(inventario);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_INVENTARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_INVENTARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return inventariosEncontrados;
    }

    @Override
    public List<InventarioEntidad> consultarTodos() {
        var sentenciaSql = "select i.id_inventario, i.cantidad_total, i.stock_minimo, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from inventario as i "
                + "inner join producto_interno as pi on i.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on i.id_unidad_medida = um.id_unidad_medida "
                + "order by pi.nombre asc";
        var inventariosEncontrados = new ArrayList<InventarioEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {

                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var inventario = new InventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_inventario")))
                        .productoInterno(productoInterno)
                        .cantidadTotal(resultado.getBigDecimal("cantidad_total"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .stockMinimo(resultado.getBigDecimal("stock_minimo"))
                        .build();
                inventariosEncontrados.add(inventario);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_INVENTARIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_INVENTARIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return inventariosEncontrados;
    }



    @Override
    public void actualizar(UUID id, InventarioEntidad entidad) {
        var sentenciaSql = "update inventario set cantidad_total = ?, stock_minimo = ? where id_inventario = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setBigDecimal(1, entidad.getCantidadTotal());
            sentencia.setBigDecimal(2, entidad.getStockMinimo());
            sentencia.setObject(3, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.InventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
