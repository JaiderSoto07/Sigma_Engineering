# ClaseMovimiento

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: ClaseMovimiento](../../01-modelo-dominio/objetos/ClaseMovimiento.md) · [Entidad MER: ClaseMovimiento](../../03-modelo-entidad-relacion/entidades/ClaseMovimiento.md)

| Campo | Valor |
|---|---|
| Clase | ClaseMovimiento |
| Estereotipo | class |
| Descripción | Representa la clase de cada movimiento de inventario, indicando si corresponde a una entrada o a una salida de existencias. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | MovimientoInventario |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de la clase de movimiento. |
| private | nombre | String |  | no final | no static | "" (cadena vacía) | Nombre de la clase de movimiento (Entrada o Salida): indica si el movimiento suma o resta inventario. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | ClaseMovimiento | Parametrizado | Constructor privado: crea la instancia de ClaseMovimiento copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombre | getter | no final | Método encargado de obtener el valor del atributo nombre. |  |  |  |  |  |  | String |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | consultarClasesDeMovimientoDisponibles | static | final | Permite consultar las clases de movimiento (Entrada o Salida) disponibles para clasificar un movimiento de inventario. |  |  |  |  |  |  | Otro | List&lt;ClaseMovimiento&gt; | No existen clases de movimiento disponibles. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombre | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | nombre | String |  | Por valor | no final | Valor que se desea asignar al atributo nombre. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de ClaseMovimiento con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | ClaseMovimiento |  |
