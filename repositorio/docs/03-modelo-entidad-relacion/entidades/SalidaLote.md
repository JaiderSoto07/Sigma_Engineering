# SalidaLote

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: SalidaLote](../../01-modelo-dominio/objetos/SalidaLote.md) · [Clase: SalidaLote](../../02-diagrama-clases/clases/SalidaLote.md)

| Campo | Valor |
|---|---|
| Entidad | SalidaLote |
| Descripción | Representa el retiro de una cantidad de producto de un lote próximo a vencer; un lote puede tener como máximo un retiro y este no genera movimiento de inventario. |

## Atributos

| Nombre | id_salida_lote | id_lote | cantidad | fecha_movimiento |
|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | decimal | date |
| Longitud |  |  |  |  |
| Cantidad dígitos parte entera |  |  | 18 |  |
| Cantidad dígitos parte decimal (Precisión) |  |  | 4 |  |
| ¿Es autoincremento? | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | NO | NO |
| Llave Foránea · Entidad |  | Lote |  |  |
| Llave Foránea · Columna(s) |  | id_lote |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_salida_lote_cantidad |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO |
| Alias |  |  |  |  |
| Descripción del campo | Identificador único de cada retiro realizado sobre un lote. (Valor por defecto: newid()) | Lote específico del cual se retira el producto. | Cantidad de producto que se retira efectivamente del lote. | Fecha en la que se realiza efectivamente el retiro del producto del lote por decisión del usuario. |
