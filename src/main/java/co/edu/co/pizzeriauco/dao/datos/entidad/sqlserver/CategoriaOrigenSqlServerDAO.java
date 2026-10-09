package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.CategoriaOrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CategoriaOrigenEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CategoriaOrigenSqlServerDAO extends SqlDAO implements CategoriaOrigenDAO {

    public CategoriaOrigenSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public CategoriaOrigenEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_categoria_origen, nombre from categoria_origen where id_categoria_origen = ?";
        var categoriaOrigenEncontrada = new CategoriaOrigenEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro
                categoriaOrigenEncontrada = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CATEGORIA_ORIGEN_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CATEGORIA_ORIGEN_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return categoriaOrigenEncontrada;
    }

    @Override
    public List<CategoriaOrigenEntidad> consultarPorFiltro(CategoriaOrigenEntidad filtro) {
        var categoriasOrigenEncontradas = new ArrayList<CategoriaOrigenEntidad>();
        var sentenciaSql = "select id_categoria_origen, nombre from categoria_origen where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_categoria_origen = ?";
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
            // por cada fila que llego, se arma el objeto y se agrega a la lista
            while (resultado.next()) {
                var categoriaOrigen = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                categoriasOrigenEncontradas.add(categoriaOrigen);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_CATEGORIA_ORIGEN_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_CATEGORIA_ORIGEN_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return categoriasOrigenEncontradas;
    }

    @Override
    public List<CategoriaOrigenEntidad> consultarTodos() {
        var sentenciaSql = "select id_categoria_origen, nombre from categoria_origen order by nombre asc";
        var categoriasOrigenEncontradas = new ArrayList<CategoriaOrigenEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var categoriaOrigen = new CategoriaOrigenEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_categoria_origen")))
                        .nombre(resultado.getString("nombre"))
                        .build();
                categoriasOrigenEncontradas.add(categoriaOrigen);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_CATEGORIAS_ORIGEN;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CategoriaOrigenSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_CATEGORIAS_ORIGEN;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return categoriasOrigenEncontradas;
    }
}
