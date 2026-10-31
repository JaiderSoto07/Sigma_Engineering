# MovimientoInventario

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: MovimientoInventario](../../01-modelo-dominio/objetos/MovimientoInventario.md) · [Clase: MovimientoInventario](../../02-diagrama-clases/clases/MovimientoInventario.md)

| Campo | Valor |
|---|---|
| Entidad | MovimientoInventario |
| Descripción | Representa cada entrada o salida de existencias sobre un lote, generada por una compra, una venta o un cambio, permitiendo mantener el control y la trazabilidad del inventario. |

## Atributos

| Nombre | id_movimiento_inventario | id_clase_movimiento | id_tipo_movimiento | cantidad | fecha_movimiento | id_lote |
|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | uniqueidentifier | decimal | date | uniqueidentifier |
| Longitud |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  | 18 |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  | 4 |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | SI | NO | NO | SI |
| Llave Foránea · Entidad |  | ClaseMovimiento | TipoMovimiento |  |  | Lote |
| Llave Foránea · Columna(s) |  | id_clase_movimiento | id_tipo_movimiento |  |  | id_lote |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  |  |  |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_movimiento_inventario_cantidad |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada movimiento: una entrada o salida concreta de un lote (Valor por defecto: newid()) | Clase de movimiento: Entrada (suma al inventario) o Salida (resta del inventario) | Codigo de la operacion (TipoMovimiento) que genero el movimiento; a traves de el se conoce la CategoriaOrigen y el documento de origen | Cantidad que entro o salio del lote, expresada en la unidad de medida del lote (kg, l o und) | Fecha en la que se registra el movimiento de inventario, correspondiente al momento en que se realiza la entrada o salida sobre el lote. | Lote sobre el que se realizo la entrada o salida. Por el lote se conoce el producto interno y la unidad de medida |
