# Cambio

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Cambio](../../02-diagrama-clases/clases/Cambio.md) · [Entidad MER: Cambio](../../03-modelo-entidad-relacion/entidades/Cambio.md)

> Es cuando se retira un insumo que ya casi se vence y se cambia por otro que sí sirve, para que la pizzería siempre tenga insumos en buen estado.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada cambio registrado |  |  |
| ProductoCambio | Producto interno que se cambia (siempre un producto no perecedero) | ProductoInterno | Si |
| Cantidad | Cantidad del producto que entra o sale por el cambio | Decimal Europeo (4) | Si |
| UnidadMedida | Unidad de medida en la que se registra la cantidad del cambio, debe ser valida para el tipo de producto | UnidadMedida | Si |
| FechaVencimiento | Fecha de vencimiento del producto que entra por el cambio | Fecha | Si |
| FechaCambio | Fecha en la que se realiza el cambio | Fecha | Si |
| TipoMovimiento | Codigo de la operacion (TipoMovimiento) del cambio; con el se rastrean los movimientos que genero el cambio | TipoMovimiento | Si |

### Datos de muestra

_2 registros de ejemplo._

| Id | ProductoCambio | Cantidad | UnidadMedida | FechaVencimiento | FechaCambio | TipoMovimiento | Combinacion unica 1 | Combinacion unica 2 |
|---|---|---|---|---|---|---|---|---|
| 1 | Pepsi 400 ml | 24 | und | 01/12/2026 | 09/09/2026 | 200 | Pepsi 400 ml 09/09/2026 | 200 |
| 2 | Aceite de oliva | 5 | l | 15/10/2026 | 09/09/2026 | 201 | Aceite de oliva 09/09/2026 | 201 |

## Caracterización

### Atributos

| Nombre atributo | Id | ProductoCambio | Cantidad | UnidadMedida | FechaVencimiento | FechaCambio | TipoMovimiento |
|---|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada cambio registrado | Producto interno que se cambia (siempre un producto no perecedero) | Cantidad del producto que entra o sale por el cambio | Unidad de medida en la que se registra la cantidad del cambio, debe ser valida para el tipo de producto | Fecha de vencimiento del producto que entra por el cambio | Fecha en la que se realiza el cambio | Codigo de la operacion (TipoMovimiento) del cambio; con el se rastrean los movimientos que genero el cambio |
| Identificador del objeto de dominio | Si | No | No | No | No | No | No |
| Tipo de dato | Identificador único universal | ProductoInterno | Decimal Europeo | UnidadMedida | Fecha | Fecha | TipoMovimiento |
| Obligatorio | Si | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |  |
| Precisión (si aplica) |  |  | 4 |  |  |  |  |
| Rendondeo (si aplica) |  |  | A la unidad (0) |  |  |  |  |
| Rango inicial (si aplica) |  |  | &gt;0 |  |  |  |  |
| Rango final (si aplica) |  |  | 10000 |  |  |  |  |
| Formato (si aplica) |  |  |  |  | dd/mm/yyyy | dd/mm/yyyy |  |
| Reglas especiales (si aplica) |  | Tiene que ser un producto interno que ya exista y que no sea perecedero: solo los productos no perecederos se cambian; los perecederos simplemente se desechan y no generan un registro de Cambio. | La cantidad debe ser mayor a cero y como maximo 10.000. | Tiene que ser una unidad de medida que ya exista y que sea compatible con el tipo de producto que se esta cambiando. | Obligatoria (a diferencia de DetalleCompra, donde es opcional): como Cambio solo aplica a productos no perecederos, siempre debe traer un dato real. Debe ser posterior a la FechaCambio. | Fecha en la que se realiza el cambio. | Cada cambio genera su propio TipoMovimiento (codigo de operacion), que no se puede repetir; con el se registran los movimientos de inventario del cambio. |
| Dato calculado | No | No | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |  |  |
| Auto generado | Si | No | No | No | No | No | Si |
| Registrar cambio de producto | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido | No Requerido |
| Consultar cambio | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos cambios con el mismo ProductoCambio y la misma FechaCambio. | ProductoCambio |
|  |  | FechaCambio |
| Combinación única 2 | No pueden existir dos cambios con el mismo TipoMovimiento; cada cambio tiene su propio codigo de operacion. | TipoMovimiento |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar cambio de producto | Registrar el reemplazo de un producto proximo a vencer por uno nuevo, en respuesta a la alerta de vencimiento emitida por Lote, generando la salida del lote saliente y la entrada del nuevo lote en el inventario. | Datos | Cambio | Informacion necesaria para registrar el cambio (producto, cantidad, unidad de medida, fecha de vencimiento del producto nuevo y fecha del cambio). | Cambio | Cambio registrado | P-CAM-001 | Asegurar que los datos requeridos para registrar un cambio sean validos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar el cambio no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-CAM-002 | Asegurar que el producto interno asociado al cambio exista en el sistema. | El producto interno asociado al cambio no existe en el sistema. | Rechazar el registro e informar que el producto interno no existe. |
|  |  |  |  |  |  |  | P-CAM-003 | Asegurar que el producto interno asociado al cambio sea no perecedero; un producto perecedero no se cambia con el proveedor, se retira del lote con SalidaLote. | El producto interno asociado al cambio es perecedero. | Rechazar el registro e informar que un producto perecedero no se cambia, se desecha. |
|  |  |  |  |  |  |  | P-CAM-004 | Asegurar que la cantidad registrada en el cambio sea mayor que cero y como maximo 10.000. | La cantidad no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-CAM-005 | Asegurar que la unidad de medida utilizada para expresar la cantidad del cambio sea valida. | La unidad de medida utilizada para el cambio no es valida. | Rechazar el registro e informar que la unidad de medida no es valida. |
|  |  |  |  |  |  |  | P-CAM-006 | Asegurar que el producto de reemplazo que entra por el cambio no llegue ya vencido: su fecha de vencimiento tiene que ser posterior a la fecha en la que se registra el cambio, es decir, el dia en que efectivamente llega y se ingresa. | La fecha de vencimiento del producto de reemplazo no es posterior a la fecha del cambio. | Rechazar el registro e informar que el producto de reemplazo llegaria ya vencido. |
|  |  |  |  |  |  |  | P-CAM-007 | Asegurar que no exista otro cambio con el mismo ProductoCambio y la misma FechaCambio. | Ya existe otro cambio registrado con el mismo producto y la misma fecha de cambio. | Rechazar el registro e informar que ya existe un cambio igual registrado. |
|  |  |  |  |  |  |  | P-CAM-009 | El registro de Cambio se realiza en respuesta a una alerta de vencimiento proximo emitida por Lote. | El cambio se esta registrando sin que exista una alerta de vencimiento proximo emitida por Lote para el producto interno indicado. | Rechazar el registro e informar que el cambio debe originarse a partir de una alerta de vencimiento. |
|  |  |  |  |  |  |  | P-CAM-010 | Al registrar un cambio se genera su propio TipoMovimiento de categoria Cambio, que no se repite. | No fue posible generar el TipoMovimiento del cambio. | Rechazar el registro y no generar movimientos. |
|  |  |  |  |  |  |  | P-CAM-011 | Al registrar un cambio se registra la Salida del lote que se devuelve al proveedor y se crea un nuevo lote con el producto de reemplazo y su Entrada, ambos con el TipoMovimiento del cambio. | No fue posible registrar la salida del lote o la entrada del reemplazo. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Consultar cambio | Permite consultar un cambio de producto registrado, con su producto, cantidad y fechas. | Id | UUID | Identificador del cambio. | Cambio | Cambio con su producto, cantidad y fechas. | P-CAM-008 | Asegurar que el cambio exista con el identificador indicado. | No existe un cambio con el identificador indicado. | Informar que el cambio no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar cambio de producto | Registrar el reemplazo de un producto proximo a vencer por uno nuevo, en respuesta a la alerta de vencimiento emitida por Lote, generando la salida del lote saliente y la entrada del nuevo lote en el inventario. | Cambio | Informacion del cambio | Producto que se cambia, cantidad, unidad de medida, fecha de vencimiento del producto nuevo y fecha en la que se realiza el cambio. | P-CAM-001 |  | Cambio registrado |  |
|  |  |  |  |  |  | P-CAM-002 |  |  |  |
|  |  |  |  |  |  | P-CAM-003 |  |  |  |
|  |  |  |  |  |  | P-CAM-004 |  |  |  |
|  |  |  |  |  |  | P-CAM-005 |  |  |  |
|  |  |  |  |  |  | P-CAM-006 |  |  |  |
|  |  |  |  |  |  | P-CAM-007 |  |  |  |
|  |  |  |  |  |  | P-CAM-009 |  |  |  |
|  |  |  |  |  |  | P-CAM-010 |  |  |  |
|  |  |  |  |  |  | P-CAM-011 |  |  |  |
| Administrador | Consultar cambio | Permite consultar un cambio de producto registrado, con su producto, cantidad y fechas. |  | Cambios registrados | Identificador del cambio que se desea consultar. | P-CAM-008 |  | Cambio consultado |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-CAM-001 | Asegurar que los datos requeridos para registrar un cambio sean validos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Cambio |  |
| P-CAM-002 | Asegurar que el producto interno asociado al cambio exista en el sistema. | ProductoInterno | P-PI-007 |
| P-CAM-003 | Asegurar que el producto interno asociado al cambio sea no perecedero; un producto perecedero no se cambia con el proveedor, se retira del lote con SalidaLote. | ProductoInterno | P-PI-003 |
| P-CAM-004 | Asegurar que la cantidad registrada en el cambio sea mayor que cero y como maximo 10.000. | Cambio |  |
| P-CAM-005 | Asegurar que la unidad de medida utilizada para expresar la cantidad del cambio sea valida. | UnidadMedida | P-UM-002 |
| P-CAM-006 | Asegurar que el producto de reemplazo que entra por el cambio no llegue ya vencido: su fecha de vencimiento tiene que ser posterior a la fecha en la que se registra el cambio, es decir, el dia en que efectivamente llega y se ingresa. | Cambio |  |
| P-CAM-007 | Asegurar que no exista otro cambio con el mismo ProductoCambio y la misma FechaCambio. | Cambio |  |
| P-CAM-008 | Asegurar que el cambio exista con el identificador indicado. | Cambio |  |
| P-CAM-009 | El registro de Cambio se realiza en respuesta a una alerta de vencimiento proximo emitida por Lote. | Lote | P-LOT-011 |
| P-CAM-010 | Al registrar un cambio se genera su propio TipoMovimiento de categoria Cambio, que no se repite. | TipoMovimiento | P-TMV-001 |
| P-CAM-011 | Al registrar un cambio se registra la Salida del lote que se devuelve al proveedor y se crea un nuevo lote con el producto de reemplazo y su Entrada, ambos con el TipoMovimiento del cambio. | MovimientoInventario | P-MI-012 |
