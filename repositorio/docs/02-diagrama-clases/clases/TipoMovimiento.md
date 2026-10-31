# TipoMovimiento

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: TipoMovimiento](../../01-modelo-dominio/objetos/TipoMovimiento.md) · [Entidad MER: TipoMovimiento](../../03-modelo-entidad-relacion/entidades/TipoMovimiento.md)

| Campo | Valor |
|---|---|
| Clase | TipoMovimiento |
| Estereotipo | class |
| Descripción | Representa el código de una operación concreta que mueve el inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo); pertenece a una categoría de origen y lo comparten la operación y los movimientos que genera. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | CategoriaOrigen |
| Es usada por | Asociación | DetalleCompra |
| Es usada por | Asociación | ConsumoVenta |
| Es usada por | Asociación | Cambio |
| Es usada por | Asociación | MovimientoInventario |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Código único de la operación concreta que genera movimientos de inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo). |
| private | categoriaOrigen | Otro | CategoriaOrigen | no final | no static | Instancia por defecto de CategoriaOrigen | Categoría de origen a la que pertenece la operación (Compra, Venta o Cambio). |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | TipoMovimiento | Parametrizado | Constructor privado: crea la instancia de TipoMovimiento copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getCategoriaOrigen | getter | no final | Método encargado de obtener el valor del atributo categoriaOrigen. |  |  |  |  |  |  | Otro | CategoriaOrigen |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | generarTipoDeMovimiento | no static | no final | Genera un nuevo código de operación con su categoría de origen cada vez que se registra un detalle de compra, un consumo de venta o un cambio. | categoriaOrigen | Otro | CategoriaOrigen | Por valor | no final | Categoría de origen de la operación que genera el código. | Otro | TipoMovimiento | La categoría de origen no existe.<br>El identificador generado ya está siendo utilizado. |
| public | consultarTipoDeMovimiento | static | final | Permite consultar un tipo de movimiento para conocer la categoría de origen de la operación. | id | Otro | UUID | Por valor | no final | Identificador del tipo de movimiento que se desea consultar. | Otro | TipoMovimiento | No existe un tipo de movimiento con el identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | categoriaOrigen | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de CategoriaOrigen. | categoriaOrigen | Otro | CategoriaOrigen | Por valor | no final | Valor que se desea asignar al atributo categoriaOrigen. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de TipoMovimiento con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | TipoMovimiento |  |
