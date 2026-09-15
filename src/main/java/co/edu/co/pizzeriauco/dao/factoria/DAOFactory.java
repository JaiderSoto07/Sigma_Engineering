package co.edu.co.pizzeriauco.dao.factoria;

import co.edu.co.pizzeriauco.dao.datos.entidad.AdministradorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.BarrioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CiudadDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ClienteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DepartamentoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.LoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.MovimientoInventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.OrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.PaisDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.PizzeriaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TamanoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.UbicacionDAO;
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

    protected void setConexion(Connection conexion) {
        // Tarea: Asegurar que la conexion este abierta y sea valida
        this.conexion = conexion;
    }

    protected abstract void abrirConexion();

    public void cerrarConexion() {
        // Tarea: ¿Como se cierra la conexion de forma segura?
    }

    public void iniciarTransaccion() {
        // Tarea: ¿Como se inicia una transaccion de forma segura?
    }

    public void confirmarTransaccion() {
        // Tarea: ¿Como se confirma una transaccion de forma segura?
    }

    public void cancelarTransaccion() {
        // Tarea: ¿Como se cancela una transaccion de forma segura?
    }

    public abstract PaisDAO obtenerPaisDAO();

    public abstract DepartamentoDAO obtenerDepartamentoDAO();

    public abstract CiudadDAO obtenerCiudadDAO();

    public abstract BarrioDAO obtenerBarrioDAO();

    public abstract OrigenDAO obtenerOrigenDAO();

    public abstract TipoMovimientoDAO obtenerTipoMovimientoDAO();

    public abstract TipoProductoDAO obtenerTipoProductoDAO();

    public abstract TamanoDAO obtenerTamanoDAO();

    public abstract UnidadMedidaDAO obtenerUnidadMedidaDAO();

    public abstract UbicacionDAO obtenerUbicacionDAO();

    public abstract PizzeriaDAO obtenerPizzeriaDAO();

    public abstract AdministradorDAO obtenerAdministradorDAO();

    public abstract ClienteDAO obtenerClienteDAO();

    public abstract ProveedorDAO obtenerProveedorDAO();

    public abstract ProductoInternoDAO obtenerProductoInternoDAO();

    public abstract ProductoDAO obtenerProductoDAO();

    public abstract DetalleRecetaDAO obtenerDetalleRecetaDAO();

    public abstract CompraDAO obtenerCompraDAO();

    public abstract LoteDAO obtenerLoteDAO();

    public abstract InventarioDAO obtenerInventarioDAO();

    public abstract HistoricoPrecioDAO obtenerHistoricoPrecioDAO();

    public abstract DetalleCompraDAO obtenerDetalleCompraDAO();

    public abstract MovimientoInventarioDAO obtenerMovimientoInventarioDAO();

    public abstract CambioDAO obtenerCambioDAO();

    public abstract VentaDAO obtenerVentaDAO();

    public abstract DetalleVentaDAO obtenerDetalleVentaDAO();
}
