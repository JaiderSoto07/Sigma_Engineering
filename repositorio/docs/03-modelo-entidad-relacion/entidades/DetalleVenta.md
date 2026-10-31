# DetalleVenta

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: DetalleVenta](../../01-modelo-dominio/objetos/DetalleVenta.md) · [Clase: DetalleVenta](../../02-diagrama-clases/clases/DetalleVenta.md)

| Campo | Valor |
|---|---|
| Entidad | DetalleVenta |
| Descripción | Representa cada uno de los productos incluidos en una venta, registrando la cantidad adquirida y el valor correspondiente a cada producto. |

## Atributos

| Nombre | id_detalle_venta | id_venta | id_producto | cantidad | precio_producto | subtotal |
|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | uniqueidentifier | int | decimal | decimal |
| Longitud |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  |  | 18 | 18 |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  | 2 | 2 |
| ¿Es autoincremento? | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | SI | NO | NO | NO |
| Llave Foránea · Entidad |  | Venta | Producto |  |  |  |
| Llave Foránea · Columna(s) |  | id_venta | id_producto |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_detalle_venta_venta_producto | uk_detalle_venta_venta_producto |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_detalle_venta_cantidad | ck_detalle_venta_precio_producto |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |
| Descripción del campo | Identificador unico del detalle venta dentro de la pizzeria (Valor por defecto: newid()) | Venta a la que pertenece; se identifica con su numero de factura | Producto que ofrece la pizzeria | Cantidad del producto que pide el cliente | Precio de cada producto | Por cada cantidad que pide el cliente de un producto se calcula un total (Columna calculada: cantidad \* precio_producto) |
