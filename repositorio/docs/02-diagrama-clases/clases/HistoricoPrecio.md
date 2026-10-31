# HistoricoPrecio

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: HistoricoPrecio](../../01-modelo-dominio/objetos/HistoricoPrecio.md) · [Entidad MER: HistoricoPrecio](../../03-modelo-entidad-relacion/entidades/HistoricoPrecio.md)

| Campo | Valor |
|---|---|
| Clase | HistoricoPrecio |
| Estereotipo | class |
| Descripción | Representa el registro de los precios que han tenido los productos de la pizzería a lo largo del tiempo, permitiendo consultar su evolución histórica. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Necesita de | Asociación | Producto |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del registro histórico de precio. |
| private | producto | Otro | Producto | no final | no static | Instancia por defecto de Producto | Producto al que corresponde este registro de precio. |
| private | precio | Otro | BigDecimal | no final | no static | 0 | Precio asignado al producto durante este período de vigencia. |
| private | fechaInicio | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha de inicio de vigencia de este precio. |
| private | fechaFin | Otro | LocalDate | no final | no static | 1000-01-01 | Fecha de finalización de vigencia de este precio; mientras el precio está vigente vale 1000-01-01. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | HistoricoPrecio | Parametrizado | Constructor privado: crea la instancia de HistoricoPrecio copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getProducto | getter | no final | Método encargado de obtener el valor del atributo producto. |  |  |  |  |  |  | Otro | Producto |  |
| public | getPrecio | getter | no final | Método encargado de obtener el valor del atributo precio. |  |  |  |  |  |  | Otro | BigDecimal |  |
| public | getFechaInicio | getter | no final | Método encargado de obtener el valor del atributo fechaInicio. |  |  |  |  |  |  | Otro | LocalDate |  |
| public | getFechaFin | getter | no final | Método encargado de obtener el valor del atributo fechaFin. |  |  |  |  |  |  | Otro | LocalDate |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarPrecio | no static | no final | Permite establecer un nuevo precio para un producto, iniciando su nuevo período de vigencia y conservando el precio anterior para mantener la trazabilidad. | historicoPrecio | Otro | HistoricoPrecio | Por valor | no final | Información necesaria para registrar el nuevo precio y su período de vigencia, conservando el precio anterior como parte del historial. | Otro | HistoricoPrecio | Los datos ingresados para registrar el nuevo precio no son válidos.<br>El producto al que se asocia el nuevo precio no existe.<br>El precio no es mayor que cero o supera 1.000.000.<br>La fecha de inicio de vigencia del precio no es válida.<br>La fecha de finalización es anterior a la fecha de inicio.<br>El nuevo período de vigencia se superpone con otro período vigente del mismo producto.<br>Ya existe un precio vigente para el producto en este momento.<br>No fue posible cerrar el precio vigente anterior. |
| public | consultarHistoricoDePreciosDeUnProducto | static | final | Permite consultar todos los precios que ha tenido un producto, con su período de vigencia. | id | Otro | UUID | Por valor | no final | Identificador del producto. | Otro | List&lt;HistoricoPrecio&gt; | El producto no existe. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | producto | no static | no final | Asigna el valor recibido; si es nulo, asigna la instancia por defecto de Producto. | producto | Otro | Producto | Por valor | no final | Valor que se desea asignar al atributo producto. | Otro | Builder |  |
| public | precio | no static | no final | Asigna el valor recibido; si es nulo o negativo, asigna 0. | precio | Otro | BigDecimal | Por valor | no final | Valor que se desea asignar al atributo precio. | Otro | Builder |  |
| public | fechaInicio | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaInicio | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaInicio. | Otro | Builder |  |
| public | fechaFin | no static | no final | Asigna la fecha recibida; si es nula, asigna la fecha por defecto 1000-01-01. | fechaFin | Otro | LocalDate | Por valor | no final | Valor que se desea asignar al atributo fechaFin. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de HistoricoPrecio con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | HistoricoPrecio |  |
