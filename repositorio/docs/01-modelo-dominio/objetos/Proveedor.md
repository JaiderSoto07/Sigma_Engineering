# Proveedor

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Proveedor](../../02-diagrama-clases/clases/Proveedor.md) · [Entidad MER: Proveedor](../../03-modelo-entidad-relacion/entidades/Proveedor.md)

> Son las personas o empresas que le venden a la pizzería los insumos que necesita para funcionar.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico del proveedor dentro de la pizzeria |  |  |
| Nombre Empresa | Nombre de la empresa a la que pertenece el proveedor | Alfanumerico(max 60 caracteres) | Si |
| NIT | Numero de identificacion tributaria (NIT) del proveedor; no se puede repetir entre proveedores | Alfanumerico(max 15 caracteres) | Si |
| Contacto | Forma de comunicacion directa con el proveedor | Alfanumerico(max 10 caracteres) | Si |
| Activo | Indica si el proveedor esta activo y disponible para nuevas compras | Tipo Logico | Si |

### Datos de muestra

_4 registros de ejemplo._

| Id | Nombre Empresa | NIT | Contacto | Activo | Combinacion unica 1 | Combinacion unica 2 |
|---|---|---|---|---|---|---|
| 1 | Colanta | 900100001-1 | 3053086510 | Si | Colanta | 900100001-1 |
| 2 | Harinera del valle | 900100002-2 | 3139562216 | Si | Harinera del valle | 900100002-2 |
| 3 | Postobon | 900100003-3 | 3108582230 | Si | Postobon | 900100003-3 |
| 4 | Central de Abastos | 900100004-4 | 3201234567 | Si | Central de Abastos | 900100004-4 |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre Empresa | NIT | Contacto | Activo |
|---|---|---|---|---|---|
| Descripción atributo | Identificador unico del proveedor dentro de la pizzeria | Nombre de la empresa a la que pertenece el proveedor | Numero de identificacion tributaria (NIT) del proveedor; no se puede repetir entre proveedores | Forma de comunicacion directa con el proveedor | Indica si el proveedor esta activo y disponible para nuevas compras |
| Identificador del objeto de dominio | Si | No | No | No | No |
| Tipo de dato | Identificador universal | Alfanumerico | Alfanumerico | Alfanumerico | Tipo Logico |
| Obligatorio | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  | 1 | 1 | 1 |  |
| Longitud máxima (si aplica) |  | 60 | 15 | 10 |  |
| Precisión (si aplica) |  |  |  |  |  |
| Rendondeo (si aplica) |  |  |  |  |  |
| Rango inicial (si aplica) |  |  |  |  |  |
| Rango final (si aplica) |  |  |  |  |  |
| Formato (si aplica) |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Se debe asegurar una sanitización del dato | No puede existir otro proveedor con el mismo NIT. Se debe asegurar una sanitizacion del dato. | Se debe asegurar una sanitizacion del dato | Se registra como activo (Si). Al retirar el proveedor pasa a No y ya no puede usarse en nuevas compras. |
| Dato calculado | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si | Si | Si | No |
| Quitar espacios en blanco al fin (si aplica) | No | Si | Si | Si | No |
| Dato sensible (si aplica) | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  | Si |
| Auto generado | Si | No | No | No | No |
| Registrar proveedor | Requerido | Requerido | Requerido | Requerido | No Requerido |
| Modificar proveedor | Requerido / No Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / No Modificable |
| Retirar proveedor | Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar proveedores activos | No Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No puede existir otro proveedor con el mismo nombre de empresa | Nombre Empresa |
| Combinación única 2 | No pueden existir dos proveedores con el mismo NIT. | NIT |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar proveedor | Permite imcorporar un crear un proveedor | Datos | Proveedor | Información necesaria para registrar e identificar al proveedor. | Proveedor | Proveedor registrado con la información proporcionada. | P-PROV-001 | Asegurar que los datos requeridos para registrar un proveedor sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato, longitud y rango correspondiente. | Los datos ingresados para registrar el proveedor no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-PROV-002 | Asegurar que el identificador generado para el proveedor no esté siendo utilizado por otro proveedor. | El identificador generado ya esta siendo utilizado por otro proveedor. | Generar un nuevo identificador hasta garantizar que no exista. |
|  |  |  |  |  |  |  | P-PROV-003 | Asegurar que no exista otro proveedor registrado con el mismo NIT. | Ya existe otro proveedor registrado con el mismo NIT. | Rechazar el registro e informar que ya existe un proveedor con ese NIT. |
|  |  |  |  |  |  |  | P-PROV-010 | Asegurar que no exista otro proveedor registrado con el mismo nombre de empresa. | Ya existe otro proveedor registrado con el mismo nombre de empresa. | Rechazar el registro e informar que ya existe un proveedor con ese nombre. |
| Modificar proveedor | Actualizar la información de un proveedor existente sin afectar la trazabilidad de las compras históricas asociadas a este. | Datos | Proveedor | Identificador del proveedor y datos que se desean modificar. | Proveedor | Proveedor actualizado con la información proporcionada. | P-PROV-004 | Asegurar que el proveedor que se desea actualizar se encuentre registrado en el sistema. | El proveedor que se desea actualizar no existe. | Rechazar la actualizacion e informar que el proveedor no existe. |
|  |  |  |  |  |  |  | P-PROV-001 | Asegurar que los datos requeridos para registrar un proveedor sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato, longitud y rango correspondiente. | Los nuevos datos del proveedor no son validos. | Rechazar la modificacion e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-PROV-005 | Asegurar que la información modificada del proveedor no genere duplicidad con otro proveedor registrado. | La informacion modificada genera duplicidad con otro proveedor registrado. | Rechazar la actualizacion e informar la duplicidad encontrada. |
| Retirar proveedor | Permite retirar un proveedor con el que la pizzeria ya no trabaja: queda inactivo y no se puede usar en nuevas compras, pero se conservan sus compras. | Id | UUID | Identificador del proveedor que se desea retirar. | Proveedor | Proveedor retirado de acuerdo con las reglas establecidas. | P-PROV-006 | Asegurar que el proveedor que se desea retirar se encuentre registrado en el sistema. | El proveedor que se desea retirar no existe. | Rechazar el retiro e informar que el proveedor no existe. |
|  |  |  |  |  |  |  | P-PROV-011 | Asegurar que el proveedor que se desea retirar no se encuentre ya retirado. | El proveedor ya se encuentra retirado. | Informar que el proveedor ya esta retirado. |
|  |  |  |  |  |  |  | P-PROV-007 | Asegurar que al retirar un proveedor se conserven sus compras registradas: el retiro solo cambia su estado a inactivo y no borra informacion. | El proveedor tiene compras registradas. | Retirar el proveedor sin borrar sus compras; siguen disponibles para consulta. |
| Consultar proveedores activos | Permite consultar los proveedores activos, ordenados por nombre de empresa, para seleccionarlos al registrar una compra. | Ninguno |  |  |  | Proveedores activos ordenados por nombre de empresa. | P-PROV-012 | Asegurar que solo se consulten los proveedores activos, que son los disponibles para registrar nuevas compras. | No existen proveedores activos. | Informar que no hay proveedores activos disponibles. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar proveedor | Permite imcorporar un crear un proveedor | Proveedor | Información del proveedor | Datos necesarios para identificar y contactar al proveedor, como nombre de empresa y medio de contacto. | P-PROV-001 |  | Proveedor registrado |  |
|  |  |  |  |  |  | P-PROV-002 |  |  |  |
|  |  |  |  |  |  | P-PROV-003 |  |  |  |
|  |  |  |  |  |  | P-PROV-010 |  |  |  |
| Administrador | Modificar proveedor | Actualizar la información de un proveedor existente sin afectar la trazabilidad de las compras históricas asociadas a este. | Proveedor | Información del proveedor | Datos actuales y nuevos del proveedor necesarios para identificarlo y realizar la actualización. | P-PROV-004 |  | Proveedor modificado |  |
|  |  |  |  |  |  | P-PROV-001 |  |  |  |
|  |  |  |  |  |  | P-PROV-005 |  |  |  |
| Administrador | Retirar proveedor | Permite retirar un proveedor con el que la pizzeria ya no trabaja: queda inactivo y no se puede usar en nuevas compras, pero se conservan sus compras. | Proveedor | Compras hechas al proveedor | Permite verificar las compras asociadas al proveedor antes de retirarlo y conservar su información histórica. | P-PROV-006 |  | Proveedor retirado |  |
|  |  |  |  |  |  | P-PROV-011 |  |  |  |
|  |  |  |  |  |  | P-PROV-007 |  |  |  |
| Administrador | Consultar proveedores activos | Permite consultar los proveedores activos, ordenados por nombre de empresa, para seleccionarlos al registrar una compra. |  | Proveedores registrados | Proveedores con estado activo. | P-PROV-012 |  | Proveedores activos consultados |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-PROV-001 | Asegurar que los datos requeridos para registrar un proveedor sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato, longitud y rango correspondiente. | Proveedor |  |
| P-PROV-002 | Asegurar que el identificador generado para el proveedor no esté siendo utilizado por otro proveedor. | Proveedor |  |
| P-PROV-003 | Asegurar que no exista otro proveedor registrado con el mismo NIT. | Proveedor |  |
| P-PROV-004 | Asegurar que el proveedor que se desea actualizar se encuentre registrado en el sistema. | Proveedor |  |
| P-PROV-005 | Asegurar que la información modificada del proveedor no genere duplicidad con otro proveedor registrado. | Proveedor |  |
| P-PROV-006 | Asegurar que el proveedor que se desea retirar se encuentre registrado en el sistema. | Proveedor |  |
| P-PROV-007 | Asegurar que al retirar un proveedor se conserven sus compras registradas: el retiro solo cambia su estado a inactivo y no borra informacion. | Proveedor |  |
| P-PROV-008 | Asegurar que un proveedor retirado o inactivo no pueda ser utilizado para registrar nuevas compras, conservando la información histórica de las compras realizadas. | Proveedor |  |
| P-PROV-009 | Asegurar que el proveedor exista con el identificador indicado | Proveedor |  |
| P-PROV-010 | Asegurar que no exista otro proveedor registrado con el mismo nombre de empresa. | Proveedor |  |
| P-PROV-011 | Asegurar que el proveedor que se desea retirar no se encuentre ya retirado. | Proveedor |  |
| P-PROV-012 | Asegurar que solo se consulten los proveedores activos, que son los disponibles para registrar nuevas compras. | Proveedor |  |
