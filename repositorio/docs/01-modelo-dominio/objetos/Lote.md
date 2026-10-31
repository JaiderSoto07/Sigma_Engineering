# Lote

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Lote](../../02-diagrama-clases/clases/Lote.md) · [Entidad MER: Lote](../../03-modelo-entidad-relacion/entidades/Lote.md)

> Es un grupo de unidades de un mismo insumo que llegó en una compra, y que se identifica para saber cuánto hay, cuándo llegó y poder rastrearlo dentro del inventario.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada lote dentro de la pizzeria |  |  |
| FechaVencimiento | Fecha de vencimiento del producto | Fecha | Si |
| Disponible | Indicativo de disponibilidad de un lote | Tipo Logico | Si |
| ProductoInterno | Producto interno al que pertenece el lote | ProductoInterno | Si |
| Cantidad | Cantidad con la que entro el lote | Decimal Europeo (4) | Si |
| Saldo | Cantidad que aun queda disponible de este lote, descontando las salidas registradas en Movimiento y los retiros registrados en SalidaLote | Decimal Europeo (4) | Si |
| UnidadMedidaInventario | Unidad de medida estandar en lote y posteriormente  inventario | UnidadMedida | Si |
| NumeroLote | Diferenciador numerico de cada lote dependiendo de su respectivo producto | Entero | Si |

### Datos de muestra

_16 registros de ejemplo._

| Id | FechaVencimiento | Disponible | ProductoInterno | Cantidad | Saldo | UnidadMedidaInventario | NumeroLote | CombinacionUnica |
|---|---|---|---|---|---|---|---|---|
| 1 | 08/09/2026 | Si | Queso | 10.2 | 10.1 | kg | 1 | 1 Queso |
| 2 | 09/09/2026 | Si | Albahaca | 10.3 | 10.25 | kg | 1 | 1 Albahaca |
| 3 | 10/09/2026 | Si | Peperoni | 12.5 | 12.34 | kg | 1 | 1 Peperoni |
| 4 | 04/02/2027 | Si | Aceite de oliva | 15.2 | 15.198 | l | 1 | 1 Aceite de oliva |
| 5 | 05/02/2027 | Si | Aceite de oliva | 3 | 3 | l | 2 | 2 Aceite de oliva |
| 6 | 02/06/2027 | No | Masa de Pizza | 0.2 | 0 | kg | 1 | 1 Masa de Pizza |
| 7 | 20/09/2026 | Si | Pepsi 400 ml | 24 | 24 | und | 1 | 1 Pepsi 400 ml |
| 8 | 15/09/2027 | Si | Masa de Pizza | 12 | 11.85 | kg | 2 | 2 Masa de Pizza |
| 9 | 08/07/2026 | Si | Queso | 0.15 | 0.15 | kg | 2 | 2 Queso |
| 10 | 15/06/2027 | Si | Salsa de Tomate | 10.5 | 10.2 | kg | 1 | 1 Salsa de Tomate |
| 11 | 01/12/2026 | Si | Pepsi 400 ml | 24 | 24 | und | 2 | 2 Pepsi 400 ml |
| 12 | 15/10/2026 | Si | Aceite de oliva | 5 | 2 | l | 3 | 3 Aceite de oliva |
| 13 | 03/10/2026 | No | Queso | 0.3 | 0 | kg | 3 | 3 Queso |
| 14 | 08/10/2026 | Si | Jamón | 1 | 0.5 | kg | 1 | 1 Jamón |
| 15 | 04/10/2026 | Si | Piña | 1 | 0.42 | kg | 1 | 1 Piña |
| 16 | 07/10/2026 | No | Queso | 0.5 | 0 | kg | 4 | 4 Queso |

## Caracterización

### Atributos

| Nombre atributo | Id | FechaVencimiento | Disponible | ProductoInterno | Cantidad | Saldo | UnidadMedidaInventario | NumeroLote |
|---|---|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada lote dentro de la pizzeria | Fecha de vencimiento del producto | Indicativo de disponibilidad de un lote | Producto interno al que pertenece el lote | Cantidad con la que entro el lote | Cantidad que aun queda disponible de este lote, descontando las salidas registradas en Movimiento y los retiros registrados en SalidaLote | Unidad de medida estandar en lote y posteriormente  inventario | Diferenciador numerico de cada lote dependiendo de su respectivo producto |
| Identificador del objeto de dominio | Si | No | No | No | No | No | No | No |
| Tipo de dato | Identificador único universal | Fecha | Tipo Logico | ProductoInterno | Decimal Europeo | Decimal Europeo | UnidadMedida | Entero |
| Obligatorio | Si | Si | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |  |  |
| Precisión (si aplica) |  |  |  |  | 4 | 4 |  |  |
| Rendondeo (si aplica) |  |  |  |  | A la unidad (0) | A la unidad (0) |  |  |
| Rango inicial (si aplica) |  |  |  |  | &gt;=0 | &gt;=0 |  | &gt;=1 |
| Rango final (si aplica) |  |  |  |  | 10000 | Cantidad del lote |  |  |
| Formato (si aplica) |  | dd/mm/yyyy |  |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Fecha de vencimiento del producto de este lote: si es no perecedero se trae tal cual  del movimiento de origen (compra o cambio); si es perecedero se calcula como fecha de movimiento mas la vida util del producto interno | Si el Saldo es mayor a cero esta disponible de lo contrario no | Se toma del movimiento de inventario que creo este lote, pero unicamente si esa entrada es de Origen Compra o Cambio. | Cantidad con la que entro este lote, es un dato congelado  desde el movimiento de inventario que lo genero, ya convertida a la unidad de medida de inventario | Es la Cantidad del lote menos todas las salidas de inventario que lo han afectado. No puede ser menor que cero ni mayor que la Cantidad del lote. | Se tienen los tipos de unidades en los que van a estar los ingredientes y bebidas, se definio que para el tipo que pertenezca a peso, en unidad de medida inventario se va a manejar en kg, para la que sea de tipo volumen va a estar en litros y las que son en unidades se dejara unicamente asi |  |
| Dato calculado | No | Si (para perecederos) | Si | No | No | Si | Si | Si |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |  |  |  |
| Auto generado | Si | No | No | No | No | No | No | Si |
| Registrar lote | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido |
| Recalcular saldo de lote | No Requerido | No Requerido | No Requerido | No Requerido | Requerido | Requerido | No Requerido | No Requerido |
| Emitir alerta de vencimiento próximo | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar |
| Consultar lote | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos lotes con el mismo NumeroLote para el mismo ProductoInterno. | NumeroLote |
|  |  | ProductoInterno |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar lote | Cuando se registra un detalle de compra o el reemplazo de un cambio, el sistema crea automaticamente el lote del producto interno con su cantidad, saldo, fecha de vencimiento y numero de lote consecutivo. | Datos | MovimientoInventario, ProductoInterno | Movimiento de entrada que origina el lote: producto interno, cantidad, unidad de medida y fecha del movimiento. | Lote | Lote registrado | P-LOT-001 | Asegurar que los datos requeridos para registrar un lote sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato, longitud y rango correspondiente. | Los datos ingresados para registrar el lote no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-LOT-002 | Asegurar que el identificador generado para el lote no esté siendo utilizado por otro lote. | El identificador generado ya esta siendo utilizado por otro lote. | Generar un nuevo identificador hasta garantizar que no exista. |
|  |  |  |  |  |  |  | P-LOT-003 | Asegurar que el producto interno asociado al lote exista y sea valido. | El producto interno asociado al lote no existe o no es valido. | Rechazar el registro e informar que el producto interno no es valido. |
|  |  |  |  |  |  |  | P-LOT-004 | Asegurar que la cantidad inicial registrada para un lote sea mayor que cero y como maximo 10.000. | La cantidad inicial no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-LOT-005 | Asegurar que la unidad de medida asociada a la cantidad del lote sea válida. | La unidad de medida asociada a la cantidad del lote no es valida. | Rechazar el registro e informar que la unidad de medida no es valida. |
|  |  |  |  |  |  |  | P-LOT-006 | Asegurar que el movimiento de inventario que origino el lote exista y sea valido. | El movimiento de inventario que origina el lote no existe o no es valido. | Rechazar el registro e informar que el movimiento de origen no es valido. |
|  |  |  |  |  |  |  | P-LOT-009 | Asegurar que no exista otro lote con el mismo NumeroLote para el mismo ProductoInterno. | Ya existe otro lote con el mismo NumeroLote para el mismo ProductoInterno. | Rechazar el registro e informar que ya existe un lote igual registrado. |
|  |  |  |  |  |  |  | P-LOT-012 | Asegurar que el NumeroLote sea consecutivo por producto interno: el primer lote es 1 y cada nuevo lote del mismo producto interno toma el siguiente numero. | El numero de lote no es el siguiente consecutivo del producto interno. | Asignar el siguiente numero de lote consecutivo. |
|  |  |  |  |  |  |  | P-LOT-010 | Ningun atributo de un lote (FechaVencimiento, ProductoInterno, Cantidad, Saldo, Disponible, UnidadMedidaInventario, NumeroLote) puede ser modificado manualmente; todos se generan o recalculan automaticamente. | Se intento modificar manualmente un atributo que solo se calcula a partir de los movimientos de inventario. | Rechazar la modificacion manual e informar que ese dato se genera automaticamente. |
| Recalcular saldo de lote | Cada vez que se registra una salida por venta o por cambio, o un retiro (SalidaLote), el saldo del lote se recalcula automaticamente. | Datos | MovimientoInventario | Movimiento de Salida que afecta el lote y cantidad consumida. | Lote | Saldo de lote actualizado | P-LOT-007 | Asegurar que el saldo de un lote nunca sea inferior a cero ni superior a su cantidad: el saldo es la cantidad menos las salidas por venta, las salidas por cambio y los retiros (SalidaLote). | El recalculo dejaria el saldo por debajo de cero o por encima de la cantidad. | Rechazar la operacion que origina el recalculo. |
|  |  |  |  |  |  |  | P-LOT-013 | Asegurar que el lote quede disponible mientras su saldo sea mayor que cero y no disponible cuando llegue a cero. | El saldo del lote llego a cero. | Marcar el lote como no disponible. |
| Emitir alerta de vencimiento próximo | Cuando la fecha de vencimiento de un lote está próxima a cumplirse, el sistema emite una alerta para que el administrador registre el Cambio (si el producto no es perecedero) o el Desecho (si es perecedero) correspondiente. | Datos | ProductoInterno | Producto interno del lote, para determinar si es perecedero o no. | Lote | Alerta de vencimiento emitida | P-LOT-011 | Asegurar que se emita una alerta cuando la fecha de vencimiento de un lote este proxima a cumplirse. | El producto interno asociado al lote no es válido, o no se puede determinar si es perecedero. | No emitir la alerta e informar la condición que lo impide. |
| Consultar lote | Permite consultar la información de un lote específico, incluyendo su cantidad, saldo, fecha de vencimiento y disponibilidad. | Id | UUID | Identificador único del lote que se desea consultar. | Lote | Lote consultado | P-LOT-008 | Asegurar que el lote exista con el identificador indicado | No existe un lote con el identificador indicado. | Informar que el lote no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Sistema | Registrar lote | Cuando se registra un detalle de compra o el reemplazo de un cambio, el sistema crea automaticamente el lote del producto interno con su cantidad, saldo, fecha de vencimiento y numero de lote consecutivo. | Lote | MovimientoInventario, ProductoInterno | Permite tomar el producto, la cantidad y la fecha del movimiento de entrada para crear el lote. | P-LOT-001 |  | Lote registrado | ¿Un lote puede crearse de forma manual sin un detalle de compra asociado? |
|  |  |  |  |  |  | P-LOT-002 |  |  |  |
|  |  |  |  |  |  | P-LOT-003 |  |  |  |
|  |  |  |  |  |  | P-LOT-004 |  |  |  |
|  |  |  |  |  |  | P-LOT-005 |  |  |  |
|  |  |  |  |  |  | P-LOT-006 |  |  |  |
|  |  |  |  |  |  | P-LOT-009 |  |  |  |
|  |  |  |  |  |  | P-LOT-012 |  |  |  |
|  |  |  |  |  |  | P-LOT-010 |  |  |  |
| Sistema | Recalcular saldo de lote | Cada vez que se registra una salida por venta o por cambio, o un retiro (SalidaLote), el saldo del lote se recalcula automaticamente. | Lote | MovimientoInventario, SalidaLote | Salidas y retiros que afectan el lote. | P-LOT-007 |  | Saldo de lote actualizado |  |
|  |  |  |  |  |  | P-LOT-013 |  |  |  |
| Sistema | Emitir alerta de vencimiento próximo | Cuando la fecha de vencimiento de un lote está próxima a cumplirse, el sistema emite una alerta para que el administrador registre el Cambio (si el producto no es perecedero) o el Desecho (si es perecedero) correspondiente. | Lote | ProductoInterno | Permite determinar si, al vencerse, el lote debe generar un Cambio o un Desecho, según el producto sea perecedero o no. | P-LOT-011 |  | Alerta de vencimiento emitida |  |
| Administrador | Consultar lote | Permite consultar la información de un lote específico, incluyendo su cantidad, saldo, fecha de vencimiento y disponibilidad. | Lote |  |  | P-LOT-008 |  | Lote consultado |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-LOT-001 | Asegurar que los datos requeridos para registrar un lote sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato, longitud y rango correspondiente. | Lote |  |
| P-LOT-002 | Asegurar que el identificador generado para el lote no esté siendo utilizado por otro lote. | Lote |  |
| P-LOT-003 | Asegurar que el producto interno asociado al lote exista y sea valido. | ProductoInterno | P-PI-007 |
| P-LOT-004 | Asegurar que la cantidad inicial registrada para un lote sea mayor que cero y como maximo 10.000. | MovimientoInventario | P-MI-004 |
| P-LOT-005 | Asegurar que la unidad de medida asociada a la cantidad del lote sea válida. | UnidadMedida | P-UM-002 |
| P-LOT-006 | Asegurar que el movimiento de inventario que origino el lote exista y sea valido. | MovimientoInventario | P-MI-010 |
| P-LOT-007 | Asegurar que el saldo de un lote nunca sea inferior a cero ni superior a su cantidad: el saldo es la cantidad menos las salidas por venta, las salidas por cambio y los retiros (SalidaLote). | Lote |  |
| P-LOT-008 | Asegurar que el lote exista con el identificador indicado | Lote |  |
| P-LOT-009 | Asegurar que no exista otro lote con el mismo NumeroLote para el mismo ProductoInterno. | Lote |  |
| P-LOT-010 | Ningun atributo de un lote (FechaVencimiento, ProductoInterno, Cantidad, Saldo, Disponible, UnidadMedidaInventario, NumeroLote) puede ser modificado manualmente; todos se generan o recalculan automaticamente. | Lote |  |
| P-LOT-011 | Asegurar que se emita una alerta cuando la fecha de vencimiento de un lote este proxima a cumplirse. | Lote |  |
| P-LOT-012 | Asegurar que el NumeroLote sea consecutivo por producto interno: el primer lote es 1 y cada nuevo lote del mismo producto interno toma el siguiente numero. | Lote |  |
| P-LOT-013 | Asegurar que el lote quede disponible mientras su saldo sea mayor que cero y no disponible cuando llegue a cero. | Lote |  |
