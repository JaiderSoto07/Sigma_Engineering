package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.OrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.OrigenEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrigenSqlServerDAO extends SqlDAO implements OrigenDAO {

    public OrigenSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public OrigenEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_origen, nombre from origen where id_origen = ?";
        //si no se encuentra, se devuelve el objeto por defecto (nunca nulo)
        var origenEncontrado = new OrigenEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                origenEncontrado = new OrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_ORIGEN_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_ORIGEN_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return origenEncontrado;
    }

    @Override
    public List<OrigenEntidad> consultarPorFiltro(OrigenEntidad filtro) {
        var origenesEncontrados = new ArrayList<OrigenEntidad>();
        var sentenciaSql = "select id_origen, nombre from origen where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_origen = ?";
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
                var origen = new OrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                origenesEncontrados.add(origen);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_ORIGEN_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_ORIGEN_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return origenesEncontrados;
    }

    @Override
    public List<OrigenEntidad> consultarTodos() {
        var sentenciaSql = "select id_origen, nombre from origen order by nombre asc";
        var origenesEncontrados = new ArrayList<OrigenEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var origen = new OrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                origenesEncontrados.add(origen);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_ORIGENES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.OrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_ORIGENES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return origenesEncontrados;
    }
}
