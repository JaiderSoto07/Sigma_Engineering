# Producto

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Producto](../../01-modelo-dominio/objetos/Producto.md) · [Entidad MER: Producto](../../03-modelo-entidad-relacion/entidades/Producto.md)

| Campo | Valor |
|---|---|
| Clase | Producto |
| Estereotipo | class |
| Descripción | Representa los artículos que la pizzería comercializa y que pueden ser vendidos al cliente, sirviendo como referencia para identificar y gestionar su oferta comercial. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | TipoProducto |
| Necesita de | Asociación | Tamano |
| Necesita de | Asociación | ProductoInterno |
| Es usada por | Composición | DetalleReceta |
| Es usada por | Asociación | DetalleVenta |
| Es usada por | Asociación | HistoricoPrecio |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del producto. |
| private | nombre | String |  | no final | no static | "" (cadena vacía) | Nombre del producto que ofrece la pizzería. |
| private | tipoProducto | Otro | TipoProducto | no final | no static | Instancia por defecto de TipoProducto | Clasificación a la que pertenece el producto (Pizza, Bebida o Adición). |
| private | tamano | Otro | Tamano | no final | no static | Instancia por defecto de Tamano | Presentación de tamaño del producto; los productos que no son pizza usan "No aplica". |
| private | productoInterno | boolean/Boolean |  | no final | no static | false | Indica si el producto se vende directamente desde el inventario, sin necesidad de receta. |
| private | productoInternoAsociado | Otro | ProductoInterno | no final | no static | Instancia por defecto de ProductoInterno | Producto interno asociado: si el producto se vende directamente desde el inventario es el producto interno con el mismo nombre; si no, es el registro "Sin insumo". |
| private | precio | Otro | BigDecimal | no final | no static | 0 | Precio de venta al público del producto; coincide con el precio vigente en el histórico de precios. |
| private | activo | boolean/Boolean |  | no final | no static | true | Indica si el producto está activo y disponible para la venta. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Producto | Parametrizado | Constructor privado: crea la instancia de Producto copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombre | getter | no final | Método encargado de obtener el valor del atributo nombre. |  |  |  |  |  |  | String |  |  |
| public | getTipoProducto | getter | no final | Método encargado de obtener el valor del atributo tipoProducto. |  |  |  |  |  |  | Otro | TipoProducto |  |
| public | getTamano | getter | no final | Método encargado de obtener el valor del atributo tamano. |  |  |  |  |  |  | Otro | Tamano |  |
| public | isProductoInterno | getter | no final | Método encargado de obtener el valor del atributo productoInterno. |  |  |  |  |  |  | boolean/Boolean |  |  |
| public | getProductoInternoAsociado | getter | no final | Método encargado de obtener el valor del atributo productoInternoAsociado. |  |  |  |  |  |  | Otro | ProductoInterno |  |
| public | getPrecio | getter | no final | Método encargado de obtener el valor del atributo precio. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | isActivo | getter | no final | Método encargado de obtener el valor del atributo activo. |  |  |  |  |  |  | boolean/Boolean |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarProducto | no static | no final | Permite incorporar un nuevo producto a la oferta comercial de la pizzería, definiendo la información necesaria para su identificación y comercialización. | producto | Otro | Producto | Por valor | no final | Información propia necesaria para registrar el producto. | Otro | Producto | Los datos ingresados para registrar el producto no cumplen las reglas establecidas.<br>El identificador generado ya está siendo utilizado por otro producto.<br>Ya existe otro producto con el mismo Nombre y el mismo Tamaño.<br>El tipo de producto utilizado no corresponde a un tipo válido definido por la pizzería.<br>No existe un producto interno con el mismo nombre del producto.<br>El tamaño utilizado no corresponde a un tamaño válido definido por la pizzería.<br>No fue posible registrar el precio inicial en el histórico de precios. |
| public | inactivarProducto | no static | no final | Permite inactivar un producto que la pizzería ya no ofrece: no se puede vender, pero se conserva su historial de precios, ventas y recetas. | id | Otro | UUID | Por valor | no final | Identificador del producto que se desea inactivar. | Otro | Producto | El producto no existe con el identificador indicado.<br>El producto ya se encuentra inactivo.<br>El producto tiene precios, ventas o recetas registradas. |
| public | consultarProductosActivos | static | final | Permite consultar los productos activos que la pizzería ofrece, por ejemplo para registrar una venta o armar una receta. |  |  |  |  |  |  | Otro | List&lt;Producto&gt; | No existen productos activos. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombre | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | nombre | String |  | Por valor | no final | Valor que se desea asignar al atributo nombre. | Otro | Builder |  |
| public | tipoProducto | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de TipoProducto. | tipoProducto | Otro | TipoProducto | Por valor | no final | Valor que se desea asignar al atributo tipoProducto. | Otro | Builder |  |
| public | tamano | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Tamano. | tamano | Otro | Tamano | Por valor | no final | Valor que se desea asignar al atributo tamano. | Otro | Builder |  |
| public | productoInterno | no static | no final | Asigna el valor recibido. | productoInterno | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo productoInterno. | Otro | Builder |  |
| public | productoInternoAsociado | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de ProductoInterno. | productoInternoAsociado | Otro | ProductoInterno | Por valor | no final | Valor que se desea asignar al atributo productoInternoAsociado. | Otro | Builder |  |
| public | precio | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | precio | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo precio. | Otro | Builder |  |
| public | activo | no static | no final | Asigna el valor recibido. | activo | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo activo. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Producto con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Producto |  |
