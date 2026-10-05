package co.edu.co.pizzeriauco.dao.datos.entidad.sqlserver;

import co.edu.co.pizzeriauco.transversal.catalogo.CatalogoMensajes;
import co.edu.co.pizzeriauco.transversal.excepciones.PizzeriaDatosExcepcion;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;
import co.edu.co.pizzeriauco.dao.datos.entidad.LoteDAO;
import co.edu.co.pizzeriauco.dao.datos.entidad.SqlDAO;
import co.edu.co.pizzeriauco.entidad.LoteEntidad;
import co.edu.co.pizzeriauco.entidad.ProductoInternoEntidad;
import co.edu.co.pizzeriauco.entidad.UnidadMedidaEntidad;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoteSqlServerDAO extends SqlDAO implements LoteDAO {

    public LoteSqlServerDAO(Connection conexion) {
        super(conexion);
    }

    @Override
    public void crear(LoteEntidad entidad) {
        //disponible no se escribe: la base la calcula con el saldo
        //el lote se crea primero (en 0) y luego su movimiento de entrada lo llena
        var sentenciaSql = "insert into lote(id_lote, numero_lote, id_producto_interno, cantidad, "
                + "saldo, id_unidad_medida, fecha_vencimiento) values(?, ?, ?, ?, ?, ?, ?)";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            //se llenan los datos en el mismo orden de los ?
            sentencia.setObject(1, entidad.getId());
            //del producto interno y la unidad solo se guarda su id (llaves foraneas)
            sentencia.setInt(2, entidad.getNumeroLote());
            sentencia.setObject(3, entidad.getProductoInterno().getId());
            sentencia.setBigDecimal(4, entidad.getCantidad());
            sentencia.setBigDecimal(5, entidad.getSaldo());
            sentencia.setObject(6, entidad.getUnidadMedidaInventario().getId());
            sentencia.setObject(7, entidad.getFechaVencimiento());
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CREANDO_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CREANDO_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }

    @Override
    public LoteEntidad consultarPorId(UUID id) {
        var sentenciaSql = "select l.id_lote, l.numero_lote, l.cantidad, l.saldo, l.fecha_vencimiento, l.disponible, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from lote as l "
                + "inner join producto_interno as pi on l.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on l.id_unidad_medida = um.id_unidad_medida "
                + "where l.id_lote = ?";
        //si no se encuentra, se devuelve el lote por defecto (nunca nulo)
        var loteEncontrado = new LoteEntidad.Builder().build();
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setObject(1, id);

            var resultado = sentencia.executeQuery();
            if (resultado.next()) {
                //primero se arman los padres (producto interno y unidad) para luego asignarlos al lote
                //del "abuelo" (unidad del producto interno) solo se trae el id
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                //disponible lo calcula la base con el saldo; aqui solo se lee
                loteEncontrado = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .disponible(resultado.getBoolean("disponible"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_LOTE_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_LOTE_POR_ID;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return loteEncontrado;
    }

    //disponible no se usa como filtro porque un boolean no tiene valor "sin definir"
    @Override
    public List<LoteEntidad> consultarPorFiltro(LoteEntidad filtro) {
        var lotesEncontrados = new ArrayList<LoteEntidad>();
        var sentenciaSql = "select l.id_lote, l.numero_lote, l.cantidad, l.saldo, l.fecha_vencimiento, l.disponible, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from lote as l "
                + "inner join producto_interno as pi on l.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on l.id_unidad_medida = um.id_unidad_medida "
                + "where 1=1";
        var parametros = new ArrayList<Object>();
        //estos los del lote
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getId())) {
            sentenciaSql = sentenciaSql + " and l.id_lote = ?";
            parametros.add(filtro.getId());
        }
        if (filtro.getNumeroLote() > 0) {
            sentenciaSql = sentenciaSql + " and l.numero_lote = ?";
            parametros.add(filtro.getNumeroLote());
        }
        if (!UtilFecha.FECHA_POR_DEFECTO.equals(filtro.getFechaVencimiento())) {
            sentenciaSql = sentenciaSql + " and l.fecha_vencimiento = ?";
            parametros.add(filtro.getFechaVencimiento());
        }
        //estos los del producto interno (para traer todos los lotes de un insumo)
        if (!UtilId.VALOR_DEFECTO.equals(filtro.getProductoInterno().getId())) {
            sentenciaSql = sentenciaSql + " and pi.id_producto_interno = ?";
            parametros.add(filtro.getProductoInterno().getId());
        }
        if (!UtilTexto.getUtilTexto().esVacia(filtro.getProductoInterno().getNombre())) {
            sentenciaSql = sentenciaSql + " and pi.nombre = ?";
            parametros.add(filtro.getProductoInterno().getNombre());
        }
        // el orden va siempre al final: por insumo y por numero de lote (orden FIFO)
        sentenciaSql = sentenciaSql + " order by pi.nombre asc, l.numero_lote asc";

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            for (var indice = 0; indice < parametros.size(); indice++) {
                sentencia.setObject(indice + 1, parametros.get(indice));
            }

            var resultado = sentencia.executeQuery();
            // por cada fila que llego, se arma un lote y se agrega a la lista
            while (resultado.next()) {
                //primero se arman los padres (producto interno y unidad) para luego asignarlos al lote
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var lote = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .disponible(resultado.getBoolean("disponible"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
                lotesEncontrados.add(lote);
            }

        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_LOTE_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_LOTE_POR_FILTRO;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return lotesEncontrados;
    }

    @Override
    public List<LoteEntidad> consultarTodos() {
        var sentenciaSql = "select l.id_lote, l.numero_lote, l.cantidad, l.saldo, l.fecha_vencimiento, l.disponible, "
                + "pi.id_producto_interno, pi.nombre as nombre_producto_interno, pi.perecedero, pi.vida_util, pi.activo, "
                + "pi.id_unidad_medida as id_unidad_medida_producto_interno, "
                + "um.id_unidad_medida, um.unidad_medida, um.tipo_medida "
                + "from lote as l "
                + "inner join producto_interno as pi on l.id_producto_interno = pi.id_producto_interno "
                + "inner join unidad_medida as um on l.id_unidad_medida = um.id_unidad_medida "
                + "order by pi.nombre asc, l.numero_lote asc";
        var lotesEncontrados = new ArrayList<LoteEntidad>();

        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {

            var resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //primero se arman los padres (producto interno y unidad) para luego asignarlos al lote
                var productoInterno = new ProductoInternoEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_producto_interno")))
                        .nombre(resultado.getString("nombre_producto_interno"))
                        .perecedero(resultado.getBoolean("perecedero"))
                        .vidaUtil(resultado.getInt("vida_util"))
                        .tipoMedida(new UnidadMedidaEntidad.Builder()
                                .id(UUID.fromString(resultado.getString("id_unidad_medida_producto_interno"))).build())
                        .activo(resultado.getBoolean("activo"))
                        .build();
                var unidadMedidaInventario = new UnidadMedidaEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_unidad_medida")))
                        .unidadMedida(resultado.getString("unidad_medida"))
                        .tipoMedida(resultado.getString("tipo_medida"))
                        .build();
                var lote = new LoteEntidad.Builder()
                        .id(UUID.fromString(resultado.getString("id_lote")))
                        .numeroLote(resultado.getInt("numero_lote"))
                        .productoInterno(productoInterno)
                        .cantidad(resultado.getBigDecimal("cantidad"))
                        .saldo(resultado.getBigDecimal("saldo"))
                        .disponible(resultado.getBoolean("disponible"))
                        .unidadMedidaInventario(unidadMedidaInventario)
                        .fechaVencimiento(resultado.getObject("fecha_vencimiento", LocalDate.class))
                        .build();
                lotesEncontrados.add(lote);
            }
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_CONSULTANDO_TODOS_LOS_LOTES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_CONSULTANDO_TODOS_LOS_LOTES;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
        return lotesEncontrados;
    }

    //normalmente solo cambia el saldo (en cada salida); cantidad y fecha de vencimiento solo cambian
    //cuando se corrige un renglon de compra o un cambio con el lote intacto (saldo = cantidad).
    //al crearse el lote queda en 0: su movimiento de entrada lo llena (cantidad = saldo = lo que entro)
    //insumo, unidad y numero de lote nunca se actualizan
    @Override
    public void actualizar(UUID id, LoteEntidad entidad) {
        var sentenciaSql = "update lote set cantidad = ?, saldo = ?, fecha_vencimiento = ? where id_lote = ?";
        try (var sentencia = getConexion().prepareStatement(sentenciaSql)) {
            sentencia.setBigDecimal(1, entidad.getCantidad());
            sentencia.setBigDecimal(2, entidad.getSaldo());
            sentencia.setObject(3, entidad.getFechaVencimiento());
            sentencia.setObject(4, id);
            sentencia.executeUpdate();
        } catch (SQLException excepcion) {
            //el controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_ACTUALIZANDO_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        } catch (Exception excepcion) {
            //no controlado
            var mensajeUsuario = CatalogoMensajes.LoteSqlServerDAO.USUARIO_ERROR_PROBLEMA_NO_CONTROLADO_ACTUALIZANDO_LOTE;
            throw PizzeriaDatosExcepcion.crear(mensajeUsuario, excepcion.getMessage(), excepcion);
        }
    }
}
