# Producto

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Producto](../../01-modelo-dominio/objetos/Producto.md) · [Clase: Producto](../../02-diagrama-clases/clases/Producto.md)

| Campo | Valor |
|---|---|
| Entidad | Producto |
| Descripción | Representa los artículos que la pizzería comercializa y que pueden ser vendidos al cliente, sirviendo como referencia para identificar y gestionar su oferta comercial. |

## Atributos

| Nombre | id_producto | nombre | id_tipo_producto | id_tamano | id_producto_interno | producto_interno | precio | activo |
|---|---|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | varchar | uniqueidentifier | uniqueidentifier | uniqueidentifier | int | decimal | bit |
| Longitud |  | 40 |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  |  |  |  | 18 |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  |  |  | 2 |  |
| ¿Es autoincremento? | No | No | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | SI | SI | SI | NO | NO | NO |
| Llave Foránea · Entidad |  |  | TipoProducto | Tamaño | ProductoInterno |  |  |  |
| Llave Foránea · Columna(s) |  |  | id_tipo_producto | id_tamano | id_producto_interno |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | SI | NO | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_producto_nombre_tamano |  | uk_producto_nombre_tamano |  |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | NO | NO | NO | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  |  |  |  | ck_producto_precio |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada producto que vende la pizzeria (Valor por defecto: newid()) | Nombre del producto; si es un producto interno, el nombre debe coincidir con uno ya existente en ProductoInterno | Tipo de producto al que pertenece (Pizza, Bebida o Adicion) | Tamaño del producto (Grande, Mediana o Pequeña) para productos que no son pizza el valor es No aplica | Producto interno asociado al producto. Si ProductoInterno es Si, es el producto interno con el mismo nombre; si no, es el registro "Sin insumo" | Dato calculado: indica si el producto se vende directamente desde el inventario (por ejemplo una bebida). Es Si cuando tiene un ProductoInternoAsociado y No cuando el asociado es "Sin insumo" (Columna calculada: case when id_producto_interno = '00000000-0000-0000-0000-000000000000' then 0 else 1 end) | Contiene el precio de venta publico del producto | Indica si el producto esta activo y disponible para la venta (Valor por defecto: 1) |
