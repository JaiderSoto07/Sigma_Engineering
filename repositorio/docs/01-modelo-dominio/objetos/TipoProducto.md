# TipoProducto

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: TipoProducto](../../02-diagrama-clases/clases/TipoProducto.md) · [Entidad MER: TipoProducto](../../03-modelo-entidad-relacion/entidades/TipoProducto.md)

> Indica en qué categoría entra cada producto que vende la pizzería, por ejemplo si es una pizza, una bebida o una adición.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Representa el identificador unico de cada producto |  |  |
| Nombre | Nombre del tipo de producto (Pizza, Bebida o Adicion) | Alfanumerico(max 20 caracteres) | Si |

### Datos de muestra

_3 registros de ejemplo._

| Id | Nombre |
|---|---|
| 1 | Pizza |
| 2 | Bebida |
| 3 | Adicion |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre |
|---|---|---|
| Descripción atributo | Representa el identificador unico de cada producto | Nombre del tipo de producto (Pizza, Bebida o Adicion) |
| Identificador del objeto de dominio | Si | No |
| Tipo de dato | Identificador unico universal | Alfanumerico |
| Obligatorio | Si | Si |
| Longitud mínima (si aplica) |  | 1 |
| Longitud máxima (si aplica) |  | 20 |
| Precisión (si aplica) |  |  |
| Rendondeo (si aplica) |  |  |
| Rango inicial (si aplica) |  |  |
| Rango final (si aplica) |  |  |
| Formato (si aplica) |  |  |
| Reglas especiales (si aplica) |  | Estos son los tipos de producto con los que arranca el catalogo (Pizza, Bebida, Adicion), pero se pueden agregar nuevos si la pizzeria llega a manejar una categoria de producto distinta. |
| Dato calculado | No | No |
| Aplica ajuste del dato (si aplica) | No | No |
| Carácter de ajuste (si aplica) |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si |
| Quitar espacios en blanco al fin (si aplica) | No | Si |
| Dato sensible (si aplica) | No | No |
| Valor por defecto (si aplica) |  |  |
| Auto generado | Si | No |
| Consultar tipos de producto disponibles | No Mostrar | Mostrar |
| Registrar tipo de producto | No Requerido | Requerido |
| Actualizar tipo de producto | Requerido / No Modificable | Requerido / Modificable |
| Eliminar tipo de producto | Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos tipos de producto con el mismo Nombre. | Nombre |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Consultar tipos de producto disponibles | Permite consultar los tipos de producto disponibles en el sistema para clasificar un producto. | id | UUID | Id necesario para realizar la consulta de los tipos de producto disponibles. | Lista de Tipos de Producto | Conjunto de tipos de producto registrados y disponibles para selección. | P-TP-001 | Asegurar que el tipo de producto exista con el identificador indicado. | No existen tipos de producto disponibles. | Informar que el tipo de producto que está buscando no existe. |
| Registrar tipo de producto | Permite agregar un nuevo tipo de producto al catalogo, para cuando la pizzeria empiece a manejar una categoria de producto que no exista todavia. | Nombre | Alfanumerico | Nombre del nuevo tipo de producto que se desea registrar. | TipoProducto | Tipo de producto registrado. | P-TP-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | El dato ingresado no es valido segun el tipo de dato, la obligatoriedad o el formato esperado. | Rechazar el registro e informar cual dato no es valido y por que. |
|  |  |  |  |  |  |  | P-TP-003 | Asegurar que no exista otro tipo de producto con el mismo Nombre al registrarlo. | Ya existe otro tipo de producto registrado con el mismo Nombre. | Rechazar el registro e informar que ya existe un tipo de producto con ese Nombre. |
| Actualizar tipo de producto | Permite modificar el nombre de un tipo de producto que ya existe. | Id | UUID | Identificador del tipo de producto que se desea modificar, junto con el nuevo Nombre. | TipoProducto | Tipo de producto actualizado. | P-TP-004 | Asegurar que el tipo de producto que se desea modificar se encuentre registrado en el sistema. | El tipo de producto no existe en el sistema. | Rechazar la actualizacion e informar que el tipo de producto no existe. |
|  |  |  |  |  |  |  | P-TP-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | El nuevo dato ingresado no es valido segun el tipo de dato, la obligatoriedad o el formato esperado. | Rechazar la actualizacion e informar cual dato no es valido y por que. |
| Eliminar tipo de producto | Permite eliminar un tipo de producto que no tenga productos asociados que deban conservarse. | Id | UUID | Identificador del tipo de producto que se desea eliminar. | TipoProducto | Tipo de producto eliminado correctamente. | P-TP-005 | Asegurar que un tipo de producto no pueda eliminarse cuando tenga productos asociados que deban conservarse para mantener la trazabilidad. | El tipo de producto tiene productos asociados que deben conservarse. | Rechazar la eliminacion e informar que el tipo de producto tiene productos asociados. |
|  |  |  |  |  |  |  | P-TP-006 | Asegurar que el tipo de producto que se desea eliminar exista con el identificador indicado. | El tipo de producto no existe en el sistema. | Rechazar la eliminacion e informar que el tipo de producto no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Consultar tipos de producto disponibles | Permite consultar los tipos de producto disponibles en el sistema para clasificar un producto. | TipoProducto |  |  | P-TP-001 |  | Tipos de producto consultados |  |
| Administrador | Registrar tipo de producto | Permite agregar un nuevo tipo de producto al catalogo, para cuando la pizzeria empiece a manejar una categoria de producto que no exista todavia. | TipoProducto |  |  | P-TP-002 |  | Tipo de producto registrado |  |
|  |  |  |  |  |  | P-TP-003 |  |  |  |
| Administrador | Actualizar tipo de producto | Permite modificar el nombre de un tipo de producto que ya existe. | TipoProducto |  |  | P-TP-004 |  | Tipo de producto actualizado |  |
|  |  |  |  |  |  | P-TP-002 |  |  |  |
| Administrador | Eliminar tipo de producto | Permite eliminar un tipo de producto que no tenga productos asociados que deban conservarse. | TipoProducto |  |  | P-TP-005 |  | Tipo de producto eliminado |  |
|  |  |  |  |  |  | P-TP-006 |  |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-TP-001 | Asegurar que el tipo de producto exista con el identificador indicado. | TipoProducto |  |
| P-TP-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | TipoProducto |  |
| P-TP-003 | Asegurar que no exista otro tipo de producto con el mismo Nombre al registrarlo. | TipoProducto |  |
| P-TP-004 | Asegurar que el tipo de producto que se desea modificar se encuentre registrado en el sistema. | TipoProducto |  |
| P-TP-005 | Asegurar que un tipo de producto no pueda eliminarse cuando tenga productos asociados que deban conservarse para mantener la trazabilidad. | TipoProducto |  |
| P-TP-006 | Asegurar que el tipo de producto que se desea eliminar exista con el identificador indicado. | TipoProducto |  |
