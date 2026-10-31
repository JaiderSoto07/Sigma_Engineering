# Lote

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Lote](../../01-modelo-dominio/objetos/Lote.md) · [Clase: Lote](../../02-diagrama-clases/clases/Lote.md)

| Campo | Valor |
|---|---|
| Entidad | Lote |
| Descripción | Representa un conjunto de unidades de un mismo insumo recibido en una compra o como reemplazo en un cambio, identificado para controlar su cantidad, saldo, fecha de vencimiento y trazabilidad dentro del inventario. |

## Atributos

| Nombre | id_lote | numero_lote | id_producto_interno | cantidad | saldo | id_unidad_medida | fecha_vencimiento | disponible |
|---|---|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | int | uniqueidentifier | decimal | decimal | uniqueidentifier | date | int |
| Longitud |  |  |  |  |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  | 18 | 18 |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  | 4 | 4 |  |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | SI | NO | NO | SI | NO | NO |
| Llave Foránea · Entidad |  |  | ProductoInterno |  |  | UnidadMedida |  |  |
| Llave Foránea · Columna(s) |  |  | id_producto_interno |  |  | id_unidad_medida |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | uk_lote_producto_numero_lote | uk_lote_producto_numero_lote |  |  |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | SI | NO | SI | SI | NO | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  | ck_lote_numero_lote |  | ck_lote_cantidad, ck_lote_saldo | ck_lote_saldo |  |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada lote dentro de la pizzeria (Valor por defecto: newid()) | Diferenciador numerico de cada lote dependiendo de su respectivo producto | Producto interno al que pertenece el lote | Cantidad con la que entro el lote | Cantidad que aun queda disponible de este lote, descontando las salidas registradas en Movimiento y los retiros registrados en SalidaLote | Unidad de medida estandar en lote y posteriormente inventario | Fecha de vencimiento del producto | Indicativo de disponibilidad de un lote (Columna calculada: case when saldo &gt; 0 then 1 else 0 end) |
