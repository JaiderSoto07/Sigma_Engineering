package co.edu.co.pizzeriauco.dao.factoria.impl;

import co.edu.co.pizzeriauco.dao.datos.entidad.CambioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.CompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleCompraDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleRecetaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.DetalleVentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.HistoricoPrecioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.InventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.LoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.MovimientoInventarioDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.OrigenDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProductoInternoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.ProveedorDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TamanoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoMovimientoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.TipoProductoDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.UnidadMedidaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.VentaDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.CambioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.CompraSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleCompraSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleRecetaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.DetalleVentaSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.HistoricoPrecioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.InventarioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.LoteSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.MovimientoInventarioSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.OrigenSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProductoInternoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProductoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.ProveedorSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TamanoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TipoMovimientoSqlServerDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver.TipoProductoSqlServerDAO;
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
    public OrigenDAO obtenerOrigenDAO() {
        return new OrigenSqlServerDAO(getConexion());
    }

    @Override
    public TipoMovimientoDAO obtenerTipoMovimientoDAO() {
        return new TipoMovimientoSqlServerDAO(getConexion());
    }

    @Override
    public TipoProductoDAO obtenerTipoProductoDAO() {
        return new TipoProductoSqlServerDAO(getConexion());
    }

    @Override
    public TamanoDAO obtenerTamanoDAO() {
        return new TamanoSqlServerDAO(getConexion());
    }

    @Override
    public UnidadMedidaDAO obtenerUnidadMedidaDAO() {
        return new UnidadMedidaSqlServerDAO(getConexion());
    }

    @Override
    public ProveedorDAO obtenerProveedorDAO() {
        return new ProveedorSqlServerDAO(getConexion());
    }

    @Override
    public ProductoInternoDAO obtenerProductoInternoDAO() {
        return new ProductoInternoSqlServerDAO(getConexion());
    }

    @Override
    public ProductoDAO obtenerProductoDAO() {
        return new ProductoSqlServerDAO(getConexion());
    }

    @Override
    public DetalleRecetaDAO obtenerDetalleRecetaDAO() {
        return new DetalleRecetaSqlServerDAO(getConexion());
    }

    @Override
    public CompraDAO obtenerCompraDAO() {
        return new CompraSqlServerDAO(getConexion());
    }

    @Override
    public LoteDAO obtenerLoteDAO() {
        return new LoteSqlServerDAO(getConexion());
    }

    @Override
    public InventarioDAO obtenerInventarioDAO() {
        return new InventarioSqlServerDAO(getConexion());
    }

    @Override
    public HistoricoPrecioDAO obtenerHistoricoPrecioDAO() {
        return new HistoricoPrecioSqlServerDAO(getConexion());
    }

    @Override
    public DetalleCompraDAO obtenerDetalleCompraDAO() {
        return new DetalleCompraSqlServerDAO(getConexion());
    }

    @Override
    public MovimientoInventarioDAO obtenerMovimientoInventarioDAO() {
        return new MovimientoInventarioSqlServerDAO(getConexion());
    }

    @Override
    public CambioDAO obtenerCambioDAO() {
        return new CambioSqlServerDAO(getConexion());
    }

    @Override
    public VentaDAO obtenerVentaDAO() {
        return new VentaSqlServerDAO(getConexion());
    }

    @Override
    public DetalleVentaDAO obtenerDetalleVentaDAO() {
        return new DetalleVentaSqlServerDAO(getConexion());
    }
}
