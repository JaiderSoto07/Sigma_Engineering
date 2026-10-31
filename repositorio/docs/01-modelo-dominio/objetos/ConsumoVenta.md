# ConsumoVenta

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: ConsumoVenta](../../02-diagrama-clases/clases/ConsumoVenta.md) · [Entidad MER: ConsumoVenta](../../03-modelo-entidad-relacion/entidades/ConsumoVenta.md)

> Son los productos internos que se deben consumir por cada detalle de venta, según la receta del producto vendido. Cada consumo tiene su propio TipoMovimiento.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada consumo de venta |  |  |
| DetalleVenta | Detalle de venta (renglon de la venta) que origina el consumo | DetalleVenta | Si |
| ProductoInterno | Producto interno que se debe consumir segun la receta del producto vendido | ProductoInterno | Si |
| Cantidad | Cantidad del producto interno que se consume: cantidad vendida por la cantidad de la receta, en la unidad de la receta | Decimal Europeo (4) | Si |
| UnidadMedida | Unidad de medida de la cantidad; es la misma de la receta y queda guardada aunque la receta cambie despues | UnidadMedida | Si |
| TipoMovimiento | Codigo de la operacion (TipoMovimiento) propio de este consumo; es el mismo que lleva su movimiento de salida | TipoMovimiento | Si |

### Datos de muestra

_9 registros de ejemplo._

| Id | DetalleVenta | ProductoInterno | Cantidad | UnidadMedida | TipoMovimiento | Combinacion unica 1 | Combinacion unica 2 |
|---|---|---|---|---|---|---|---|
| 1 | FV-000001  Margarita Grande | Masa de Pizza | 350 | g | 153 | FV-000001  Margarita Grande Masa de Pizza | 153 |
| 2 | FV-000001  Margarita Grande | Salsa de Tomate | 300 | g | 154 | FV-000001  Margarita Grande Salsa de Tomate | 154 |
| 3 | FV-000001  Margarita Grande | Aceite de oliva | 2 | ml | 155 | FV-000001  Margarita Grande Aceite de oliva | 155 |
| 4 | FV-000001  Margarita Grande | Queso | 100 | g | 156 | FV-000001  Margarita Grande Queso | 156 |
| 5 | FV-000001  Margarita Grande | Albahaca | 50 | g | 157 | FV-000001  Margarita Grande Albahaca | 157 |
| 6 | FV-000002  Peperoni Mediana | Peperoni | 160 | g | 158 | FV-000002  Peperoni Mediana Peperoni | 158 |
| 7 | FV-000004  Hawaiana Grande | Queso | 500 | g | 150 | FV-000004  Hawaiana Grande Queso | 150 |
| 8 | FV-000004  Hawaiana Grande | Jamón | 100 | g | 151 | FV-000004  Hawaiana Grande Jamón | 151 |
| 9 | FV-000004  Hawaiana Grande | Piña | 80 | g | 152 | FV-000004  Hawaiana Grande Piña | 152 |

## Caracterización

### Atributos

| Nombre atributo | Id | DetalleVenta | ProductoInterno | Cantidad | UnidadMedida | TipoMovimiento |
|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada consumo de venta | Detalle de venta (renglon de la venta) que origina el consumo | Producto interno que se debe consumir segun la receta del producto vendido | Cantidad del producto interno que se consume: cantidad vendida por la cantidad de la receta, en la unidad de la receta | Unidad de medida de la cantidad; es la misma de la receta y queda guardada aunque la receta cambie despues | Codigo de la operacion (TipoMovimiento) propio de este consumo; es el mismo que lleva su movimiento de salida |
| Identificador del objeto de dominio | Si | No | No | No | No | No |
| Tipo de dato | Identificador único universal | DetalleVenta | ProductoInterno | Decimal Europeo | UnidadMedida | TipoMovimiento |
| Obligatorio | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |
| Precisión (si aplica) |  |  |  | 4 |  |  |
| Rendondeo (si aplica) |  |  |  |  |  |  |
| Rango inicial (si aplica) |  |  |  | &gt;0 |  |  |
| Rango final (si aplica) |  |  |  | 1000000 |  |  |
| Formato (si aplica) |  |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Debe corresponder a un detalle de venta existente. | Se toma de la receta (DetalleReceta) del producto vendido; si el producto es un producto interno (por ejemplo una bebida) se consume el propio producto interno asociado. | Se calcula como la cantidad del ingrediente en la receta multiplicada por la cantidad vendida en el detalle de venta. | Es la misma unidad de la receta (g, ml o und). | Cada consumo genera su propio TipoMovimiento de categoria Venta, que no se puede repetir; con el se registran las salidas de inventario. |
| Dato calculado | No | No | No | Si | Si | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |  |
| Auto generado | Si | No | No | No | No | Si |
| Registrar consumo de venta | No Requerido | Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar consumos de un detalle de venta | No Requerido | Requerido | No Requerido | No Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | Un mismo producto interno no puede consumirse dos veces para el mismo detalle de venta. | DetalleVenta |
|  |  | ProductoInterno |
| Combinación única 2 | No pueden existir dos consumos con el mismo TipoMovimiento; cada consumo tiene su propio codigo de operacion. | TipoMovimiento |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar consumo de venta | Al confirmar una venta, registra por cada detalle de venta los productos internos que se consumen segun la receta del producto vendido. | Datos | DetalleVenta | Detalle de venta confirmado que origina el consumo. | Lista de ConsumoVenta | Consumos registrados para el detalle de venta. | P-CV-001 | Asegurar que el detalle de venta exista. | El detalle de venta no existe. | Rechazar el registro e informar que el detalle de venta no existe. |
|  |  |  |  |  |  |  | P-CV-002 | Asegurar que los productos internos a consumir se obtengan de la receta del producto vendido, o del producto interno asociado cuando el producto se vende directo del inventario. | El producto vendido no tiene receta ni producto interno asociado. | Rechazar el registro e informar que el producto no tiene receta. |
|  |  |  |  |  |  |  | P-CV-003 | Asegurar que no se registre dos veces el mismo producto interno para el mismo detalle de venta. | El producto interno ya fue consumido para ese detalle de venta. | Rechazar el registro e informar la duplicidad. |
|  |  |  |  |  |  |  | P-CV-004 | Asegurar que exista saldo suficiente en inventario para cubrir la cantidad a consumir. | No hay saldo suficiente en inventario. | Rechazar el registro e informar que no hay existencias suficientes. |
|  |  |  |  |  |  |  | P-CV-006 | Al registrar un consumo se genera su propio TipoMovimiento de categoria Venta y se registran las salidas de inventario por FIFO sobre los lotes del producto interno. | No fue posible registrar las salidas de inventario del consumo. | Rechazar el registro completo para no dejar el inventario inconsistente. |
| Consultar consumos de un detalle de venta | Permite consultar los productos internos consumidos por un detalle de venta. | Id | UUID | Identificador del detalle de venta. | Lista de ConsumoVenta | Consumos registrados para el detalle de venta. | P-CV-005 | Asegurar que el detalle de venta consultado exista. | El detalle de venta no existe. | Informar que el detalle de venta no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Sistema | Registrar consumo de venta | Al confirmar una venta, registra por cada detalle de venta los productos internos que se consumen segun la receta del producto vendido. | ConsumoVenta | Detalle de venta y receta | Detalle de venta confirmado y receta del producto vendido. | P-CV-001 |  | Consumo de venta registrado |  |
|  |  |  |  |  |  | P-CV-002 |  |  |  |
|  |  |  |  |  |  | P-CV-003 |  |  |  |
|  |  |  |  |  |  | P-CV-004 |  |  |  |
|  |  |  |  |  |  | P-CV-006 |  |  |  |
| Administrador | Consultar consumos de un detalle de venta | Permite consultar los productos internos consumidos por un detalle de venta. | ConsumoVenta | Consumos registrados | Detalle de venta del que se desean conocer los consumos. | P-CV-005 |  | Consumos de venta consultados |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-CV-001 | Asegurar que el detalle de venta exista. | DetalleVenta | P-DV-012 |
| P-CV-002 | Asegurar que los productos internos a consumir se obtengan de la receta del producto vendido, o del producto interno asociado cuando el producto se vende directo del inventario. | DetalleReceta |  |
| P-CV-003 | Asegurar que no se registre dos veces el mismo producto interno para el mismo detalle de venta. | ConsumoVenta |  |
| P-CV-004 | Asegurar que exista saldo suficiente en inventario para cubrir la cantidad a consumir. | Inventario |  |
| P-CV-005 | Asegurar que el detalle de venta consultado exista. | DetalleVenta |  |
| P-CV-006 | Al registrar un consumo se genera su propio TipoMovimiento de categoria Venta y se registran las salidas de inventario por FIFO sobre los lotes del producto interno. | MovimientoInventario | P-MI-016 |
