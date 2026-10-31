# CategoriaOrigen

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: CategoriaOrigen](../../01-modelo-dominio/objetos/CategoriaOrigen.md) · [Clase: CategoriaOrigen](../../02-diagrama-clases/clases/CategoriaOrigen.md)

| Campo | Valor |
|---|---|
| Entidad | CategoriaOrigen |
| Descripción | Representa la categoría de origen de la operación que genera un movimiento de inventario: una compra, una venta o un cambio de producto. |

## Atributos

| Nombre | id_categoria_origen | nombre |
|---|---|---|
| Tipo de dato | uniqueidentifier | varchar |
| Longitud |  | 10 |
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
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  | ck_categoria_origen_nombre |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO |
| Restricciones de índice · Tipo |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO |
| Alias |  |  |
| Descripción del campo | Representa el identificador unico de cada categoria de origen (Valor por defecto: newid()) | Nombre de la categoria de origen: indica de donde proviene la operacion (Compra, Venta o Cambio) |
