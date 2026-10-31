# Venta

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Venta](../../02-diagrama-clases/clases/Venta.md) · [Entidad MER: Venta](../../03-modelo-entidad-relacion/entidades/Venta.md)

> Es cuando un cliente compra productos en la pizzería.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador de cada venta dentro del sistema |  |  |
| Fecha | Fecha en la que se realizo la venta | Fecha | Si |
| Hora | Hora en la que se realizo la venta | Time | Si |
| Factura | Número de la factura de venta. El sistema lo asigna solo en el momento en que la venta se hace | Alfanumerico(max 9 caracteres) | Si |
| Cliente | Indicativo de a que cliente le pertenece la venta | Alfanumerico(max 10 caracteres) | No |
| Total | Total de la venta | Decimal Europeo (2) | Si |

### Datos de muestra

_4 registros de ejemplo._

| Id | Fecha | Hora | Factura | Cliente | Total | CombinacionUnica |
|---|---|---|---|---|---|---|
| 1 | 03/09/2026 | 11:30:00 Pm | FV-000001 | 1036456892 | 25000 | FV-000001 |
| 2 | 03/09/2026 | 11:45:00 Pm | FV-000002 | 1030789345 | 106000 | FV-000002 |
| 3 | 24/07/2026 | 1:00:00 Pm | FV-000003 | 1040875813 | 5000 | FV-000003 |
| 4 | 02/10/2026 | 12:30:00 Pm | FV-000004 | 2222222222 | 35000 | FV-000004 |

## Caracterización

### Atributos

| Nombre atributo | Id | Fecha | Hora | Factura | Cliente | Total |
|---|---|---|---|---|---|---|
| Descripción atributo | Identificador de cada venta dentro del sistema | Fecha en la que se realizo la venta | Hora en la que se realizo la venta | Número de la factura de venta. El sistema lo asigna solo en el momento en que la venta se hace | Indicativo de a que cliente le pertenece la venta | Total de la venta |
| Identificador del objeto de dominio | Si | No | No | No | No | No |
| Tipo de dato | Identificador unico universal | Fecha | Time | Alfanumerico | Alfanumerico | Decimal Europeo |
| Obligatorio | Si | Si | Si | Si | No | Si |
| Longitud mínima (si aplica) |  |  |  | 9 | 1 |  |
| Longitud máxima (si aplica) |  |  |  | 9 | 10 |  |
| Precisión (si aplica) |  |  |  |  |  | 2 |
| Rendondeo (si aplica) |  |  |  |  |  | A la unidad (0) |
| Rango inicial (si aplica) |  |  |  |  |  | &gt;0 |
| Rango final (si aplica) |  |  |  |  |  | 1000000 |
| Formato (si aplica) |  | dd/mm/yyyy | HH:mm:ss | FV-###### |  |  |
| Reglas especiales (si aplica) |  |  | Se registra con precision de segundos para evitar que dos ventas del mismo dia choquen en la combinacion unica. | No lo escribe nadie a mano: el sistema lo asigna en el momento en que la venta se confirma. Formato FV- seguido de 6 digitos (por ejemplo FV-000001) y no se puede repetir. | Documento del cliente. Si no se indica, se registra 2222222222 (consumidor final). | El valor total de una venta corresponde a la suma de los Subtotal de todos los DetalleVenta asociados a la misma. |
| Dato calculado | No | No | No | No | No | Si |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | Si | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | Si | No |
| Dato sensible (si aplica) | No | No | No | No | No | Si |
| Valor por defecto (si aplica) |  |  |  |  | 2222222222 |  |
| Auto generado | Si | Si | Si | Si | No | No |
| Registrar venta | Requerido | Requerido | Requerido | No Requerido | No Requerido | No Requerido |
| Confirmar venta | Requerido / No Modificable | No Requerido / No Modificable | No Requerido / No Modificable | No Requerido / No Modificable | No Requerido / No Modificable | No Requerido / No Modificable |
| Eliminar venta | Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar venta | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos ventas con la misma Fecha y la misma Hora (a precision de segundos). | Fecha |
|  |  | Hora |
| Combinación única 2 | No pueden existir dos ventas con el mismo número de factura. | Factura |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar venta | Permite registrar una venta realizada en el establecimiento, asociando los productos solicitados y la información correspondiente. | Datos | Venta | Información necesaria para registrar la venta. | Venta | Venta registrada con la información proporcionada. | P-VEN-001 | Asegurar que los datos requeridos para registrar una venta sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar la venta no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-VEN-002 | Asegurar que no exista otra venta registrada con la misma Fecha y la misma Hora. | Ya existe otra venta registrada con la misma Fecha y la misma Hora. | Rechazar el registro e informar que ya existe una venta en ese mismo momento. |
|  |  |  |  |  |  |  | P-VEN-011 | Si no se indica el cliente de la venta, se registra 2222222222 (consumidor final). | No se indico el cliente de la venta. | Registrar la venta con el cliente 2222222222 (consumidor final). |
| Confirmar venta | Permite confirmar una venta: se calcula el total, se asigna el numero de factura, se registran los consumos y se descuenta el inventario por FIFO. Una venta confirmada ya no se puede modificar. | Id | UUID | Identificador de la venta que se desea confirmar. | Venta | Venta confirmada, con el consumo de inventario registrado y su número de factura asignado. | P-VEN-007 | Asegurar que la venta exista con el identificador indicado | La venta que se desea confirmar no existe. | Rechazar la confirmacion e informar que la venta no existe. |
|  |  |  |  |  |  |  | P-VEN-009 | Asegurar que una venta confirmada no pueda ser modificada o eliminada. | La venta ya se encuentra confirmada. | Informar que la venta ya fue confirmada y no puede modificarse. |
|  |  |  |  |  |  |  | P-VEN-003 | Asegurar que una venta contenga al menos un detalle de venta antes de ser confirmada. | La venta no contiene ningun detalle de venta. | Rechazar la confirmacion e informar que la venta debe tener al menos un detalle. |
|  |  |  |  |  |  |  | P-VEN-004 | Asegurar que todos los detalles asociados a una venta sean válidos antes de confirmar la operación. | Alguno de los detalles asociados a la venta no es valido. | Rechazar la confirmacion e informar que hay un detalle no valido. |
|  |  |  |  |  |  |  | P-VEN-005 | Asegurar que antes de confirmar una venta exista disponibilidad suficiente para atender los productos que requieren consumo de inventario. | No existe disponibilidad suficiente de inventario para atender los productos que requieren consumo. | Rechazar la confirmacion e informar que no hay disponibilidad suficiente en el inventario. |
|  |  |  |  |  |  |  | P-VEN-006 | Asegurar que el total de la venta corresponda a la suma de los subtotales de sus detalles y no supere 1.000.000. | El total no corresponde a la suma de los subtotales o supera 1.000.000. | Rechazar la confirmacion e informar el total calculado. |
|  |  |  |  |  |  |  | P-VEN-010 | Asegurar que al confirmar una venta se le asigne el siguiente numero de factura del consecutivo (FV- seguido de 6 digitos), sin repetir un numero que ya tenga otra venta. | El número de factura que le corresponde a la venta ya está asignado a otra venta. | Rechazar la confirmación e informar que no se pudo asignar el número de factura. |
|  |  |  |  |  |  |  | P-VEN-012 | Al confirmar una venta se registran los consumos de cada detalle de venta, segun la receta del producto vendido o su producto interno asociado. | No fue posible registrar los consumos de la venta. | Rechazar la confirmacion completa para no dejar el inventario inconsistente. |
|  |  |  |  |  |  |  | P-VEN-013 | Al confirmar una venta, cada producto interno consumido sale del inventario por FIFO: primero del lote mas antiguo con saldo disponible (el de menor numero de lote); se registra un movimiento de Salida por cada lote afectado y se actualiza el inventario. | No hay saldo suficiente en los lotes para descontar los productos internos. | Rechazar la confirmacion e informar que producto interno no tiene existencias suficientes. |
| Eliminar venta | Permite eliminar una venta que no haya sido confirmada y que no tenga detalles de venta asociados que deban conservarse para mantener la trazabilidad. | Id | UUID | Identificador de la venta que se desea eliminar. | Venta | Venta eliminada correctamente. | P-VEN-007 | Asegurar que la venta exista con el identificador indicado | La venta que se desea eliminar no existe. | Rechazar la eliminacion e informar que la venta no existe. |
|  |  |  |  |  |  |  | P-VEN-009 | Asegurar que una venta confirmada no pueda ser modificada o eliminada. | La venta ya se encuentra confirmada. | Rechazar la eliminacion e informar que una venta confirmada no puede eliminarse. |
|  |  |  |  |  |  |  | P-VEN-008 | Asegurar que una venta no pueda eliminarse cuando tenga detalles de venta asociados que deban conservarse para mantener la trazabilidad. | La venta tiene detalles de venta asociados que deben conservarse. | Rechazar la eliminacion e informar que la venta tiene detalles asociados. |
| Consultar venta | Permite consultar una venta con sus detalles, su total y su numero de factura. | Id | UUID | Identificador de la venta. | Venta | Venta con sus detalles y su total. | P-VEN-007 | Asegurar que la venta exista con el identificador indicado | No existe una venta con el identificador indicado. | Informar que la venta no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar venta | Permite registrar una venta realizada en el establecimiento, asociando los productos solicitados y la información correspondiente. | Venta | Productos disponibles, precios vigentes, información del cliente | Permite verificar que los productos puedan ser vendidos y que la información utilizada en la venta sea correcta. | P-VEN-001 |  | Venta registrada |  |
|  |  |  |  |  |  | P-VEN-002 |  |  |  |
|  |  |  |  |  |  | P-VEN-011 |  |  |  |
| Administrador | Confirmar venta | Permite confirmar una venta: se calcula el total, se asigna el numero de factura, se registran los consumos y se descuenta el inventario por FIFO. Una venta confirmada ya no se puede modificar. | Venta | Detalles de venta, disponibilidad de inventario | Permite comprobar que la venta contiene información válida y que puede ser atendida con las existencias disponibles. | P-VEN-007 |  | Venta confirmada |  |
|  |  |  |  |  |  | P-VEN-009 |  |  |  |
|  |  |  |  |  |  | P-VEN-003 |  |  |  |
|  |  |  |  |  |  | P-VEN-004 |  |  |  |
|  |  |  |  |  |  | P-VEN-005 |  |  |  |
|  |  |  |  |  |  | P-VEN-006 |  |  |  |
|  |  |  |  |  |  | P-VEN-010 |  |  |  |
|  |  |  |  |  |  | P-VEN-012 |  |  |  |
|  |  |  |  |  |  | P-VEN-013 |  |  |  |
| Administrador | Eliminar venta | Permite eliminar una venta que no haya sido confirmada y que no tenga detalles de venta asociados que deban conservarse para mantener la trazabilidad. | Venta | Ventas registradas | Permite identificar la venta existente antes de eliminarla. | P-VEN-007 |  | Venta eliminada |  |
|  |  |  |  |  |  | P-VEN-009 |  |  |  |
|  |  |  |  |  |  | P-VEN-008 |  |  |  |
| Administrador | Consultar venta | Permite consultar una venta con sus detalles, su total y su numero de factura. |  | Ventas registradas | Identificador de la venta que se desea consultar. | P-VEN-007 |  | Venta consultada |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-VEN-001 | Asegurar que los datos requeridos para registrar una venta sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Venta |  |
| P-VEN-002 | Asegurar que no exista otra venta registrada con la misma Fecha y la misma Hora. | Venta |  |
| P-VEN-003 | Asegurar que una venta contenga al menos un detalle de venta antes de ser confirmada. | DetalleVenta | P-DV-012 |
| P-VEN-004 | Asegurar que todos los detalles asociados a una venta sean válidos antes de confirmar la operación. | DetalleVenta | P-DV-012 |
| P-VEN-005 | Asegurar que antes de confirmar una venta exista disponibilidad suficiente para atender los productos que requieren consumo de inventario. | Lote | P-LOT-007 |
| P-VEN-006 | Asegurar que el total de la venta corresponda a la suma de los subtotales de sus detalles y no supere 1.000.000. | Venta |  |
| P-VEN-007 | Asegurar que la venta exista con el identificador indicado | Venta |  |
| P-VEN-008 | Asegurar que una venta no pueda eliminarse cuando tenga detalles de venta asociados que deban conservarse para mantener la trazabilidad. | DetalleVenta | P-DV-012 |
| P-VEN-009 | Asegurar que una venta confirmada no pueda ser modificada o eliminada. | Venta |  |
| P-VEN-010 | Asegurar que al confirmar una venta se le asigne el siguiente numero de factura del consecutivo (FV- seguido de 6 digitos), sin repetir un numero que ya tenga otra venta. | Venta |  |
| P-VEN-011 | Si no se indica el cliente de la venta, se registra 2222222222 (consumidor final). | Venta |  |
| P-VEN-012 | Al confirmar una venta se registran los consumos de cada detalle de venta, segun la receta del producto vendido o su producto interno asociado. | ConsumoVenta | P-CV-001 |
| P-VEN-013 | Al confirmar una venta, cada producto interno consumido sale del inventario por FIFO: primero del lote mas antiguo con saldo disponible (el de menor numero de lote); se registra un movimiento de Salida por cada lote afectado y se actualiza el inventario. | MovimientoInventario | P-MI-001 |
