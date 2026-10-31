# TipoMovimiento

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: TipoMovimiento](../../02-diagrama-clases/clases/TipoMovimiento.md) · [Entidad MER: TipoMovimiento](../../03-modelo-entidad-relacion/entidades/TipoMovimiento.md)

> Es el código de una operación concreta que mueve el inventario (cada detalle de compra, consumo de una venta o cambio tiene el suyo). Pertenece a una CategoriaOrigen y lo comparten la operación y los movimientos que genera.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Codigo unico de una operacion concreta que genera movimientos de inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo) |  |  |
| CategoriaOrigen | Categoria de origen a la que pertenece la operacion. Cada TipoMovimiento pertenece a una sola CategoriaOrigen | CategoriaOrigen | Si |

### Datos de muestra

_25 registros de ejemplo._

| Id | CategoriaOrigen |
|---|---|
| 99 | Compra |
| 100 | Compra |
| 101 | Compra |
| 102 | Compra |
| 103 | Compra |
| 104 | Compra |
| 105 | Compra |
| 106 | Compra |
| 107 | Compra |
| 108 | Compra |
| 109 | Compra |
| 110 | Compra |
| 111 | Compra |
| 112 | Compra |
| 150 | Venta |
| 151 | Venta |
| 152 | Venta |
| 153 | Venta |
| 154 | Venta |
| 155 | Venta |
| 156 | Venta |
| 157 | Venta |
| 158 | Venta |
| 200 | Cambio |
| 201 | Cambio |

## Caracterización

### Atributos

| Nombre atributo | Id | CategoriaOrigen |
|---|---|---|
| Descripción atributo | Codigo unico de una operacion concreta que genera movimientos de inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo) | Categoria de origen a la que pertenece la operacion. Cada TipoMovimiento pertenece a una sola CategoriaOrigen |
| Identificador del objeto de dominio | Si | No |
| Tipo de dato | Identificador único universal | CategoriaOrigen |
| Obligatorio | Si | Si |
| Longitud mínima (si aplica) |  |  |
| Longitud máxima (si aplica) |  |  |
| Precisión (si aplica) |  |  |
| Rendondeo (si aplica) |  |  |
| Rango inicial (si aplica) |  |  |
| Rango final (si aplica) |  |  |
| Formato (si aplica) |  |  |
| Reglas especiales (si aplica) | Es el codigo de la operacion: lo comparten la operacion (detalle de compra, consumo de venta o cambio) y los movimientos de inventario que genera. | Debe ser una categoria de origen existente (Compra, Venta o Cambio), segun la operacion que genera el codigo. |
| Dato calculado | No | No |
| Aplica ajuste del dato (si aplica) | No | No |
| Carácter de ajuste (si aplica) |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No |
| Dato sensible (si aplica) | No | No |
| Valor por defecto (si aplica) |  |  |
| Auto generado | Si | No |
| Generar tipo de movimiento | No Requerido | Requerido |
| Consultar tipo de movimiento | Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| No aplica | El tipo de movimiento se identifica unicamente por su Id (codigo de operacion); la base de datos no define otra combinacion unica. |  |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Generar tipo de movimiento | Genera un nuevo codigo de operacion con su categoria de origen cada vez que se registra un detalle de compra, un consumo de venta o un cambio. | Datos | CategoriaOrigen | Categoria de origen de la operacion que genera el codigo. | TipoMovimiento | Codigo de operacion generado con su categoria de origen. | P-TMV-001 | Asegurar que la categoria de origen indicada exista (Compra, Venta o Cambio). | La categoria de origen no existe. | Rechazar la generacion e informar que la categoria de origen no es valida. |
|  |  |  |  |  |  |  | P-TMV-002 | Asegurar que el identificador generado para el tipo de movimiento no exista previamente. | El identificador generado ya esta siendo utilizado. | Generar un nuevo identificador hasta garantizar que no exista. |
| Consultar tipo de movimiento | Permite consultar un tipo de movimiento para conocer la categoria de origen de la operacion. | Id | UUID | Identificador del tipo de movimiento que se desea consultar. | TipoMovimiento | Tipo de movimiento con su categoria de origen. | P-TMV-003 | Asegurar que el tipo de movimiento exista con el identificador indicado. | No existe un tipo de movimiento con el identificador indicado. | Informar que el tipo de movimiento no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Sistema | Generar tipo de movimiento | Genera un nuevo codigo de operacion con su categoria de origen cada vez que se registra un detalle de compra, un consumo de venta o un cambio. | TipoMovimiento | Categoria de origen | Categoria de la operacion que necesita el codigo. | P-TMV-001 |  | Tipo de movimiento generado |  |
|  |  |  |  |  |  | P-TMV-002 |  |  |  |
| Administrador | Consultar tipo de movimiento | Permite consultar un tipo de movimiento para conocer la categoria de origen de la operacion. | TipoMovimiento | Tipos de movimiento registrados | Identificador del tipo de movimiento que se desea consultar. | P-TMV-003 |  | Tipo de movimiento consultado |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-TMV-001 | Asegurar que la categoria de origen indicada exista (Compra, Venta o Cambio). | CategoriaOrigen |  |
| P-TMV-002 | Asegurar que el identificador generado para el tipo de movimiento no exista previamente. | TipoMovimiento |  |
| P-TMV-003 | Asegurar que el tipo de movimiento exista con el identificador indicado. | TipoMovimiento |  |
