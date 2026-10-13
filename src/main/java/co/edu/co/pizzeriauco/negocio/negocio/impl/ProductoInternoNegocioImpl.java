package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.ProductoInternoDominio;
import co.edu.co.pizzeriauco.negocio.negocio.ProductoInternoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.ProductoInternoEntidadAssembler;

import java.util.ArrayList;
import java.util.List;

public class ProductoInternoNegocioImpl implements ProductoInternoNegocio {

    private final DAOFactory daoFactory;

    public ProductoInternoNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<ProductoInternoDominio> consultarActivos() {
        var productosInternosEncontrados = daoFactory.obtenerProductoInternoDAO().consultarActivos();
        var productosInternosDominio = new ArrayList<ProductoInternoDominio>();
        for (var productoInternoEntidad : productosInternosEncontrados) {
            productosInternosDominio.add(
                    ProductoInternoEntidadAssembler.getInstance().convertirADominio(productoInternoEntidad));
        }
        return productosInternosDominio;
    }

}
