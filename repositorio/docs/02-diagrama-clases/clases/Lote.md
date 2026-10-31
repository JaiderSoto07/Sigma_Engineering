# Lote

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Lote](../../01-modelo-dominio/objetos/Lote.md) · [Entidad MER: Lote](../../03-modelo-entidad-relacion/entidades/Lote.md)

| Campo | Valor |
|---|---|
| Clase | Lote |
| Estereotipo | class |
| Descripción | Representa un conjunto de unidades de un mismo insumo recibido en una compra o como reemplazo en un cambio, identificado para controlar su cantidad, saldo, fecha de vencimiento y trazabilidad dentro del inventario. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |
| Es usada por | Asociación | SalidaLote |
| Es usada por | Asociación | MovimientoInventario |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del lote. |
| private | fechaVencimiento | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha de vencimiento del producto contenido en el lote. |
| private | disponible | boolean/Boolean |  | no final | no static | false | Indica si el lote todavía tiene existencias disponibles para consumo. |
| private | productoInterno | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno al que pertenece el lote. |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad con la que ingresó el lote, ya convertida a la unidad de medida de inventario. |
| private | saldo | Otro | BigDecimal | no final | no static | 0 | Cantidad que aún queda disponible del lote, descontando las salidas registradas y el retiro por vencimiento (SalidaLote). |
| private | unidadMedidaInventario | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida estándar del lote: kg para peso, l para volumen y und para unidades. |
| private | numeroLote | int/Integer |  | no final | no static | 0 | Número diferenciador del lote dentro de su respectivo producto interno. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Lote | Parametrizado | Constructor privado: crea la instancia de Lote copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getFechaVencimiento | getter | no final | Método encargado de obtener el valor del atributo fechaVencimiento. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | isDisponible | getter | no final | Método encargado de obtener el valor del atributo disponible. |  |  |  |  |  |  | boolean/Boolean |  |  |
| public | getProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getSaldo | getter | no final | Método encargado de obtener el valor del atributo saldo. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getUnidadMedidaInventario | getter | no final | Método encargado de obtener el valor del atributo unidadMedidaInventario. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getNumeroLote | getter | no final | Método encargado de obtener el valor del atributo numeroLote. |  |  |  |  |  |  | int/Integer |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarLote | no static | no final | Cuando se registra un detalle de compra o el reemplazo de un cambio, el sistema crea automáticamente el lote del producto interno con su cantidad, saldo, fecha de vencimiento y número de lote consecutivo. | movimientoInventario | Otro | MovimientoInventario | Por valor | no final | Movimiento de entrada que origina el lote: producto interno, cantidad, unidad de medida y fecha del movimiento. | Otro | Lote | Los datos ingresados para registrar el lote no son válidos.<br>El identificador generado ya está siendo utilizado por otro lote.<br>El producto interno asociado al lote no existe o no es válido.<br>La cantidad inicial no es mayor que cero o supera 10.000.<br>La unidad de medida asociada a la cantidad del lote no es válida.<br>El movimiento de inventario que origina el lote no existe o no es válido.<br>Ya existe otro lote con el mismo NumeroLote para el mismo ProductoInterno.<br>El número de lote no es el siguiente consecutivo del producto interno.<br>Se intentó modificar manualmente un atributo que solo se calcula a partir de los movimientos de inventario. |
| public | recalcularSaldoDeLote | no static | no final | Cada vez que se registra una salida por venta o por cambio, o un retiro (SalidaLote), el saldo del lote se recalcula automáticamente. | movimientoInventario | Otro | MovimientoInventario | Por valor | no final | Movimiento de Salida que afecta el lote y cantidad consumida. | Otro | Lote | El recálculo dejaría el saldo por debajo de cero o por encima de la cantidad.<br>El saldo del lote llegó a cero. |
| public | emitirAlertaDeVencimientoProximo | no static | no final | Cuando la fecha de vencimiento de un lote está próxima a cumplirse, el sistema emite una alerta para que el administrador registre el Cambio (si el producto no es perecedero) o el retiro del lote con una SalidaLote (si es perecedero). | lote | Otro | Lote | Por valor | no final | Lote cuya fecha de vencimiento está próxima; por su producto interno se sabe si es perecedero. | Otro | Lote | El producto interno asociado al lote no es válido, o no se puede determinar si es perecedero. |
| public | consultarLote | static | final | Permite consultar la información de un lote específico, incluyendo su cantidad, saldo, fecha de vencimiento y disponibilidad. | id | Otro | UUID | Por valor | no final | Identificador único del lote que se desea consultar. | Otro | Lote | No existe un lote con el identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | fechaVencimiento | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaVencimiento | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaVencimiento. | Otro | Builder |  |
| public | disponible | no static | no final | Asigna el valor recibido. | disponible | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo disponible. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInterno | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | saldo | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | saldo | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo saldo. | Otro | Builder |  |
| public | unidadMedidaInventario | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedidaInventario | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedidaInventario. | Otro | Builder |  |
| public | numeroLote | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | numeroLote | int/Integer |  | Por valor | no final | Valor que se desea asignar al atributo numeroLote. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Lote con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Lote |  |
