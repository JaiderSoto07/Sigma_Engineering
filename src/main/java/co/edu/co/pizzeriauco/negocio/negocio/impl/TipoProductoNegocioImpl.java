package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.TipoProductoDominio;
import co.edu.co.pizzeriauco.negocio.negocio.TipoProductoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.TipoProductoEntidadAssembler;

import java.util.ArrayList;
import java.util.List;

public class TipoProductoNegocioImpl implements TipoProductoNegocio {

    private final DAOFactory daoFactory;

    public TipoProductoNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<TipoProductoDominio> consultarTodos() {
        var tiposProductoEncontrados = daoFactory.obtenerTipoProductoDAO().consultarTodos();
        var tiposProductoDominio = new ArrayList<TipoProductoDominio>();
        for (var tipoProductoEntidad : tiposProductoEncontrados) {
            tiposProductoDominio.add(
                    TipoProductoEntidadAssembler.getInstance().convertirADominio(tipoProductoEntidad));
        }
        return tiposProductoDominio;
    }

}
