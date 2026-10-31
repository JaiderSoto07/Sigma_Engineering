# DetalleReceta

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: DetalleReceta](../../01-modelo-dominio/objetos/DetalleReceta.md) · [Clase: DetalleReceta](../../02-diagrama-clases/clases/DetalleReceta.md)

| Campo | Valor |
|---|---|
| Entidad | DetalleReceta |
| Descripción | Representa cada uno de los ingredientes necesarios para elaborar una receta, indicando la cantidad requerida y la unidad de medida utilizada. |

## Atributos

| Nombre | id_detalle_receta | id_producto | id_producto_interno | cantidad | id_unidad_medida |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | uniqueidentifier | decimal | uniqueidentifier |
| Longitud |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  | 18 |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  | 4 |  |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | SI | NO | SI |
| Llave Foránea · Entidad |  | Producto | ProductoInterno |  | UnidadMedida |
| Llave Foránea · Columna(s) |  | id_producto | id_producto_interno |  | id_unidad_medida |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_detalle_receta_producto_producto_interno | uk_detalle_receta_producto_producto_interno |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_detalle_receta_cantidad |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada ingrediente (linea) de una receta (Valor por defecto: newid()) | Producto (Pizza o Adicion) al que pertenece esta linea de receta (Al eliminar el producto se eliminan sus detalles de receta: on delete cascade) | Producto interno que se utiliza en la receta | Cantidad del producto interno | Unidad de medida del ingrediente; se asigna automaticamente segun el tipo de medida del producto interno (Peso: g, Volumen: ml, Unidad: und) |
