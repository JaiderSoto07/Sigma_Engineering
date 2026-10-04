package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.dao.datos.entidad.MovimientoInventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;
import co.edu.co.pizzeriauco.entidad.ClaseMovimientoEntidad;
import co.edu.co.pizzeriauco.entidad.LoteEntidad;
import co.edu.co.pizzeriauco.entidad.MovimientoInventarioEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MovimientoInventarioSqlServerDAO extends SqlDAO implements MovimientoInventarioDAO {

    public MovimientoInventarioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    //entrada: el lote ya fue creado (en 0) y negocio lo llena con esta cantidad;
    //salida: negocio descuenta esta cantidad del saldo del lote. En los dos casos tambien ajusta el inventario
    @Override
    public void crear(MovimientoInventarioEntidad entidad) {
        var sentenciaSql = "insert into movimiento_inventario(id_movimiento_inventario, id_clase_movimiento, id_tipo_movimiento, "
                + "cantidad, fecha_movimiento, id_lote) values(?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //de la clase, el codigo y el lote solo se guarda su id (llaves foraneas)
            sentencia.setObject(2, entidad.getClaseMovimiento().getId());
            sentencia.setObject(3, entidad.getTipoMovimiento().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setObject(5, entidad.getFechaMovimiento());
            sentencia.setObject(6, entidad.getLote().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_MOVIMIENTO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_MOVIMIENTO_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public MovimientoInventarioEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select mi.id_movimiento_inventario, mi.cantidad, mi.fecha_movimiento, "
                + "cm.id_clase_movimiento, cm.nombre as nombre_clase_movimiento, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen, "
                + "l.id_lote, l.numero_lote, l.id_producto_interno, l.cantidad as cantidad_lote, l.saldo, "
                + "l.id_unidad_medida, l.fecha_vencimiento "
                + "from movimiento_inventario as mi "
                + "inner join clase_movimiento as cm on mi.id_clase_movimiento = cm.id_clase_movimiento "
                + "inner join tipo_movimiento as tm on mi.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "inner join lote as l on mi.id_lote = l.id_lote "
                + "where mi.id_movimiento_inventario = ?";
        //si no se encuentra, se devuelve el movimiento por defecto (nunca nulo)
        var movimientoEncontrado = new MovimientoInventarioEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arman los padres (clase, codigo y lote) para luego asignarlos al movimiento
                //de los "abuelos" (categoria del codigo; insumo y unidad del lote) solo se trae el id
                var claseMovimiento = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre_clase_movimiento"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                var lote = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno"))).build())
                        .cantidad(resultado.getBigDecimal("cantidad_lote"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .unidadMedidaInventario(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida"))).build())
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
                movimientoEncontrado = new MovimientoInventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_movimiento_inventario")))
                        .claseMovimiento(claseMovimiento)
                        .tipoMovimiento(tipoMovimiento)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .lote(lote)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return movimientoEncontrado;
    }

    @Override
    public List<MovimientoInventarioEntidad> consultarPorFiltro(MovimientoInventarioEntidad filtro) {
        var movimientosEncontrados = new ArrayList<MovimientoInventarioEntidad>();
        var sentenciaSql = "select mi.id_movimiento_inventario, mi.cantidad, mi.fecha_movimiento, "
                + "cm.id_clase_movimiento, cm.nombre as nombre_clase_movimiento, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen, "
                + "l.id_lote, l.numero_lote, l.id_producto_interno, l.cantidad as cantidad_lote, l.saldo, "
                + "l.id_unidad_medida, l.fecha_vencimiento "
                + "from movimiento_inventario as mi "
                + "inner join clase_movimiento as cm on mi.id_clase_movimiento = cm.id_clase_movimiento "
                + "inner join tipo_movimiento as tm on mi.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "inner join lote as l on mi.id_lote = l.id_lote "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los del movimiento
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and mi.id_movimiento_inventario = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaMovimiento())) {
            sentenciaSql = sentenciaSql + " and mi.fecha_movimiento = ?";
            parametros.add(filtro.getFechaMovimiento());
        }
        //este el de la clase (solo entradas o solo salidas)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getClaseMovimiento().getId())) {
            sentenciaSql = sentenciaSql + " and cm.id_clase_movimiento = ?";
            parametros.add(filtro.getClaseMovimiento().getId());
        }
        //este el del codigo (todos los movimientos de un renglon de compra, consumo de venta o cambio)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoMovimiento().getId())) {
            sentenciaSql = sentenciaSql + " and tm.id_tipo_movimiento = ?";
            parametros.add(filtro.getTipoMovimiento().getId());
        }
        //estos los del lote (la historia de un lote, o la de todos los lotes de un insumo)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getLote().getId())) {
            sentenciaSql = sentenciaSql + " and l.id_lote = ?";
            parametros.add(filtro.getLote().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getLote().getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and l.id_producto_interno = ?";
            parametros.add(filtro.getLote().getProductoInterno().getId());
        }
        // el orden va siempre al final: como un kardex, del mas viejo al mas nuevo
        sentenciaSql = sentenciaSql + " order by mi.fecha_movimiento asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un movimiento y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (clase, codigo y lote) para luego asignarlos al movimiento
                //de los "abuelos" (categoria del codigo; insumo y unidad del lote) solo se trae el id
                var claseMovimiento = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre_clase_movimiento"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                var lote = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno"))).build())
                        .cantidad(resultado.getBigDecimal("cantidad_lote"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .unidadMedidaInventario(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida"))).build())
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
                var movimiento = new MovimientoInventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_movimiento_inventario")))
                        .claseMovimiento(claseMovimiento)
                        .tipoMovimiento(tipoMovimiento)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .lote(lote)
                        .build();
                movimientosEncontrados.add(movimiento);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_MOVIMIENTO_INVENTARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return movimientosEncontrados;
    }

    @Override
    public List<MovimientoInventarioEntidad> consultarTodos() {
        var sentenciaSql = "select mi.id_movimiento_inventario, mi.cantidad, mi.fecha_movimiento, "
                + "cm.id_clase_movimiento, cm.nombre as nombre_clase_movimiento, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen, "
                + "l.id_lote, l.numero_lote, l.id_producto_interno, l.cantidad as cantidad_lote, l.saldo, "
                + "l.id_unidad_medida, l.fecha_vencimiento "
                + "from movimiento_inventario as mi "
                + "inner join clase_movimiento as cm on mi.id_clase_movimiento = cm.id_clase_movimiento "
                + "inner join tipo_movimiento as tm on mi.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "inner join lote as l on mi.id_lote = l.id_lote "
                + "order by mi.fecha_movimiento asc";
        var movimientosEncontrados = new ArrayList<MovimientoInventarioEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (clase, codigo y lote) para luego asignarlos al movimiento
                //de los "abuelos" (categoria del codigo; insumo y unidad del lote) solo se trae el id
                var claseMovimiento = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre_clase_movimiento"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(new CategoriaOrigenEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_categoria_origen"))).build())
                        .build();
                var lote = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(new ProductoInternoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_producto_interno"))).build())
                        .cantidad(resultado.getBigDecimal("cantidad_lote"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .unidadMedidaInventario(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida"))).build())
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
                var movimiento = new MovimientoInventarioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_movimiento_inventario")))
                        .claseMovimiento(claseMovimiento)
                        .tipoMovimiento(tipoMovimiento)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .lote(lote)
                        .build();
                movimientosEncontrados.add(movimiento);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_MOVIMIENTOS_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.MovimientoInventarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_MOVIMIENTOS_INVENTARIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return movimientosEncontrados;
    }
}
