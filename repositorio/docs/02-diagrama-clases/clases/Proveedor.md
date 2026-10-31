# Proveedor

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Proveedor](../../01-modelo-dominio/objetos/Proveedor.md) · [Entidad MER: Proveedor](../../03-modelo-entidad-relacion/entidades/Proveedor.md)

| Campo | Valor |
|---|---|
| Clase | Proveedor |
| Estereotipo | class |
| Descripción | Representa a las personas o empresas que suministran a la pizzería los insumos y productos necesarios para su operación. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|
| Es usada por | Asociación | Compra |

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único del proveedor. |
| private | nombreEmpresa | String |  | no final | no static | "" (cadena vacía) | Nombre de la empresa a la que pertenece el proveedor. |
| private | nit | String |  | no final | no static | "" (cadena vacía) | Número de identificación tributaria (NIT) del proveedor; no se puede repetir entre proveedores. |
| private | contacto | String |  | no final | no static | "" (cadena vacía) | Forma de comunicación directa con el proveedor. |
| private | activo | boolean/Boolean |  | no final | no static | true | Indica si el proveedor está activo y disponible para nuevas compras. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Proveedor | Parametrizado | Constructor privado: crea la instancia de Proveedor copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getNombreEmpresa | getter | no final | Método encargado de obtener el valor del atributo nombreEmpresa. |  |  |  |  |  |  | String |  |  |
| public | getNit | getter | no final | Método encargado de obtener el valor del atributo nit. |  |  |  |  |  |  | String |  |  |
| public | getContacto | getter | no final | Método encargado de obtener el valor del atributo contacto. |  |  |  |  |  |  | String |  |  |
| public | isActivo | getter | no final | Método encargado de obtener el valor del atributo activo. |  |  |  |  |  |  | boolean/Boolean |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | registrarProveedor | no static | no final | Permite incorporar un nuevo proveedor al sistema. | proveedor | Otro | Proveedor | Por valor | no final | Información necesaria para registrar e identificar al proveedor. | Otro | Proveedor | Los datos ingresados para registrar el proveedor no son válidos.<br>El identificador generado ya está siendo utilizado por otro proveedor.<br>Ya existe otro proveedor registrado con el mismo NIT.<br>Ya existe otro proveedor registrado con el mismo nombre de empresa. |
| public | modificarProveedor | no static | no final | Actualizar la información de un proveedor existente sin afectar la trazabilidad de las compras históricas asociadas a este. | proveedor | Otro | Proveedor | Por valor | no final | Identificador del proveedor y datos que se desean modificar. | Otro | Proveedor | El proveedor que se desea actualizar no existe.<br>Los nuevos datos del proveedor no son válidos.<br>La información modificada genera duplicidad con otro proveedor registrado. |
| public | retirarProveedor | no static | no final | Permite retirar un proveedor con el que la pizzería ya no trabaja: queda inactivo y no se puede usar en nuevas compras, pero se conservan sus compras. | id | Otro | UUID | Por valor | no final | Identificador del proveedor que se desea retirar. | Otro | Proveedor | El proveedor que se desea retirar no existe.<br>El proveedor ya se encuentra retirado.<br>El proveedor tiene compras registradas. |
| public | consultarProveedoresActivos | static | final | Permite consultar los proveedores activos, ordenados por nombre de empresa, para seleccionarlos al registrar una compra. |  |  |  |  |  |  | Otro | List&lt;Proveedor&gt; | No existen proveedores activos. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | nombreEmpresa | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | nombreEmpresa | String |  | Por valor | no final | Valor que se desea asignar al atributo nombreEmpresa. | Otro | Builder |  |
| public | nit | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | nit | String |  | Por valor | no final | Valor que se desea asignar al atributo nit. | Otro | Builder |  |
| public | contacto | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | contacto | String |  | Por valor | no final | Valor que se desea asignar al atributo contacto. | Otro | Builder |  |
| public | activo | no static | no final | Asigna el valor recibido. | activo | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo activo. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Proveedor con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Proveedor |  |
