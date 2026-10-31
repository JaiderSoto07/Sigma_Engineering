# Inventario

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Inventario](../../02-diagrama-clases/clases/Inventario.md) · [Entidad MER: Inventario](../../03-modelo-entidad-relacion/entidades/Inventario.md)

> Es la cantidad que hay disponible de cada insumo o producto que la pizzería necesita para trabajar y vender.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada registro de inventario |  |  |
| CantidadTotal | Cantidad Total del producto | Decimal Europeo (4) | Si |
| ProductoInterno | Producto interno (ingrediente o bebida) al que corresponde este registro de inventario | ProductoInterno | Si |
| UnidadMedidaInventario | Unidad de medida que se maneja en el inventario | UnidadMedida | Si |
| StockMinimo | Cantidad minima de cada producto interno que debe haber en el inventario, expresada en la misma unidad de medida de inventario | Decimal Europeo (4) | Si |

### Datos de muestra

_9 registros de ejemplo._

| Id | CantidadTotal | ProductoInterno | UnidadMedidaInventario | StockMinimo | CombinacionUnica |
|---|---|---|---|---|---|
| 1 | 10.25 | Queso | kg | 10 | Queso |
| 2 | 10.25 | Albahaca | kg | 10 | Albahaca |
| 3 | 12.34 | Peperoni | kg | 12 | Peperoni |
| 4 | 20.198 | Aceite de oliva | l | 15 | Aceite de oliva |
| 5 | 11.85 | Masa de Pizza | kg | 10 | Masa de Pizza |
| 6 | 48 | Pepsi 400 ml | und | 15 | Pepsi 400 ml |
| 7 | 10.2 | Salsa de Tomate | kg | 10 | Salsa de Tomate |
| 8 | 0.5 | Jamón | kg | 1 | Jamón |
| 9 | 0.42 | Piña | kg | 1 | Piña |

## Caracterización

### Atributos

| Nombre atributo | Id | CantidadTotal | ProductoInterno | StockMinimo | UnidadMedidaInventario |
|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada registro de inventario | Cantidad Total del producto | Producto interno (ingrediente o bebida) al que corresponde este registro de inventario | Cantidad minima de cada producto interno que debe haber en el inventario, expresada en la misma unidad de medida de inventario | Unidad de medida que se maneja en el inventario |
| Identificador del objeto de dominio | Sí | No | No | No | No |
| Tipo de dato | Identificador único universal | Decimal Europeo | ProductoInterno | Decimal Europeo | UnidadMedida |
| Obligatorio | Sí | Sí | Sí | Sí | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |
| Precisión (si aplica) |  | 4 |  | 4 |  |
| Rendondeo (si aplica) |  | A la unidad (0) |  | A la unidad (0) |  |
| Rango inicial (si aplica) |  | &gt;=0 |  | &gt;0 |  |
| Rango final (si aplica) |  | 100000 |  | 100000 |  |
| Formato (si aplica) |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Es la suma de lo que queda disponible en todos los lotes de ese producto | Debe corresponder a un producto interno existente; cada producto interno tiene un unico registro de inventario. | Debe ser mayor que cero. Si no se indica, se registra 1. | Se toma del lote, queda determinada por el tipo de medida del producto (Peso, Volumen o Unidad). |
| Dato calculado | No | Si | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  | 1 |  |
| Auto generado | Sí | No | No | No | No |
| Actualizar inventario | Requerido / No Modificable | Requerido / No Modificable | Requerido / No Modificable | No Requerido / No Modificable | Requerido / No Modificable |
| Consultar inventario | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |
| Consultar existencias por lote | No Mostrar | Mostrar | Mostrar | No Mostrar | Mostrar |
| Establecer stock mínimo | No Requerido / No Modificable | No Requerido / No Modificable | Requerido / No Modificable | Requerido / Modificable | No Requerido / No Modificable |
| Emitir alerta de stock bajo | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos registros de inventario para el mismo producto. | ProductoInterno |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|
| Actualizar inventario | Actualizar la existencia representada en el inventario como consecuencia de un movimiento de entrada, salida o ajuste previamente registrado. | Datos | MovimientoInventario | Información del movimiento que permite determinar la variación que debe reflejarse en la existencia del inventario. | Inventario / Inventario actualizado | P-INV-001 | Asegurar que las existencias registradas en el inventario correspondan a lotes válidos y registrados. | El lote asociado a la actualizacion del inventario no es valido o no esta registrado. | Rechazar la actualizacion e informar que el lote no es valido. |
|  |  |  |  |  |  | P-INV-002 | Asegurar que la cantidad disponible de una existencia no sea inferior a cero ni superior a 100.000. | La cantidad resultante seria inferior a cero o superior a 100.000. | Rechazar la actualizacion e informar el rango permitido. |
|  |  |  |  |  |  | P-INV-005 | Asegurar que no exista mas de un registro de inventario para el mismo ProductoInterno. | Ya existe otro registro de inventario para el mismo producto interno. | Rechazar la operacion e informar la duplicidad. |
|  |  |  |  |  |  | P-INV-003 | Asegurar que la cantidad disponible que se va a reflejar en el inventario corresponda a la suma de las cantidades disponibles de todos los lotes asociados al mismo producto. | La cantidad calculada no corresponde a la suma de las cantidades disponibles de los lotes asociados al producto. | Rechazar la actualizacion e informar que la cantidad no coincide con la suma de los lotes asociados al producto. |
| Consultar inventario | Permite visualizar el estado actual y las existencias de ingredientes y bebidas. | Datos | Inventario | Informacion necesaria para poder consultar el inventario | Inventario / Estado y cantidad disponible de todos los productos e insumos administrados en el inventario. | P-INV-007 | Asegurar que el inventario exista con el identificador indicado. | No se encuentra una existencia que corresponda a los criterios de consulta. | Informar que no existen existencias que coincidan con la consulta. |
| Consultar existencias por lote | Consultar las cantidades disponibles asociadas a los lotes existentes para conocer la trazabilidad de las existencias. | Datos | Inventario | Información necesaria para identificar el producto y lote cuya existencia se desea consultar. | Inventario / Existencia consultada por lote | P-INV-001 | Asegurar que las existencias registradas en el inventario correspondan a lotes válidos y registrados. | El lote indicado no corresponde a una existencia disponible para consulta, o el lote no se encuentra registrado en el sistema. | Informar que no existe una existencia asociada al lote indicado. |
| Establecer stock mínimo | Permite definir la cantidad mínima de existencias que debe mantenerse para controlar la necesidad de reposición. | Datos | Inventario | Cantidad de stock mínimo que se desea establecer para el producto. | Inventario / Stock mínimo actualizado. | P-INV-004 | Asegurar que el stock minimo sea mayor que cero y como maximo 100.000; si no se indica, se registra 1. | El stock minimo no es mayor que cero o supera 100.000. | Rechazar el cambio e informar el rango permitido. |
| Emitir alerta de stock bajo | Cuando la cantidad total disponible de un producto cae por debajo del stock minimo establecido, el sistema emite una alerta para que el administrador gestione la compra de reposicion. | Datos | Inventario | Cantidad total disponible y stock mínimo establecido para el producto. | Inventario / Alerta de stock bajo emitida | P-INV-006 | Asegurar que se emita una alerta cuando la cantidad total disponible de un producto sea inferior o se este acercando  al stock minimo establecido. | El stock mínimo no está definido para el producto, o no se puede determinar la cantidad total disponible. | No emitir la alerta e informar la condición que lo impide. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Sistema | Actualizar inventario | Actualizar la existencia representada en el inventario como consecuencia de un movimiento de entrada, salida o ajuste previamente registrado. | Inventario | Lotes, MovimientoInventario | Información del movimiento que afecta la existencia, incluyendo tipo de movimiento, cantidad, unidad de medida, lote y origen cuando corresponda. | P-INV-001 |  | Inventario actualizado |  |
|  |  |  |  |  |  | P-INV-002 |  |  |  |
|  |  |  |  |  |  | P-INV-005 |  |  |  |
|  |  |  |  |  |  | P-INV-003 |  |  |  |
| Administrador | Consultar inventario | Permite visualizar el estado actual y las existencias de ingredientes y bebidas. | Inventario | Existencias registradas | Permite visualizar el estado y la cantidad disponible de todos los ingredientes y bebidas almacenados. | P-INV-007 |  | Inventario consultado |  |
| Administrador | Consultar existencias por lote | Consultar las cantidades disponibles asociadas a los lotes existentes para conocer la trazabilidad de las existencias. | Inventario | Información del lote | Identificador del lote y producto o ingrediente cuya existencia se desea consultar. | P-INV-001 |  | Existencia consultada por lote |  |
| Administrador | Establecer stock mínimo | Permite definir la cantidad mínima de existencias que debe mantenerse para controlar la necesidad de reposición. | Inventario |  |  | P-INV-004 |  | Stock mínimo establecido |  |
| Sistema | Emitir alerta de stock bajo | Cuando la cantidad total disponible de un producto cae por debajo del stock minimo establecido, el sistema emite una alerta para que el administrador gestione la compra de reposicion. | Inventario |  |  | P-INV-006 |  | Alerta de stock bajo emitida |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-INV-001 | Asegurar que las existencias registradas en el inventario correspondan a lotes válidos y registrados. | Lote | P-LOT-008 |
| P-INV-002 | Asegurar que la cantidad disponible de una existencia no sea inferior a cero ni superior a 100.000. | Inventario |  |
| P-INV-003 | Asegurar que la cantidad disponible que se va a reflejar en el inventario corresponda a la suma de las cantidades disponibles de todos los lotes asociados al mismo producto. | Inventario |  |
| P-INV-004 | Asegurar que el stock minimo sea mayor que cero y como maximo 100.000; si no se indica, se registra 1. | Inventario |  |
| P-INV-005 | Asegurar que no exista mas de un registro de inventario para el mismo ProductoInterno. | Inventario |  |
| P-INV-006 | Asegurar que se emita una alerta cuando la cantidad total disponible de un producto sea inferior o se este acercando  al stock minimo establecido. | Inventario |  |
| P-INV-007 | Asegurar que el inventario exista con el identificador indicado. | Inventario |  |
