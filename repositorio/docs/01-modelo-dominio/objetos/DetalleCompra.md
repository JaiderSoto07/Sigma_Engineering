# DetalleCompra

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: DetalleCompra](../../02-diagrama-clases/clases/DetalleCompra.md) · [Entidad MER: DetalleCompra](../../03-modelo-entidad-relacion/entidades/DetalleCompra.md)

> Es cada uno de los productos o insumos que se compraron en una compra, con la cantidad de cada uno.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada detalle de compra |  |  |
| ProductoInterno | Nombre del producto Interno | Producto interno | Si |
| Cantidad | Cantidad que se compro de un producto determinado | Decimal Europeo (4) | Si |
| UnidadMedida | Se ingresa la  unidad de medida en la que se hizo la compra , esta unidad de medida debe ser valida para el tipo de dato , por ejemplo si el producto es aceite de oliva , el va a ser de tipo volumen entonces puede estar en ml o l | Unidad de medida | Si |
| PrecioCompra | Valor monetario de cada producto | Decimal Europeo(2) | Si |
| FechaVencimiento | Se ingresa la fecha de vencimiento del producto , en caso de que la traiga de lo contrario hay que calcular una vida util | Fecha | No |
| Compra | Compra a la que pertenece este detalle | Compra | Si |
| TipoMovimiento | Codigo de la operacion (TipoMovimiento) propio de este detalle de compra; es el mismo que lleva su movimiento de entrada | TipoMovimiento | Si |

### Datos de muestra

_14 registros de ejemplo._

| Id | ProductoInterno | Cantidad | UnidadMedida | PrecioCompra | FechaVencimiento | Compra | TipoMovimiento | Combinacion unica 1 | Combinacion unica 2 |
|---|---|---|---|---|---|---|---|---|---|
| 1 | Queso | 10200 | g | 255000 | 01/01/1000 | Colanta FE-1023 | 99 | Colanta FE-1023 Queso 01/01/1000 | 99 |
| 2 | Albahaca | 10300 | g | 300000 | 20/01/2027 | Colanta FE-1023 | 100 | Colanta FE-1023 Albahaca 20/01/2027 | 100 |
| 3 | Peperoni | 12.5 | kg | 137500 | 01/01/1000 | Colanta FE-1023 | 101 | Colanta FE-1023 Peperoni 01/01/1000 | 101 |
| 4 | Aceite de oliva | 15200 | ml | 456000 | 04/02/2027 | Harinera del valle FV-4587 | 102 | Harinera del valle FV-4587 Aceite de oliva 04/02/2027 | 102 |
| 5 | Aceite de oliva | 3000 | ml | 93000 | 05/02/2027 | Harinera del valle FV-4587 | 103 | Harinera del valle FV-4587 Aceite de oliva 05/02/2027 | 103 |
| 6 | Masa de Pizza | 200 | g | 2000 | 02/06/2027 | Harinera del valle FV-4587 | 104 | Harinera del valle FV-4587 Masa de Pizza 02/06/2027 | 104 |
| 7 | Pepsi 400 ml | 24 | und | 3600 | 20/09/2026 | Postobon PO-9012 | 105 | Postobon PO-9012 Pepsi 400 ml 20/09/2026 | 105 |
| 8 | Masa de Pizza | 12000 | g | 126000 | 15/09/2027 | Harinera del valle FV-5102 | 106 | Harinera del valle FV-5102 Masa de Pizza 15/09/2027 | 106 |
| 9 | Queso | 150 | g | 3750 | 01/01/1000 | Colanta FE-1102 | 107 | Colanta FE-1102 Queso 01/01/1000 | 107 |
| 10 | Salsa de Tomate | 10500 | g | 105000 | 15/06/2027 | Central de Abastos CA-2201 | 108 | Central de Abastos CA-2201 Salsa de Tomate 15/06/2027 | 108 |
| 11 | Queso | 300 | g | 7500 | 01/01/1000 | Colanta FE-1250 | 109 | Colanta FE-1250 Queso 01/01/1000 | 109 |
| 12 | Jamón | 1000 | g | 30000 | 01/01/1000 | Central de Abastos CA-2310 | 110 | Central de Abastos CA-2310 Jamón 01/01/1000 | 110 |
| 13 | Piña | 1000 | g | 6000 | 01/01/1000 | Central de Abastos CA-2310 | 111 | Central de Abastos CA-2310 Piña 01/01/1000 | 111 |
| 14 | Queso | 500 | g | 12500 | 01/01/1000 | Colanta FE-1263 | 112 | Colanta FE-1263 Queso 01/01/1000 | 112 |

## Caracterización

### Atributos

| Nombre atributo | Id | ProductoInterno | Cantidad | UnidadMedida | PrecioCompra | FechaVencimiento | Compra | TipoMovimiento |
|---|---|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada detalle de compra | Nombre del producto Interno | Cantidad que se compro de un producto determinado | Se ingresa la  unidad de medida en la que se hizo la compra , esta unidad de medida debe ser valida para el tipo de dato , por ejemplo si el producto es aceite de oliva , el va a ser de tipo volumen entonces puede estar en ml o l | Valor monetario de cada producto | Se ingresa la fecha de vencimiento del producto , en caso de que la traiga de lo contrario hay que calcular una vida util | Compra a la que pertenece este detalle | Codigo de la operacion (TipoMovimiento) propio de este detalle de compra; es el mismo que lleva su movimiento de entrada |
| Identificador del objeto de dominio | Si | No | No | No | No | No | No | No |
| Tipo de dato | Identificador único universal | ProductoInterno | Decimal Europeo | UnidadMedida | Decimal Europeo | Fecha | Compra | TipoMovimiento |
| Obligatorio | Si | Si | Si | Si | Si | No | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |  |  |
| Precisión (si aplica) |  |  | 4 |  | 2 |  |  |  |
| Rendondeo (si aplica) |  |  | A la unidad (0) |  | A la unidad (0) |  |  |  |
| Rango inicial (si aplica) |  |  | &gt;0 |  | &gt;=0 |  |  |  |
| Rango final (si aplica) |  |  | 100000 |  | 5000000 |  |  |  |
| Formato (si aplica) |  |  |  |  |  | dd/mm/yyyy |  |  |
| Reglas especiales (si aplica) |  | Debe corresponder a un ProductoInterno existente . | La cantidad debe ser mayor a cero. | Debe corresponder a una UnidadMedida válida y compatible con el tipo de producto comprado. DetalleCompra permite registrar la cantidad en distintas unidades de medida compatibles; al momento de ingresar al inventario, la cantidad se convierte a una unidad estandar, y es esa cantidad estandar la que almacena y controla el Lote. |  | Solo se ingresa un valor real cuando el producto interno es no perecedero ,si el producto interno es perecedero, no se ingresa ningun valor real y el sistema deja el valor por defecto, ya que su fecha de vencimiento se calcula despues en Lote | Indica a qué compra pertenece este detalle. | Cada detalle de compra genera su propio TipoMovimiento (codigo de operacion), que no se puede repetir; con el se registra la entrada al inventario. |
| Dato calculado | No | No | No | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  | 01/01/1000 |  |  |
| Auto generado | Si | No | No | No | No | No | No | Si |
| Registrar detalle de compra | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido | No Requerido |
| Modificar detalle de compra | Requerido / No Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / No Modificable | No Requerido / No Modificable |
| Eliminar detalle de compra | Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | Combinacion unica de la linea de compra: la compra a la que pertenece, el producto interno y su fecha de vencimiento (si el producto es perecedero, esta ultima siempre sera la misma para todos, por lo que no puede repetirse el mismo producto sin una fecha real que lo distinga) | Compra |
|  |  | ProductoInterno |
|  |  | FechaVencimiento |
| Combinación única 2 | No pueden existir dos detalles de compra con el mismo TipoMovimiento; cada detalle tiene su propio codigo de operacion. | TipoMovimiento |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar detalle de compra | Registrar un producto adquirido dentro de una compra, indicando la cantidad, unidad de medida, precio de compra y, cuando corresponda, su fecha de vencimiento. | Datos | DetalleCompra | Información necesaria para registrar el detalle de la compra. | DetalleCompra | Detalle de compra registrado con la información proporcionada. | P-DC-001 | Asegurar que los datos requeridos para registrar un detalle de compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar el detalle de compra no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-DC-002 | Asegurar que la compra a la que pertenece el detalle de compra exista en el sistema. | La compra a la que pertenece el detalle de compra no existe en el sistema. | Rechazar el registro e informar que la compra no existe. |
|  |  |  |  |  |  |  | P-DC-003 | Asegurar que el producto interno asociado al detalle de compra exista en el sistema. | El producto interno asociado al detalle de compra no existe en el sistema. | Rechazar el registro e informar que el producto interno no existe. |
|  |  |  |  |  |  |  | P-DC-013 | Asegurar que el producto interno del detalle de compra se encuentre activo. | El producto interno se encuentra inactivo. | Rechazar el registro e informar que el producto interno no esta disponible. |
|  |  |  |  |  |  |  | P-DC-004 | Asegurar que la fecha de vencimiento del detalle de compra solo tenga un valor real cuando el producto interno sea no perecedero; si es perecedero, debe quedar en el valor por defecto (01/01/1000), ya que su vencimiento se calcula en Lote. | Se registro una fecha de vencimiento real para un producto interno perecedero, o no se registro para uno no perecedero cuando si debia. | Rechazar el registro e informar la inconsistencia con la fecha de vencimiento. |
|  |  |  |  |  |  |  | P-DC-005 | Asegurar que la cantidad registrada en un detalle de compra sea mayor que cero y como maximo 100.000. | La cantidad no es mayor que cero o supera 100.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DC-012 | Asegurar que el precio de compra sea mayor o igual a cero y como maximo 5.000.000. | El precio de compra es negativo o supera 5.000.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DC-006 | Asegurar que la unidad de medida utilizada para expresar la cantidad del detalle de compra sea válida. | La unidad de medida utilizada para el detalle de compra no es valida. | Rechazar el registro e informar que la unidad de medida no es valida. |
|  |  |  |  |  |  |  | P-DC-009 | Asegurar que un mismo producto interno no se repita dentro de una misma compra, salvo que tenga una fecha de vencimiento real distinta (aplicable unicamente a productos no perecederos, ya que un perecedero nunca trae fecha de vencimiento propia). | El mismo producto interno ya esta registrado dentro de la misma compra con la misma fecha de vencimiento. | Rechazar el registro e informar que el producto interno ya esta registrado en esa compra. |
|  |  |  |  |  |  |  | P-DC-014 | Al registrar un detalle de compra se genera su propio TipoMovimiento de categoria Compra, que no se repite. | No fue posible generar el TipoMovimiento del detalle. | Rechazar el registro y no crear el lote ni el movimiento. |
|  |  |  |  |  |  |  | P-DC-015 | Al registrar un detalle de compra se crea un nuevo Lote del producto interno con la cantidad comprada (en la unidad del inventario) y su numero de lote consecutivo. Su fecha de vencimiento es la del detalle si el producto no es perecedero, o la fecha de compra mas la vida util si es perecedero. | No fue posible crear el lote del producto interno. | Rechazar el registro completo para no dejar el inventario inconsistente. |
|  |  |  |  |  |  |  | P-DC-016 | Al registrar un detalle de compra se registra el movimiento de inventario de Entrada sobre el lote creado y se actualiza la cantidad total del inventario del producto interno. | No fue posible registrar la entrada de inventario. | Rechazar el registro completo para no dejar el inventario inconsistente. |
|  |  |  |  |  |  |  | P-DC-017 | Al registrar, modificar o eliminar un detalle de compra se recalcula el total de la compra. | El total recalculado de la compra supera 5.000.000. | Rechazar el registro e informar que la compra supera el total permitido. |
| Modificar detalle de compra | Actualizar la información de un detalle de compra existente sin comprometer la trazabilidad de los movimientos de inventario ya generados. | Datos | DetalleCompra | Identificador del detalle y datos que se desean modificar. | DetalleCompra | Detalle de compra actualizado con la información proporcionada. | P-DC-007 | Asegurar que el detalle de compra que se desea modificar se encuentre registrado en el sistema. | El detalle de compra que se desea modificar no existe. | Rechazar la modificación e informar que el detalle de compra no existe. |
|  |  |  |  |  |  |  | P-DC-001 | Asegurar que los datos requeridos para registrar un detalle de compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los nuevos datos del detalle de compra no son validos. | Rechazar la modificacion e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-DC-005 | Asegurar que la cantidad registrada en un detalle de compra sea mayor que cero y como maximo 100.000. | La nueva cantidad no es mayor que cero o supera 100.000. | Rechazar la modificacion e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DC-012 | Asegurar que el precio de compra sea mayor o igual a cero y como maximo 5.000.000. | El nuevo precio de compra es negativo o supera 5.000.000. | Rechazar la modificacion e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DC-017 | Al registrar, modificar o eliminar un detalle de compra se recalcula el total de la compra. | El total recalculado de la compra supera 5.000.000. | Rechazar la modificacion e informar que la compra supera el total permitido. |
| Eliminar detalle de compra | Permite eliminar un detalle de compra que no haya generado un lote asociado que deba conservarse para mantener la trazabilidad. | Id | UUID | Identificador del detalle de compra que se desea eliminar. | DetalleCompra | Detalle de compra eliminado correctamente. | P-DC-008 | Asegurar que el detalle de compra que se desea eliminar se encuentre registrado en el sistema. | El detalle de compra que se desea eliminar no existe. | Rechazar la eliminacion e informar que el detalle de compra no existe. |
|  |  |  |  |  |  |  | P-DC-011 | Asegurar que un detalle de compra no pueda eliminarse cuando ya haya generado un lote asociado que deba conservarse para mantener la trazabilidad. | El detalle de compra ya genero un lote asociado que debe conservarse. | Rechazar la eliminacion e informar que el detalle de compra tiene un lote asociado. |
|  |  |  |  |  |  |  | P-DC-017 | Al registrar, modificar o eliminar un detalle de compra se recalcula el total de la compra. | No fue posible recalcular el total de la compra. | Rechazar la eliminacion para no dejar el total inconsistente. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar detalle de compra | Registrar un producto adquirido dentro de una compra, indicando la cantidad, unidad de medida, precio de compra y, cuando corresponda, su fecha de vencimiento. | DetalleCompra | Información del detalle de compra | Producto adquirido, cantidad, unidad de medida, precio de compra, fecha de vencimiento y compra a la que pertenece. | P-DC-001 |  | Detalle de compra registrado |  |
|  |  |  |  |  |  | P-DC-002 |  |  |  |
|  |  |  |  |  |  | P-DC-003 |  |  |  |
|  |  |  |  |  |  | P-DC-013 |  |  |  |
|  |  |  |  |  |  | P-DC-004 |  |  |  |
|  |  |  |  |  |  | P-DC-005 |  |  |  |
|  |  |  |  |  |  | P-DC-012 |  |  |  |
|  |  |  |  |  |  | P-DC-006 |  |  |  |
|  |  |  |  |  |  | P-DC-009 |  |  |  |
|  |  |  |  |  |  | P-DC-014 |  |  |  |
|  |  |  |  |  |  | P-DC-015 |  |  |  |
|  |  |  |  |  |  | P-DC-016 |  |  |  |
|  |  |  |  |  |  | P-DC-017 |  |  |  |
| Administrador | Modificar detalle de compra | Actualizar la información de un detalle de compra existente sin comprometer la trazabilidad de los movimientos de inventario ya generados. | DetalleCompra | Información del detalle de compra | Identificador del detalle y nuevos datos necesarios para realizar la modificación. | P-DC-007 |  | Detalle de compra modificado |  |
|  |  |  |  |  |  | P-DC-001 |  |  |  |
|  |  |  |  |  |  | P-DC-005 |  |  |  |
|  |  |  |  |  |  | P-DC-012 |  |  |  |
|  |  |  |  |  |  | P-DC-017 |  |  |  |
| Administrador | Eliminar detalle de compra | Permite eliminar un detalle de compra que no haya generado un lote asociado que deba conservarse para mantener la trazabilidad. | DetalleCompra | Información del detalle de compra | Permite identificar el detalle de compra existente antes de eliminarlo. | P-DC-008 |  | Detalle de compra eliminado |  |
|  |  |  |  |  |  | P-DC-011 |  |  |  |
|  |  |  |  |  |  | P-DC-017 |  |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-DC-001 | Asegurar que los datos requeridos para registrar un detalle de compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | DetalleCompra |  |
| P-DC-002 | Asegurar que la compra a la que pertenece el detalle de compra exista en el sistema. | Compra | P-COM-006 |
| P-DC-003 | Asegurar que el producto interno asociado al detalle de compra exista en el sistema. | ProductoInterno | P-PI-007 |
| P-DC-004 | Asegurar que la fecha de vencimiento del detalle de compra solo tenga un valor real cuando el producto interno sea no perecedero; si es perecedero, debe quedar en el valor por defecto (01/01/1000), ya que su vencimiento se calcula en Lote. | DetalleCompra |  |
| P-DC-005 | Asegurar que la cantidad registrada en un detalle de compra sea mayor que cero y como maximo 100.000. | DetalleCompra |  |
| P-DC-006 | Asegurar que la unidad de medida utilizada para expresar la cantidad del detalle de compra sea válida. | UnidadMedida | P-UM-002 |
| P-DC-007 | Asegurar que el detalle de compra que se desea modificar se encuentre registrado en el sistema. | DetalleCompra |  |
| P-DC-008 | Asegurar que el detalle de compra que se desea eliminar se encuentre registrado en el sistema. | DetalleCompra |  |
| P-DC-009 | Asegurar que un mismo producto interno no se repita dentro de una misma compra, salvo que tenga una fecha de vencimiento real distinta (aplicable unicamente a productos no perecederos, ya que un perecedero nunca trae fecha de vencimiento propia). | DetalleCompra |  |
| P-DC-010 | Asegurar que el detalle de compra exista con el identificador indicado | DetalleCompra |  |
| P-DC-011 | Asegurar que un detalle de compra no pueda eliminarse cuando ya haya generado un lote asociado que deba conservarse para mantener la trazabilidad. | Lote | P-LOT-008 |
| P-DC-012 | Asegurar que el precio de compra sea mayor o igual a cero y como maximo 5.000.000. | DetalleCompra |  |
| P-DC-013 | Asegurar que el producto interno del detalle de compra se encuentre activo. | ProductoInterno |  |
| P-DC-014 | Al registrar un detalle de compra se genera su propio TipoMovimiento de categoria Compra, que no se repite. | TipoMovimiento | P-TMV-001 |
| P-DC-015 | Al registrar un detalle de compra se crea un nuevo Lote del producto interno con la cantidad comprada (en la unidad del inventario) y su numero de lote consecutivo. Su fecha de vencimiento es la del detalle si el producto no es perecedero, o la fecha de compra mas la vida util si es perecedero. | Lote | P-LOT-001 |
| P-DC-016 | Al registrar un detalle de compra se registra el movimiento de inventario de Entrada sobre el lote creado y se actualiza la cantidad total del inventario del producto interno. | MovimientoInventario | P-MI-001 |
| P-DC-017 | Al registrar, modificar o eliminar un detalle de compra se recalcula el total de la compra. | Compra | P-COM-009 |
