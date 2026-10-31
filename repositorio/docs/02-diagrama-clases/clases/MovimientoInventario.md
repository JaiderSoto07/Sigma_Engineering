# MovimientoInventario

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: MovimientoInventario](../../01-modelo-dominio/objetos/MovimientoInventario.md) · [Entidad MER: MovimientoInventario](../../03-modelo-entidad-relacion/entidades/MovimientoInventario.md)

| Campo | Valor |
|---|---|
| Clase | MovimientoInventario |
| Estereotipo | class |
| Descripción | Representa cada entrada o salida de existencias sobre un lote, generada por una compra, una venta o un cambio, permitiendo mantener el control y la trazabilidad del inventario. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ClaseMovimiento |
| Necesita de | Asociación | TipoMovimiento |
| Necesita de | Asociación | Lote |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del movimiento de inventario. |
| private | claseMovimiento | Otro | ClaseMovimiento | no final | no static | Instancia por defecto de ClaseMovimiento | Clase de movimiento: Entrada (suma al inventario) o Salida (resta del inventario). |
| private | tipoMovimiento | Otro | TipoMovimiento | no final | no static | Instancia por defecto de TipoMovimiento | Código de la operación (TipoMovimiento) que generó el movimiento; a través de él se conoce la categoría de origen. |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad que entra o sale del lote en este movimiento, expresada en la unidad de medida del lote. |
| private | fechaMovimiento | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha en la que ocurrió el movimiento. |
| private | lote | Otro | Lote | no final | no static | Instancia por defecto de Lote | Lote sobre el que se realiza la entrada o salida; por el lote se conoce el producto interno. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | MovimientoInventario | Parametrizado | Constructor privado: crea la instancia de MovimientoInventario copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getClaseMovimiento | getter | no final | Método encargado de obtener el valor del atributo claseMovimiento. |  |  |  |  |  |  | Otro | ClaseMovimiento |  |
| public | getTipoMovimiento | getter | no final | Método encargado de obtener el valor del atributo tipoMovimiento. |  |  |  |  |  |  | Otro | TipoMovimiento |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getFechaMovimiento | getter | no final | Método encargado de obtener el valor del atributo fechaMovimiento. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getLote | getter | no final | Método encargado de obtener el valor del atributo lote. |  |  |  |  |  |  | Otro | Lote |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarEntradaDeInventarioPorCompra | no static | no final | Registrar el ingreso de las existencias recibidas como consecuencia de una compra válida, asociando la entrada con el lote correspondiente. | detalleCompra | Otro | DetalleCompra | Por valor | no final | Producto adquirido, cantidad recibida, unidad de medida, compra, detalle de compra y lote sobre el cual se registra la entrada. | Otro | MovimientoInventario | Los datos requeridos para registrar el movimiento no son válidos.<br>La clase de movimiento no corresponde a la operación.<br>El TipoMovimiento no existe o su categoría de origen no es válida.<br>El detalle de compra asociado no es válido.<br>El lote sobre el cual se registra el movimiento no existe.<br>La cantidad no es mayor que cero o supera 10.000.<br>Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote.<br>La cantidad no corresponde a la de la operación que genera el movimiento.<br>No fue posible actualizar el saldo del lote o el inventario. |
| public | registrarSalidaDeInventarioPorVenta | no static | no final | Registra la salida de los productos internos consumidos en una venta confirmada, descontándolos de los lotes por FIFO. | consumoVenta | Otro | ConsumoVenta | Por valor | no final | Consumo de venta que origina la salida. | Otro | List&lt;MovimientoInventario&gt; | Los datos requeridos para registrar el movimiento no son válidos.<br>La clase de movimiento no corresponde a la operación.<br>El TipoMovimiento no existe o su categoría de origen no es válida.<br>El consumo de venta asociado no es válido.<br>No hay lotes con saldo suficiente para cubrir la salida.<br>El lote sobre el cual se registra el movimiento no existe.<br>La cantidad no es mayor que cero o supera 10.000.<br>Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote.<br>La cantidad no corresponde a la de la operación que genera el movimiento.<br>No fue posible actualizar el saldo del lote o el inventario. |
| public | registrarMovimientosPorCambioDeProducto | no static | no final | Cuando se registra el cambio de un producto no perecedero, registra la Salida del lote que se devuelve al proveedor y la Entrada del lote de reemplazo, con el TipoMovimiento del cambio. | cambio | Otro | Cambio | Por valor | no final | Cambio registrado que origina la salida del lote próximo a vencer y la entrada del lote de reemplazo. | Otro | List&lt;MovimientoInventario&gt; | Los datos requeridos para registrar el movimiento no son válidos.<br>La clase de movimiento no corresponde a la operación.<br>El TipoMovimiento no existe o su categoría de origen no es válida.<br>El cambio asociado no existe o no es válido.<br>El producto interno del cambio es perecedero.<br>El lote sobre el cual se registra el movimiento no existe.<br>La cantidad no es mayor que cero o supera 10.000.<br>Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote.<br>La cantidad no corresponde a la de la operación que genera el movimiento.<br>No fue posible actualizar el saldo del lote o el inventario. |
| public | consultarMovimientosDeUnLote | static | final | Permite consultar las entradas y salidas registradas sobre un lote, para conocer la trazabilidad de sus existencias. | id | Otro | UUID | Por valor | no final | Identificador del lote. | Otro | List&lt;MovimientoInventario&gt; | El lote no existe.<br>Se buscan retiros por vencimiento entre los movimientos de inventario. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | claseMovimiento | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ClaseMovimiento. | claseMovimiento | Otro | ClaseMovimiento | Por valor | no final | Valor que se desea asignar al atributo claseMovimiento. | Otro | Builder |  |
| public | tipoMovimiento | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de TipoMovimiento. | tipoMovimiento | Otro | TipoMovimiento | Por valor | no final | Valor que se desea asignar al atributo tipoMovimiento. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | fechaMovimiento | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaMovimiento | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaMovimiento. | Otro | Builder |  |
| public | lote | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Lote. | lote | Otro | Lote | Por valor | no final | Valor que se desea asignar al atributo lote. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de MovimientoInventario con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | MovimientoInventario |  |
