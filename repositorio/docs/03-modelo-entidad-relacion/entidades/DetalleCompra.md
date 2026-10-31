# DetalleCompra

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: DetalleCompra](../../01-modelo-dominio/objetos/DetalleCompra.md) · [Clase: DetalleCompra](../../02-diagrama-clases/clases/DetalleCompra.md)

| Campo | Valor |
|---|---|
| Entidad | DetalleCompra |
| Descripción | Representa cada uno de los insumos o productos adquiridos dentro de una compra, especificando qué se compró y en qué cantidad. |

## Atributos

| Nombre | id_detalle_compra | id_compra | id_producto_interno | cantidad | id_unidad_medida | precio_compra | fecha_vencimiento | id_tipo_movimiento |
|---|---|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | uniqueidentifier | decimal | uniqueidentifier | decimal | date | uniqueidentifier |
| Longitud |  |  |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  | 18 |  | 18 |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  | 4 |  | 2 |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | SI | NO | SI | NO | NO | SI |
| Llave Foránea · Entidad |  | Compra | ProductoInterno |  | UnidadMedida |  |  | TipoMovimiento |
| Llave Foránea · Columna(s) |  | id_compra | id_producto_interno |  | id_unidad_medida |  |  | id_tipo_movimiento |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO | NO | SI | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_detalle_compra_compra_producto_fecha | uk_detalle_compra_compra_producto_fecha |  |  |  | uk_detalle_compra_compra_producto_fecha | Unique de columna (sin nombre en el script) |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO | SI | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_detalle_compra_cantidad |  | ck_detalle_compra_precio_compra |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada detalle de compra (Valor por defecto: newid()) | Compra a la que pertenece este detalle | Producto interno que se compra en este detalle | Cantidad que se compro de un producto determinado | Se ingresa la unidad de medida en la que se hizo la compra, esta unidad de medida debe ser valida para el tipo de dato, por ejemplo si el producto es aceite de oliva, el va a ser de tipo volumen entonces puede estar en ml o l | Valor monetario de cada producto | Se ingresa la fecha de vencimiento del producto, en caso de que la traiga de lo contrario hay que calcular una vida util (Valor por defecto: 1000-01-01) | Codigo de la operacion (TipoMovimiento) propio de este detalle de compra; es el mismo que lleva su movimiento de entrada |
