package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TipoProductoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoProductoSqlServerDAO extends SqlDAO implements TipoProductoDAO {

    public TipoProductoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(TipoProductoEntidad entidad) {
        var sentenciaSql = "insert into tipo_producto(id_tipo_producto, nombre) values(?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getNombre());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public TipoProductoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_tipo_producto, nombre from tipo_producto where id_tipo_producto = ?";
        //si no se encuentra, se devuelve el objeto por defecto (nunca nulo)
        var tipoProductoEncontrado = new TipoProductoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                tipoProductoEncontrado = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_PRODUCTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_PRODUCTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tipoProductoEncontrado;
    }

    @Override
    public List<TipoProductoEntidad> consultarPorFiltro(TipoProductoEntidad filtro) {
        var tiposProductoEncontrados = new ArrayList<TipoProductoEntidad>();
        var sentenciaSql = "select id_tipo_producto, nombre from tipo_producto where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_tipo_producto = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and nombre = ?";
            parametros.add(filtro.getNombre());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma el objeto y se agrega a la lista
            while (resultado.next()) {
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                tiposProductoEncontrados.add(tipoProducto);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_PRODUCTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_PRODUCTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tiposProductoEncontrados;
    }

    @Override
    public List<TipoProductoEntidad> consultarTodos() {
        var sentenciaSql = "select id_tipo_producto, nombre from tipo_producto order by nombre asc";
        var tiposProductoEncontrados = new ArrayList<TipoProductoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var tipoProducto = new TipoProductoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_producto")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                tiposProductoEncontrados.add(tipoProducto);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TIPOS_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TIPOS_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tiposProductoEncontrados;
    }

    @Override
    public void actualizar(UUID id, TipoProductoEntidad entidad) {
        var sentenciaSql = "update tipo_producto set nombre = ? where id_tipo_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getNombre());
            sentencia.setObject(2, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //si tiene productos asociados la base no deja eliminarlo (llave foranea)
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from tipo_producto where id_tipo_producto = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoProductoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_TIPO_PRODUCTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
