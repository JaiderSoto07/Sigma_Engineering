# Compra

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Compra](../../01-modelo-dominio/objetos/Compra.md) · [Entidad MER: Compra](../../03-modelo-entidad-relacion/entidades/Compra.md)

| Campo | Valor |
|---|---|
| Clase | Compra |
| Estereotipo | class |
| Descripción | Representa la adquisición de insumos o productos que realiza la pizzería a un proveedor para abastecer las necesidades de su operación. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | Proveedor |
| Es usada por | Composición | DetalleCompra |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de la compra. |
| private | proveedor | Otro | Proveedor | no final | no static | Instancia por defecto de Proveedor | Proveedor al que se le realizó la compra. |
| private | fechaCompra | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha en la que se efectuó la compra. |
| private | numeroFactura | String |  | no final | no static | "" (cadena vacía) | Número de factura entregado por el proveedor al momento de la compra. |
| private | total | Otro | BigDecimal | no final | no static | 0 | Valor monetario total de la compra, calculado como la suma de los precios de compra de sus detalles; no puede superar 5.000.000. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Compra | Parametrizado | Constructor privado: crea la instancia de Compra copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getProveedor | getter | no final | Método encargado de obtener el valor del atributo proveedor. |  |  |  |  |  |  | Otro | Proveedor |  |
| public | getFechaCompra | getter | no final | Método encargado de obtener el valor del atributo fechaCompra. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getNumeroFactura | getter | no final | Método encargado de obtener el valor del atributo numeroFactura. |  |  |  |  |  |  | String |  |  |
| public | getTotal | getter | no final | Método encargado de obtener el valor del atributo total. |  |  |  |  |  |  | Otro | BigDecimal |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarCompra | no static | no final | Registrar una nueva compra asociada a un proveedor y a la pizzería correspondiente, dejando constancia de la operación de adquisición. | compra | Otro | Compra | Por valor | no final | Información necesaria para registrar la compra. | Otro | Compra | Los datos ingresados para registrar la compra no son válidos.<br>El proveedor asociado a la compra no existe en el sistema.<br>Ya existe otra compra registrada con el mismo Proveedor y el mismo NumeroFactura.<br>El proveedor asociado a la compra se encuentra retirado o inactivo.<br>El total calculado no corresponde a la suma de los detalles o supera 5.000.000. |
| public | modificarCompra | no static | no final | Permite corregir la información de una compra existente antes de que tenga movimientos de inventario asociados. | compra | Otro | Compra | Por valor | no final | Identificador de la compra y datos que se desean modificar. | Otro | Compra | La compra no existe con el identificador indicado.<br>Los nuevos datos de la compra no son válidos.<br>La modificación deja dos compras con el mismo Proveedor y el mismo NumeroFactura.<br>El proveedor indicado se encuentra retirado o inactivo. |
| public | eliminarCompra | no static | no final | Permite eliminar una compra que no tenga detalles de compra asociados que deban conservarse para mantener la trazabilidad. | id | Otro | UUID | Por valor | no final | Identificador de la compra que se desea eliminar. | void |  | La compra que se desea eliminar no existe.<br>La compra tiene detalles de compra asociados que deben conservarse. |
| public | consultarCompra | static | final | Consultar la información registrada de una compra para conocer su proveedor, fecha, pizzería y los detalles asociados. | id | Otro | UUID | Por valor | no final | Identificador que permite localizar la compra que se desea consultar. | Otro | Compra | No existe una compra asociada al identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | proveedor | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Proveedor. | proveedor | Otro | Proveedor | Por valor | no final | Valor que se desea asignar al atributo proveedor. | Otro | Builder |  |
| public | fechaCompra | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaCompra | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaCompra. | Otro | Builder |  |
| public | numeroFactura | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | numeroFactura | String |  | Por valor | no final | Valor que se desea asignar al atributo numeroFactura. | Otro | Builder |  |
| public | total | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | total | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo total. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Compra con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Compra |  |
