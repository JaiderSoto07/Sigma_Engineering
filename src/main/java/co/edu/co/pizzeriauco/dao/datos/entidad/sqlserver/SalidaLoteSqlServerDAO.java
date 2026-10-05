package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.dao.datos.entidad.SalidaLoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.LoteEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.SalidaLoteEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SalidaLoteSqlServerDAO extends SqlDAO implements SalidaLoteDAO {

    public SalidaLoteSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    //negocio, en la misma transaccion, deja el saldo del lote en 0 y lo descuenta del inventario
    @Override
    public void crear(SalidaLoteEntidad entidad) {
        var sentenciaSql = "insert into salida_lote(id_salida_lote, id_lote, cantidad, fecha_movimiento) values(?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del lote solo se guarda su id (llave foranea)
            sentencia.setObject(2, entidad.getLote().getId());
            sentencia.setBigDecimal(3, entidad.getCantidad());
            sentencia.setObject(4, entidad.getFechaMovimiento());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_SALIDA_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_SALIDA_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public SalidaLoteEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select sl.id_salida_lote, sl.cantidad, sl.fecha_movimiento, "
                + "l.id_lote, l.numero_lote, l.cantidad as cantidad_lote, l.saldo, l.fecha_vencimiento, "
                + "l.id_producto_interno, l.id_unidad_medida "
                + "from salida_lote as sl "
                + "inner join lote as l on sl.id_lote = l.id_lote "
                + "where sl.id_salida_lote = ?";
        //si no se encuentra, se devuelve la salida por defecto (nunca nulo)
        var salidaLoteEncontrada = new SalidaLoteEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arma el lote para luego asignarlo a la salida
                //de los "abuelos" (insumo y unidad) solo se trae el id
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
                salidaLoteEncontrada = new SalidaLoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_salida_lote")))
                        .lote(lote)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_SALIDA_LOTE_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_SALIDA_LOTE_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return salidaLoteEncontrada;
    }

    @Override
    public List<SalidaLoteEntidad> consultarPorFiltro(SalidaLoteEntidad filtro) {
        var salidasEncontradas = new ArrayList<SalidaLoteEntidad>();
        var sentenciaSql = "select sl.id_salida_lote, sl.cantidad, sl.fecha_movimiento, "
                + "l.id_lote, l.numero_lote, l.cantidad as cantidad_lote, l.saldo, l.fecha_vencimiento, "
                + "l.id_producto_interno, l.id_unidad_medida "
                + "from salida_lote as sl "
                + "inner join lote as l on sl.id_lote = l.id_lote "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los de la salida
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and sl.id_salida_lote = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaMovimiento())) {
            sentenciaSql = sentenciaSql + " and sl.fecha_movimiento = ?";
            parametros.add(filtro.getFechaMovimiento());
        }
        //estos los del lote (la salida de un lote, o todas las salidas de un insumo)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getLote().getId())) {
            sentenciaSql = sentenciaSql + " and l.id_lote = ?";
            parametros.add(filtro.getLote().getId());
        }
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getLote().getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and l.id_producto_interno = ?";
            parametros.add(filtro.getLote().getProductoInterno().getId());
        }
        // el orden va siempre al final: las salidas mas recientes primero
        sentenciaSql = sentenciaSql + " order by sl.fecha_movimiento desc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma una salida y se agrega a la lista
            while (resultado.next()) {
                //primero se arma el lote para luego asignarlo a la salida
                //de los "abuelos" (insumo y unidad) solo se trae el id
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
                var salidaLote = new SalidaLoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_salida_lote")))
                        .lote(lote)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .build();
                salidasEncontradas.add(salidaLote);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_SALIDA_LOTE_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_SALIDA_LOTE_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return salidasEncontradas;
    }

    @Override
    public List<SalidaLoteEntidad> consultarTodos() {
        var sentenciaSql = "select sl.id_salida_lote, sl.cantidad, sl.fecha_movimiento, "
                + "l.id_lote, l.numero_lote, l.cantidad as cantidad_lote, l.saldo, l.fecha_vencimiento, "
                + "l.id_producto_interno, l.id_unidad_medida "
                + "from salida_lote as sl "
                + "inner join lote as l on sl.id_lote = l.id_lote "
                + "order by sl.fecha_movimiento desc";
        var salidasEncontradas = new ArrayList<SalidaLoteEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arma el lote para luego asignarlo a la salida
                //de los "abuelos" (insumo y unidad) solo se trae el id
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
                var salidaLote = new SalidaLoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_salida_lote")))
                        .lote(lote)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .fechaMovimiento(resultado.getObject("fecha_movimiento", LocalDate.class))
                        .build();
                salidasEncontradas.add(salidaLote);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_SALIDAS_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.SalidaLoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_SALIDAS_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return salidasEncontradas;
    }
}
