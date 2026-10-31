# Cambio

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Cambio](../../01-modelo-dominio/objetos/Cambio.md) · [Clase: Cambio](../../02-diagrama-clases/clases/Cambio.md)

| Campo | Valor |
|---|---|
| Entidad | Cambio |
| Descripción | Representa la operación mediante la cual un insumo no perecedero próximo a vencer se devuelve al proveedor y se reemplaza por uno nuevo, manteniendo la disponibilidad y las condiciones adecuadas de los insumos de la pizzería. |

## Atributos

| Nombre | id_cambio | id_producto_interno | cantidad | id_unidad_medida | fecha_vencimiento | fecha_cambio | id_tipo_movimiento |
|---|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | uniqueidentifier | decimal | uniqueidentifier | date | date | uniqueidentifier |
| Longitud |  |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  | 18 |  |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  | 4 |  |  |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | SI | NO | SI | NO | NO | SI |
| Llave Foránea · Entidad |  | ProductoInterno |  | UnidadMedida |  |  | TipoMovimiento |
| Llave Foránea · Columna(s) |  | id_producto_interno |  | id_unidad_medida |  |  | id_tipo_movimiento |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | NO | NO | SI | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_cambio_producto_fecha_cambio |  |  |  | uk_cambio_producto_fecha_cambio | Unique de columna (sin nombre en el script) |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI | NO | SI | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_cambio_cantidad |  | ck_cambio_fechas | ck_cambio_fechas |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada cambio registrado (Valor por defecto: newid()) | Producto interno que se cambia (siempre un producto no perecedero) | Cantidad del producto que entra o sale por el cambio | Unidad de medida en la que se registra la cantidad del cambio, debe ser valida para el tipo de producto | Fecha de vencimiento del producto que entra por el cambio | Fecha en la que se realiza el cambio | Codigo de la operacion (TipoMovimiento) del cambio; con el se rastrean los movimientos que genero el cambio |
