# ConsumoVenta

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: ConsumoVenta](../../01-modelo-dominio/objetos/ConsumoVenta.md) · [Clase: ConsumoVenta](../../02-diagrama-clases/clases/ConsumoVenta.md)

| Campo | Valor |
|---|---|
| Entidad | ConsumoVenta |
| Descripción | Representa los productos internos que se consumen por cada detalle de venta, según la receta del producto vendido o su producto interno asociado. |

## Atributos

| Nombre | id_consumo_venta | id_detalle_venta | id_producto_interno | cantidad | id_unidad_medida | id_tipo_movimiento |
|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | uniqueidentifier | decimal | uniqueidentifier | uniqueidentifier |
| Longitud |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  | 18 |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  | 4 |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | SI | NO | SI | SI |
| Llave Foránea · Entidad |  | DetalleVenta | ProductoInterno |  | UnidadMedida | TipoMovimiento |
| Llave Foránea · Columna(s) |  | id_detalle_venta | id_producto_interno |  | id_unidad_medida | id_tipo_movimiento |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_consumo_venta_detalle_venta_producto_interno | uk_consumo_venta_detalle_venta_producto_interno |  |  | Unique de columna (sin nombre en el script) |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_consumo_venta_cantidad |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada consumo de venta (Valor por defecto: newid()) | Detalle de venta (renglon de la venta) que origina el consumo | Producto interno que se debe consumir segun la receta del producto vendido | Cantidad del producto interno que se consume: cantidad vendida por la cantidad de la receta, en la unidad de la receta | Unidad de medida de la cantidad; es la misma de la receta y queda guardada aunque la receta cambie despues | Codigo de la operacion (TipoMovimiento) propio de este consumo; es el mismo que lleva su movimiento de salida |
