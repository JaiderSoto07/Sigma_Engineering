package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.HistoricoPrecioEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoricoPrecioSqlServerDAO extends SqlDAO implements HistoricoPrecioDAO {

    public HistoricoPrecioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(HistoricoPrecioEntidad entidad) {
        var sentenciaSql = "insert into historico_precio(id_historico_precio, id_producto, precio, fecha_inicio, fecha_fin) "
                + "values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del producto solo se guarda su id (llave foranea)
            sentencia.setObject(2, entidad.getProducto().getId());
            sentencia.setBigDecimal(3, entidad.getPrecio());
            sentencia.setObject(4, entidad.getFechaInicio());
            //un precio nuevo llega con fecha fin 01/01/1000 = vigente
            sentencia.setObject(5, entidad.getFechaFin());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_HISTORICO_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_HISTORICO_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public HistoricoPrecioEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select hp.id_historico_precio, hp.precio, hp.fecha_inicio, hp.fecha_fin, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio as precio_producto, p.activo "
                + "from historico_precio as hp "
                + "inner join producto as p on hp.id_producto = p.id_producto "
                + "where hp.id_historico_precio = ?";
        //si no se encuentra, se devuelve el historico por defecto (nunca nulo)
        var historicoEncontrado = new HistoricoPrecioEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arma el producto para luego poderlo asignar al historico
                //de sus padres (tipo, tamano e insumo asociado) solo se trae el id
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
                        .precio(resultado.getBigDecimal("precio_producto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                historicoEncontrado = new HistoricoPrecioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_historico_precio")))
                        .producto(producto)
                        .precio(resultado.getBigDecimal("precio"))
                        .fechaInicio(resultado.getObject("fecha_inicio", LocalDate.class))
                        .fechaFin(resultado.getObject("fecha_fin", LocalDate.class))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_HISTORICO_PRECIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_HISTORICO_PRECIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return historicoEncontrado;
    }

    //ojo: fecha fin 01/01/1000 es el valor por defecto, por eso no sirve para filtrar "el vigente";
    //para buscar el precio vigente se consultan los precios del producto y se toma el de fecha fin 01/01/1000
    @Override
    public List<HistoricoPrecioEntidad> consultarPorFiltro(HistoricoPrecioEntidad filtro) {
        var historicosEncontrados = new ArrayList<HistoricoPrecioEntidad>();
        var sentenciaSql = "select hp.id_historico_precio, hp.precio, hp.fecha_inicio, hp.fecha_fin, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio as precio_producto, p.activo "
                + "from historico_precio as hp "
                + "inner join producto as p on hp.id_producto = p.id_producto "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los del historico
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and hp.id_historico_precio = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaInicio())) {
            sentenciaSql = sentenciaSql + " and hp.fecha_inicio = ?";
            parametros.add(filtro.getFechaInicio());
        }
        //estos los del producto (para traer todos los precios de un producto)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProducto().getId())) {
            sentenciaSql = sentenciaSql + " and p.id_producto = ?";
            parametros.add(filtro.getProducto().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProducto().getNombre())) {
            sentenciaSql = sentenciaSql + " and p.nombre = ?";
            parametros.add(filtro.getProducto().getNombre());
        }
        // el orden va siempre al final: por producto y del precio mas reciente al mas viejo
        sentenciaSql = sentenciaSql + " order by p.nombre asc, hp.fecha_inicio desc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un historico y se agrega a la lista
            while (resultado.next()) {
                //primero se arma el producto para luego poderlo asignar al historico
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
                        .precio(resultado.getBigDecimal("precio_producto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var historico = new HistoricoPrecioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_historico_precio")))
                        .producto(producto)
                        .precio(resultado.getBigDecimal("precio"))
                        .fechaInicio(resultado.getObject("fecha_inicio", LocalDate.class))
                        .fechaFin(resultado.getObject("fecha_fin", LocalDate.class))
                        .build();
                historicosEncontrados.add(historico);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_HISTORICO_PRECIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_HISTORICO_PRECIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return historicosEncontrados;
    }

    @Override
    public List<HistoricoPrecioEntidad> consultarTodos() {
        var sentenciaSql = "select hp.id_historico_precio, hp.precio, hp.fecha_inicio, hp.fecha_fin, "
                + "p.id_producto, p.nombre as nombre_producto, p.id_tipo_producto, p.id_tamano, "
                + "p.id_producto_interno as id_producto_interno_asociado, p.precio as precio_producto, p.activo "
                + "from historico_precio as hp "
                + "inner join producto as p on hp.id_producto = p.id_producto "
                + "order by p.nombre asc, hp.fecha_inicio desc";
        var historicosEncontrados = new ArrayList<HistoricoPrecioEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arma el producto para luego poderlo asignar al historico
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
                        .precio(resultado.getBigDecimal("precio_producto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var historico = new HistoricoPrecioEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_historico_precio")))
                        .producto(producto)
                        .precio(resultado.getBigDecimal("precio"))
                        .fechaInicio(resultado.getObject("fecha_inicio", LocalDate.class))
                        .fechaFin(resultado.getObject("fecha_fin", LocalDate.class))
                        .build();
                historicosEncontrados.add(historico);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_HISTORICOS_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_HISTORICOS_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return historicosEncontrados;
    }

    //solo para cerrar el precio vigente (fecha fin = ayer) o corregir el precio de un vigente que empezo hoy;
    //el producto y la fecha de inicio no cambian
    @Override
    public void actualizar(UUID id, HistoricoPrecioEntidad entidad) {
        var sentenciaSql = "update historico_precio set precio = ?, fecha_fin = ? where id_historico_precio = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setBigDecimal(1, entidad.getPrecio());
            sentencia.setObject(2, entidad.getFechaFin());
            sentencia.setObject(3, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_HISTORICO_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.HistoricoPrecioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_HISTORICO_PRECIO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
