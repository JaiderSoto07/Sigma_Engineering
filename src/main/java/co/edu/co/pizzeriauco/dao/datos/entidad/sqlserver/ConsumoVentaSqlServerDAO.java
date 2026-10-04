package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ConsumoVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;
import co.edu.co.pizzeriauco.entidad.ConsumoVentaEntidad;
import co.edu.co.pizzeriauco.entidad.DetalleVentaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsumoVentaSqlServerDAO extends SqlDAO implements ConsumoVentaDAO {

    public ConsumoVentaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    //negocio lo crea al registrar la venta: un consumo por cada insumo de la receta del producto vendido
    @Override
    public void crear(ConsumoVentaEntidad entidad) {
        var sentenciaSql = "insert into consumo_venta(id_consumo_venta, id_detalle_venta, id_producto_interno, cantidad, "
                + "id_unidad_medida, id_tipo_movimiento) values(?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del renglon de venta, el insumo, la unidad y el codigo solo se guarda su id (llaves foraneas)
            sentencia.setObject(2, entidad.getDetalleVenta().getId());
            sentencia.setObject(3, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setObject(5, entidad.getUnidadMedida().getId());
            sentencia.setObject(6, entidad.getTipoMovimiento().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_CONSUMO_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_CONSUMO_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public ConsumoVentaEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select cv.id_consumo_venta, cv.cantidad, "
                + "dv.id_detalle_venta, dv.cantidad as cantidad_detalle_venta, dv.precio_producto, dv.id_venta, dv.id_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from consumo_venta as cv "
                + "inner join detalle_venta as dv on cv.id_detalle_venta = dv.id_detalle_venta "
                + "inner join producto_interno as pi on cv.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on cv.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on cv.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where cv.id_consumo_venta = ?";
        //si no se encuentra, se devuelve el consumo por defecto (nunca nulo)
        var consumoVentaEncontrado = new ConsumoVentaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arman los padres (renglon de venta, insumo, unidad y codigo) para luego asignarlos al consumo
                //de los "abuelos" (venta y producto del renglon, unidad del insumo, categoria del codigo) solo se trae el id
                var detalleVenta = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(new VentaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_venta"))).build())
                        .producto(new ProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto"))).build())
                        .cantidad(resultado.getInt("cantidad_detalle_venta"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                consumoVentaEncontrado = new ConsumoVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_consumo_venta")))
                        .detalleVenta(detalleVenta)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .tipoMovimiento(tipoMovimiento)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CONSUMO_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CONSUMO_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return consumoVentaEncontrado;
    }

    @Override
    public List<ConsumoVentaEntidad> consultarPorFiltro(ConsumoVentaEntidad filtro) {
        var consumosEncontrados = new ArrayList<ConsumoVentaEntidad>();
        var sentenciaSql = "select cv.id_consumo_venta, cv.cantidad, "
                + "dv.id_detalle_venta, dv.cantidad as cantidad_detalle_venta, dv.precio_producto, dv.id_venta, dv.id_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from consumo_venta as cv "
                + "inner join detalle_venta as dv on cv.id_detalle_venta = dv.id_detalle_venta "
                + "inner join producto_interno as pi on cv.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on cv.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on cv.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //este el del consumo
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and cv.id_consumo_venta = ?";
            parametros.add(filtro.getId());
        }
        //estos los del renglon de venta (los insumos de un renglon, o de toda una venta)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getDetalleVenta().getId())) {
            sentenciaSql = sentenciaSql + " and dv.id_detalle_venta = ?";
            parametros.add(filtro.getDetalleVenta().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getDetalleVenta().getVenta().getId())) {
            sentenciaSql = sentenciaSql + " and dv.id_venta = ?";
            parametros.add(filtro.getDetalleVenta().getVenta().getId());
        }
        //estos los del insumo
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getProductoInterno().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProductoInterno().getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getProductoInterno().getNombre());
        }
        //este el del codigo (para llegar al consumo desde sus movimientos)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoMovimiento().getId())) {
            sentenciaSql = sentenciaSql + " and tm.id_tipo_movimiento = ?";
            parametros.add(filtro.getTipoMovimiento().getId());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un consumo y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (renglon de venta, insumo, unidad y codigo) para luego asignarlos al consumo
                //de los "abuelos" (venta y producto del renglon, unidad del insumo, categoria del codigo) solo se trae el id
                var detalleVenta = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(new VentaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_venta"))).build())
                        .producto(new ProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto"))).build())
                        .cantidad(resultado.getInt("cantidad_detalle_venta"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                var consumoVenta = new ConsumoVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_consumo_venta")))
                        .detalleVenta(detalleVenta)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                consumosEncontrados.add(consumoVenta);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CONSUMO_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CONSUMO_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return consumosEncontrados;
    }

    @Override
    public List<ConsumoVentaEntidad> consultarTodos() {
        var sentenciaSql = "select cv.id_consumo_venta, cv.cantidad, "
                + "dv.id_detalle_venta, dv.cantidad as cantidad_detalle_venta, dv.precio_producto, dv.id_venta, dv.id_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from consumo_venta as cv "
                + "inner join detalle_venta as dv on cv.id_detalle_venta = dv.id_detalle_venta "
                + "inner join producto_interno as pi on cv.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on cv.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on cv.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "order by pi.nombre asc";
        var consumosEncontrados = new ArrayList<ConsumoVentaEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (renglon de venta, insumo, unidad y codigo) para luego asignarlos al consumo
                //de los "abuelos" (venta y producto del renglon, unidad del insumo, categoria del codigo) solo se trae el id
                var detalleVenta = new DetalleVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_venta")))
                        .venta(new VentaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_venta"))).build())
                        .producto(new ProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto"))).build())
                        .cantidad(resultado.getInt("cantidad_detalle_venta"))
                        .precioProducto(resultado.getBigDecimal("precio_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                var consumoVenta = new ConsumoVentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_consumo_venta")))
                        .detalleVenta(detalleVenta)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                consumosEncontrados.add(consumoVenta);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_CONSUMOS_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ConsumoVentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_CONSUMOS_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return consumosEncontrados;
    }
}
