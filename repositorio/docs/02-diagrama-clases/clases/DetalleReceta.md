# DetalleReceta

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: DetalleReceta](../../01-modelo-dominio/objetos/DetalleReceta.md) · [Entidad MER: DetalleReceta](../../03-modelo-entidad-relacion/entidades/DetalleReceta.md)

| Campo | Valor |
|---|---|
| Clase | DetalleReceta |
| Estereotipo | class |
| Descripción | Representa cada uno de los ingredientes necesarios para elaborar una receta, indicando la cantidad requerida y la unidad de medida utilizada. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | ProductoInterno |
| Necesita de | Asociación | UnidadMedida |
| Necesita de | Composición | Producto |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del detalle de receta. |
| private | productoInterno | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno (ingrediente) que se utiliza en la receta. |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad requerida del ingrediente para elaborar el producto. |
| private | unidadMedida | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida de la cantidad del ingrediente; se asigna automáticamente según el tipo de medida del producto interno (g, ml o und). |
| private | producto | Otro | Producto | no final | no static | Instancia por defecto de Producto | Producto (pizza o adición) al que pertenece esta línea de receta. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | DetalleReceta | Parametrizado | Constructor privado: crea la instancia de DetalleReceta copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getUnidadMedida | getter | no final | Método encargado de obtener el valor del atributo unidadMedida. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | getProducto | getter | no final | Método encargado de obtener el valor del atributo producto. |  |  |  |  |  |  | Otro | Producto |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | agregarIngredienteAReceta | no static | no final | Permite incorporar un ingrediente a la composición de una receta indicando la cantidad necesaria para su elaboración. | detalleReceta | Otro | DetalleReceta | Por valor | no final | Información necesaria para incorporar el ingrediente a la receta. | Otro | DetalleReceta | Los datos ingresados para registrar el detalle de receta no son válidos.<br>El producto (pizza o adición) al que pertenece el detalle de receta no existe.<br>El producto se encuentra inactivo.<br>El producto asociado al detalle de receta no es una pizza ni una adición.<br>El producto interno que se quiere asociar al detalle de receta no existe.<br>El producto interno se encuentra inactivo.<br>La cantidad no es mayor que cero o supera 10.000.<br>No fue posible determinar la unidad de medida a partir del producto interno.<br>El producto interno ya se encuentra registrado dentro de la misma receta.<br>El identificador generado ya está siendo utilizado. |
| public | modificarCantidadDeIngrediente | no static | no final | Permite ajustar la cantidad de un ingrediente requerida para la elaboración de una receta. | detalleReceta | Otro | DetalleReceta | Por valor | no final | Identificador del detalle y nueva información necesaria para modificar la cantidad. | Otro | DetalleReceta | El detalle de receta que se desea actualizar no existe.<br>La nueva cantidad no es mayor que cero o supera 10.000. |
| public | retirarIngredienteDeReceta | no static | no final | Permite retirar un ingrediente de la composición de una receta cuando deja de ser requerido para su elaboración. | id | Otro | UUID | Por valor | no final | Identificador del detalle de receta que se desea retirar. | void |  | El detalle de receta no existe con el identificador indicado. |
| public | consultarIngredientesPorFiltro | static | final | Permite consultar los ingredientes de recetas que cumplan con los criterios indicados, por ejemplo todos los ingredientes de un producto. | filtro | Otro | DetalleReceta | Por valor | no final | Criterios de búsqueda. | Otro | List&lt;DetalleReceta&gt; | No existen ingredientes que cumplan con el filtro. |
| public | consultarRecetasDeProductosActivos | static | final | Permite consultar las recetas (ingredientes) de los productos que la pizzería ofrece actualmente. |  |  |  |  |  |  | Otro | List&lt;DetalleReceta&gt; | No existen recetas de productos activos. |
| public | consultarRecetasDeProductosInactivos | static | final | Permite consultar las recetas de los productos que la pizzería ya no ofrece, para conservar su historial. |  |  |  |  |  |  | Otro | List&lt;DetalleReceta&gt; | No existen recetas de productos inactivos. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInterno | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | unidadMedida | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | unidadMedida | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo unidadMedida. | Otro | Builder |  |
| public | producto | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Producto. | producto | Otro | Producto | Por valor | no final | Valor que se desea asignar al atributo producto. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de DetalleReceta con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | DetalleReceta |  |
