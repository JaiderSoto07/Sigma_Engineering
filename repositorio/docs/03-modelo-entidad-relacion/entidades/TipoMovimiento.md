# TipoMovimiento

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: TipoMovimiento](../../01-modelo-dominio/objetos/TipoMovimiento.md) · [Clase: TipoMovimiento](../../02-diagrama-clases/clases/TipoMovimiento.md)

| Campo | Valor |
|---|---|
| Entidad | TipoMovimiento |
| Descripción | Representa el código de una operación concreta que mueve el inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo); pertenece a una categoría de origen y lo comparten la operación y los movimientos que genera. |

## Atributos

| Nombre | id_tipo_movimiento | id_categoria_origen |
|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier |
| Longitud |  |  |
| Cantidad dígitos parte entera |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |
| ¿Es autoincremento? | No | No |
| ¿Es obligatorio? | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI |
| Llave Foránea · Entidad |  | CategoriaOrigen |
| Llave Foránea · Columna(s) |  | id_categoria_origen |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO |
| Restricciones de índice · Tipo |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO |
| Alias |  |  |
| Descripción del campo | Codigo unico de una operacion concreta que genera movimientos de inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo) (Valor por defecto: newid()) | Categoria de origen a la que pertenece la operacion. Cada TipoMovimiento pertenece a una sola CategoriaOrigen |
