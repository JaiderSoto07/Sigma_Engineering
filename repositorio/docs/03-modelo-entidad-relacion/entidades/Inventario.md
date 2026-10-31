# Inventario

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Inventario](../../01-modelo-dominio/objetos/Inventario.md) · [Clase: Inventario](../../02-diagrama-clases/clases/Inventario.md)

| Campo | Valor |
|---|---|
| Entidad | Inventario |
| Descripción | Representa las existencias disponibles de los insumos y productos que la pizzería necesita para operar y atender sus ventas. |

## Atributos

| Nombre | id_inventario | id_producto_interno | cantidad_total | id_unidad_medida | stock_minimo |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | decimal | uniqueidentifier | decimal |
| Longitud |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  | 18 |  | 18 |
| Cantidad dígitos parte decimal (Precisión) |  |  | 4 |  | 4 |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | NO | SI | NO |
| Llave Foránea · Entidad |  | ProductoInterno |  | UnidadMedida |  |
| Llave Foránea · Columna(s) |  | id_producto_interno |  | id_unidad_medida |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI | NO | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_inventario_cantidad_total |  | ck_inventario_stock_minimo |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada registro de inventario (Valor por defecto: newid()) | Producto interno (ingrediente o bebida) al que corresponde este registro de inventario | Cantidad Total del producto | Unidad de medida que se maneja en el inventario | Cantidad minima de cada producto interno que debe haber en el inventario, expresada en la misma unidad de medida de inventario (Valor por defecto: 1) |
