# MovimientoInventario

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: MovimientoInventario](../../02-diagrama-clases/clases/MovimientoInventario.md) · [Entidad MER: MovimientoInventario](../../03-modelo-entidad-relacion/entidades/MovimientoInventario.md)

> Es cada entrada o salida concreta de un lote, registrada en una fecha (FechaMovimiento). Lo genera una operación (TipoMovimiento), indica si suma o resta (ClaseMovimiento) y por el lote se sabe qué producto interno se movió. Si una operación usa varios lotes (FIFO), genera un movimiento por cada lote.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada movimiento: una entrada o salida concreta de un lote |  |  |
| FechaMovimiento | Fecha en la que se registra el movimiento de inventario, correspondiente al momento en que se realiza la entrada o salida sobre el lote. | Fecha | Si |
| TipoMovimiento | Codigo de la operacion (TipoMovimiento) que genero el movimiento; a traves de el se conoce la CategoriaOrigen y el documento de origen | TipoMovimiento | Si |
| ClaseMovimiento | Clase de movimiento: Entrada (suma al inventario) o Salida (resta del inventario) | ClaseMovimiento | Si |
| Lote | Lote sobre el que se realizo la entrada o salida. Por el lote se conoce el producto interno y la unidad de medida | Lote | Si |
| Cantidad | Cantidad que entro o salio del lote, expresada en la unidad de medida del lote (kg, l o und) | Decimal Europeo (4) | Si |

### Datos de muestra

_27 registros de ejemplo._

| Id | FechaMovimiento | TipoMovimiento | ClaseMovimiento | Lote | Cantidad | Combinacion unica 1 |
|---|---|---|---|---|---|---|
| 1 | 01/09/2026 | 99 | Entrada | 1 Queso | 10.2 | 99 1 Queso |
| 2 | 01/09/2026 | 100 | Entrada | 1 Albahaca | 10.3 | 100 1 Albahaca |
| 3 | 01/09/2026 | 101 | Entrada | 1 Peperoni | 12.5 | 101 1 Peperoni |
| 4 | 02/05/2026 | 102 | Entrada | 1 Aceite de oliva | 15.2 | 102 1 Aceite de oliva |
| 5 | 02/05/2026 | 103 | Entrada | 2 Aceite de oliva | 3 | 103 2 Aceite de oliva |
| 6 | 02/05/2026 | 104 | Entrada | 1 Masa de Pizza | 0.2 | 104 1 Masa de Pizza |
| 7 | 10/07/2026 | 105 | Entrada | 1 Pepsi 400 ml | 24 | 105 1 Pepsi 400 ml |
| 8 | 15/08/2026 | 106 | Entrada | 2 Masa de Pizza | 12 | 106 2 Masa de Pizza |
| 9 | 01/07/2026 | 107 | Entrada | 2 Queso | 0.15 | 107 2 Queso |
| 10 | 15/06/2026 | 108 | Entrada | 1 Salsa de Tomate | 10.5 | 108 1 Salsa de Tomate |
| 11 | 03/09/2026 | 153 | Salida | 1 Masa de Pizza | 0.2 | 153 1 Masa de Pizza |
| 12 | 03/09/2026 | 153 | Salida | 2 Masa de Pizza | 0.15 | 153 2 Masa de Pizza |
| 13 | 03/09/2026 | 154 | Salida | 1 Salsa de Tomate | 0.3 | 154 1 Salsa de Tomate |
| 14 | 03/09/2026 | 155 | Salida | 1 Aceite de oliva | 0.002 | 155 1 Aceite de oliva |
| 15 | 03/09/2026 | 156 | Salida | 1 Queso | 0.1 | 156 1 Queso |
| 16 | 03/09/2026 | 157 | Salida | 1 Albahaca | 0.05 | 157 1 Albahaca |
| 17 | 03/09/2026 | 158 | Salida | 1 Peperoni | 0.16 | 158 1 Peperoni |
| 20 | 09/09/2026 | 200 | Entrada | 2 Pepsi 400 ml | 24 | 200 2 Pepsi 400 ml |
| 21 | 09/09/2026 | 201 | Entrada | 3 Aceite de oliva | 5 | 201 3 Aceite de oliva |
| 23 | 26/09/2026 | 109 | Entrada | 3 Queso | 0.3 | 109 3 Queso |
| 24 | 28/09/2026 | 110 | Entrada | 1 Jamón | 1 | 110 1 Jamón |
| 25 | 28/09/2026 | 111 | Entrada | 1 Piña | 1 | 111 1 Piña |
| 26 | 30/09/2026 | 112 | Entrada | 4 Queso | 0.5 | 112 4 Queso |
| 27 | 02/10/2026 | 150 | Salida | 3 Queso | 0.3 | 150 3 Queso |
| 28 | 02/10/2026 | 150 | Salida | 4 Queso | 0.2 | 150 4 Queso |
| 29 | 02/10/2026 | 151 | Salida | 1 Jamón | 0.1 | 151 1 Jamón |
| 30 | 02/10/2026 | 152 | Salida | 1 Piña | 0.08 | 152 1 Piña |

## Caracterización

### Atributos

| Nombre atributo | Id | ClaseMovimiento | TipoMovimiento | Cantidad | FechaMovimiento | Lote |
|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada movimiento: una entrada o salida concreta de un lote | Clase de movimiento: Entrada (suma al inventario) o Salida (resta del inventario) | Codigo de la operacion (TipoMovimiento) que genero el movimiento; a traves de el se conoce la CategoriaOrigen y el documento de origen | Cantidad que entro o salio del lote, expresada en la unidad de medida del lote (kg, l o und) | Fecha en la que se registra el movimiento de inventario, correspondiente al momento en que se realiza la entrada o salida sobre el lote. | Lote sobre el que se realizo la entrada o salida. Por el lote se conoce el producto interno y la unidad de medida |
| Identificador del objeto de dominio | Si | No | No | No | No | No |
| Tipo de dato | Identificador único universal | ClaseMovimiento | TipoMovimiento | Decimal Europeo | Fecha | Lote |
| Obligatorio | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |
| Precisión (si aplica) |  |  |  | 4 |  |  |
| Rendondeo (si aplica) |  |  |  |  |  |  |
| Rango inicial (si aplica) |  |  |  | &gt;0 |  |  |
| Rango final (si aplica) |  |  |  | 10000 |  |  |
| Formato (si aplica) |  |  |  |  | dd/mm/yyyy |  |
| Reglas especiales (si aplica) |  | Entrada (suma al inventario) o Salida (resta del inventario). Debe ser una clase de movimiento existente. | Codigo de la operacion que genero el movimiento (detalle de compra, consumo de venta o cambio); a traves de el se conoce la categoria de origen. | Cantidad que entra o sale del lote, en la unidad de medida del lote. Debe ser mayor a cero y como maximo 10.000. | Fecha en la que se registra el movimiento. | Lote sobre el que se realiza la entrada o salida. Es obligatorio en todos los movimientos. |
| Dato calculado | No | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |  |
| Auto generado | Si | No | No | No | No | No |
| Registrar entrada de inventario por compra | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido |
| Registrar salida de inventario por venta | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido |
| Registrar movimientos por cambio de producto | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido |
| Consultar movimientos de un lote | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No se puede registrar dos veces el mismo codigo de operacion (TipoMovimiento) sobre el mismo Lote. | TipoMovimiento |
|  |  | Lote |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar entrada de inventario por compra | Registrar el ingreso de las existencias recibidas como consecuencia de una compra válida, asociando la entrada con el lote correspondiente. | Datos | DetalleCompra | Producto adquirido, cantidad recibida, unidad de medida, compra, detalle de compra y lote sobre el cual se registra la entrada. | MovimientoInventario | Entrada de inventario registrada | P-MI-001 | Asegurar que los datos requeridos para registrar un movimiento de inventario sean válidos de acuerdo con el tipo de movimiento, cantidad, fecha y demás información obligatoria. | Los datos requeridos para registrar el movimiento no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-MI-002 | Asegurar que la clase de movimiento sea valida para la operacion: Entrada para compras y para el lote de reemplazo de un cambio; Salida para ventas y para el lote que se devuelve en un cambio. | La clase de movimiento no corresponde a la operacion. | Rechazar el registro e informar la clase de movimiento esperada. |
|  |  |  |  |  |  |  | P-MI-015 | Asegurar que el TipoMovimiento del movimiento exista y pertenezca a una categoria de origen valida (Compra, Venta o Cambio). | El TipoMovimiento no existe o su categoria de origen no es valida. | Rechazar el registro e informar que el codigo de operacion no es valido. |
|  |  |  |  |  |  |  | P-MI-008 | Asegurar que un movimiento de inventario relacionado con una compra corresponda a un detalle de compra valido y use su mismo TipoMovimiento. | El detalle de compra asociado no es valido. | Rechazar el registro e informar que el detalle de compra no existe. |
|  |  |  |  |  |  |  | P-MI-003 | Asegurar que el lote sobre el cual se registra el movimiento exista en el sistema. | El lote sobre el cual se registra el movimiento no existe. | Rechazar el registro e informar que el lote no existe. |
|  |  |  |  |  |  |  | P-MI-004 | Asegurar que la cantidad registrada en un movimiento de inventario sea mayor que cero y como maximo 10.000. | La cantidad no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-MI-013 | Asegurar que no exista otro movimiento de inventario con el mismo Codigo de operacion y el mismo Lote. | Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote. | Rechazar el registro e informar la duplicidad. |
|  |  |  |  |  |  |  | P-MI-011 | La Cantidad del movimiento se copia de la operacion que lo genera y queda fija: del DetalleCompra para Compra, del ConsumoVenta para Venta (repartida entre los lotes por FIFO) y del Cambio para Cambio. No se recalcula despues, aunque la receta cambie. | La cantidad no corresponde a la de la operacion que genera el movimiento. | Copiar la cantidad de la operacion de origen y dejarla fija. |
|  |  |  |  |  |  |  | P-MI-017 | Al registrar un movimiento se actualiza el saldo del lote afectado y la cantidad total del inventario del producto interno. | No fue posible actualizar el saldo del lote o el inventario. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Registrar salida de inventario por venta | Registra la salida de los productos internos consumidos en una venta confirmada, descontandolos de los lotes por FIFO. | Datos | ConsumoVenta | Consumo de venta que origina la salida. | MovimientoInventario | Salida de inventario por venta registrada | P-MI-001 | Asegurar que los datos requeridos para registrar un movimiento de inventario sean válidos de acuerdo con el tipo de movimiento, cantidad, fecha y demás información obligatoria. | Los datos requeridos para registrar el movimiento no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-MI-002 | Asegurar que la clase de movimiento sea valida para la operacion: Entrada para compras y para el lote de reemplazo de un cambio; Salida para ventas y para el lote que se devuelve en un cambio. | La clase de movimiento no corresponde a la operacion. | Rechazar el registro e informar la clase de movimiento esperada. |
|  |  |  |  |  |  |  | P-MI-015 | Asegurar que el TipoMovimiento del movimiento exista y pertenezca a una categoria de origen valida (Compra, Venta o Cambio). | El TipoMovimiento no existe o su categoria de origen no es valida. | Rechazar el registro e informar que el codigo de operacion no es valido. |
|  |  |  |  |  |  |  | P-MI-009 | Asegurar que un movimiento de Salida por venta corresponda a un consumo de venta valido y use su mismo TipoMovimiento. | El consumo de venta asociado no es valido. | Rechazar el registro e informar que el consumo de venta no existe. |
|  |  |  |  |  |  |  | P-MI-016 | Asegurar que las salidas por venta se tomen por FIFO: primero del lote mas antiguo con saldo disponible del producto interno, pasando al siguiente lote cuando el saldo no alcance. | No hay lotes con saldo suficiente para cubrir la salida. | Rechazar el registro e informar que no hay existencias suficientes. |
|  |  |  |  |  |  |  | P-MI-003 | Asegurar que el lote sobre el cual se registra el movimiento exista en el sistema. | El lote sobre el cual se registra el movimiento no existe. | Rechazar el registro e informar que el lote no existe. |
|  |  |  |  |  |  |  | P-MI-004 | Asegurar que la cantidad registrada en un movimiento de inventario sea mayor que cero y como maximo 10.000. | La cantidad no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-MI-013 | Asegurar que no exista otro movimiento de inventario con el mismo Codigo de operacion y el mismo Lote. | Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote. | Rechazar el registro e informar la duplicidad. |
|  |  |  |  |  |  |  | P-MI-011 | La Cantidad del movimiento se copia de la operacion que lo genera y queda fija: del DetalleCompra para Compra, del ConsumoVenta para Venta (repartida entre los lotes por FIFO) y del Cambio para Cambio. No se recalcula despues, aunque la receta cambie. | La cantidad no corresponde a la de la operacion que genera el movimiento. | Copiar la cantidad de la operacion de origen y dejarla fija. |
|  |  |  |  |  |  |  | P-MI-017 | Al registrar un movimiento se actualiza el saldo del lote afectado y la cantidad total del inventario del producto interno. | No fue posible actualizar el saldo del lote o el inventario. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Registrar movimientos por cambio de producto | Cuando se registra el cambio de un producto no perecedero, registra la Salida del lote que se devuelve al proveedor y la Entrada del lote de reemplazo, con el TipoMovimiento del cambio. | Datos | Cambio, Lote | Permite identificar el lote proximo a vencer y el registro de Cambio correspondiente al reemplazo. | MovimientoInventario | Salida por cambio y entrada de reemplazo registradas | P-MI-001 | Asegurar que los datos requeridos para registrar un movimiento de inventario sean válidos de acuerdo con el tipo de movimiento, cantidad, fecha y demás información obligatoria. | Los datos requeridos para registrar el movimiento no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-MI-002 | Asegurar que la clase de movimiento sea valida para la operacion: Entrada para compras y para el lote de reemplazo de un cambio; Salida para ventas y para el lote que se devuelve en un cambio. | La clase de movimiento no corresponde a la operacion. | Rechazar el registro e informar la clase de movimiento esperada. |
|  |  |  |  |  |  |  | P-MI-015 | Asegurar que el TipoMovimiento del movimiento exista y pertenezca a una categoria de origen valida (Compra, Venta o Cambio). | El TipoMovimiento no existe o su categoria de origen no es valida. | Rechazar el registro e informar que el codigo de operacion no es valido. |
|  |  |  |  |  |  |  | P-MI-012 | Asegurar que un movimiento de inventario relacionado con un Cambio corresponda a un registro de Cambio valido y existente. | El cambio asociado no existe o no es valido. | Rechazar el registro e informar que el cambio no existe. |
|  |  |  |  |  |  |  | P-MI-014 | Asegurar que un movimiento con categoria de origen Cambio solo se genere para un producto interno no perecedero. | El producto interno del cambio es perecedero. | Rechazar el registro: los perecederos no se cambian, se retiran con SalidaLote. |
|  |  |  |  |  |  |  | P-MI-003 | Asegurar que el lote sobre el cual se registra el movimiento exista en el sistema. | El lote sobre el cual se registra el movimiento no existe. | Rechazar el registro e informar que el lote no existe. |
|  |  |  |  |  |  |  | P-MI-004 | Asegurar que la cantidad registrada en un movimiento de inventario sea mayor que cero y como maximo 10.000. | La cantidad no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-MI-013 | Asegurar que no exista otro movimiento de inventario con el mismo Codigo de operacion y el mismo Lote. | Ya existe un movimiento con el mismo TipoMovimiento sobre el mismo lote. | Rechazar el registro e informar la duplicidad. |
|  |  |  |  |  |  |  | P-MI-011 | La Cantidad del movimiento se copia de la operacion que lo genera y queda fija: del DetalleCompra para Compra, del ConsumoVenta para Venta (repartida entre los lotes por FIFO) y del Cambio para Cambio. No se recalcula despues, aunque la receta cambie. | La cantidad no corresponde a la de la operacion que genera el movimiento. | Copiar la cantidad de la operacion de origen y dejarla fija. |
|  |  |  |  |  |  |  | P-MI-017 | Al registrar un movimiento se actualiza el saldo del lote afectado y la cantidad total del inventario del producto interno. | No fue posible actualizar el saldo del lote o el inventario. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Consultar movimientos de un lote | Permite consultar las entradas y salidas registradas sobre un lote, para conocer la trazabilidad de sus existencias. | Id | UUID | Identificador del lote. | Lista de MovimientoInventario | Entradas y salidas del lote ordenadas por fecha. | P-MI-003 | Asegurar que el lote sobre el cual se registra el movimiento exista en el sistema. | El lote no existe. | Informar que el lote no existe. |
|  |  |  |  |  |  |  | P-MI-005 | El retiro de producto de un lote por vencimiento no es un movimiento de inventario: se registra como SalidaLote, que descuenta el saldo del lote. | Se buscan retiros por vencimiento entre los movimientos de inventario. | Indicar que los retiros por vencimiento se consultan en SalidaLote. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Sistema | Registrar entrada de inventario por compra | Registrar el ingreso de las existencias recibidas como consecuencia de una compra válida, asociando la entrada con el lote correspondiente. | MovimientoInventario | DetalleCompra | Producto adquirido, cantidad recibida, unidad de medida, compra, detalle de compra y lote sobre el cual se registra la entrada. | P-MI-001 |  | Entrada de inventario registrada |  |
|  |  |  |  |  |  | P-MI-002 |  |  |  |
|  |  |  |  |  |  | P-MI-015 |  |  |  |
|  |  |  |  |  |  | P-MI-008 |  |  |  |
|  |  |  |  |  |  | P-MI-003 |  |  |  |
|  |  |  |  |  |  | P-MI-004 |  |  |  |
|  |  |  |  |  |  | P-MI-013 |  |  |  |
|  |  |  |  |  |  | P-MI-011 |  |  |  |
|  |  |  |  |  |  | P-MI-017 |  |  |  |
| Sistema | Registrar salida de inventario por venta | Registra la salida de los productos internos consumidos en una venta confirmada, descontandolos de los lotes por FIFO. | MovimientoInventario | ConsumoVenta, Lote | Consumos de la venta y lotes disponibles del producto interno. | P-MI-001 |  | Salida de inventario por venta registrada |  |
|  |  |  |  |  |  | P-MI-002 |  |  |  |
|  |  |  |  |  |  | P-MI-015 |  |  |  |
|  |  |  |  |  |  | P-MI-009 |  |  |  |
|  |  |  |  |  |  | P-MI-016 |  |  |  |
|  |  |  |  |  |  | P-MI-003 |  |  |  |
|  |  |  |  |  |  | P-MI-004 |  |  |  |
|  |  |  |  |  |  | P-MI-013 |  |  |  |
|  |  |  |  |  |  | P-MI-011 |  |  |  |
|  |  |  |  |  |  | P-MI-017 |  |  |  |
| Sistema | Registrar movimientos por cambio de producto | Cuando se registra el cambio de un producto no perecedero, registra la Salida del lote que se devuelve al proveedor y la Entrada del lote de reemplazo, con el TipoMovimiento del cambio. | MovimientoInventario | Cambio, Lote | Permite identificar el lote proximo a vencer y el registro de Cambio correspondiente al reemplazo. | P-MI-001 |  | Salida por cambio y entrada de reemplazo registradas |  |
|  |  |  |  |  |  | P-MI-002 |  |  |  |
|  |  |  |  |  |  | P-MI-015 |  |  |  |
|  |  |  |  |  |  | P-MI-012 |  |  |  |
|  |  |  |  |  |  | P-MI-014 |  |  |  |
|  |  |  |  |  |  | P-MI-003 |  |  |  |
|  |  |  |  |  |  | P-MI-004 |  |  |  |
|  |  |  |  |  |  | P-MI-013 |  |  |  |
|  |  |  |  |  |  | P-MI-011 |  |  |  |
|  |  |  |  |  |  | P-MI-017 |  |  |  |
| Administrador | Consultar movimientos de un lote | Permite consultar las entradas y salidas registradas sobre un lote, para conocer la trazabilidad de sus existencias. |  | Movimientos registrados | Lote del que se desean conocer los movimientos. | P-MI-003 |  | Movimientos de lote consultados |  |
|  |  |  |  |  |  | P-MI-005 |  |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-MI-001 | Asegurar que los datos requeridos para registrar un movimiento de inventario sean válidos de acuerdo con el tipo de movimiento, cantidad, fecha y demás información obligatoria. | MovimientoInventario |  |
| P-MI-002 | Asegurar que la clase de movimiento sea valida para la operacion: Entrada para compras y para el lote de reemplazo de un cambio; Salida para ventas y para el lote que se devuelve en un cambio. | ClaseMovimiento | P-CM-001 |
| P-MI-003 | Asegurar que el lote sobre el cual se registra el movimiento exista en el sistema. | Lote | P-LOT-008 |
| P-MI-004 | Asegurar que la cantidad registrada en un movimiento de inventario sea mayor que cero y como maximo 10.000. | MovimientoInventario |  |
| P-MI-005 | El retiro de producto de un lote por vencimiento no es un movimiento de inventario: se registra como SalidaLote, que descuenta el saldo del lote. | SalidaLote | P-SL-001 |
| P-MI-006 | Asegurar que un movimiento de inventario registrado no pueda modificarse o eliminarse de manera que se pierda la trazabilidad de las existencias. | MovimientoInventario |  |
| P-MI-007 | Asegurar que los movimientos de inventario mantengan la trazabilidad del lote sobre el cual se realizó la operación. | Lote | P-LOT-008 |
| P-MI-008 | Asegurar que un movimiento de inventario relacionado con una compra corresponda a un detalle de compra valido y use su mismo TipoMovimiento. | DetalleCompra | P-DC-010 |
| P-MI-009 | Asegurar que un movimiento de Salida por venta corresponda a un consumo de venta valido y use su mismo TipoMovimiento. | ConsumoVenta | P-CV-001 |
| P-MI-010 | Asegurar que el movimiento de inventario exista con el identificador indicado. | MovimientoInventario |  |
| P-MI-011 | La Cantidad del movimiento se copia de la operacion que lo genera y queda fija: del DetalleCompra para Compra, del ConsumoVenta para Venta (repartida entre los lotes por FIFO) y del Cambio para Cambio. No se recalcula despues, aunque la receta cambie. | MovimientoInventario |  |
| P-MI-012 | Asegurar que un movimiento de inventario relacionado con un Cambio corresponda a un registro de Cambio valido y existente. | Cambio | P-CAM-008 |
| P-MI-013 | Asegurar que no exista otro movimiento de inventario con el mismo Codigo de operacion y el mismo Lote. | MovimientoInventario |  |
| P-MI-014 | Asegurar que un movimiento con categoria de origen Cambio solo se genere para un producto interno no perecedero. | MovimientoInventario | P-CAM-003 |
| P-MI-015 | Asegurar que el TipoMovimiento del movimiento exista y pertenezca a una categoria de origen valida (Compra, Venta o Cambio). | TipoMovimiento | P-TMV-003 |
| P-MI-016 | Asegurar que las salidas por venta se tomen por FIFO: primero del lote mas antiguo con saldo disponible del producto interno, pasando al siguiente lote cuando el saldo no alcance. | Lote |  |
| P-MI-017 | Al registrar un movimiento se actualiza el saldo del lote afectado y la cantidad total del inventario del producto interno. | Lote | P-LOT-007 |
