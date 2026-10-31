# ProductoInterno

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: ProductoInterno](../../01-modelo-dominio/objetos/ProductoInterno.md) · [Clase: ProductoInterno](../../02-diagrama-clases/clases/ProductoInterno.md)

| Campo | Valor |
|---|---|
| Entidad | ProductoInterno |
| Descripción | Representa cada insumo o producto que la pizzeria compra y almacena en su inventario, ya sea que se consuma dentro de una receta o se venda directamente al cliente. |

## Atributos

| Nombre | id_producto_interno | nombre | perecedero | vida_util | id_unidad_medida | activo |
|---|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | varchar | bit | int | uniqueidentifier | bit |
| Longitud |  | 40 |  |  |  |  |
| Cantidad dígitos parte entera |  |  |  |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  |  |  |
| ¿Es autoincremento? | No | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | NO | NO | SI | NO |
| Llave Foránea · Entidad |  |  |  |  | UnidadMedida |  |
| Llave Foránea · Columna(s) |  |  |  |  | id_unidad_medida |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |  |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | SI | SI | NO | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  | ck_producto_interno_vida_util | ck_producto_interno_vida_util |  |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada producto interno dentro de la pizzeria (Valor por defecto: newid()) | Nombre de cada producto interno (ingrediente o bebida almacenada) | Cuando se crea el ingrediente se debe decir si es un producto perecedero o no | Vida util en dias: de 1 a 400 si el producto es perecedero y 0 si no lo es (Valor por defecto: 0) | Unidad de medida (UnidadMedida) en la que se maneja el producto interno; su tipo (Peso, Volumen o Unidad) define la unidad de las recetas | Indica si el producto interno esta activo y disponible para usarse (Valor por defecto: 1) |
