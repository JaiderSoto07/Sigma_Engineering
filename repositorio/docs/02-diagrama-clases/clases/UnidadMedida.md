# UnidadMedida

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: UnidadMedida](../../01-modelo-dominio/objetos/UnidadMedida.md) · [Entidad MER: UnidadMedida](../../03-modelo-entidad-relacion/entidades/UnidadMedida.md)

| Campo | Valor |
|---|---|
| Clase | UnidadMedida |
| Estereotipo | class |
| Descripción | Representa las unidades utilizadas por la pizzería para cuantificar los ingredientes, productos y demás elementos que requieren control de cantidades. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | ProductoInterno |
| Es usada por | Asociación | DetalleReceta |
| Es usada por | Asociación | Inventario |
| Es usada por | Asociación | Lote |
| Es usada por | Asociación | DetalleCompra |
| Es usada por | Asociación | ConsumoVenta |
| Es usada por | Asociación | Cambio |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de la unidad de medida. |
| private | unidadMedida | String |  | no final | no static | "" (cadena vacía) | Símbolo o nombre corto de la unidad de medida (por ejemplo: g, kg, ml, l, und). |
| private | tipoMedida | String |  | no final | no static | "" (cadena vacía) | Tipo de magnitud al que corresponde la unidad (peso, volumen o unidades). |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | UnidadMedida | Parametrizado | Constructor privado: crea la instancia de UnidadMedida copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getUnidadMedida | getter | no final | Método encargado de obtener el valor del atributo unidadMedida. |  |  |  |  |  |  | String |  |  |
| public | getTipoMedida | getter | no final | Método encargado de obtener el valor del atributo tipoMedida. |  |  |  |  |  |  | String |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | consultarUnidadesDeMedidaDisponibles | static | final | Permite consultar las unidades de medida precargadas en el sistema para seleccionar la unidad adecuada al registrar cantidades de productos, ingredientes, compras, recetas o movimientos de inventario. | unidadMedida | Otro | UnidadMedida | Por valor | no final | Criterios necesarios para realizar la consulta de las unidades de medida disponibles. | Otro | List&lt;UnidadMedida&gt; | No existen unidades de medida registradas en el catálogo.<br>No existe una unidad de medida asociada al identificador indicado. |
| public | eliminarUnidadDeMedida | no static | no final | Permite eliminar una unidad de medida que no esté siendo utilizada por ningún otro registro de la pizzería. | id | Otro | UUID | Por valor | no final | Identificador de la unidad de medida que se desea eliminar. | void |  | La unidad de medida que se desea eliminar no existe.<br>La unidad de medida está siendo utilizada por un producto interno, un lote o un registro de inventario.<br>La unidad de medida está siendo utilizada por un detalle de receta, un detalle de compra, un cambio o un consumo de venta. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | unidadMedida | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | unidadMedida | String |  | Por valor | no final | Valor que se desea asignar al atributo unidadMedida. | Otro | Builder |  |
| public | tipoMedida | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | tipoMedida | String |  | Por valor | no final | Valor que se desea asignar al atributo tipoMedida. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de UnidadMedida con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | UnidadMedida |  |
