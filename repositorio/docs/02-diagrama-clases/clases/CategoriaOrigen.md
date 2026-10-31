# CategoriaOrigen

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: CategoriaOrigen](../../01-modelo-dominio/objetos/CategoriaOrigen.md) · [Entidad MER: CategoriaOrigen](../../03-modelo-entidad-relacion/entidades/CategoriaOrigen.md)

| Campo | Valor |
|---|---|
| Clase | CategoriaOrigen |
| Estereotipo | class |
| Descripción | Representa la categoría de origen de la operación que genera un movimiento de inventario: una compra, una venta o un cambio de producto. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | TipoMovimiento |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de la categoría de origen. |
| private | nombre | String |  | no final | no static | "" (cadena vacía) | Nombre de la categoría de origen de la operación que mueve el inventario (Compra, Venta o Cambio). |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | CategoriaOrigen | Parametrizado | Constructor privado: crea la instancia de CategoriaOrigen copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombre | getter | no final | Método encargado de obtener el valor del atributo nombre. |  |  |  |  |  |  | String |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | consultarCategoriasDeOrigenDisponibles | static | final | Permite consultar las categorías de origen (Compra, Venta o Cambio) que indican que operación generó un movimiento de inventario. |  |  |  |  |  |  | Otro | List&lt;CategoriaOrigen&gt; | No existen categorías de origen disponibles. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombre | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | nombre | String |  | Por valor | no final | Valor que se desea asignar al atributo nombre. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de CategoriaOrigen con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | CategoriaOrigen |  |
