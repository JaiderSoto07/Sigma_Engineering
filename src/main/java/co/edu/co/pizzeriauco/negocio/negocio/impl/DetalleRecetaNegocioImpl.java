package co.edu.co.pizzeriauco.negocio.negocio.impl;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaNegocioExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilNumero;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.dao.factoria.DAOFactory;
import co.edu.co.pizzeriauco.dominio.DetalleRecetaDominio;
import co.edu.co.pizzeriauco.entidad.DetalleRecetaEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.TamanoEntidad;
import co.edu.co.pizzeriauco.negocio.negocio.DetalleRecetaNegocio;
import co.edu.co.pizzeriauco.negocio.negocio.assembler.impl.DetalleRecetaEntidadAssembler;

import java.math.BigDecimal;
import java.util.UUID;

public class DetalleRecetaNegocioImpl implements DetalleRecetaNegocio {

    private static final BigDecimal CANTIDAD_MAXIMA = new BigDecimal("10000");
    private static final int CANTIDAD_MAXIMA_DECIMALES = 4;

    private final DAOFactory daoFactory;

    public DetalleRecetaNegocioImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void registrarInformacionNuevoDetalleReceta(DetalleRecetaDominio datos) {
        asegurarDatosRegistroNuevoDetalleRecetaValidos(datos);
        var productoReceta = asegurarProductoExiste(datos.getProducto().getId());
        asegurarNombreRecetaNoExista(productoReceta);
        asegurarProductoInternoExiste(datos.getProductoInterno().getId());
        asegurarProductoInternoNoRepetidoEnReceta(datos.getProducto().getId(), datos.getProductoInterno().getId());
        var idDetalleReceta = generarIdDetalleRecetaUnico();

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

    private void asegurarDatosRegistroNuevoDetalleRecetaValidos(DetalleRecetaDominio datos) {
        if (UtilObjeto.esNulo(datos)) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.DATOS_DETALLE_RECETA_OBLIGATORIOS);
        }

        if (UtilId.VALOR_DEFECTO.equals(datos.getProducto().getId())) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_DETALLE_RECETA_OBLIGATORIO);
        }
        if (UtilId.VALOR_DEFECTO.equals(datos.getProductoInterno().getId())) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_DETALLE_RECETA_OBLIGATORIO);
        }
        if (UtilId.VALOR_DEFECTO.equals(datos.getUnidadMedida().getId())) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.UNIDAD_MEDIDA_DETALLE_RECETA_OBLIGATORIA);
        }
        if (!UtilNumero.mayorQue(datos.getCantidad(), BigDecimal.ZERO)) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.CANTIDAD_DETALLE_RECETA_OBLIGATORIA);
        }
        if (!UtilNumero.menorIgual(datos.getCantidad(), CANTIDAD_MAXIMA)) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.CANTIDAD_DETALLE_RECETA_FUERA_DE_RANGO);
        }

        if (datos.getCantidad().stripTrailingZeros().scale() > CANTIDAD_MAXIMA_DECIMALES) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.CANTIDAD_DETALLE_RECETA_FORMATO_INVALIDO);
        }
    }

    private ProductoEntidad asegurarProductoExiste(UUID idProducto) {
        var productoEncontrado = daoFactory.obtenerProductoDAO().consultarPorId(idProducto);

        if (UtilId.VALOR_DEFECTO.equals(productoEncontrado.getId())) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_DETALLE_RECETA_NO_EXISTE);
        }
        return productoEncontrado;
    }

    private void asegurarNombreRecetaNoExista(ProductoEntidad productoReceta) {
        var filtro = new ProductoEntidad.Builder()
                .nombre(productoReceta.getNombre())
                .tamano(new TamanoEntidad.Builder().id(productoReceta.getTamano().getId()).build())
                .build();

        var resultado = daoFactory.obtenerProductoDAO().consultarPorFiltro(filtro);

        for (var producto : resultado) {
            if (!productoReceta.getId().equals(producto.getId())) {
                throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.EXISTE_OTRA_RECETA_CON_EL_MISMO_NOMBRE);
            }
        }
    }

    private void asegurarProductoInternoExiste(UUID idProductoInterno) {
        var productoInternoEncontrado = daoFactory.obtenerProductoInternoDAO().consultarPorId(idProductoInterno);

        if (UtilId.VALOR_DEFECTO.equals(productoInternoEncontrado.getId())) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_DETALLE_RECETA_NO_EXISTE);
        }
    }

    private void asegurarProductoInternoNoRepetidoEnReceta(UUID idProducto, UUID idProductoInterno) {
        var filtro = new DetalleRecetaEntidad.Builder()
                .producto(new ProductoEntidad.Builder().id(idProducto).build())
                .productoInterno(new ProductoInternoEntidad.Builder().id(idProductoInterno).build())
                .build();

        var resultado = daoFactory.obtenerDetalleRecetaDAO().consultarPorFiltro(filtro);

        if (!resultado.isEmpty()) {
            throw PizzeriaNegocioExcepcion.crear(CatalogoMensajes.DetalleRecetaNegocioImpl.PRODUCTO_INTERNO_YA_REGISTRADO_EN_RECETA);
        }
    }

    private UUID generarIdDetalleRecetaUnico() {
        UUID idGenerado;
        do {
            idGenerado = UtilId.generarId();
        } while (!UtilId.VALOR_DEFECTO.equals(daoFactory.obtenerDetalleRecetaDAO().consultarPorId(idGenerado).getId()));

        return idGenerado;
    }
}
