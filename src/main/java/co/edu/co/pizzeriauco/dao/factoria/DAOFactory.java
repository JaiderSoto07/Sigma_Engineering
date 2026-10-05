package co.edu.co.pizzeriauco.dao.factoria;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilSql;
import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ConsumoVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.LoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.MovimientoInventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CategoriaOrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SalidaLoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TamanoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ClaseMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.UnidadMedidaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.VentaDAO;

import java.sql.Connection;

public abstract class DAOFactory {

    private Connection conexion;

    protected DAOFactory() {
        abrirConexion();
    }

    protected Connection getConexion() {
        return conexion;
    }

    //antes de guardar la conexion me aseguro de que exista y este abierta
    protected void setConexion(Connection conexion) {
        UtilSql.asegurarConexionAbierta(conexion);
        this.conexion = conexion;
    }

    //abstract para que cada base de datos la implemente de forma diferente
    protected abstract void abrirConexion();

    public void cerrarConexion() {
        UtilSql.cerrarConexion(conexion);
    }

    public void iniciarTransaccion() {
        UtilSql.iniciarTransaccion(conexion);
    }

    public void confirmarTransaccion() {
        UtilSql.confirmarTransaccion(conexion);
    }

    public void cancelarTransaccion() {
        UtilSql.cancelarTransaccion(conexion);
    }

    //va a fabricar los DAO de las entidades

    public abstract CategoriaOrigenDAO obtenerCategoriaOrigenDAO();

    public abstract ClaseMovimientoDAO obtenerClaseMovimientoDAO();

    public abstract TipoMovimientoDAO obtenerTipoMovimientoDAO();

    public abstract TipoProductoDAO obtenerTipoProductoDAO();

    public abstract TamanoDAO obtenerTamanoDAO();

    public abstract UnidadMedidaDAO obtenerUnidadMedidaDAO();

    public abstract ProveedorDAO obtenerProveedorDAO();

    public abstract ProductoInternoDAO obtenerProductoInternoDAO();

    public abstract ProductoDAO obtenerProductoDAO();

    public abstract DetalleRecetaDAO obtenerDetalleRecetaDAO();

    public abstract CompraDAO obtenerCompraDAO();

    public abstract LoteDAO obtenerLoteDAO();

    public abstract SalidaLoteDAO obtenerSalidaLoteDAO();

    public abstract InventarioDAO obtenerInventarioDAO();

    public abstract HistoricoPrecioDAO obtenerHistoricoPrecioDAO();

    public abstract DetalleCompraDAO obtenerDetalleCompraDAO();

    public abstract MovimientoInventarioDAO obtenerMovimientoInventarioDAO();

    public abstract CambioDAO obtenerCambioDAO();

    public abstract VentaDAO obtenerVentaDAO();

    public abstract DetalleVentaDAO obtenerDetalleVentaDAO();

    public abstract ConsumoVentaDAO obtenerConsumoVentaDAO();
}
