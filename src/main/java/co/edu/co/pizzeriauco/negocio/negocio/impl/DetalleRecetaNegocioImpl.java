package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.negocio.negocio.DetalleRecetaNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.DetalleRecetaEntidadAssembler;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.AsegurarIdDetalleRecetaNoExisteRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.AsegurarNombreRecetaNoExisteRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.AsegurarProductoExisteRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.AsegurarProductoInternoExisteRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.AsegurarProductoInternoNoRepetidoEnRecetaRule;
import co.edu.co.pizzeriauco.negocio.negocio.reglas.impl.detalleReceta.ValidarDatosRegistrarNuevoDetalleRecetaRule;

import java.util.UUID;

public class DetalleRecetaNegocioImpl implements DetalleRecetaNegocio {

    private final DAOFactory daoFactory;

    public DetalleRecetaNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoDetalleReceta(DetalleRecetaDominio datos) {
        ValidarDatosRegistrarNuevoDetalleRecetaRule.obtenerInstancia().ejecutar(datos);
        AsegurarProductoExisteRule.obtenerInstancia().ejecutar(datos.getProducto().getId(), daoFactory);
        AsegurarNombreRecetaNoExisteRule.obtenerInstancia().ejecutar(datos.getProducto().getId(), daoFactory);
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
                .unidadMedida(datos.getUnidadMedida())
                .build();
        var detalleRecetaEntidad = DetalleRecetaEntidadAssembler.getInstance().convertirAEntidad(detalleRecetaConId);

        daoFactory.obtenerDetalleRecetaDAO().crear(detalleRecetaEntidad);
    }

    private UUID generarIdDetalleRecetaUnico() {
        return UtilId.generarId();
    }
}
