# Inventario

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Inventario](../../01-modelo-dominio/objetos/Inventario.md) · [Entidad MER: Inventario](../../03-modelo-entidad-relacion/entidades/Inventario.md)

| Campo | Valor |
|---|---|
| Clase | Inventario |
| Estereotipo | class |
| Descripción | Representa las existencias disponibles de los insumos y productos que la pizzería necesita para operar y atender sus ventas. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del registro de inventario. |
| private | cantidadTotal | Otro | BigDecimal | no final | no static | 0 | Cantidad total disponible del producto interno, expresada en su unidad de medida de inventario. |
| private | productoInterno | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno (ingrediente o bebida) al que corresponde este registro de inventario; cada producto interno tiene un único registro. |
| private | unidadMedidaInventario | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida en la que se maneja el inventario (kg, l o und), determinada por el tipo de medida del producto interno. |
| private | stockMinimo | Otro | BigDecimal | no final | no static | 1 | Cantidad mínima que debe mantenerse disponible antes de requerir reposición. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Inventario | Parametrizado | Constructor privado: crea la instancia de Inventario copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getCantidadTotal | getter | no final | Método encargado de obtener el valor del atributo cantidadTotal. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getUnidadMedidaInventario | getter | no final | Método encargado de obtener el valor del atributo unidadMedidaInventario. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getStockMinimo | getter | no final | Método encargado de obtener el valor del atributo stockMinimo. |  |  |  |  |  |  | Otro | BigDecimal |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | actualizarInventario | no static | no final | Actualizar la existencia representada en el inventario como consecuencia de un movimiento de entrada, salida o ajuste previamente registrado. | movimientoInventario | Otro | MovimientoInventario | Por valor | no final | Información del movimiento que permite determinar la variación que debe reflejarse en la existencia del inventario. | Otro | Inventario | El lote asociado a la actualización del inventario no es válido o no está registrado.<br>La cantidad resultante sería inferior a cero o superior a 100.000.<br>Ya existe otro registro de inventario para el mismo producto interno.<br>La cantidad calculada no corresponde a la suma de las cantidades disponibles de los lotes asociados al producto. |
| public | consultarInventario | static | final | Permite visualizar el estado actual y las existencias de ingredientes y bebidas. | inventario | Otro | Inventario | Por valor | no final | Información necesaria para poder consultar el inventario. | Otro | Inventario | No se encuentra una existencia que corresponda a los criterios de consulta. |
| public | consultarExistenciasPorLote | static | final | Consultar las cantidades disponibles asociadas a los lotes existentes para conocer la trazabilidad de las existencias. | inventario | Otro | Inventario | Por valor | no final | Información necesaria para identificar el producto y lote cuya existencia se desea consultar. | Otro | Inventario | El lote indicado no corresponde a una existencia disponible para consulta, o el lote no se encuentra registrado en el sistema. |
| public | establecerStockMinimo | no static | no final | Permite definir la cantidad mínima de existencias que debe mantenerse para controlar la necesidad de reposición. | inventario | Otro | Inventario | Por valor | no final | Cantidad de stock mínimo que se desea establecer para el producto. | Otro | Inventario | El stock mínimo no es mayor que cero o supera 100.000. |
| public | emitirAlertaDeStockBajo | no static | no final | Cuando la cantidad total disponible de un producto cae por debajo del stock mínimo establecido, el sistema emite una alerta para que el administrador gestione la compra de reposición. | inventario | Otro | Inventario | Por valor | no final | Cantidad total disponible y stock mínimo establecido para el producto. | Otro | Inventario | El stock mínimo no está definido para el producto, o no se puede determinar la cantidad total disponible. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | cantidadTotal | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidadTotal | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidadTotal. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInterno | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | unidadMedidaInventario | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedidaInventario | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedidaInventario. | Otro | Builder |  |
| public | stockMinimo | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 1. | stockMinimo | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo stockMinimo. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Inventario con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Inventario |  |
