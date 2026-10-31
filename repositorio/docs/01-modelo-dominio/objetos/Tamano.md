# Tamaño

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Tamano](../../02-diagrama-clases/clases/Tamano.md) · [Entidad MER: Tamaño](../../03-modelo-entidad-relacion/entidades/Tamano.md)

> Son los distintos tamaños en los que la pizzería puede ofrecer una pizza.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Representa el identificador unico de cada tamaño |  |  |
| Tamaño | Nombre del tamaño (Grande, Mediana, Pequeña o No aplica) | Alfanumerico(max 20 caracteres) | Si |

### Datos de muestra

_4 registros de ejemplo._

| Id | Tamaño |
|---|---|
| 1 | Grande |
| 2 | Mediana |
| 3 | Pequeña |
| 4 | No aplica |

## Caracterización

### Atributos

| Nombre atributo | Id | Tamaño |
|---|---|---|
| Descripción atributo | Representa el identificador unico de cada tamaño | Nombre del tamaño (Grande, Mediana, Pequeña o No aplica) |
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
| Reglas especiales (si aplica) |  | Estos son los tamaños con los que arranca el catalogo (Grande, Mediana, Pequeña, No aplica), pero se pueden agregar nuevos si la pizzeria llega a manejar una presentacion de tamaño distinta. |
| Dato calculado | No | No |
| Aplica ajuste del dato (si aplica) | No | No |
| Carácter de ajuste (si aplica) |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si |
| Quitar espacios en blanco al fin (si aplica) | No | Si |
| Dato sensible (si aplica) | No | No |
| Valor por defecto (si aplica) |  |  |
| Auto generado | Si | No |
| Consultar tamaños disponibles | No Mostrar | Mostrar |
| Registrar tamaño | No Requerido | Requerido |
| Actualizar tamaño | Requerido / No Modificable | Requerido / Modificable |
| Eliminar tamaño | Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos tamaños con el mismo Nombre. | Tamaño |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Consultar tamaños disponibles | Permite consultar los tamaños disponibles en el sistema para definir el tamaño de un producto. | id | UUID | Id necesario para realizar la consulta de los tamaños disponibles. | Lista de Tamaños | Conjunto de tamaños registrados y disponibles para selección. | P-TAM-001 | Asegurar que el tamaño exista con el identificador indicado. | No existen tamaños disponibles. | Informar que el tamaño que está buscando no existe. |
| Registrar tamaño | Permite agregar un nuevo tamaño al catalogo, para cuando la pizzeria empiece a ofrecer una presentacion que no exista todavia. | Nombre | Alfanumerico | Nombre del nuevo tamaño que se desea registrar. | Tamaño | Tamaño registrado. | P-TAM-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | El dato ingresado no es valido segun el tipo de dato, la obligatoriedad o el formato esperado. | Rechazar el registro e informar cual dato no es valido y por que. |
|  |  |  |  |  |  |  | P-TAM-003 | Asegurar que no exista otro tamaño con el mismo Nombre al registrarlo. | Ya existe otro tamaño registrado con el mismo Nombre. | Rechazar el registro e informar que ya existe un tamaño con ese Nombre. |
| Actualizar tamaño | Permite modificar el nombre de un tamaño que ya existe. | Id | UUID | Identificador del tamaño que se desea modificar, junto con el nuevo Nombre. | Tamaño | Tamaño actualizado. | P-TAM-004 | Asegurar que el tamaño que se desea modificar se encuentre registrado en el sistema. | El tamaño no existe en el sistema. | Rechazar la actualizacion e informar que el tamaño no existe. |
|  |  |  |  |  |  |  | P-TAM-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | El nuevo dato ingresado no es valido segun el tipo de dato, la obligatoriedad o el formato esperado. | Rechazar la actualizacion e informar cual dato no es valido y por que. |
| Eliminar tamaño | Permite eliminar un tamaño que no tenga productos asociados que deban conservarse. | Id | UUID | Identificador del tamaño que se desea eliminar. | Tamaño | Tamaño eliminado correctamente. | P-TAM-005 | Asegurar que un tamaño no pueda eliminarse cuando tenga productos asociados que deban conservarse para mantener la trazabilidad. | El tamaño tiene productos asociados que deben conservarse. | Rechazar la eliminacion e informar que el tamaño tiene productos asociados. |
|  |  |  |  |  |  |  | P-TAM-006 | Asegurar que el tamaño que se desea eliminar exista con el identificador indicado. | El tamaño no existe en el sistema. | Rechazar la eliminacion e informar que el tamaño no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Consultar tamaños disponibles | Permite consultar los tamaños disponibles en el sistema para definir el tamaño de un producto. | Tamaño |  |  | P-TAM-001 |  | Tamaños consultados |  |
| Administrador | Registrar tamaño | Permite agregar un nuevo tamaño al catalogo, para cuando la pizzeria empiece a ofrecer una presentacion que no exista todavia. | Tamaño |  |  | P-TAM-002 |  | Tamaño registrado |  |
|  |  |  |  |  |  | P-TAM-003 |  |  |  |
| Administrador | Actualizar tamaño | Permite modificar el nombre de un tamaño que ya existe. | Tamaño |  |  | P-TAM-004 |  | Tamaño actualizado |  |
|  |  |  |  |  |  | P-TAM-002 |  |  |  |
| Administrador | Eliminar tamaño | Permite eliminar un tamaño que no tenga productos asociados que deban conservarse. | Tamaño |  |  | P-TAM-005 |  | Tamaño eliminado |  |
|  |  |  |  |  |  | P-TAM-006 |  |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-TAM-001 | Asegurar que el tamaño exista con el identificador indicado. | Tamaño |  |
| P-TAM-002 | Asegurar que los datos ingresados sean validos segun el tipo de dato, la obligatoriedad y el formato correspondiente. | Tamaño |  |
| P-TAM-003 | Asegurar que no exista otro tamaño con el mismo Nombre al registrarlo. | Tamaño |  |
| P-TAM-004 | Asegurar que el tamaño que se desea modificar se encuentre registrado en el sistema. | Tamaño |  |
| P-TAM-005 | Asegurar que un tamaño no pueda eliminarse cuando tenga productos asociados que deban conservarse para mantener la trazabilidad. | Tamaño |  |
| P-TAM-006 | Asegurar que el tamaño que se desea eliminar exista con el identificador indicado. | Tamaño |  |
