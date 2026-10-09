package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoSqlServerDAO extends SqlDAO implements ProductoDAO {

    public ProductoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProductoEntidad entidad) {
        var sentenciaSql = "insert into producto(id_producto, nombre, id_tipo_producto, id_tamano, id_producto_interno, precio, activo) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getNombre());
            sentencia.setObject(3, entidad.getTipoProducto().getId());
            sentencia.setObject(4, entidad.getTamano().getId());
            sentencia.setObject(5, entidad.getProductoInternoAsociado().getId());
            sentencia.setBigDecimal(6, entidad.getPrecio());
            sentencia.setBoolean(7, entidad.isActivo());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public ProductoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select p.id_producto, p.nombre as nombre_producto, p.id_producto_interno, p.producto_interno, p.precio, p.activo, "
                + "tp.id_tipo_producto, tp.nombre as nombre_tipo_producto, t.id_tamano, t.tamano "
                + "from producto as p "
                + "inner join tipo_producto as tp on p.id_tipo_producto = tp.id_tipo_producto "
                + "inner join tamano as t on p.id_tamano = t.id_tamano "
                + "where p.id_producto = ?";
        var productoEncontrado = new ProductoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre_tipo_producto"))
                        .build();
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                var productoInternoAsociado = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .build();

                productoEncontrado = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(tipoProducto)
                        .tamano(tamano)
                        .productoInterno(resultado.getBoolean("producto_interno"))
                        .productoInternoAsociado(productoInternoAsociado)
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productoEncontrado;
    }

    //activo no se usa como filtro porque un boolean no tiene valor "sin definir"
    @Override
    public List<ProductoEntidad> consultarPorFiltro(ProductoEntidad filtro) {
        var productosEncontrados = new ArrayList<ProductoEntidad>();
        var sentenciaSql = "select p.id_producto, p.nombre as nombre_producto, p.id_producto_interno, p.producto_interno, p.precio, p.activo, "
                + "tp.id_tipo_producto, tp.nombre as nombre_tipo_producto, t.id_tamano, t.tamano "
                + "from producto as p "
                + "inner join tipo_producto as tp on p.id_tipo_producto = tp.id_tipo_producto "
                + "inner join tamano as t on p.id_tamano = t.id_tamano "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los del producto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and p.id_producto = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and p.nombre = ?";
            parametros.add(filtro.getNombre());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoProducto().getId())) {
            sentenciaSql = sentenciaSql + " and tp.id_tipo_producto = ?";
            parametros.add(filtro.getTipoProducto().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTipoProducto().getNombre())) {
            sentenciaSql = sentenciaSql + " and tp.nombre = ?";
            parametros.add(filtro.getTipoProducto().getNombre());
        }

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTamano().getId())) {
            sentenciaSql = sentenciaSql + " and t.id_tamano = ?";
            parametros.add(filtro.getTamano().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTamano().getTamano())) {
            sentenciaSql = sentenciaSql + " and t.tamano = ?";
            parametros.add(filtro.getTamano().getTamano());
        }

        sentenciaSql = sentenciaSql + " order by p.nombre asc, t.tamano asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un producto y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (tipo de producto, tamano e insumo asociado) para luego asignarlos al producto
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre_tipo_producto"))
                        .build();
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                var productoInternoAsociado = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(tipoProducto)
                        .tamano(tamano)
                        .productoInterno(resultado.getBoolean("producto_interno"))
                        .productoInternoAsociado(productoInternoAsociado)
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosEncontrados.add(producto);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosEncontrados;
    }

    @Override
    public List<ProductoEntidad> consultarTodos() {
        var sentenciaSql = "select p.id_producto, p.nombre as nombre_producto, p.id_producto_interno, p.producto_interno, p.precio, p.activo, "
                + "tp.id_tipo_producto, tp.nombre as nombre_tipo_producto, t.id_tamano, t.tamano "
                + "from producto as p "
                + "inner join tipo_producto as tp on p.id_tipo_producto = tp.id_tipo_producto "
                + "inner join tamano as t on p.id_tamano = t.id_tamano "
                + "order by p.nombre asc, t.tamano asc";
        var productosEncontrados = new ArrayList<ProductoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (tipo de producto, tamano e insumo asociado) para luego asignarlos al producto
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre_tipo_producto"))
                        .build();
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                var productoInternoAsociado = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(tipoProducto)
                        .tamano(tamano)
                        .productoInterno(resultado.getBoolean("producto_interno"))
                        .productoInternoAsociado(productoInternoAsociado)
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosEncontrados.add(producto);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PRODUCTOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PRODUCTOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosEncontrados;
    }

    @Override
    public List<ProductoEntidad> consultarActivos() {

        var sentenciaSql = "select p.id_producto, p.nombre as nombre_producto, p.id_producto_interno, p.producto_interno, p.precio, p.activo, "
                + "tp.id_tipo_producto, tp.nombre as nombre_tipo_producto, t.id_tamano, t.tamano "
                + "from producto as p "
                + "inner join tipo_producto as tp on p.id_tipo_producto = tp.id_tipo_producto "
                + "inner join tamano as t on p.id_tamano = t.id_tamano "
                + "where p.activo=1 "
                + "order by p.nombre asc, t.tamano asc";
        var productosEncontrados = new ArrayList<ProductoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre_tipo_producto"))
                        .build();
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                var productoInternoAsociado = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .build();
                var producto = new ProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto")))
                        .nombre(resultado.getString("nombre_producto"))
                        .tipoProducto(tipoProducto)
                        .tamano(tamano)
                        .productoInterno(resultado.getBoolean("producto_interno"))
                        .productoInternoAsociado(productoInternoAsociado)
                        .precio(resultado.getBigDecimal("precio"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosEncontrados.add(producto);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTOS_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTOS_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosEncontrados;
    }


    @Override
    public void actualizar(UUID id, ProductoEntidad entidad) {
        var sentenciaSql = "update producto set nombre = ?, id_tipo_producto = ?, id_tamano = ?, "
                + "id_producto_interno = ?, precio = ?, activo = ? where id_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getNombre());
            sentencia.setObject(2, entidad.getTipoProducto().getId());
            sentencia.setObject(3, entidad.getTamano().getId());
            sentencia.setObject(4, entidad.getProductoInternoAsociado().getId());
            sentencia.setBigDecimal(5, entidad.getPrecio());
            sentencia.setBoolean(6, entidad.isActivo());
            sentencia.setObject(7, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from producto where id_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
