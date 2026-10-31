# Tamano

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Tamaño](../../01-modelo-dominio/objetos/Tamano.md) · [Entidad MER: Tamaño](../../03-modelo-entidad-relacion/entidades/Tamano.md)

| Campo | Valor |
|---|---|
| Clase | Tamano |
| Estereotipo | class |
| Descripción | Representa las distintas presentaciones de tamaño en las que la pizzería puede ofrecer un producto; los productos que no son pizza usan "No aplica". |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | Producto |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del tamaño. |
| private | tamano | String |  | no final | no static | "" (cadena vacía) | Nombre de la presentación de tamaño (Grande, Mediana, Pequeña o No aplica). |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Tamano | Parametrizado | Constructor privado: crea la instancia de Tamano copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getTamano | getter | no final | Método encargado de obtener el valor del atributo tamano. |  |  |  |  |  |  | String |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | consultarTamanosDisponibles | static | final | Permite consultar los tamaños disponibles en el sistema para definir el tamaño de un producto. |  |  |  |  |  |  | Otro | List&lt;Tamano&gt; | No existen tamaños disponibles. |
| public | registrarTamano | no static | no final | Permite agregar un nuevo tamaño al catálogo, para cuando la pizzería empiece a ofrecer una presentación que no exista todavía. | tamano | Otro | Tamano | Por valor | no final | Nombre del nuevo tamaño que se desea registrar. | Otro | Tamano | El dato ingresado no es válido según el tipo de dato, la obligatoriedad o el formato esperado.<br>Ya existe otro tamaño registrado con el mismo Nombre. |
| public | actualizarTamano | no static | no final | Permite modificar el nombre de un tamaño que ya existe. | tamano | Otro | Tamano | Por valor | no final | Identificador del tamaño que se desea modificar, junto con el nuevo nombre. | Otro | Tamano | El tamaño no existe en el sistema.<br>El nuevo dato ingresado no es válido según el tipo de dato, la obligatoriedad o el formato esperado. |
| public | eliminarTamano | no static | no final | Permite eliminar un tamaño que no tenga productos asociados que deban conservarse. | id | Otro | UUID | Por valor | no final | Identificador del tamaño que se desea eliminar. | void |  | El tamaño tiene productos asociados que deben conservarse.<br>El tamaño no existe en el sistema. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | tamano | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | tamano | String |  | Por valor | no final | Valor que se desea asignar al atributo tamano. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Tamano con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Tamano |  |
