# HistoricoPrecio

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: HistoricoPrecio](../../01-modelo-dominio/objetos/HistoricoPrecio.md) · [Clase: HistoricoPrecio](../../02-diagrama-clases/clases/HistoricoPrecio.md)

| Campo | Valor |
|---|---|
| Entidad | HistoricoPrecio |
| Descripción | Representa el registro de los precios que han tenido los productos de la pizzería a lo largo del tiempo, permitiendo consultar su evolución histórica. |

## Atributos

| Nombre | id_historico_precio | id_producto | precio | fecha_inicio | fecha_fin |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | decimal | date | date |
| Longitud |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  | 18 |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  | 2 |  |  |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | NO | NO | NO |
| Llave Foránea · Entidad |  | Producto |  |  |  |
| Llave Foránea · Columna(s) |  | id_producto |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | SI | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_historico_precio_producto_fecha_inicio, uk_historico_precio_producto_fecha_fin |  | uk_historico_precio_producto_fecha_inicio | uk_historico_precio_producto_fecha_fin |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI | SI | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_historico_precio_precio | ck_historico_precio_fechas | ck_historico_precio_fechas |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Representa el identificador único de cada historico de precio (Valor por defecto: newid()) | Producto al cual se le realizo un cambio de precio | Precio correspondiente al producto en ese periodo | Fecha en la que se dio inicio al precio de un producto, ya sea que en esta fecha se ingreso por primera vez el producto o en esta fecha se inicio el nuevo precio de un producto | Fecha en la que finaliza ese precio en especifico (Valor por defecto: 1000-01-01) |
