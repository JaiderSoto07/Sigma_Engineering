package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.dominio.UnidadMedidaDominio;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.*;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.negocio.negocio.DetalleRecetaNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.DetalleRecetaEntidadAssembler;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;


public class DetalleRecetaNegocioImpl implements DetalleRecetaNegocio {

    private final DAOFactory daoFactory;
    private static final String TIPO_MEDIDA_PESO = "Peso";
    private static final String TIPO_MEDIDA_VOLUMEN = "Volumen";
    private static final String UNIDAD_RECETA_PESO = "g";
    private static final String UNIDAD_RECETA_VOLUMEN = "ml";
    private static final String UNIDAD_RECETA_UNIDAD = "und";

    public DetalleRecetaNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoDetalleReceta(DetalleRecetaDominio datos) {
        ValidarDatosRegistrarNuevoDetalleRecetaRule.obtenerInstancia().ejecutar(datos);
        AsegurarProductoExisteRule.obtenerInstancia().ejecutar(datos.getProducto().getId(), daoFactory);
        AsegurarProductoInternoExisteRule.obtenerInstancia().ejecutar(datos.getProductoInterno().getId(), daoFactory);
        AsegurarProductoInternoNoRepetidoEnRecetaRule.obtenerInstancia().ejecutar(datos.getProducto().getId(),
                datos.getProductoInterno().getId(), daoFactory);

        var idDetalleReceta = generarIdDetalleRecetaUnico();
        AsegurarIdDetalleRecetaNoExisteRule.obtenerInstancia().ejecutar(idDetalleReceta, daoFactory);

        var detalleRecetaConId = new DetalleRecetaDominio.Builder()
                .id(idDetalleReceta)
                .producto(datos.getProducto())
                .productoInterno(datos.getProductoInterno())
                .cantidad(datos.getCantidad())
                .unidadMedida(obtenerUnidadMedidaDeReceta(datos.getProductoInterno().getId()))
                .build();
        var detalleRecetaEntidad = DetalleRecetaEntidadAssembler.getInstance().convertirAEntidad(detalleRecetaConId);
        daoFactory.obtenerDetalleRecetaDAO().crear(detalleRecetaEntidad);

    }

    @Override
    public void modificarCantidadIngrediente(UUID id,DetalleRecetaDominio datos) {
       AsegurarQueIngredienteExisteRule.obtenerInstancia().ejecutar(id,daoFactory);
       AsegurarQueLaCantidadSeaValidaRule.obtenerInstancia().ejecutar(datos.getCantidad());
        var detalleActual = daoFactory.obtenerDetalleRecetaDAO().consultarPorId(id);

        var detalleConCantidadNueva = new DetalleRecetaEntidad.Builder()
                .id(id)
                .producto(detalleActual.getProducto())
                .productoInterno(detalleActual.getProductoInterno())
                .cantidad(datos.getCantidad())
                .unidadMedida(detalleActual.getUnidadMedida())
                .build();

        daoFactory.obtenerDetalleRecetaDAO().actualizar(id, detalleConCantidadNueva);
    }


    @Override
    public void retirarIngredienteDeReceta(UUID id) {
        AsegurarQueIngredienteExisteRule.obtenerInstancia().ejecutar(id,daoFactory);
        daoFactory.obtenerDetalleRecetaDAO().eliminar(id);


    }

    @Override
    public List<DetalleRecetaDominio> consultarPorFiltro(DetalleRecetaDominio filtro) {
        var filtroEntidad = DetalleRecetaEntidadAssembler.getInstance().convertirAEntidad(filtro);
        var detallesEncontrados = daoFactory.obtenerDetalleRecetaDAO().consultarPorFiltro(filtroEntidad);
        var detallesDominio = new ArrayList<DetalleRecetaDominio>();
        for (var detalleRecetaEntidad : detallesEncontrados) {
            detallesDominio.add(
                    DetalleRecetaEntidadAssembler.getInstance().convertirADominio(detalleRecetaEntidad));
        }
        return detallesDominio;
    }

    @Override
    public List<DetalleRecetaDominio> consultarRecetasDeProductosActivos() {
        var detallesEncontrados = daoFactory.obtenerDetalleRecetaDAO().consultarTodos();
        var detallesDeProductosActivos = new ArrayList<DetalleRecetaDominio>();
        for (var detalleRecetaEntidad : detallesEncontrados) {
            if (detalleRecetaEntidad.getProducto().isActivo()) {
                detallesDeProductosActivos.add(
                        DetalleRecetaEntidadAssembler.getInstance().convertirADominio(detalleRecetaEntidad));
            }
        }
        return detallesDeProductosActivos;
    }

    @Override
    public List<DetalleRecetaDominio> consultarRecetasDeProductosInactivos() {
        var detallesEncontrados = daoFactory.obtenerDetalleRecetaDAO().consultarTodos();
        var detallesDeProductosInactivos = new ArrayList<DetalleRecetaDominio>();
        for (var detalleRecetaEntidad : detallesEncontrados) {
            if (!detalleRecetaEntidad.getProducto().isActivo()) {
                detallesDeProductosInactivos.add(
                        DetalleRecetaEntidadAssembler.getInstance().convertirADominio(detalleRecetaEntidad));
            }
        }
        return detallesDeProductosInactivos;
    }

    private UUID generarIdDetalleRecetaUnico() {
        return UtilId.generarId();
    }

    private UnidadMedidaDominio obtenerUnidadMedidaDeReceta(UUID idProductoInterno) {
        var productoInterno = daoFactory.obtenerProductoInternoDAO().consultarPorId(idProductoInterno);
        var tipoMedida = productoInterno.getTipoMedida().getTipoMedida();

        var abreviatura = UNIDAD_RECETA_UNIDAD;
        if (TIPO_MEDIDA_PESO.equals(tipoMedida)) {
            abreviatura = UNIDAD_RECETA_PESO;
        }
        if (TIPO_MEDIDA_VOLUMEN.equals(tipoMedida)) {
            abreviatura = UNIDAD_RECETA_VOLUMEN;
        }

        var filtro = new UnidadMedidaEntidad.Builder().unidadMedida(abreviatura).build();
        var unidadEncontrada = daoFactory.obtenerUnidadMedidaDAO().consultarPorFiltro(filtro).get(0);

        return new UnidadMedidaDominio.Builder()
                .id(unidadEncontrada.getId())
                .unidadMedida(unidadEncontrada.getUnidadMedida())
                .tipoMedida(unidadEncontrada.getTipoMedida())
                .build();
    }


}
