# ClaseMovimiento

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: ClaseMovimiento](../../02-diagrama-clases/clases/ClaseMovimiento.md) · [Entidad MER: ClaseMovimiento](../../03-modelo-entidad-relacion/entidades/ClaseMovimiento.md)

> Indica si un movimiento de inventario fue una entrada o una salida de insumos o productos.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Representa el identificador unico de cada clase de movimiento |  |  |
| Nombre | Nombre de la clase de movimiento (Entrada o Salida): indica si el movimiento suma (Entrada) o resta (Salida) inventario | Alfanumerico(max 10 caracteres) | Si |

### Datos de muestra

_2 registros de ejemplo._

| Id | Nombre |
|---|---|
| 1 | Entrada |
| 2 | Salida |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre |
|---|---|---|
| Descripción atributo | Representa el identificador unico de cada clase de movimiento | Nombre de la clase de movimiento (Entrada o Salida): indica si el movimiento suma (Entrada) o resta (Salida) inventario |
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
| Reglas especiales (si aplica) |  | Catalogo fijo de solo 2 valores (Entrada, Salida) |
| Dato calculado | No | No |
| Aplica ajuste del dato (si aplica) | No | No |
| Carácter de ajuste (si aplica) |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si |
| Quitar espacios en blanco al fin (si aplica) | No | Si |
| Dato sensible (si aplica) | No | No |
| Valor por defecto (si aplica) |  |  |
| Auto generado | Si | No |
| Consultar clases de movimiento disponibles | No Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos clases de movimiento con el mismo Nombre. | Nombre |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Consultar clases de movimiento disponibles | Permite consultar las clases de movimiento (Entrada o Salida) disponibles para clasificar un movimiento de inventario. | id | UUID | Id necesario para realizar la consulta de las clases de movimiento disponibles. | Lista de ClasesMovimiento | Conjunto de clases de movimiento (Entrada y Salida) disponibles para seleccion. | P-CM-001 | Asegurar que la clase de movimiento exista con el identificador indicado. | No existen clases de movimiento disponibles. | Informar que la clase de movimiento que esta buscando no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Consultar clases de movimiento disponibles | Permite consultar las clases de movimiento (Entrada o Salida) disponibles para clasificar un movimiento de inventario. | ClaseMovimiento |  |  | P-CM-001 |  | Clases de movimiento consultadas |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-CM-001 | Asegurar que la clase de movimiento exista con el identificador indicado. | ClaseMovimiento |  |
