package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleVentaSqlServerDAO extends SqlDAO implements DetalleVentaDAO {

    public DetalleVentaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleVentaEntidad entidad) {
        var sentenciaSql = "insert into detalle_venta(id_detalle_venta, id_venta, id_producto, cantidad, precio_producto) "
                + "values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setObject(2, entidad.getVenta().getId());
            sentencia.setObject(3, entidad.getProducto().getId());
            sentencia.setInt(4, entidad.getCantidad());
            sentencia.setBigDecimal(5, entidad.getPrecioProducto());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public DetalleVentaEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select dv.id_detalle_venta, dv.cantidad, dv.precio_producto, dv.subtotal, "
                + "v.id_venta, v.fecha, v.hora, v.factura, v.cliente, v.total, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo "
                + "from detalle_venta as dv "
                + "inner join venta as v on dv.id_venta = v.id_venta "
                + "inner join producto as p on dv.id_producto = p.id_producto "
                + "where dv.id_detalle_venta = ?";
        var detalleVentaEncontrado = new DetalleVentaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var venta = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno_asociado"))).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                //subtotal lo calcula la base (cantidad * precio); aqui solo se lee
                detalleVentaEncontrado = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(venta)
                        .producto(producto)
                        .cantidad(resultado.getInt("cantidad"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .subtotal(resultado.getBigDecimal("subtotal"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detalleVentaEncontrado;
    }

    @Override
    public List<DetalleVentaEntidad> consultarPorFiltro(DetalleVentaEntidad filtro) {
        var detallesEncontrados = new ArrayList<DetalleVentaEntidad>();
        var sentenciaSql = "select dv.id_detalle_venta, dv.cantidad, dv.precio_producto, dv.subtotal, "
                + "v.id_venta, v.fecha, v.hora, v.factura, v.cliente, v.total, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo "
                + "from detalle_venta as dv "
                + "inner join venta as v on dv.id_venta = v.id_venta "
                + "inner join producto as p on dv.id_producto = p.id_producto "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and dv.id_detalle_venta = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getVenta().getId())) {
            sentenciaSql = sentenciaSql + " and v.id_venta = ?";
            parametros.add(filtro.getVenta().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getVenta().getFactura())) {
            sentenciaSql = sentenciaSql + " and v.factura = ?";
            parametros.add(filtro.getVenta().getFactura());
        }
        //estos los del producto (por ejemplo, para saber cuanto se ha vendido de un producto)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProducto().getId())) {
            sentenciaSql = sentenciaSql + " and p.id_producto = ?";
            parametros.add(filtro.getProducto().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProducto().getNombre())) {
            sentenciaSql = sentenciaSql + " and p.nombre = ?";
            parametros.add(filtro.getProducto().getNombre());
        }
        sentenciaSql = sentenciaSql + " order by v.fecha desc, v.hora desc, p.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var venta = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno_asociado"))).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var detalleVenta = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(venta)
                        .producto(producto)
                        .cantidad(resultado.getInt("cantidad"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .subtotal(resultado.getBigDecimal("subtotal"))
                        .build();
                detallesEncontrados.add(detalleVenta);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
    }

    @Override
    public List<DetalleVentaEntidad> consultarTodos() {
        var sentenciaSql = "select dv.id_detalle_venta, dv.cantidad, dv.precio_producto, dv.subtotal, "
                + "v.id_venta, v.fecha, v.hora, v.factura, v.cliente, v.total, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo "
                + "from detalle_venta as dv "
                + "inner join venta as v on dv.id_venta = v.id_venta "
                + "inner join producto as p on dv.id_producto = p.id_producto "
                + "order by v.fecha desc, v.hora desc, p.nombre asc";
        var detallesEncontrados = new ArrayList<DetalleVentaEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var venta = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno_asociado"))).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var detalleVenta = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(venta)
                        .producto(producto)
                        .cantidad(resultado.getInt("cantidad"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .subtotal(resultado.getBigDecimal("subtotal"))
                        .build();
                detallesEncontrados.add(detalleVenta);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
    }
}
