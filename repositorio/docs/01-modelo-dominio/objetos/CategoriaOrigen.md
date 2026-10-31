# CategoriaOrigen

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: CategoriaOrigen](../../02-diagrama-clases/clases/CategoriaOrigen.md) · [Entidad MER: CategoriaOrigen](../../03-modelo-entidad-relacion/entidades/CategoriaOrigen.md)

> Indica qué operación fue la que generó un movimiento de inventario: una compra, una venta, un cambio de un producto que no es perecedero, o el retiro de un lote (porque se cambió o porque se venció).

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Representa el identificador unico de cada categoria de origen |  |  |
| Nombre | Nombre de la categoria de origen: indica de donde proviene la operacion (Compra, Venta o Cambio) | Alfanumerico(max 10 caracteres) | Si |

### Datos de muestra

_3 registros de ejemplo._

| Id | Nombre |
|---|---|
| 1 | Compra |
| 2 | Venta |
| 3 | Cambio |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre |
|---|---|---|
| Descripción atributo | Representa el identificador unico de cada categoria de origen | Nombre de la categoria de origen: indica de donde proviene la operacion (Compra, Venta o Cambio) |
| Identificador del objeto de dominio | Si | No |
| Tipo de dato | Identificador unico universal | Alfanumerico |
| Obligatorio | Si | Si |
| Longitud mínima (si aplica) |  | 1 |
| Longitud máxima (si aplica) |  | 10 |
| Precisión (si aplica) |  |  |
| Rendondeo (si aplica) |  |  |
| Rango inicial (si aplica) |  |  |
| Rango final (si aplica) |  |  |
| Formato (si aplica) |  |  |
| Reglas especiales (si aplica) |  | Catalogo fijo de 3 valores (Compra, Venta, Cambio) |
| Dato calculado | No | No |
| Aplica ajuste del dato (si aplica) | No | No |
| Carácter de ajuste (si aplica) |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si |
| Quitar espacios en blanco al fin (si aplica) | No | Si |
| Dato sensible (si aplica) | No | No |
| Valor por defecto (si aplica) |  |  |
| Auto generado | Si | No |
| Consultar categorias de origen disponibles | No Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos categorias de origen con el mismo Nombre. | Nombre |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Consultar categorias de origen disponibles | Permite consultar las categorias de origen (Compra, Venta o Cambio) que indican que operacion genero un movimiento de inventario. | id | UUID | Id necesario para realizar la consulta de las categorias de origen disponibles. | Lista de CategoriasOrigen | Conjunto de categorias de origen (Compra, Venta y Cambio) disponibles para seleccion. | P-ORI-001 | Asegurar que la categoria de origen exista con el identificador indicado. | No existen categorias de origen disponibles. | Informar que la categoria de origen que esta buscando no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Consultar categorias de origen disponibles | Permite consultar las categorias de origen (Compra, Venta o Cambio) que indican que operacion genero un movimiento de inventario. | CategoriaOrigen |  |  | P-ORI-001 |  | Categorias de origen consultadas |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-ORI-001 | Asegurar que la categoria de origen exista con el identificador indicado. | CategoriaOrigen |  |
