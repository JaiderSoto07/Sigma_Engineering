# UnidadMedida

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: UnidadMedida](../../01-modelo-dominio/objetos/UnidadMedida.md) · [Clase: UnidadMedida](../../02-diagrama-clases/clases/UnidadMedida.md)

| Campo | Valor |
|---|---|
| Entidad | UnidadMedida |
| Descripción | Representa las unidades utilizadas por la pizzería para cuantificar los ingredientes, productos y demás elementos que requieren control de cantidades. |

## Atributos

| Nombre | id_unidad_medida | unidad_medida | tipo_medida |
|---|---|---|---|
| Tipo de dato | uniqueidentifier | varchar | varchar |
| Longitud |  | 4 | 10 |
| Cantidad dígitos parte entera |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |
| ¿Es autoincremento? | No | No | No |
| ¿Es obligatorio? | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | NO |
| Llave Foránea · Entidad |  |  |  |
| Llave Foránea · Columna(s) |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_unidad_medida_tipo_medida |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO |
| Alias |  |  |  |
| Descripción del campo | Identificador unico de unidad de medida dentro del sistema (Valor por defecto: newid()) | Abreviatura de la unidad de medida (g, kg, ml, l o und) | Tipo de medida a la que corresponde cada unidad de medida |
