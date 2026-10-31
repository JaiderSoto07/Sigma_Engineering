# SalidaLote

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: SalidaLote](../../02-diagrama-clases/clases/SalidaLote.md) · [Entidad MER: SalidaLote](../../03-modelo-entidad-relacion/entidades/SalidaLote.md)

> SalidaLote representa el retiro efectivo de una cantidad de producto perteneciente a un lote, realizado por el usuario después de atender una alerta de proximidad al vencimiento. Atributos: Id, Lote, Cantidad y FechaMovimiento. Relación: Lote 1 — 0..1 SalidaLote (un lote puede tener como máximo un retiro; cada retiro pertenece a un único lote). La alerta no se almacena aquí: se calcula a partir de Lote.FechaVencimiento.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador único de cada retiro realizado sobre un lote. |  |  |
| Lote | Lote específico del cual se retira el producto. | Lote | Si |
| Cantidad | Cantidad de producto que se retira efectivamente del lote. | Decimal Europeo (4) | Si |
| FechaMovimiento | Fecha en la que se realiza efectivamente el retiro del producto del lote por decisión del usuario. | Fecha | Si |

### Datos de muestra

_4 registros de ejemplo._

| Id | Lote | Cantidad | FechaMovimiento | Combinacion unica 1 |
|---|---|---|---|---|
| 1 | 1 Piña | 0.5 | 02/10/2026 | 1 Piña |
| 2 | 4 Queso | 0.3 | 02/10/2026 | 4 Queso |
| 3 | 1 Jamón | 0.4 | 03/10/2026 | 1 Jamón |
| 4 | 3 Aceite de oliva | 3 | 03/10/2026 | 3 Aceite de oliva |

## Caracterización

### Atributos

| Nombre atributo | Id | Lote | Cantidad | FechaMovimiento |
|---|---|---|---|---|
| Descripción atributo | Identificador único de cada retiro realizado sobre un lote. | Lote específico del cual se retira el producto. | Cantidad de producto que se retira efectivamente del lote. | Fecha en la que se realiza efectivamente el retiro del producto del lote por decisión del usuario. |
| Identificador del objeto de dominio | Si | No | No | No |
| Tipo de dato | Identificador único universal | Lote | Decimal Europeo | Fecha |
| Obligatorio | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |
| Precisión (si aplica) |  |  | 4 |  |
| Rendondeo (si aplica) |  |  |  |  |
| Rango inicial (si aplica) |  |  | &gt;0 |  |
| Rango final (si aplica) |  |  | 10000 |  |
| Formato (si aplica) |  |  |  | dd/mm/yyyy |
| Reglas especiales (si aplica) |  | Debe corresponder a un lote existente con saldo disponible. Un lote solo puede tener un retiro. | Debe ser mayor a cero, como maximo 10.000 y no puede superar el saldo del lote. | Fecha en la que se realiza el retiro del producto del lote. |
| Dato calculado | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |
| Auto generado | Si | No | No | No |
| Registrar salida de lote | No Requerido | Requerido | Requerido | Requerido |
| Consultar salida de lote | Requerido | No Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | Un lote solo puede tener un retiro registrado. | Lote |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar salida de lote | Registra el retiro de una cantidad de un lote proximo a vencer, despues de atender la alerta de vencimiento. | Datos | SalidaLote | Lote, cantidad y fecha del retiro. | SalidaLote | Salida de lote registrada. | P-SL-001 | Asegurar que los datos requeridos para registrar la salida de lote sean validos. | Los datos de la salida no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-SL-002 | Asegurar que el lote exista y tenga saldo disponible. | El lote no existe o no tiene saldo disponible. | Rechazar el registro e informar que el lote no esta disponible. |
|  |  |  |  |  |  |  | P-SL-003 | Asegurar que la cantidad a retirar no supere el saldo del lote. | La cantidad a retirar supera el saldo del lote. | Rechazar el registro e informar el saldo disponible. |
|  |  |  |  |  |  |  | P-SL-004 | Asegurar que el lote no tenga otra salida registrada. | El lote ya tiene una salida registrada. | Rechazar el registro e informar que el lote ya fue retirado. |
|  |  |  |  |  |  |  | P-SL-006 | Al registrar una salida de lote se descuenta la cantidad del saldo del lote y de la cantidad total del inventario del producto interno; no genera movimiento de inventario. | No fue posible actualizar el saldo del lote o el inventario. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Consultar salida de lote | Permite consultar un retiro registrado sobre un lote. | Id | UUID | Identificador de la salida de lote. | SalidaLote | Salida de lote con su lote, cantidad y fecha. | P-SL-005 | Asegurar que la salida de lote exista con el identificador indicado. | No existe una salida de lote con el identificador indicado. | Informar que la salida de lote no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar salida de lote | Registra el retiro de una cantidad de un lote proximo a vencer, despues de atender la alerta de vencimiento. | SalidaLote | Lote y alerta de vencimiento | Lote proximo a vencer y cantidad que se va a retirar. | P-SL-001 |  | Salida de lote registrada |  |
|  |  |  |  |  |  | P-SL-002 |  |  |  |
|  |  |  |  |  |  | P-SL-003 |  |  |  |
|  |  |  |  |  |  | P-SL-004 |  |  |  |
|  |  |  |  |  |  | P-SL-006 |  |  |  |
| Administrador | Consultar salida de lote | Permite consultar un retiro registrado sobre un lote. | SalidaLote | Salidas de lote registradas | Identificador de la salida que se desea consultar. | P-SL-005 |  | Salida de lote consultada |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-SL-001 | Asegurar que los datos requeridos para registrar la salida de lote sean validos. | SalidaLote |  |
| P-SL-002 | Asegurar que el lote exista y tenga saldo disponible. | Lote | P-LOT-008 |
| P-SL-003 | Asegurar que la cantidad a retirar no supere el saldo del lote. | Lote |  |
| P-SL-004 | Asegurar que el lote no tenga otra salida registrada. | SalidaLote |  |
| P-SL-005 | Asegurar que la salida de lote exista con el identificador indicado. | SalidaLote |  |
| P-SL-006 | Al registrar una salida de lote se descuenta la cantidad del saldo del lote y de la cantidad total del inventario del producto interno; no genera movimiento de inventario. | Lote | P-LOT-007 |
