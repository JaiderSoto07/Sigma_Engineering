# Cambio

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Cambio](../../01-modelo-dominio/objetos/Cambio.md) · [Entidad MER: Cambio](../../03-modelo-entidad-relacion/entidades/Cambio.md)

| Campo | Valor |
|---|---|
| Clase | Cambio |
| Estereotipo | class |
| Descripción | Representa la operación mediante la cual un insumo no perecedero próximo a vencer se devuelve al proveedor y se reemplaza por uno nuevo, manteniendo la disponibilidad y las condiciones adecuadas de los insumos de la pizzería. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |
| Necesita de | Asociación | TipoMovimiento |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del cambio. |
| private | productoCambio | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno que se cambia (siempre un producto no perecedero). |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad del producto que sale y entra por efecto del cambio. |
| private | unidadMedida | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida en la que se expresa la cantidad del cambio. |
| private | fechaVencimiento | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha de vencimiento del producto que ingresa como reemplazo. |
| private | fechaCambio | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha en la que se realiza el cambio. |
| private | tipoMovimiento | Otro | TipoMovimiento | no final | no static | Instancia por defecto de TipoMovimiento | Código de la operación (TipoMovimiento) del cambio; con él se rastrean los movimientos que generó. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Cambio | Parametrizado | Constructor privado: crea la instancia de Cambio copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getProductoCambio | getter | no final | Método encargado de obtener el valor del atributo productoCambio. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getUnidadMedida | getter | no final | Método encargado de obtener el valor del atributo unidadMedida. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getFechaVencimiento | getter | no final | Método encargado de obtener el valor del atributo fechaVencimiento. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getFechaCambio | getter | no final | Método encargado de obtener el valor del atributo fechaCambio. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getTipoMovimiento | getter | no final | Método encargado de obtener el valor del atributo tipoMovimiento. |  |  |  |  |  |  | Otro | TipoMovimiento |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarCambioDeProducto | no static | no final | Registrar el reemplazo de un producto próximo a vencer por uno nuevo, en respuesta a la alerta de vencimiento emitida por Lote, generando la salida del lote saliente y la entrada del nuevo lote en el inventario. | cambio | Otro | Cambio | Por valor | no final | Información necesaria para registrar el cambio (producto, cantidad, unidad de medida, fecha de vencimiento del producto nuevo y fecha del cambio). | Otro | Cambio | Los datos ingresados para registrar el cambio no son válidos.<br>El producto interno asociado al cambio no existe en el sistema.<br>El producto interno asociado al cambio es perecedero.<br>La cantidad no es mayor que cero o supera 10.000.<br>La unidad de medida utilizada para el cambio no es válida.<br>La fecha de vencimiento del producto de reemplazo no es posterior a la fecha del cambio.<br>Ya existe otro cambio registrado con el mismo producto y la misma fecha de cambio.<br>El cambio se está registrando sin que exista una alerta de vencimiento próximo emitida por Lote para el producto interno indicado.<br>No fue posible generar el TipoMovimiento del cambio.<br>No fue posible registrar la salida del lote o la entrada del reemplazo. |
| public | consultarCambio | static | final | Permite consultar un cambio de producto registrado, con su producto, cantidad y fechas. | id | Otro | UUID | Por valor | no final | Identificador del cambio. | Otro | Cambio | No existe un cambio con el identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | productoCambio | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoCambio | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoCambio. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | unidadMedida | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedida | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedida. | Otro | Builder |  |
| public | fechaVencimiento | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaVencimiento | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaVencimiento. | Otro | Builder |  |
| public | fechaCambio | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaCambio | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaCambio. | Otro | Builder |  |
| public | tipoMovimiento | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de TipoMovimiento. | tipoMovimiento | Otro | TipoMovimiento | Por valor | no final | Valor que se desea asignar al atributo tipoMovimiento. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Cambio con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Cambio |  |
