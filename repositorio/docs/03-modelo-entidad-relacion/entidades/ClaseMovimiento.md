# ClaseMovimiento

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: ClaseMovimiento](../../01-modelo-dominio/objetos/ClaseMovimiento.md) · [Clase: ClaseMovimiento](../../02-diagrama-clases/clases/ClaseMovimiento.md)

| Campo | Valor |
|---|---|
| Entidad | ClaseMovimiento |
| Descripción | Representa la clase de cada movimiento de inventario, indicando si corresponde a una entrada o a una salida de existencias. |

## Atributos

| Nombre | id_clase_movimiento | nombre |
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
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  | ck_clase_movimiento_nombre |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO |
| Restricciones de índice · Tipo |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO |
| Alias |  |  |
| Descripción del campo | Representa el identificador unico de cada clase de movimiento (Valor por defecto: newid()) | Nombre de la clase de movimiento (Entrada o Salida): indica si el movimiento suma (Entrada) o resta (Salida) inventario |
