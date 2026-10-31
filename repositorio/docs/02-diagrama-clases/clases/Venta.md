# Venta

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Venta](../../01-modelo-dominio/objetos/Venta.md) · [Entidad MER: Venta](../../03-modelo-entidad-relacion/entidades/Venta.md)

| Campo | Valor |
|---|---|
| Clase | Venta |
| Estereotipo | class |
| Descripción | Representa la operación comercial mediante la cual la pizzería registra los productos adquiridos por un cliente. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Composición | DetalleVenta |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de la venta. |
| private | fecha | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha en la que se realizó la venta. |
| private | hora | Otro | LocalTime | no final | no static | 00:00 | Hora en la que se realizó la venta. |
| private | factura | String |  | no final | no static | "" (cadena vacía) | Número de la factura de venta (FV- seguido de 6 dígitos); el sistema lo asigna al confirmar la venta. |
| private | cliente | String |  | no final | no static | 2222222222 | Documento del cliente; si no se indica, se registra 2222222222 (consumidor final). |
| private | total | Otro | BigDecimal | no final | no static | 0 | Valor total de la venta, calculado a partir de la suma de sus detalles de venta. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Venta | Parametrizado | Constructor privado: crea la instancia de Venta copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getFecha | getter | no final | Método encargado de obtener el valor del atributo fecha. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getHora | getter | no final | Método encargado de obtener el valor del atributo hora. |  |  |  |  |  |  | Otro | LocalTime |  |
| public | getFactura | getter | no final | Método encargado de obtener el valor del atributo factura. |  |  |  |  |  |  | String |  |  |
| public | getCliente | getter | no final | Método encargado de obtener el valor del atributo cliente. |  |  |  |  |  |  | String |  |  |
| public | getTotal | getter | no final | Método encargado de obtener el valor del atributo total. |  |  |  |  |  |  | Otro | BigDecimal |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarVenta | no static | no final | Permite registrar una venta realizada en el establecimiento, asociando los productos solicitados y la información correspondiente. | venta | Otro | Venta | Por valor | no final | Información necesaria para registrar la venta. | Otro | Venta | Los datos ingresados para registrar la venta no son válidos.<br>Ya existe otra venta registrada con la misma Fecha y la misma Hora.<br>No se indicó el cliente de la venta. |
| public | confirmarVenta | no static | no final | Permite confirmar una venta: se calcula el total, se asigna el número de factura, se registran los consumos y se descuenta el inventario por FIFO. Una venta confirmada ya no se puede modificar. | id | Otro | UUID | Por valor | no final | Identificador de la venta que se desea confirmar. | Otro | Venta | La venta que se desea confirmar no existe.<br>La venta ya se encuentra confirmada.<br>La venta no contiene ningún detalle de venta.<br>Alguno de los detalles asociados a la venta no es válido.<br>No existe disponibilidad suficiente de inventario para atender los productos que requieren consumo.<br>El total no corresponde a la suma de los subtotales o supera 1.000.000.<br>El número de factura que le corresponde a la venta ya está asignado a otra venta.<br>No fue posible registrar los consumos de la venta.<br>No hay saldo suficiente en los lotes para descontar los productos internos. |
| public | eliminarVenta | no static | no final | Permite eliminar una venta que no haya sido confirmada y que no tenga detalles de venta asociados que deban conservarse para mantener la trazabilidad. | id | Otro | UUID | Por valor | no final | Identificador de la venta que se desea eliminar. | void |  | La venta que se desea eliminar no existe.<br>La venta ya se encuentra confirmada.<br>La venta tiene detalles de venta asociados que deben conservarse. |
| public | consultarVenta | static | final | Permite consultar una venta con sus detalles, su total y su número de factura. | id | Otro | UUID | Por valor | no final | Identificador de la venta. | Otro | Venta | No existe una venta con el identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | fecha | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fecha | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fecha. | Otro | Builder |  |
| public | hora | no static | no final | Asigna la hora recibida; si es nula, asigna 00:00. | hora | Otro | LocalTime | Por valor | no final | Valor que se desea asignar al atributo hora. | Otro | Builder |  |
| public | factura | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | factura | String |  | Por valor | no final | Valor que se desea asignar al atributo factura. | Otro | Builder |  |
| public | cliente | no static | no final | Asigna el documento recibido sin espacios en blanco al inicio y al final; si es nulo o vacío, asigna 2222222222 (consumidor final). | cliente | String |  | Por valor | no final | Valor que se desea asignar al atributo cliente. | Otro | Builder |  |
| public | total | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | total | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo total. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Venta con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Venta |  |
