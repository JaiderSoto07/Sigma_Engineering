package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.VentaDAO;
import co.edu.co.pizzeriauco.entidad.VentaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VentaSqlServerDAO extends SqlDAO implements VentaDAO {

    public VentaSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(VentaEntidad entidad) {
        var sentenciaSql = "insert into venta(id_venta, fecha, hora, factura, cliente, total) values(?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, entidad.getId());
            sentencia.setObject(2, entidad.getFecha());
            sentencia.setObject(3, entidad.getHora());
            sentencia.setString(4, entidad.getFactura());
            sentencia.setString(5, entidad.getCliente());
            sentencia.setBigDecimal(6, entidad.getTotal());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_VENTA;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public VentaEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select id_venta, fecha, hora, factura, cliente, total from venta where id_venta = ?";
        var ventaEncontrada = new VentaEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                ventaEncontrada = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_VENTA_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ventaEncontrada;
    }

    @Override
    public List<VentaEntidad> consultarPorFiltro(VentaEntidad filtro) {
        var ventasEncontradas = new ArrayList<VentaEntidad>();
        var sentenciaSql = "select id_venta, fecha, hora, factura, cliente, total from venta where 1=1";
        var parametros = new ArrayList<Object>();
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and id_venta = ?";
            parametros.add(filtro.getId());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFecha())) {
            sentenciaSql = sentenciaSql + " and fecha = ?";
            parametros.add(filtro.getFecha());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getFactura())) {
            sentenciaSql = sentenciaSql + " and factura = ?";
            parametros.add(filtro.getFactura());
        }
        if (!UtilTexto.CLIENTE_POR_DEFECTO.equals(filtro.getCliente())) {
            sentenciaSql = sentenciaSql + " and cliente = ?";
            parametros.add(filtro.getCliente());
        }

        sentenciaSql = sentenciaSql + " order by fecha desc, hora desc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var venta = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                ventasEncontradas.add(venta);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_VENTA_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ventasEncontradas;
    }

    @Override
    public List<VentaEntidad> consultarTodos() {
        var sentenciaSql = "select id_venta, fecha, hora, factura, cliente, total from venta order by fecha desc, hora desc";
        var ventasEncontradas = new ArrayList<VentaEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                var venta = new VentaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_venta")))
                        .fecha(resultado.getObject("fecha", LocalDate.class))
                        .hora(resultado.getObject("hora", LocalTime.class))
                        .factura(resultado.getString("factura"))
                        .cliente(resultado.getString("cliente"))
                        .total(resultado.getBigDecimal("total"))
                        .build();
                ventasEncontradas.add(venta);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODAS_LAS_VENTAS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.VentaSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODAS_LAS_VENTAS;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return ventasEncontradas;
    }
}
