# TipoProducto

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: TipoProducto](../../01-modelo-dominio/objetos/TipoProducto.md) · [Clase: TipoProducto](../../02-diagrama-clases/clases/TipoProducto.md)

| Campo | Valor |
|---|---|
| Entidad | TipoProducto |
| Descripción | Representa la clasificacion de cada producto que ofrece la pizzeria, indicando si corresponde a una pizza, una bebida o una adicion. |

## Atributos

| Nombre | id_tipo_producto | nombre |
|---|---|---|
| Tipo de dato | uniqueidentifier | varchar |
| Longitud |  | 20 |
| Cantidad dígitos parte entera |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |
| ¿Es autoincremento? | No | No |
| ¿Es obligatorio? | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO |
| Llave Foránea · Entidad |  |  |
| Llave Foránea · Columna(s) |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO |
| Restricciones de índice · Tipo |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO |
| Alias |  |  |
| Descripción del campo | Identificador unico de cada tipo de producto (Valor por defecto: newid()) | Nombre del tipo de producto (Pizza, Bebida o Adicion) |
