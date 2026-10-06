package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CambioEntidad;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CambioSqlServerDAO extends SqlDAO implements CambioDAO {

    public CambioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    //el cambio es solo la ENTRADA del producto nuevo; la salida del lote viejo va aparte en SalidaLote
    @Override
    public void crear(CambioEntidad entidad) {
        var sentenciaSql = "insert into cambio(id_cambio, id_producto_interno, cantidad, id_unidad_medida, fecha_vencimiento, fecha_cambio, "
                + "id_tipo_movimiento) values(?, ?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setObject(2, entidad.getProductoCambio().getId());
            sentencia.setBigDecimal(3, entidad.getCantidad());
            sentencia.setObject(4, entidad.getUnidadMedida().getId());
            sentencia.setObject(5, entidad.getFechaVencimiento());
            sentencia.setObject(6, entidad.getFechaCambio());
            sentencia.setObject(7, entidad.getTipoMovimiento().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public CambioEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select ca.id_cambio, ca.cantidad, ca.fecha_vencimiento, ca.fecha_cambio, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from cambio as ca "
                + "inner join producto_interno as pi on ca.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on ca.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on ca.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where ca.id_cambio = ?";

        var cambioEncontrado = new CambioEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var productoCambio = new ProductoInternoEntidad.Builder()
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
                cambioEncontrado = new CambioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_cambio")))
                        .productoCambio(productoCambio)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .fechaCambio(resultado.getObject("fecha_cambio", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CAMBIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CAMBIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return cambioEncontrado;
    }

    @Override
    public List<CambioEntidad> consultarPorFiltro(CambioEntidad filtro) {
        var cambiosEncontrados = new ArrayList<CambioEntidad>();
        var sentenciaSql = "select ca.id_cambio, ca.cantidad, ca.fecha_vencimiento, ca.fecha_cambio, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from cambio as ca "
                + "inner join producto_interno as pi on ca.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on ca.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on ca.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and ca.id_cambio = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaCambio())) {
            sentenciaSql = sentenciaSql + " and ca.fecha_cambio = ?";
            parametros.add(filtro.getFechaCambio());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoCambio().getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getProductoCambio().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProductoCambio().getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getProductoCambio().getNombre());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getUnidadMedida().getId())) {
            sentenciaSql = sentenciaSql + " and um.id_unidad_medida = ?";
            parametros.add(filtro.getUnidadMedida().getId());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoMovimiento().getId())) {
            sentenciaSql = sentenciaSql + " and tm.id_tipo_movimiento = ?";
            parametros.add(filtro.getTipoMovimiento().getId());
        }
        sentenciaSql = sentenciaSql + " order by ca.fecha_cambio desc, pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();

            while (resultado.next()) {


                var productoCambio = new ProductoInternoEntidad.Builder()
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
                var cambio = new CambioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_cambio")))
                        .productoCambio(productoCambio)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .fechaCambio(resultado.getObject("fecha_cambio", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                cambiosEncontrados.add(cambio);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CAMBIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CAMBIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return cambiosEncontrados;
    }

    @Override
    public List<CambioEntidad> consultarTodos() {
        var sentenciaSql = "select ca.id_cambio, ca.cantidad, ca.fecha_vencimiento, ca.fecha_cambio, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from cambio as ca "
                + "inner join producto_interno as pi on ca.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on ca.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on ca.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "order by ca.fecha_cambio desc, pi.nombre asc";
        var cambiosEncontrados = new ArrayList<CambioEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {


                var productoCambio = new ProductoInternoEntidad.Builder()
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
                var cambio = new CambioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_cambio")))
                        .productoCambio(productoCambio)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .fechaCambio(resultado.getObject("fecha_cambio", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                cambiosEncontrados.add(cambio);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_CAMBIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_CAMBIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return cambiosEncontrados;
    }



    @Override
    public void actualizar(UUID id, CambioEntidad entidad) {
        var sentenciaSql = "update cambio set id_producto_interno = ?, cantidad = ?, id_unidad_medida = ?, "
                + "fecha_vencimiento = ?, fecha_cambio = ? where id_cambio = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getProductoCambio().getId());
            sentencia.setBigDecimal(2, entidad.getCantidad());
            sentencia.setObject(3, entidad.getUnidadMedida().getId());
            sentencia.setObject(4, entidad.getFechaVencimiento());
            sentencia.setObject(5, entidad.getFechaCambio());
            sentencia.setObject(6, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }


    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from cambio where id_cambio = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CambioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_CAMBIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
