package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ClaseMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ClaseMovimientoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClaseMovimientoSqlServerDAO extends SqlDAO implements ClaseMovimientoDAO {

    public ClaseMovimientoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public ClaseMovimientoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_clase_movimiento, nombre from clase_movimiento where id_clase_movimiento = ?";
        var claseMovimientoEncontrada = new ClaseMovimientoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                claseMovimientoEncontrada = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CLASE_MOVIMIENTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CLASE_MOVIMIENTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return claseMovimientoEncontrada;
    }

    @Override
    public List<ClaseMovimientoEntidad> consultarPorFiltro(ClaseMovimientoEntidad filtro) {
        var clasesMovimientoEncontradas = new ArrayList<ClaseMovimientoEntidad>();
        var sentenciaSql = "select id_clase_movimiento, nombre from clase_movimiento where 1=1";
        var parametros = new ArrayList<Object>();

        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_clase_movimiento = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombre())) {
            sentenciaSql = sentenciaSql + " and nombre = ?";
            parametros.add(filtro.getNombre());
        }

        sentenciaSql = sentenciaSql + " order by nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var claseMovimiento = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                clasesMovimientoEncontradas.add(claseMovimiento);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CLASE_MOVIMIENTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CLASE_MOVIMIENTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return clasesMovimientoEncontradas;
    }

    @Override
    public List<ClaseMovimientoEntidad> consultarTodos() {
        var sentenciaSql = "select id_clase_movimiento, nombre from clase_movimiento order by nombre asc";
        var clasesMovimientoEncontradas = new ArrayList<ClaseMovimientoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var claseMovimiento = new ClaseMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_clase_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                clasesMovimientoEncontradas.add(claseMovimiento);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_CLASES_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ClaseMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_CLASES_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return clasesMovimientoEncontradas;
    }
}
