package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;
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
    public void crear(TipoMovimientoEntidad entidad) {
        var sentenciaSql = "insert into tipo_movimiento(id_tipo_movimiento, id_categoria_origen) values(?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //de la categoria solo se guarda su id (llave foranea)
            sentencia.setObject(2, entidad.getCategoriaOrigen().getId());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_TIPO_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.TipoMovimientoSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_TIPO_MOVIMIENTO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public TipoMovimientoEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select tm.id_tipo_movimiento, "
                + "co.id_categoria_origen, co.nombre "
                + "from tipo_movimiento as tm inner join categoria_origen as co on tm.id_categoria_origen = co.id_categoria_origen "
                + "where tm.id_tipo_movimiento = ?";
        //si no se encuentra, se devuelve el tipo de movimiento por defecto (nunca nulo)
        var tipoMovimientoEncontrado = new TipoMovimientoEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arma la categoria para luego poderla asignar al tipo de movimiento
                var categoriaOrigen = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                tipoMovimientoEncontrado = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(categoriaOrigen)
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
        var sentenciaSql = "select tm.id_tipo_movimiento, "
                + "co.id_categoria_origen, co.nombre "
                + "from tipo_movimiento as tm inner join categoria_origen as co on tm.id_categoria_origen = co.id_categoria_origen "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //este el del tipo de movimiento
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and tm.id_tipo_movimiento = ?";
            parametros.add(filtro.getId());
        }
        //estos los de la categoria
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getCategoriaOrigen().getId())) {
            sentenciaSql = sentenciaSql + " and co.id_categoria_origen = ?";
            parametros.add(filtro.getCategoriaOrigen().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getCategoriaOrigen().getNombre())) {
            sentenciaSql = sentenciaSql + " and co.nombre = ?";
            parametros.add(filtro.getCategoriaOrigen().getNombre());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by co.nombre asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un tipo de movimiento y se agrega a la lista
            while (resultado.next()) {
                //primero se arma la categoria para luego poderla asignar al tipo de movimiento
                var categoriaOrigen = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(categoriaOrigen)
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
        var sentenciaSql = "select tm.id_tipo_movimiento, "
                + "co.id_categoria_origen, co.nombre "
                + "from tipo_movimiento as tm inner join categoria_origen as co on tm.id_categoria_origen = co.id_categoria_origen "
                + "order by co.nombre asc";
        var tiposMovimientoEncontrados = new ArrayList<TipoMovimientoEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arma la categoria para luego poderla asignar al tipo de movimiento
                var categoriaOrigen = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                var tipoMovimiento = new TipoMovimientoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_tipo_movimiento")))
                        .categoriaOrigen(categoriaOrigen)
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
