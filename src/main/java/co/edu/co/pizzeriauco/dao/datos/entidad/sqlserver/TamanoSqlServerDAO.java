package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.TamanoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TamanoSqlServerDAO extends SqlDAO implements TamanoDAO {

    public TamanoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(TamanoEntidad entidad) {
        var sentenciaSql = "insert into tamano(id_tamano, tamano) values(?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getTamano());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public TamanoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_tamano, tamano from tamano where id_tamano = ?";
        //si no se encuentra, se devuelve el objeto por defecto (nunca nulo)
        var tamanoEncontrado = new TamanoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                tamanoEncontrado = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TAMANO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TAMANO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tamanoEncontrado;
    }

    @Override
    public List<TamanoEntidad> consultarPorFiltro(TamanoEntidad filtro) {
        var tamanosEncontrados = new ArrayList<TamanoEntidad>();
        var sentenciaSql = "select id_tamano, tamano from tamano where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_tamano = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTamano())) {
            sentenciaSql = sentenciaSql + " and tamano = ?";
            parametros.add(filtro.getTamano());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by tamano asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma el objeto y se agrega a la lista
            while (resultado.next()) {
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                tamanosEncontrados.add(tamano);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TAMANO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TAMANO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tamanosEncontrados;
    }

    @Override
    public List<TamanoEntidad> consultarTodos() {
        var sentenciaSql = "select id_tamano, tamano from tamano order by tamano asc";
        var tamanosEncontrados = new ArrayList<TamanoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var tamano = new TamanoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tamano")))
                        .tamano(resultado.getString("tamano"))
                        .build();
                tamanosEncontrados.add(tamano);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TAMANOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TAMANOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tamanosEncontrados;
    }

    @Override
    public void actualizar(UUID id, TamanoEntidad entidad) {
        var sentenciaSql = "update tamano set tamano = ? where id_tamano = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getTamano());
            sentencia.setObject(2, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //si tiene productos asociados la base no deja eliminarlo (llave foranea)
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from tamano where id_tamano = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TamanoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_TAMANO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
