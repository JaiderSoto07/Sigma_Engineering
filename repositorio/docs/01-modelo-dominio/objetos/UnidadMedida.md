# UnidadMedida

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: UnidadMedida](../../02-diagrama-clases/clases/UnidadMedida.md) · [Entidad MER: UnidadMedida](../../03-modelo-entidad-relacion/entidades/UnidadMedida.md)

> Es la unidad con la que se mide cada ingrediente o producto, por ejemplo kilos, litros o unidades.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de unidad de medida dentro del sistema |  |  |
| UnidadMedida | Unidad de medida | Alfanumerico(max 4 caracteres) | Si |
| TipoMedida | Tipo de medida a la que corresponde cada unidad de medida | Alfanumerico(max 10 caracteres) | Si |

### Datos de muestra

_5 registros de ejemplo._

| Id | UnidadMedida | TipoMedida | Combinación única 1 |
|---|---|---|---|
| 1 | g | Peso | g |
| 2 | kg | Peso | kg |
| 3 | ml | Volumen | ml |
| 4 | l | Volumen | l |
| 5 | und | Unidad | und |

## Caracterización

### Atributos

| Nombre atributo | Id | UnidadMedida | TipoMedida |
|---|---|---|---|
| Descripción atributo | Identificador unico de unidad de medida dentro del sistema | Unidad de medida | Tipo de medida a la que corresponde cada unidad de medida |
| Identificador del objeto de dominio | Si | No | No |
| Tipo de dato | Identificador universal | Alfanumerico | Alfanumerico |
| Obligatorio | Si | Si | Si |
| Longitud mínima (si aplica) |  | 1 | 1 |
| Longitud máxima (si aplica) |  | 4 | 10 |
| Precisión (si aplica) |  |  |  |
| Rendondeo (si aplica) |  |  |  |
| Rango inicial (si aplica) |  |  |  |
| Rango final (si aplica) |  |  |  |
| Formato (si aplica) |  |  |  |
| Reglas especiales (si aplica) |  | Se debe asegurar una sanitizacion del dato. | Solo se permiten los tipos Peso (g, kg), Volumen (ml, l) y Unidad (und). Asi el producto interno queda asociado a una unidad valida para su tipo de medida. |
| Dato calculado | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) |  | Si | Si |
| Quitar espacios en blanco al fin (si aplica) |  | Si | Si |
| Dato sensible (si aplica) | No | No | No |
| Valor por defecto (si aplica) |  |  |  |
| Auto generado | Si | No | No |
| Consultar unidades de medida disponibles | No Mostrar | Mostrar | No Mostrar |
| Eliminar unidad de medida | Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos unidades de medida con el mismo nombre. | UnidadMedida |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Consultar unidades de medida disponibles | Permite consultar las unidades de medida precargadas en el sistema para seleccionar la unidad adecuada al registrar cantidades de productos, ingredientes, compras, recetas o movimientos de inventario. | Datos | UnidadMedida | Criterios necesarios para realizar la consulta de las unidades de medida disponibles. | Lista de UnidadesMedida | Conjunto de unidades de medida disponibles para selección. | P-UM-001 | Asegurar que solo se consulten las unidades de medida del catalogo definido por la pizzeria (g, kg, ml, l y und), cada una con su tipo de medida (Peso, Volumen o Unidad). | No existen unidades de medida registradas en el catalogo. | Informar al administrador que no hay unidades de medida disponibles. |
|  |  |  |  |  |  |  | P-UM-002 | Asegurar que la unidad de medida exista con el identificador indicado | No existe una unidad de medida asociada al identificador indicado. | Informar que la unidad de medida no existe. |
| Eliminar unidad de medida | Permite eliminar una unidad de medida que no este siendo utilizada por ningun otro registro de la pizzeria. | Id | UUID | Identificador de la unidad de medida que se desea eliminar. | UnidadMedida | Unidad de medida eliminada correctamente. | P-UM-003 | Asegurar que la unidad de medida que se desea eliminar se encuentre registrada en el sistema. | La unidad de medida que se desea eliminar no existe. | Rechazar la eliminacion e informar que la unidad de medida no existe. |
|  |  |  |  |  |  |  | P-UM-004 | Asegurar que la unidad de medida que se desea eliminar no este siendo utilizada por productos internos, lotes o registros de inventario que requieran conservarla. | La unidad de medida esta siendo utilizada por un producto interno, un lote o un registro de inventario. | Rechazar la eliminacion e informar en que registros esta en uso la unidad de medida. |
|  |  |  |  |  |  |  | P-UM-005 | Asegurar que la unidad de medida que se desea eliminar no este siendo utilizada por detalles de receta, detalles de compra, cambios o consumos de venta que requieran conservarla. | La unidad de medida esta siendo utilizada por un detalle de receta, un detalle de compra, un cambio o un consumo de venta. | Rechazar la eliminacion e informar en que registros esta en uso la unidad de medida. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|
| Administrador | Consultar unidades de medida disponibles | Permite consultar las unidades de medida precargadas en el sistema para seleccionar la unidad adecuada al registrar cantidades de productos, ingredientes, compras, recetas o movimientos de inventario. | UnidadMedida |  |  | P-UM-001 | Unidades de medida consultadas |  |
|  |  |  |  |  |  | P-UM-002 |  |  |
| Administrador | Eliminar unidad de medida | Permite eliminar una unidad de medida que no este siendo utilizada por ningun otro registro de la pizzeria. | UnidadMedida |  |  | P-UM-003 | Unidad de medida eliminada |  |
|  |  |  |  |  |  | P-UM-004 |  |  |
|  |  |  |  |  |  | P-UM-005 |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-UM-001 | Asegurar que solo se consulten las unidades de medida del catalogo definido por la pizzeria (g, kg, ml, l y und), cada una con su tipo de medida (Peso, Volumen o Unidad). | UnidadMedida |  |
| P-UM-002 | Asegurar que la unidad de medida exista con el identificador indicado | UnidadMedida |  |
| P-UM-003 | Asegurar que la unidad de medida que se desea eliminar se encuentre registrada en el sistema. | UnidadMedida |  |
| P-UM-004 | Asegurar que la unidad de medida que se desea eliminar no este siendo utilizada por productos internos, lotes o registros de inventario que requieran conservarla. | UnidadMedida |  |
| P-UM-005 | Asegurar que la unidad de medida que se desea eliminar no este siendo utilizada por detalles de receta, detalles de compra, cambios o consumos de venta que requieran conservarla. | UnidadMedida |  |
