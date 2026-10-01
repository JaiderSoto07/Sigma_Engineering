package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.TipoMovimientoEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoMovimientoSqlServerDAO extends SqlDAO implements TipoMovimientoDAO {

    public TipoMovimientoSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public TipoMovimientoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_tipo_movimiento, nombre from tipo_movimiento where id_tipo_movimiento = ?";
        //si no se encuentra, se devuelve el objeto por defecto (nunca nulo)
        var tipoMovimientoEncontrado = new TipoMovimientoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                tipoMovimientoEncontrado = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_MOVIMIENTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_MOVIMIENTO_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tipoMovimientoEncontrado;
    }

    @Override
    public List<TipoMovimientoEntidad> consultarPorFiltro(TipoMovimientoEntidad filtro) {
        var tiposMovimientoEncontrados = new ArrayList<TipoMovimientoEntidad>();
        var sentenciaSql = "select id_tipo_movimiento, nombre from tipo_movimiento where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_tipo_movimiento = ?";
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
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                tiposMovimientoEncontrados.add(tipoMovimiento);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TIPO_MOVIMIENTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TIPO_MOVIMIENTO_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tiposMovimientoEncontrados;
    }

    @Override
    public List<TipoMovimientoEntidad> consultarTodos() {
        var sentenciaSql = "select id_tipo_movimiento, nombre from tipo_movimiento order by nombre asc";
        var tiposMovimientoEncontrados = new ArrayList<TipoMovimientoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                tiposMovimientoEncontrados.add(tipoMovimiento);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_TIPOS_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_TIPOS_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return tiposMovimientoEncontrados;
    }
}
