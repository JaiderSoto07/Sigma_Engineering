package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CompraEntidad;
import co.edu.co.pizzeriauco.entidad.DetalleCompraEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleCompraSqlServerDAO extends SqlDAO implements DetalleCompraDAO {

    public DetalleCompraSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleCompraEntidad entidad) {
        var sentenciaSql = "insert into detalle_compra(id_detalle_compra, id_compra, id_producto_interno, cantidad, "
                + "id_unidad_medida, precio_compra, fecha_vencimiento, id_tipo_movimiento) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //de la compra, el producto interno, la unidad y el codigo solo se guarda su id (llaves foraneas)
            sentencia.setObject(2, entidad.getCompra().getId());
            sentencia.setObject(3, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setObject(5, entidad.getUnidadMedida().getId());
            sentencia.setBigDecimal(6, entidad.getPrecioCompra());
            sentencia.setObject(7, entidad.getFechaVencimiento());
            sentencia.setObject(8, entidad.getTipoMovimiento().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public DetalleCompraEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select dc.id_detalle_compra, dc.cantidad, dc.precio_compra, dc.fecha_vencimiento, "
                + "c.id_compra, c.fecha_compra, c.numero_factura, c.total, c.id_proveedor, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from detalle_compra as dc "
                + "inner join compra as c on dc.id_compra = c.id_compra "
                + "inner join producto_interno as pi on dc.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dc.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on dc.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where dc.id_detalle_compra = ?";
        //si no se encuentra, se devuelve el detalle por defecto (nunca nulo)
        var detalleCompraEncontrado = new DetalleCompraEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arman los padres (compra, producto interno, unidad y codigo) para luego asignarlos al detalle
                //de los "abuelos" (proveedor de la compra, unidad del producto interno, categoria del codigo) solo se trae el id
                var compra = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(new ProveedorEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_proveedor"))).build())
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
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
                detalleCompraEncontrado = new DetalleCompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_compra")))
                        .compra(compra)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .precioCompra(resultado.getBigDecimal("precio_compra"))
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_COMPRA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_COMPRA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detalleCompraEncontrado;
    }

    @Override
    public List<DetalleCompraEntidad> consultarPorFiltro(DetalleCompraEntidad filtro) {
        var detallesEncontrados = new ArrayList<DetalleCompraEntidad>();
        var sentenciaSql = "select dc.id_detalle_compra, dc.cantidad, dc.precio_compra, dc.fecha_vencimiento, "
                + "c.id_compra, c.fecha_compra, c.numero_factura, c.total, c.id_proveedor, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from detalle_compra as dc "
                + "inner join compra as c on dc.id_compra = c.id_compra "
                + "inner join producto_interno as pi on dc.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dc.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on dc.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //este el del detalle
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and dc.id_detalle_compra = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaVencimiento())) {
            sentenciaSql = sentenciaSql + " and dc.fecha_vencimiento = ?";
            parametros.add(filtro.getFechaVencimiento());
        }
        //estos los de la compra (para traer todos los renglones de una compra)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getCompra().getId())) {
            sentenciaSql = sentenciaSql + " and c.id_compra = ?";
            parametros.add(filtro.getCompra().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getCompra().getNumeroFactura())) {
            sentenciaSql = sentenciaSql + " and c.numero_factura = ?";
            parametros.add(filtro.getCompra().getNumeroFactura());
        }
        //estos los del producto interno
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getProductoInterno().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProductoInterno().getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getProductoInterno().getNombre());
        }
        //este el de la unidad de medida
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getUnidadMedida().getId())) {
            sentenciaSql = sentenciaSql + " and um.id_unidad_medida = ?";
            parametros.add(filtro.getUnidadMedida().getId());
        }
        //este el del codigo (para llegar al renglon desde su movimiento)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoMovimiento().getId())) {
            sentenciaSql = sentenciaSql + " and tm.id_tipo_movimiento = ?";
            parametros.add(filtro.getTipoMovimiento().getId());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by c.fecha_compra desc, pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un detalle y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (compra, producto interno, unidad y codigo) para luego asignarlos al detalle
                var compra = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(new ProveedorEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_proveedor"))).build())
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
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
                var detalleCompra = new DetalleCompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_compra")))
                        .compra(compra)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .precioCompra(resultado.getBigDecimal("precio_compra"))
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                detallesEncontrados.add(detalleCompra);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_COMPRA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_COMPRA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
    }

    @Override
    public List<DetalleCompraEntidad> consultarTodos() {
        var sentenciaSql = "select dc.id_detalle_compra, dc.cantidad, dc.precio_compra, dc.fecha_vencimiento, "
                + "c.id_compra, c.fecha_compra, c.numero_factura, c.total, c.id_proveedor, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida, "
                + "tm.id_tipo_movimiento, tm.id_categoria_origen "
                + "from detalle_compra as dc "
                + "inner join compra as c on dc.id_compra = c.id_compra "
                + "inner join producto_interno as pi on dc.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dc.id_unidad_medida = um.id_unidad_medida "
                + "inner join tipo_movimiento as tm on dc.id_tipo_movimiento = tm.id_tipo_movimiento "
                + "order by c.fecha_compra desc, pi.nombre asc";
        var detallesEncontrados = new ArrayList<DetalleCompraEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (compra, producto interno, unidad y codigo) para luego asignarlos al detalle
                var compra = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(new ProveedorEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_proveedor"))).build())
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
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
                var detalleCompra = new DetalleCompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_compra")))
                        .compra(compra)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .precioCompra(resultado.getBigDecimal("precio_compra"))
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .tipoMovimiento(tipoMovimiento)
                        .build();
                detallesEncontrados.add(detalleCompra);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
    }

    //negocio solo lo permite mientras el lote del renglon este intacto (saldo = cantidad)
    //el codigo (id_tipo_movimiento) no se actualiza: el renglon conserva siempre el mismo
    @Override
    public void actualizar(UUID id, DetalleCompraEntidad entidad) {
        var sentenciaSql = "update detalle_compra set id_compra = ?, id_producto_interno = ?, cantidad = ?, "
                + "id_unidad_medida = ?, precio_compra = ?, fecha_vencimiento = ? where id_detalle_compra = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getCompra().getId());
            sentencia.setObject(2, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(3, entidad.getCantidad());
            sentencia.setObject(4, entidad.getUnidadMedida().getId());
            sentencia.setBigDecimal(5, entidad.getPrecioCompra());
            sentencia.setObject(6, entidad.getFechaVencimiento());
            sentencia.setObject(7, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //negocio solo lo permite mientras el lote del renglon este intacto (saldo = cantidad)
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from detalle_compra where id_detalle_compra = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleCompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
