package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.UsuarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.UsuarioEntidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioSqlServerDAO extends SqlDAO implements UsuarioDAO {

    public UsuarioSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public UsuarioEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_usuario, usuario, clave, rol, activo from usuario where id_usuario = ?";
        var usuarioEncontrado = new UsuarioEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                usuarioEncontrado = construirUsuario(resultado);
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_USUARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_USUARIO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return usuarioEncontrado;
    }

    @Override
    public List<UsuarioEntidad> consultarPorFiltro(UsuarioEntidad filtro) {
        var usuariosEncontrados = new ArrayList<UsuarioEntidad>();
        var sentenciaSql = "select id_usuario, usuario, clave, rol, activo from usuario where 1=1";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_usuario = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getUsuario())) {
            sentenciaSql = sentenciaSql + " and usuario = ?";
            parametros.add(filtro.getUsuario());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getRol())) {
            sentenciaSql = sentenciaSql + " and rol = ?";
            parametros.add(filtro.getRol());
        }

        sentenciaSql = sentenciaSql + " order by usuario asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();

            while (resultado.next()) {
                usuariosEncontrados.add(construirUsuario(resultado));
            }

        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_USUARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_USUARIO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return usuariosEncontrados;
    }

    @Override
    public List<UsuarioEntidad> consultarTodos() {
        var sentenciaSql = "select id_usuario, usuario, clave, rol, activo from usuario order by usuario asc";
        var usuariosEncontrados = new ArrayList<UsuarioEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                usuariosEncontrados.add(construirUsuario(resultado));
            }
        } catch (SQLException excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_USUARIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            var mensajeUsuario = CatalogoMensajes.UsuarioSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_USUARIOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return usuariosEncontrados;
    }

    private UsuarioEntidad construirUsuario(ResultSet resultado) throws SQLException {
        return new UsuarioEntidad.Builder()
                .id(UUID.fromString(resultado.getString("id_usuario")))
                .usuario(resultado.getString("usuario"))
                .clave(resultado.getString("clave"))
                .rol(resultado.getString("rol"))
                .activo(resultado.getBoolean("activo"))
                .build();
    }
}
