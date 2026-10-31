# SalidaLote

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: SalidaLote](../../01-modelo-dominio/objetos/SalidaLote.md) · [Entidad MER: SalidaLote](../../03-modelo-entidad-relacion/entidades/SalidaLote.md)

| Campo | Valor |
|---|---|
| Clase | SalidaLote |
| Estereotipo | class |
| Descripción | Representa el retiro de una cantidad de producto de un lote próximo a vencer; un lote puede tener como máximo un retiro y este no genera movimiento de inventario. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | Lote |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de cada retiro realizado sobre un lote. |
| private | lote | Otro | Lote | no final | no static | Instancia por defecto de Lote | Lote del cual se retira el producto; un lote solo puede tener un retiro. |
| private | cantidad | Otro | BigDecimal | no final | no static | 0 | Cantidad de producto que se retira efectivamente del lote; no puede superar su saldo. |
| private | fechaMovimiento | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha en la que se realiza el retiro del producto del lote. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | SalidaLote | Parametrizado | Constructor privado: crea la instancia de SalidaLote copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getLote | getter | no final | Método encargado de obtener el valor del atributo lote. |  |  |  |  |  |  | Otro | Lote |  |
| public | getCantidad | getter | no final | Método encargado de obtener el valor del atributo cantidad. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getFechaMovimiento | getter | no final | Método encargado de obtener el valor del atributo fechaMovimiento. |  |  |  |  |  |  | Otro | LocalDate |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarSalidaDeLote | no static | no final | Registra el retiro de una cantidad de un lote próximo a vencer, después de atender la alerta de vencimiento. | salidaLote | Otro | SalidaLote | Por valor | no final | Lote, cantidad y fecha del retiro. | Otro | SalidaLote | Los datos de la salida no son válidos.<br>El lote no existe o no tiene saldo disponible.<br>La cantidad a retirar supera el saldo del lote.<br>El lote ya tiene una salida registrada.<br>No fue posible actualizar el saldo del lote o el inventario. |
| public | consultarSalidaDeLote | static | final | Permite consultar un retiro registrado sobre un lote. | id | Otro | UUID | Por valor | no final | Identificador de la salida de lote. | Otro | SalidaLote | No existe una salida de lote con el identificador indicado. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | lote | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Lote. | lote | Otro | Lote | Por valor | no final | Valor que se desea asignar al atributo lote. | Otro | Builder |  |
| public | cantidad | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | cantidad | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo cantidad. | Otro | Builder |  |
| public | fechaMovimiento | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaMovimiento | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaMovimiento. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de SalidaLote con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | SalidaLote |  |
