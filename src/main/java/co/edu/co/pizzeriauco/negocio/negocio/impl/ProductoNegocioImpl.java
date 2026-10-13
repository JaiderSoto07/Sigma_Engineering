package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.ProductoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.ProductoEntidadAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.ProductoInternoEntidadAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.producto.*;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductoNegocioImpl implements ProductoNegocio {

    private final DAOFactory daoFactory;

    public ProductoNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoProducto(ProductoDominio datos) {
        ValidarDatosRegistrarNuevoProductoRule.obtenerInstancia().ejecutar(datos);
        AsegurarProductoNoRepetidoRule.obtenerInstancia().ejecutar(datos.getNombre(), datos.getTamano().getId(), daoFactory);

        var productoConId = new ProductoDominio.Builder()
                .id(generarIdProductoUnico())
                .nombre(datos.getNombre())
                .tipoProducto(datos.getTipoProducto())
                .tamano(datos.getTamano())
                .productoInterno(datos.isProductoInterno())
                .productoInternoAsociado(obtenerProductoInternoAsociado(datos))
                .precio(datos.getPrecio())
                .activo(true)
                .build();
        var productoEntidad = ProductoEntidadAssembler.getInstance().convertirAEntidad(productoConId);
        daoFactory.obtenerProductoDAO().crear(productoEntidad);
    }

    @Override
    public List<ProductoDominio> consultarActivos() {
        var productosEncontrados = daoFactory.obtenerProductoDAO().consultarActivos();
        var productosDominio = new ArrayList<ProductoDominio>();
        for (var productoEntidad : productosEncontrados) {
            productosDominio.add(
                    ProductoEntidadAssembler.getInstance().convertirADominio(productoEntidad));
        }
        return productosDominio;
    }

    private UUID generarIdProductoUnico() {
        return UtilId.generarId();
    }

    private ProductoInternoDominio obtenerProductoInternoAsociado(ProductoDominio datos) {
        if (!datos.isProductoInterno()) {
            return new ProductoInternoDominio.Builder().build();
        }
        AsegurarProductoInternoConMismoNombreExisteRule.obtenerInstancia().ejecutar(datos.getNombre(), daoFactory);

        var filtro = new ProductoInternoEntidad.Builder().nombre(datos.getNombre()).build();
        var productoInternoEncontrado = daoFactory.obtenerProductoInternoDAO().consultarPorFiltro(filtro).get(0);

        return ProductoInternoEntidadAssembler.getInstance().convertirADominio(productoInternoEncontrado);
    }

}
