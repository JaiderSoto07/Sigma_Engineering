package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProveedorSqlServerDAO extends SqlDAO implements ProveedorDAO {

    public ProveedorSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(ProveedorEntidad entidad) {
        var sentenciaSql = "insert into proveedor(id_proveedor, nombre_empresa, nit, contacto, activo) values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            sentencia.setString(2, entidad.getNombreEmpresa());
            sentencia.setString(3, entidad.getNit());
            sentencia.setString(4, entidad.getContacto());
            sentencia.setBoolean(5, entidad.isActivo());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public ProveedorEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_proveedor, nombre_empresa, nit, contacto, activo from proveedor where id_proveedor = ?";
        //si no se encuentra, se devuelve el proveedor por defecto (nunca nulo)
        var proveedorEncontrado = new ProveedorEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //si entra aca es porque se encontro el proveedor
                proveedorEncontrado = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PROVEEDOR_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PROVEEDOR_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return proveedorEncontrado;
    }

    //activo no se usa como filtro porque un boolean no tiene valor "sin definir"
    @Override
    public List<ProveedorEntidad> consultarPorFiltro(ProveedorEntidad filtro) {
        var proveedoresEncontrados = new ArrayList<ProveedorEntidad>();
        var sentenciaSql = "select id_proveedor, nombre_empresa, nit, contacto, activo from proveedor where 1=1";
        var parametros = new ArrayList<Object>();
        //solo se filtra por los datos que vengan diferentes al valor por defecto
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_proveedor = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNombreEmpresa())) {
            sentenciaSql = sentenciaSql + " and nombre_empresa = ?";
            parametros.add(filtro.getNombreEmpresa());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNit())) {
            sentenciaSql = sentenciaSql + " and nit = ?";
            parametros.add(filtro.getNit());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getContacto())) {
            sentenciaSql = sentenciaSql + " and contacto = ?";
            parametros.add(filtro.getContacto());
        }
        // el orden va siempre al final
        sentenciaSql = sentenciaSql + " order by nombre_empresa asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un proveedor y se agrega a la lista
            while (resultado.next()) {
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                proveedoresEncontrados.add(proveedor);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PROVEEDOR_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PROVEEDOR_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return proveedoresEncontrados;
    }

    @Override
    public List<ProveedorEntidad> consultarTodos() {
        var sentenciaSql = "select id_proveedor, nombre_empresa, nit, contacto, activo from proveedor order by nombre_empresa asc";
        var proveedoresEncontrados = new ArrayList<ProveedorEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                proveedoresEncontrados.add(proveedor);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_PROVEEDORES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_PROVEEDORES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return proveedoresEncontrados;
    }
    @Override
    public List<ProveedorEntidad> consultarActivos() {
        var sentenciaSql = "select id_proveedor, nombre_empresa, nit, contacto, activo from proveedor"
        +" where activo=1 order by nombre_empresa asc";
        var proveedoresEncontrados = new ArrayList<ProveedorEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {


            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                proveedoresEncontrados.add(proveedor);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_PROVEEDORES_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_PROVEEDORES_ACTIVOS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return proveedoresEncontrados;
    }

    //tambien guarda activo: desactivar un proveedor (retirarlo) es actualizarlo con activo = false
    @Override
    public void actualizar(UUID id, ProveedorEntidad entidad) {
        var sentenciaSql = "update proveedor set nombre_empresa = ?, nit = ?, contacto = ?, activo = ? where id_proveedor = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setString(1, entidad.getNombreEmpresa());
            sentencia.setString(2, entidad.getNit());
            sentencia.setString(3, entidad.getContacto());
            sentencia.setBoolean(4, entidad.isActivo());
            sentencia.setObject(5, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //eliminar = borrar de verdad, solo si no tiene compras (si las tiene, la base no deja);
    //para retirarlo sin perder su historial se usa actualizar con activo = false
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from proveedor where id_proveedor = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.ProveedorSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_PROVEEDOR;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

}
