package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoInternoSqlServerDAO extends SqlDAO implements ProductoInternoDAO {

    public ProductoInternoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProductoInternoEntidad entidad) {
        var sentenciaSql = "insert into producto_interno(id_producto_interno, nombre, perecedero, vida_util, id_unidad_medida, activo) values(?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getNombre());
            sentencia.setBoolean(3, entidad.isPerecedero());
            sentencia.setInt(4, entidad.getVidaUtil());
            //de la unidad de medida solo se guarda su id (llave foranea)
            sentencia.setObject(5, entidad.getTipoMedida().getId());
            sentencia.setBoolean(6, entidad.isActivo());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public ProductoInternoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from producto_interno as pi inner join unidad_medida as um on pi.id_unidad_medida = um.id_unidad_medida "
                + "where pi.id_producto_interno = ?";
        //si no se encuentra, se devuelve el producto interno por defecto (nunca nulo)
        var productoInternoEncontrado = new ProductoInternoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arma la unidad de medida para luego poderla asignar al producto interno
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                productoInternoEncontrado = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(unidadMedida)
                        .activo(resultado.getBoolean("activo"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_INTERNO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_INTERNO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productoInternoEncontrado;
    }

    //perecedero y activo no se usan como filtro porque un boolean no tiene valor "sin definir"
    @Override
    public List<ProductoInternoEntidad> consultarPorFiltro(ProductoInternoEntidad filtro) {
        var productosInternosEncontrados = new ArrayList<ProductoInternoEntidad>();
        var sentenciaSql = "select pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from producto_interno as pi inner join unidad_medida as um on pi.id_unidad_medida = um.id_unidad_medida "
                + "where pi.id_producto_interno <> ?";
        var parametros = new ArrayList<Object>();
        parametros.add(UtilId.VALOR_DEFECTO); // para que el priemr valor me quede con el comodin
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getNombre());
        }
        //estos los de la unidad de medida
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getTipoMedida().getId())) {
            sentenciaSql = sentenciaSql + " and um.id_unidad_medida = ?";
            parametros.add(filtro.getTipoMedida().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTipoMedida().getUnidadMedida())) {
            sentenciaSql = sentenciaSql + " and um.unidad_medida = ?";
            parametros.add(filtro.getTipoMedida().getUnidadMedida());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTipoMedida().getTipoMedida())) {
            sentenciaSql = sentenciaSql + " and um.tipo_medida = ?";
            parametros.add(filtro.getTipoMedida().getTipoMedida());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by pi.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un producto interno y se agrega a la lista
            while (resultado.next()) {
                //primero se arma la unidad de medida para luego poderla asignar al producto interno
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(unidadMedida)
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosInternosEncontrados.add(productoInterno);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTO_INTERNO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTO_INTERNO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosInternosEncontrados;
    }

    @Override
    public List<ProductoInternoEntidad> consultarTodos() {
        var sentenciaSql = "select pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from producto_interno as pi inner join unidad_medida as um on pi.id_unidad_medida = um.id_unidad_medida "
                + "where pi.id_producto_interno <> ? "
                + "order by pi.nombre asc";
        var productosInternosEncontrados = new ArrayList<ProductoInternoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, UtilId.VALOR_DEFECTO);
            var resultado = sentencia.executeQuery();
            while (resultado.next()) {

                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(unidadMedida)
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosInternosEncontrados.add(productoInterno);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PRODUCTOS_INTERNOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PRODUCTOS_INTERNOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosInternosEncontrados;
    }

    @Override
    public List<ProductoInternoEntidad> consultarActivos() {

        var sentenciaSql = "select pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from producto_interno as pi inner join unidad_medida as um on pi.id_unidad_medida = um.id_unidad_medida "
                + "where pi.id_producto_interno <> ? and pi.activo=1 "
                + "order by pi.nombre asc";

        var productosInternosEncontrados = new ArrayList<ProductoInternoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, UtilId.VALOR_DEFECTO);
            var resultado = sentencia.executeQuery();
            while (resultado.next()) {

                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(unidadMedida)
                        .activo(resultado.getBoolean("activo"))
                        .build();
                productosInternosEncontrados.add(productoInterno);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PRODUCTOS_INTERNOS_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PRODUCTOS_INTERNOS_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return productosInternosEncontrados;
    }

    //tambien guarda activo: desactivar un producto interno (descontinuarlo) es actualizarlo con activo = false
    @Override
    public void actualizar(UUID id, ProductoInternoEntidad entidad) {
        var sentenciaSql = "update producto_interno set nombre = ?, perecedero = ?, vida_util = ?, id_unidad_medida = ?, activo = ? where id_producto_interno = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getNombre());
            sentencia.setBoolean(2, entidad.isPerecedero());
            sentencia.setInt(3, entidad.getVidaUtil());
            sentencia.setObject(4, entidad.getTipoMedida().getId());
            sentencia.setBoolean(5, entidad.isActivo());
            sentencia.setObject(6, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //eliminar = borrar de verdad, solo si no tiene compras, lotes, movimientos o recetas (si los tiene, la base no deja);
    //para retirarlo sin perder su historial se usa actualizar con activo = false
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from producto_interno where id_producto_interno = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProductoInternoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PRODUCTO_INTERNO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
