package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DetalleRecetaSqlServerDAO extends SqlDAO implements DetalleRecetaDAO {

    public DetalleRecetaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(DetalleRecetaEntidad entidad) {
        var sentenciaSql = "insert into detalle_receta(id_detalle_receta, id_producto, id_producto_interno, cantidad, id_unidad_medida) "
                + "values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del producto, el producto interno y la unidad solo se guarda su id (llaves foraneas)
            sentencia.setObject(2, entidad.getProducto().getId());
            sentencia.setObject(3, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setObject(5, entidad.getUnidadMedida().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public DetalleRecetaEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select dr.id_detalle_receta, dr.cantidad, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo as activo_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo as activo_producto_interno, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from detalle_receta as dr "
                + "inner join producto as p on dr.id_producto = p.id_producto "
                + "inner join producto_interno as pi on dr.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dr.id_unidad_medida = um.id_unidad_medida "
                + "where dr.id_detalle_receta = ?";
        //si no se encuentra, se devuelve el detalle por defecto (nunca nulo)
        var detalleRecetaEncontrado = new DetalleRecetaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arman los padres (producto, producto interno y unidad) para luego asignarlos al detalle
                //de los "abuelos" (tipo, tamano e insumo del producto; unidad del producto interno) solo se trae el id
                var idInsumoAsociado = resultado.getString("id_producto_interno_asociado");
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(idInsumoAsociado == null ? null : UUID.fromString(idInsumoAsociado)).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo_producto_interno"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                detalleRecetaEncontrado = new DetalleRecetaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_receta")))
                        .producto(producto)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detalleRecetaEncontrado;
    }

    @Override
    public List<DetalleRecetaEntidad> consultarPorFiltro(DetalleRecetaEntidad filtro) {
        var detallesEncontrados = new ArrayList<DetalleRecetaEntidad>();
        var sentenciaSql = "select dr.id_detalle_receta, dr.cantidad, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo as activo_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo as activo_producto_interno, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from detalle_receta as dr "
                + "inner join producto as p on dr.id_producto = p.id_producto "
                + "inner join producto_interno as pi on dr.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dr.id_unidad_medida = um.id_unidad_medida "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //este el del detalle
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and dr.id_detalle_receta = ?";
            parametros.add(filtro.getId());
        }
        //estos los del producto (para traer toda la receta de un producto)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProducto().getId())) {
            sentenciaSql = sentenciaSql + " and p.id_producto = ?";
            parametros.add(filtro.getProducto().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProducto().getNombre())) {
            sentenciaSql = sentenciaSql + " and p.nombre = ?";
            parametros.add(filtro.getProducto().getNombre());
        }
        //estos los del producto interno (ingrediente)
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
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by p.nombre asc, pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un detalle y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (producto, producto interno y unidad) para luego asignarlos al detalle
                var idInsumoAsociado = resultado.getString("id_producto_interno_asociado");
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(idInsumoAsociado == null ? null : UUID.fromString(idInsumoAsociado)).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo_producto_interno"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var detalleReceta = new DetalleRecetaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_receta")))
                        .producto(producto)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .build();
                detallesEncontrados.add(detalleReceta);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_DETALLE_RECETA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_DETALLE_RECETA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
    }

    @Override
    public List<DetalleRecetaEntidad> consultarTodos() {
        var sentenciaSql = "select dr.id_detalle_receta, dr.cantidad, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, p.id_producto_interno as id_producto_interno_asociado, p.precio, p.activo as activo_producto, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo as activo_producto_interno, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from detalle_receta as dr "
                + "inner join producto as p on dr.id_producto = p.id_producto "
                + "inner join producto_interno as pi on dr.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on dr.id_unidad_medida = um.id_unidad_medida "
                + "order by p.nombre asc, pi.nombre asc";
        var detallesEncontrados = new ArrayList<DetalleRecetaEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (producto, producto interno y unidad) para luego asignarlos al detalle
                var idInsumoAsociado = resultado.getString("id_producto_interno_asociado");
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(new TipoProductoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tipo_producto"))).build())
                        .tamano(new TamanoEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_tamano"))).build())
                        .productoInternoAsociado(new ProductoInternoEntidad.Builder()
                                .id(idInsumoAsociado == null ? null : UUID.fromString(idInsumoAsociado)).build())
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo_producto"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo_producto_interno"))
                        .build();
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var detalleReceta = new DetalleRecetaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_detalle_receta")))
                        .producto(producto)
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .unidadMedida(unidadMedida)
                        .build();
                detallesEncontrados.add(detalleReceta);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_DETALLES_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_DETALLES_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return detallesEncontrados;
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
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //retirar un ingrediente de la receta es un borrado real: la historia de lo vendido queda en el kardex
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from detalle_receta where id_detalle_receta = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.DetalleRecetaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_DETALLE_RECETA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
