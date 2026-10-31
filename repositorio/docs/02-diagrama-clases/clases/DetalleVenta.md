# DetalleVenta

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: DetalleVenta](../../01-modelo-dominio/objetos/DetalleVenta.md) · [Entidad MER: DetalleVenta](../../03-modelo-entidad-relacion/entidades/DetalleVenta.md)

| Campo | Valor |
|---|---|
| Clase | DetalleVenta |
| Estereotipo | class |
| Descripción | Representa cada uno de los productos incluidos en una venta, registrando la cantidad adquirida y el valor correspondiente a cada producto. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Composición | Venta |
| Necesita de | Asociación | Producto |
| Es usada por | Composición | ConsumoVenta |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del detalle de venta. |
| private | venta | Otro | Venta | no final | no static | Instancia por defecto de Venta | Venta a la que pertenece este detalle. |
| private | cantidad | int/Integer |  | no final | no static | 0 | Cantidad solicitada del producto por el cliente. |
| private | producto | Otro | Producto | no final | no static | Instancia por defecto de Producto | Producto solicitado por el cliente. |
| private | precioProducto | Otro | BigDecimal | no final | no static | 0 | Precio del producto vigente en el momento de la venta. |
| private | subtotal | Otro | BigDecimal | no final | no static | 0 | Valor calculado a partir de la cantidad solicitada y el precio del producto. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | DetalleVenta | Parametrizado | Constructor privado: crea la instancia de DetalleVenta copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getVenta | getter | no final | Método encargado de obtener el valor del atributo venta. |  |  |  |  |  |  | Otro | Venta |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | int/Integer |  |  |
| public | getProducto | getter | no final | Método encargado de obtener el valor del atributo producto. |  |  |  |  |  |  | Otro | Producto |  |
| public | getPrecioProducto | getter | no final | Método encargado de obtener el valor del atributo precioProducto. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getSubtotal | getter | no final | Método encargado de obtener el valor del atributo subtotal. |  |  |  |  |  |  | Otro | BigDecimal |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | agregarProductoAVenta | no static | no final | Permite incorporar a la venta un producto solicitado. | detalleVenta | Otro | DetalleVenta | Por valor | no final | Información necesaria para incorporar el producto a la venta. | Otro | DetalleVenta | La venta a la que se agrega el producto no existe.<br>La venta ya fue confirmada.<br>La información del detalle de venta no es válida o está incompleta.<br>El producto no existe o se encuentra inactivo.<br>La cantidad no es un número entero entre 1 y 1000.<br>El precio registrado no corresponde al precio vigente del producto en el momento de la venta.<br>El producto ya está en la venta.<br>El subtotal no corresponde a la cantidad por el precio. |
| public | modificarCantidadDeProducto | no static | no final | Permite ajustar la cantidad de un producto incluido en la venta. | detalleVenta | Otro | DetalleVenta | Por valor | no final | Identificador del detalle y nueva información necesaria para modificar la cantidad. | Otro | DetalleVenta | El detalle de venta que se pretende modificar no existe.<br>La venta ya fue confirmada.<br>La nueva cantidad no es un número entero entre 1 y 1000.<br>El subtotal no corresponde a la cantidad por el precio. |
| public | retirarProductoDeLaVenta | no static | no final | Permite quitar un producto de una venta que todavía no ha sido confirmada. | id | Otro | UUID | Por valor | no final | Identificador del detalle de venta. | void |  | El detalle de venta que se desea retirar no existe.<br>La venta ya fue confirmada. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | venta | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Venta. | venta | Otro | Venta | Por valor | no final | Valor que se desea asignar al atributo venta. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | int/Integer |  | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | producto | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Producto. | producto | Otro | Producto | Por valor | no final | Valor que se desea asignar al atributo producto. | Otro | Builder |  |
| public | precioProducto | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | precioProducto | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo precioProducto. | Otro | Builder |  |
| public | subtotal | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | subtotal | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo subtotal. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de DetalleVenta con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | DetalleVenta |  |
