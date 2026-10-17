package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.TamanoDominio;
import co.edu.co.pizzeriauco.negocio.negocio.TamanoNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.TamanoEntidadAssembler;

import java.util.ArrayList;
import java.util.List;

public class TamanoNegocioImpl implements TamanoNegocio {

    private final DAOFactory daoFactory;

    public TamanoNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<TamanoDominio> consultarTodos() {
        var tamanosEncontrados = daoFactory.obtenerTamanoDAO().consultarTodos();
        return  TamanoEntidadAssembler.getInstance().convertirADominio(tamanosEncontrados);

    }

}
