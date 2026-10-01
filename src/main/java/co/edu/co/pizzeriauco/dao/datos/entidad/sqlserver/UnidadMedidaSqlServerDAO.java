package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.crosscuting.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.crosscuting.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.UnidadMedidaDAO;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UnidadMedidaSqlServerDAO extends SqlDAO implements UnidadMedidaDAO {

    public UnidadMedidaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public UnidadMedidaEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_unidad_medida, unidad_medida, tipo_medida from unidad_medida where id_unidad_medida = ?";
        //si no se encuentra, se devuelve la unidad por defecto (nunca nulo)
        var unidadMedidaEncontrada = new UnidadMedidaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro la unidad de medida
                unidadMedidaEncontrada = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_UNIDAD_MEDIDA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_UNIDAD_MEDIDA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return unidadMedidaEncontrada;
    }

    @Override
    public List<UnidadMedidaEntidad> consultarPorFiltro(UnidadMedidaEntidad filtro) {
        var unidadesEncontradas = new ArrayList<UnidadMedidaEntidad>();
        var sentenciaSql = "select id_unidad_medida, unidad_medida, tipo_medida from unidad_medida where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_unidad_medida = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getUnidadMedida())) {
            sentenciaSql = sentenciaSql + " and unidad_medida = ?";
            parametros.add(filtro.getUnidadMedida());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getTipoMedida())) {
            sentenciaSql = sentenciaSql + " and tipo_medida = ?";
            parametros.add(filtro.getTipoMedida());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by tipo_medida asc, unidad_medida asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma una unidad de medida y se agrega a la lista
            while (resultado.next()) {
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                unidadesEncontradas.add(unidadMedida);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_UNIDAD_MEDIDA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_UNIDAD_MEDIDA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return unidadesEncontradas;
    }

    @Override
    public List<UnidadMedidaEntidad> consultarTodos() {
        var sentenciaSql = "select id_unidad_medida, unidad_medida, tipo_medida from unidad_medida order by tipo_medida asc, unidad_medida asc";
        var unidadesEncontradas = new ArrayList<UnidadMedidaEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var unidadMedida = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                unidadesEncontradas.add(unidadMedida);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_UNIDADES_MEDIDA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.UnidadMedidaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_UNIDADES_MEDIDA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return unidadesEncontradas;
    }
}
