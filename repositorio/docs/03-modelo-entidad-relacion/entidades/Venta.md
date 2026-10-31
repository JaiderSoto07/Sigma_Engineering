# Venta

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Venta](../../01-modelo-dominio/objetos/Venta.md) · [Clase: Venta](../../02-diagrama-clases/clases/Venta.md)

| Campo | Valor |
|---|---|
| Entidad | Venta |
| Descripción | Representa la operación comercial mediante la cual la pizzería registra los productos adquiridos por un cliente. |

## Atributos

| Nombre | id_venta | fecha | hora | factura | cliente | total |
|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | date | time | varchar | varchar | decimal |
| Longitud |  |  |  | 9 | 10 |  |
| Cantidad dígitos parte entera |  |  |  |  |  | 18 |
| Cantidad dígitos parte decimal (Precisión) |  |  | 0 |  |  | 2 |
| ¿Es autoincremento? | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | NO | NO | NO | NO |
| Llave Foránea · Entidad |  |  |  |  |  |  |
| Llave Foránea · Columna(s) |  |  |  |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | NO | NO | SI | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  |  |  | Unique de columna (sin nombre en el script) |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_venta_factura |  | ck_venta_total |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |
| Descripción del campo | Identificador de cada venta dentro del sistema (Valor por defecto: newid()) | Fecha en la que se realizo la venta | Hora en la que se realizo la venta | Número de la factura de venta. El sistema lo asigna solo en el momento en que la venta se hace | Indicativo de a que cliente le pertenece la venta (Valor por defecto: 2222222222) | Total de la venta |
