# ProductoInterno

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: ProductoInterno](../../01-modelo-dominio/objetos/ProductoInterno.md) · [Entidad MER: ProductoInterno](../../03-modelo-entidad-relacion/entidades/ProductoInterno.md)

| Campo | Valor |
|---|---|
| Clase | ProductoInterno |
| Estereotipo | class |
| Descripción | Representa cada insumo o producto que la pizzería compra y almacena en su inventario, ya sea que se consuma dentro de una receta o se venda directamente al cliente. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | UnidadMedida |
| Es usada por | Asociación | Producto |
| Es usada por | Asociación | DetalleReceta |
| Es usada por | Asociación | Inventario |
| Es usada por | Asociación | Lote |
| Es usada por | Asociación | DetalleCompra |
| Es usada por | Asociación | ConsumoVenta |
| Es usada por | Asociación | Cambio |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del producto interno. |
| private | nombre | String |  | no final | no static | "" (cadena vacía) | Nombre del producto interno (ingrediente o bebida almacenada). |
| private | perecedero | boolean/Boolean |  | no final | no static | false | Indica si el producto interno es perecedero. |
| private | vidaUtil | int/Integer |  | no final | no static | 0 | Vida útil del producto interno en días: de 1 a 400 si es perecedero y 0 si no lo es. |
| private | tipoMedida | Otro | UnidadMedida | no final | no static | Instancia por defecto de UnidadMedida | Unidad de medida en la que se maneja el producto interno; su tipo (Peso, Volumen o Unidad) define la unidad de las recetas. |
| private | activo | boolean/Boolean |  | no final | no static | true | Indica si el producto interno está activo y disponible para usarse en recetas y compras. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | ProductoInterno | Parametrizado | Constructor privado: crea la instancia de ProductoInterno copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombre | getter | no final | Método encargado de obtener el valor del atributo nombre. |  |  |  |  |  |  | String |  |  |
| public | isPerecedero | getter | no final | Método encargado de obtener el valor del atributo perecedero. |  |  |  |  |  |  | boolean/Boolean |  |  |
| public | getVidaUtil | getter | no final | Método encargado de obtener el valor del atributo vidaUtil. |  |  |  |  |  |  | int/Integer |  |  |
| public | getTipoMedida | getter | no final | Método encargado de obtener el valor del atributo tipoMedida. |  |  |  |  |  |  | Otro | UnidadMedida |  |
| public | isActivo | getter | no final | Método encargado de obtener el valor del atributo activo. |  |  |  |  |  |  | boolean/Boolean |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarProductoInterno | no static | no final | Permite incorporar un nuevo producto interno (ingrediente o bebida almacenada) al inventario de la pizzería. | productoInterno | Otro | ProductoInterno | Por valor | no final | Información propia necesaria para registrar el producto interno. | Otro | ProductoInterno | Los datos ingresados para registrar el producto interno no son válidos.<br>Ya existe otro producto interno registrado con el mismo Nombre.<br>La vida útil no está entre 1 y 400 días para un producto perecedero, o no es 0 para uno no perecedero.<br>La TipoMedida indicada no corresponde a una unidad de medida existente.<br>No fue posible crear el registro de inventario del producto interno. |
| public | actualizarProductoInterno | no static | no final | Permite modificar la información de un producto interno existente. | productoInterno | Otro | ProductoInterno | Por valor | no final | Identificador del producto interno y datos que se desean actualizar. | Otro | ProductoInterno | El producto interno no existe en el sistema.<br>Se intentó modificar el registro "Sin insumo".<br>Los nuevos datos del producto interno no son válidos.<br>El nuevo Nombre ya lo tiene otro producto interno.<br>La nueva vida útil no corresponde a si el producto es perecedero o no. |
| public | inactivarProductoInterno | no static | no final | Permite inactivar un producto interno que la pizzería ya no usa: no se puede usar en nuevas recetas ni compras, pero se conserva su historial. | id | Otro | UUID | Por valor | no final | Identificador del producto interno que se desea inactivar. | Otro | ProductoInterno | El producto interno que se desea inactivar no existe.<br>El producto interno ya se encuentra inactivo.<br>Se intentó inactivar el registro "Sin insumo".<br>El producto interno tiene lotes, compras o recetas registradas. |
| public | consultarProductosInternosActivos | static | final | Permite consultar los productos internos activos, por ejemplo para escoger los ingredientes de una receta. |  |  |  |  |  |  | Otro | List&lt;ProductoInterno&gt; | No existen productos internos activos. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombre | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | nombre | String |  | Por valor | no final | Valor que se desea asignar al atributo nombre. | Otro | Builder |  |
| public | perecedero | no static | no final | Asigna el valor recibido. | perecedero | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo perecedero. | Otro | Builder |  |
| public | vidaUtil | no static | no final | Asigna el valor recibido; si es nulo, asigna 0. | vidaUtil | int/Integer |  | Por valor | no final | Valor que se desea asignar al atributo vidaUtil. | Otro | Builder |  |
| public | tipoMedida | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de UnidadMedida. | tipoMedida | Otro | UnidadMedida | Por valor | no final | Valor que se desea asignar al atributo tipoMedida. | Otro | Builder |  |
| public | activo | no static | no final | Asigna el valor recibido. | activo | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo activo. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de ProductoInterno con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | ProductoInterno |  |
