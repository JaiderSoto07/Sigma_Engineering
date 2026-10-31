# TipoProducto

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: TipoProducto](../../01-modelo-dominio/objetos/TipoProducto.md) · [Entidad MER: TipoProducto](../../03-modelo-entidad-relacion/entidades/TipoProducto.md)

| Campo | Valor |
|---|---|
| Clase | TipoProducto |
| Estereotipo | class |
| Descripción | Representa la clasificación de cada producto que ofrece la pizzería, indicando si corresponde a una pizza, una bebida o una adición. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | Producto |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del tipo de producto. |
| private | nombre | String |  | no final | no static | "" (cadena vacía) | Nombre de la clasificación del producto (Pizza, Bebida o Adición). |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | TipoProducto | Parametrizado | Constructor privado: crea la instancia de TipoProducto copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombre | getter | no final | Método encargado de obtener el valor del atributo nombre. |  |  |  |  |  |  | String |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | consultarTiposDeProductoDisponibles | static | final | Permite consultar los tipos de producto disponibles en el sistema para clasificar un producto. |  |  |  |  |  |  | Otro | List&lt;TipoProducto&gt; | No existen tipos de producto disponibles. |
| public | registrarTipoDeProducto | no static | no final | Permite agregar un nuevo tipo de producto al catálogo, para cuando la pizzería empiece a manejar una categoría de producto que no exista todavía. | tipoProducto | Otro | TipoProducto | Por valor | no final | Nombre del nuevo tipo de producto que se desea registrar. | Otro | TipoProducto | El dato ingresado no es válido según el tipo de dato, la obligatoriedad o el formato esperado.<br>Ya existe otro tipo de producto registrado con el mismo Nombre. |
| public | actualizarTipoDeProducto | no static | no final | Permite modificar el nombre de un tipo de producto que ya existe. | tipoProducto | Otro | TipoProducto | Por valor | no final | Identificador del tipo de producto que se desea modificar, junto con el nuevo nombre. | Otro | TipoProducto | El tipo de producto no existe en el sistema.<br>El nuevo dato ingresado no es válido según el tipo de dato, la obligatoriedad o el formato esperado. |
| public | eliminarTipoDeProducto | no static | no final | Permite eliminar un tipo de producto que no tenga productos asociados que deban conservarse. | id | Otro | UUID | Por valor | no final | Identificador del tipo de producto que se desea eliminar. | void |  | El tipo de producto tiene productos asociados que deben conservarse.<br>El tipo de producto no existe en el sistema. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombre | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | nombre | String |  | Por valor | no final | Valor que se desea asignar al atributo nombre. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de TipoProducto con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | TipoProducto |  |
