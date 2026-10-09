package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
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
        var unidadMedidaEncontrada = new UnidadMedidaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
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

        sentenciaSql = sentenciaSql + " order by tipo_medida asc, unidad_medida asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

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
