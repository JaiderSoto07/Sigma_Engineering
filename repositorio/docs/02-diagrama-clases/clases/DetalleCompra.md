# DetalleCompra

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: DetalleCompra](../../01-modelo-dominio/objetos/DetalleCompra.md) · [Entidad MER: DetalleCompra](../../03-modelo-entidad-relacion/entidades/DetalleCompra.md)

| Campo | Valor |
|---|---|
| Clase | DetalleCompra |
| Estereotipo | class |
| Descripción | Representa cada uno de los insumos o productos adquiridos dentro de una compra, especificando qué se compró y en qué cantidad. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |
| Necesita de | Composición | Compra |
| Necesita de | Asociación | TipoMovimiento |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del detalle de compra. |
| private | productoInterno | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno adquirido en este detalle de compra. |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad adquirida del producto interno. |
| private | unidadMedida | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida en la que se realizó la compra del producto interno. |
| private | precioCompra | Otro | BigDecimal | no final | no static | 0 | Valor monetario pagado por el producto interno adquirido. |
| private | fechaVencimiento | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha de vencimiento del producto, cuando aplica (solo productos no perecederos). |
| private | compra | Otro | Compra | no final | no static | Instancia por defecto de Compra | Compra a la que pertenece este detalle. |
| private | tipoMovimiento | Otro | TipoMovimiento | no final | no static | Instancia por defecto de TipoMovimiento | Código de la operación (TipoMovimiento) propio de este detalle de compra; es el mismo que lleva su movimiento de entrada. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | DetalleCompra | Parametrizado | Constructor privado: crea la instancia de DetalleCompra copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getUnidadMedida | getter | no final | Método encargado de obtener el valor del atributo unidadMedida. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getPrecioCompra | getter | no final | Método encargado de obtener el valor del atributo precioCompra. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getFechaVencimiento | getter | no final | Método encargado de obtener el valor del atributo fechaVencimiento. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getCompra | getter | no final | Método encargado de obtener el valor del atributo compra. |  |  |  |  |  |  | Otro | Compra |  |
| public | getTipoMovimiento | getter | no final | Método encargado de obtener el valor del atributo tipoMovimiento. |  |  |  |  |  |  | Otro | TipoMovimiento |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarDetalleDeCompra | no static | no final | Registrar un producto adquirido dentro de una compra, indicando la cantidad, unidad de medida, precio de compra y, cuando corresponda, su fecha de vencimiento. | detalleCompra | Otro | DetalleCompra | Por valor | no final | Información necesaria para registrar el detalle de la compra. | Otro | DetalleCompra | Los datos ingresados para registrar el detalle de compra no son válidos.<br>La compra a la que pertenece el detalle de compra no existe en el sistema.<br>El producto interno asociado al detalle de compra no existe en el sistema.<br>El producto interno se encuentra inactivo.<br>Se registró una fecha de vencimiento real para un producto interno perecedero, o no se registró para uno no perecedero cuando sí debía.<br>La cantidad no es mayor que cero o supera 100.000.<br>El precio de compra es negativo o supera 5.000.000.<br>La unidad de medida utilizada para el detalle de compra no es válida.<br>El mismo producto interno ya está registrado dentro de la misma compra con la misma fecha de vencimiento.<br>No fue posible generar el TipoMovimiento del detalle.<br>No fue posible crear el lote del producto interno.<br>No fue posible registrar la entrada de inventario.<br>El total recalculado de la compra supera 5.000.000. |
| public | modificarDetalleDeCompra | no static | no final | Actualizar la información de un detalle de compra existente sin comprometer la trazabilidad de los movimientos de inventario ya generados. | detalleCompra | Otro | DetalleCompra | Por valor | no final | Identificador del detalle y datos que se desean modificar. | Otro | DetalleCompra | El detalle de compra que se desea modificar no existe.<br>Los nuevos datos del detalle de compra no son válidos.<br>La nueva cantidad no es mayor que cero o supera 100.000.<br>El nuevo precio de compra es negativo o supera 5.000.000.<br>El total recalculado de la compra supera 5.000.000. |
| public | eliminarDetalleDeCompra | no static | no final | Permite eliminar un detalle de compra que no haya generado un lote asociado que deba conservarse para mantener la trazabilidad. | id | Otro | UUID | Por valor | no final | Identificador del detalle de compra que se desea eliminar. | void |  | El detalle de compra que se desea eliminar no existe.<br>El detalle de compra ya generó un lote asociado que debe conservarse.<br>No fue posible recalcular el total de la compra. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInterno | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | unidadMedida | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedida | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedida. | Otro | Builder |  |
| public | precioCompra | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | precioCompra | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo precioCompra. | Otro | Builder |  |
| public | fechaVencimiento | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaVencimiento | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaVencimiento. | Otro | Builder |  |
| public | compra | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Compra. | compra | Otro | Compra | Por valor | no final | Valor que se desea asignar al atributo compra. | Otro | Builder |  |
| public | tipoMovimiento | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de TipoMovimiento. | tipoMovimiento | Otro | TipoMovimiento | Por valor | no final | Valor que se desea asignar al atributo tipoMovimiento. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de DetalleCompra con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | DetalleCompra |  |
