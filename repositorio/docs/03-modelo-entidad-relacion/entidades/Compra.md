# Compra

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Compra](../../01-modelo-dominio/objetos/Compra.md) · [Clase: Compra](../../02-diagrama-clases/clases/Compra.md)

| Campo | Valor |
|---|---|
| Entidad | Compra |
| Descripción | Representa la adquisición de insumos o productos que realiza la pizzería a un proveedor para abastecer las necesidades de su operación. |

## Atributos

| Nombre | id_compra | id_proveedor | fecha_compra | numero_factura | total |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | date | varchar | decimal |
| Longitud |  |  |  | 40 |  |
| Cantidad dígitos parte entera |  |  |  |  | 18 |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  | 2 |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | NO | NO | NO |
| Llave Foránea · Entidad |  | Proveedor |  |  |  |
| Llave Foránea · Columna(s) |  | id_proveedor |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | SI | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_compra_proveedor_numero_factura |  | uk_compra_proveedor_numero_factura |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | NO | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  |  | ck_compra_total |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada compra de la pizzeria (Valor por defecto: newid()) | Proveedor al que se le hizo la compra | Representa la fecha en la que se efectuó la compra | Número de factura que el proveedor entrega al momento de la compra. | Representa el valor monetario total de los productos comprados al proveedor. |
