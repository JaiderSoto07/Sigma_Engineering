# Tamaño

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Tamaño](../../01-modelo-dominio/objetos/Tamano.md) · [Clase: Tamano](../../02-diagrama-clases/clases/Tamano.md)

| Campo | Valor |
|---|---|
| Entidad | Tamaño |
| Descripción | Representa las distintas presentaciones de tamaño en las que la pizzería puede ofrecer un producto; los productos que no son pizza usan "No aplica". |

## Atributos

| Nombre | id_tamano | tamano |
|---|---|---|
| Tipo de dato | uniqueidentifier | varchar |
| Longitud |  | 20 |
| Cantidad dígitos parte entera |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |
| ¿Es autoincremento? | No | No |
| ¿Es obligatorio? | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO |
| Llave Foránea · Entidad |  |  |
| Llave Foránea · Columna(s) |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO |
| Restricciones de índice · Tipo |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO |
| Alias |  |  |
| Descripción del campo | Representa el identificador unico de cada tamaño (Valor por defecto: newid()) | Nombre del tamaño (Grande, Mediana, Pequeña o No aplica) |
