# Proveedor

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Proveedor](../../01-modelo-dominio/objetos/Proveedor.md) · [Clase: Proveedor](../../02-diagrama-clases/clases/Proveedor.md)

| Campo | Valor |
|---|---|
| Entidad | Proveedor |
| Descripción | Representa a las personas o empresas que suministran a la pizzería los insumos y productos necesarios para su operación. |

## Atributos

| Nombre | id_proveedor | nombre_empresa | nit | contacto | activo |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | varchar | varchar | varchar | bit |
| Longitud |  | 60 | 15 | 10 |  |
| Cantidad dígitos parte entera |  |  |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  |  |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | NO | NO | NO |
| Llave Foránea · Entidad |  |  |  |  |  |
| Llave Foránea · Columna(s) |  |  |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | SI | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) | Unique de columna (sin nombre en el script) |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Identificador unico del proveedor dentro de la pizzeria (Valor por defecto: newid()) | Nombre de la empresa a la que pertenece el proveedor | Numero de identificacion tributaria (NIT) del proveedor; no se puede repetir entre proveedores | Forma de comunicacion directa con el proveedor | Indica si el proveedor esta activo y disponible para nuevas compras (Valor por defecto: 1) |
