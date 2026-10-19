package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.ProductoDominio;
import co.edu.co.pizzeriauco.negocio.negocio.ProductoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.ProductoEntidadAssembler;

import java.util.List;

public class ProductoNegocioImpl implements ProductoNegocio {

    private final DAOFactory daoFactory;

    public ProductoNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ProductoDominio> consultarActivos() {
        var productosEncontrados = daoFactory.obtenerProductoDAO().consultarActivos();
        return ProductoEntidadAssembler.getInstance().convertirADominio(productosEncontrados);
    }

}
