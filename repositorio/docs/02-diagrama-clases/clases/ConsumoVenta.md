# ConsumoVenta

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: ConsumoVenta](../../01-modelo-dominio/objetos/ConsumoVenta.md) · [Entidad MER: ConsumoVenta](../../03-modelo-entidad-relacion/entidades/ConsumoVenta.md)

| Campo | Valor |
|---|---|
| Clase | ConsumoVenta |
| Estereotipo | class |
| Descripción | Representa los productos internos que se consumen por cada detalle de venta, según la receta del producto vendido o su producto interno asociado. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Composición | DetalleVenta |
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |
| Necesita de | Asociación | TipoMovimiento |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de cada consumo de venta. |
| private | detalleVenta | Otro | DetalleVenta | no final | no static | Instancia por defecto de DetalleVenta | Detalle de venta que origina el consumo. |
| private | productoInterno | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno que se consume según la receta del producto vendido (o el propio producto interno si se vende directamente). |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad del producto interno que se consume: la cantidad de la receta multiplicada por la cantidad vendida. |
| private | unidadMedida | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida de la cantidad consumida; es la misma de la receta (g, ml o und). |
| private | tipoMovimiento | Otro | TipoMovimiento | no final | no static | Instancia por defecto de TipoMovimiento | Código de la operación (TipoMovimiento) propio de este consumo; es el mismo que lleva su movimiento de salida. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | ConsumoVenta | Parametrizado | Constructor privado: crea la instancia de ConsumoVenta copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getDetalleVenta | getter | no final | Método encargado de obtener el valor del atributo detalleVenta. |  |  |  |  |  |  | Otro | DetalleVenta |  |
| public | getProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getUnidadMedida | getter | no final | Método encargado de obtener el valor del atributo unidadMedida. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getTipoMovimiento | getter | no final | Método encargado de obtener el valor del atributo tipoMovimiento. |  |  |  |  |  |  | Otro | TipoMovimiento |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarConsumoDeVenta | no static | no final | Al confirmar una venta, registra por cada detalle de venta los productos internos que se consumen según la receta del producto vendido. | detalleVenta | Otro | DetalleVenta | Por valor | no final | Detalle de venta confirmado que origina el consumo. | Otro | List&lt;ConsumoVenta&gt; | El detalle de venta no existe.<br>El producto vendido no tiene receta ni producto interno asociado.<br>El producto interno ya fue consumido para ese detalle de venta.<br>No hay saldo suficiente en inventario.<br>No fue posible registrar las salidas de inventario del consumo. |
| public | consultarConsumosDeUnDetalleDeVenta | static | final | Permite consultar los productos internos consumidos por un detalle de venta. | id | Otro | UUID | Por valor | no final | Identificador del detalle de venta. | Otro | List&lt;ConsumoVenta&gt; | El detalle de venta no existe. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | detalleVenta | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de DetalleVenta. | detalleVenta | Otro | DetalleVenta | Por valor | no final | Valor que se desea asignar al atributo detalleVenta. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInterno | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | unidadMedida | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedida | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedida. | Otro | Builder |  |
| public | tipoMovimiento | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de TipoMovimiento. | tipoMovimiento | Otro | TipoMovimiento | Por valor | no final | Valor que se desea asignar al atributo tipoMovimiento. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de ConsumoVenta con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | ConsumoVenta |  |
