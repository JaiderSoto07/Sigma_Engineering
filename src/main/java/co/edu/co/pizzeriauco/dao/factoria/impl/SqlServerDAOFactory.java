package co.edu.co.pizzeriauco.dao.factoria.impl;

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
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.AdministradorSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.BarrioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.CambioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.CiudadSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ClienteSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.CompraSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DepartamentoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleCompraSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleRecetaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleVentaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.HistoricoPrecioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.InventarioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.LoteSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.MovimientoInventarioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.OrigenSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.PaisSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.PizzeriaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProductoInternoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProductoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProveedorSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TamanoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TipoMovimientoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TipoProductoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.UbicacionSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.UnidadMedidaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.VentaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;

import java.sql.Connection;

public class SqlServerDAOFactory extends DAOFactory {

    @Override
    protected void abrirConexion() {
        // Tarea: ¿Como abrir una conexion con SQL Server desde Java?
        Connection conexion = null;
        setConexion(conexion);
    }

    @Override
    public PaisDAO obtenerPaisDAO() {
        return new PaisSqlServerDAO();
    }

    @Override
    public DepartamentoDAO obtenerDepartamentoDAO() {
        return new DepartamentoSqlServerDAO();
    }

    @Override
    public CiudadDAO obtenerCiudadDAO() {
        return new CiudadSqlServerDAO();
    }

    @Override
    public BarrioDAO obtenerBarrioDAO() {
        return new BarrioSqlServerDAO();
    }

    @Override
    public OrigenDAO obtenerOrigenDAO() {
        return new OrigenSqlServerDAO();
    }

    @Override
    public TipoMovimientoDAO obtenerTipoMovimientoDAO() {
        return new TipoMovimientoSqlServerDAO();
    }

    @Override
    public TipoProductoDAO obtenerTipoProductoDAO() {
        return new TipoProductoSqlServerDAO();
    }

    @Override
    public TamanoDAO obtenerTamanoDAO() {
        return new TamanoSqlServerDAO();
    }

    @Override
    public UnidadMedidaDAO obtenerUnidadMedidaDAO() {
        return new UnidadMedidaSqlServerDAO();
    }

    @Override
    public UbicacionDAO obtenerUbicacionDAO() {
        return new UbicacionSqlServerDAO();
    }

    @Override
    public PizzeriaDAO obtenerPizzeriaDAO() {
        return new PizzeriaSqlServerDAO();
    }

    @Override
    public AdministradorDAO obtenerAdministradorDAO() {
        return new AdministradorSqlServerDAO();
    }

    @Override
    public ClienteDAO obtenerClienteDAO() {
        return new ClienteSqlServerDAO();
    }

    @Override
    public ProveedorDAO obtenerProveedorDAO() {
        return new ProveedorSqlServerDAO();
    }

    @Override
    public ProductoInternoDAO obtenerProductoInternoDAO() {
        return new ProductoInternoSqlServerDAO();
    }

    @Override
    public ProductoDAO obtenerProductoDAO() {
        return new ProductoSqlServerDAO();
    }

    @Override
    public DetalleRecetaDAO obtenerDetalleRecetaDAO() {
        return new DetalleRecetaSqlServerDAO();
    }

    @Override
    public CompraDAO obtenerCompraDAO() {
        return new CompraSqlServerDAO();
    }

    @Override
    public LoteDAO obtenerLoteDAO() {
        return new LoteSqlServerDAO();
    }

    @Override
    public InventarioDAO obtenerInventarioDAO() {
        return new InventarioSqlServerDAO();
    }

    @Override
    public HistoricoPrecioDAO obtenerHistoricoPrecioDAO() {
        return new HistoricoPrecioSqlServerDAO();
    }

    @Override
    public DetalleCompraDAO obtenerDetalleCompraDAO() {
        return new DetalleCompraSqlServerDAO();
    }

    @Override
    public MovimientoInventarioDAO obtenerMovimientoInventarioDAO() {
        return new MovimientoInventarioSqlServerDAO();
    }

    @Override
    public CambioDAO obtenerCambioDAO() {
        return new CambioSqlServerDAO();
    }

    @Override
    public VentaDAO obtenerVentaDAO() {
        return new VentaSqlServerDAO();
    }

    @Override
    public DetalleVentaDAO obtenerDetalleVentaDAO() {
        return new DetalleVentaSqlServerDAO();
    }
}
