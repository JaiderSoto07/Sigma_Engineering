package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.CompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.CompraEntidad;
import co.edu.co.pizzeriauco.entidad.ProveedorEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompraSqlServerDAO extends SqlDAO implements CompraDAO {

    public CompraSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(CompraEntidad entidad) {
        var sentenciaSql = "insert into compra(id_compra, id_proveedor, fecha_compra, numero_factura, total) values(?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del proveedor solo se guarda su id (llave foranea)
            sentencia.setObject(2, entidad.getProveedor().getId());
            sentencia.setObject(3, entidad.getFechaCompra());
            sentencia.setString(4, entidad.getNumeroFactura());
            sentencia.setBigDecimal(5, entidad.getTotal());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public CompraEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select c.id_compra, c.fecha_compra, c.numero_factura, c.total, "
                + "p.id_proveedor, p.nombre_empresa, p.nit, p.contacto, p.activo "
                + "from compra as c inner join proveedor as p on c.id_proveedor = p.id_proveedor "
                + "where c.id_compra = ?";
        //si no se encuentra, se devuelve la compra por defecto (nunca nulo)
        var compraEncontrada = new CompraEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arma el proveedor para luego poderlo asignar a la compra
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                compraEncontrada = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(proveedor)
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_COMPRA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_COMPRA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return compraEncontrada;
    }

    @Override
    public List<CompraEntidad> consultarPorFiltro(CompraEntidad filtro) {
        var comprasEncontradas = new ArrayList<CompraEntidad>();
        var sentenciaSql = "select c.id_compra, c.fecha_compra, c.numero_factura, c.total, "
                + "p.id_proveedor, p.nombre_empresa, p.nit, p.contacto, p.activo "
                + "from compra as c inner join proveedor as p on c.id_proveedor = p.id_proveedor "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los de la compra
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and c.id_compra = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaCompra())) {
            sentenciaSql = sentenciaSql + " and c.fecha_compra = ?";
            parametros.add(filtro.getFechaCompra());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getNumeroFactura())) {
            sentenciaSql = sentenciaSql + " and c.numero_factura = ?";
            parametros.add(filtro.getNumeroFactura());
        }
        //estos los del proveedor
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProveedor().getId())) {
            sentenciaSql = sentenciaSql + " and p.id_proveedor = ?";
            parametros.add(filtro.getProveedor().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProveedor().getNombreEmpresa())) {
            sentenciaSql = sentenciaSql + " and p.nombre_empresa = ?";
            parametros.add(filtro.getProveedor().getNombreEmpresa());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProveedor().getNit())) {
            sentenciaSql = sentenciaSql + " and p.nit = ?";
            parametros.add(filtro.getProveedor().getNit());
        }
        // el orden va siempre al final: las compras mas recientes primero
        sentenciaSql = sentenciaSql + " order by c.fecha_compra desc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma una compra y se agrega a la lista
            while (resultado.next()) {
                //primero se arma el proveedor para luego poderlo asignar a la compra
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var compra = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(proveedor)
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                comprasEncontradas.add(compra);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_COMPRA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_COMPRA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return comprasEncontradas;
    }

    @Override
    public List<CompraEntidad> consultarTodos() {
        var sentenciaSql = "select c.id_compra, c.fecha_compra, c.numero_factura, c.total, "
                + "p.id_proveedor, p.nombre_empresa, p.nit, p.contacto, p.activo "
                + "from compra as c inner join proveedor as p on c.id_proveedor = p.id_proveedor "
                + "order by c.fecha_compra desc";
        var comprasEncontradas = new ArrayList<CompraEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arma el proveedor para luego poderlo asignar a la compra
                var proveedor = new ProveedorEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_proveedor")))
                        .nombreEmpresa(resultado.getString("nombre_empresa"))
                        .nit(resultado.getString("nit"))
                        .contacto(resultado.getString("contacto"))
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var compra = new CompraEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_compra")))
                        .proveedor(proveedor)
                        .fechaCompra(resultado.getObject("fecha_compra", LocalDate.class))
                        .numeroFactura(resultado.getString("numero_factura"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                comprasEncontradas.add(compra);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_COMPRAS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_COMPRAS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return comprasEncontradas;
    }

    //negocio solo deja corregir el encabezado (fecha, numero de factura); los renglones no se tocan aqui
    @Override
    public void actualizar(UUID id, CompraEntidad entidad) {
        var sentenciaSql = "update compra set id_proveedor = ?, fecha_compra = ?, numero_factura = ?, total = ? where id_compra = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getProveedor().getId());
            sentencia.setObject(2, entidad.getFechaCompra());
            sentencia.setString(3, entidad.getNumeroFactura());
            sentencia.setBigDecimal(4, entidad.getTotal());
            sentencia.setObject(5, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    //si la compra tiene renglones la base no deja eliminarla (P-COM-005)
    @Override
    public void eliminar(UUID id) {
        var sentenciaSql = "delete from compra where id_compra = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_ELIMINANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.CompraSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ELIMINANDO_COMPRA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
