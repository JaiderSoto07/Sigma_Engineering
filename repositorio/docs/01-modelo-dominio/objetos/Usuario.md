# Usuario

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Usuario](../../02-diagrama-clases/clases/Usuario.md) · [Entidad MER: Usuario](../../03-modelo-entidad-relacion/entidades/Usuario.md)

> Es la persona que puede ingresar a la aplicacion; solo el administrador de la pizzeria puede iniciar sesion.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada usuario de la aplicacion |  |  |
| Usuario | Nombre de usuario con el que se inicia sesion; no se puede repetir | Alfanumerico(max 30 caracteres) | Si |
| Clave | Contrasena del usuario; se guarda cifrada con BCrypt, nunca en texto plano | Alfanumerico(max 100 caracteres) | Si |
| Rol | Rol del usuario dentro de la aplicacion; solo existe el rol Administrador | Alfanumerico(max 20 caracteres) | Si |
| Activo | Indica si el usuario esta activo; un usuario inactivo no puede iniciar sesion | Tipo Logico | Si |

### Datos de muestra

_2 registros de ejemplo._

| Id | Usuario | Clave | Rol | Activo | Combinacion unica 1 |
|---|---|---|---|---|---|
| 1 | pizzeriaUser | (cifrada con BCrypt) | Administrador | Si | pizzeriaUser |
| 2 | adminRetirado | (cifrada con BCrypt) | Administrador | No | adminRetirado |

## Caracterización

### Atributos

| Nombre atributo | Id | Usuario | Clave | Rol | Activo |
|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada usuario de la aplicacion | Nombre de usuario con el que se inicia sesion; no se puede repetir | Contrasena del usuario; se guarda cifrada con BCrypt, nunca en texto plano | Rol del usuario dentro de la aplicacion; solo existe el rol Administrador | Indica si el usuario esta activo; un usuario inactivo no puede iniciar sesion |
| Identificador del objeto de dominio | Si | No | No | No | No |
| Tipo de dato | Identificador único universal | Alfanumerico | Alfanumerico | Alfanumerico | Tipo Logico |
| Obligatorio | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  | 1 | 1 | 1 |  |
| Longitud máxima (si aplica) |  | 30 | 72 | 20 |  |
| Precisión (si aplica) |  |  |  |  |  |
| Rendondeo (si aplica) |  |  |  |  |  |
| Rango inicial (si aplica) |  |  |  |  |  |
| Rango final (si aplica) |  |  |  |  |  |
| Formato (si aplica) |  |  |  |  |  |
| Reglas especiales (si aplica) |  | No puede existir otro usuario con el mismo nombre de usuario. Al iniciar sesion se quitan los espacios al inicio y al final. | Se ingresa con maximo 72 caracteres (limite de BCrypt) y se guarda cifrada (hash de hasta 100 caracteres). No se le quitan espacios y nunca se devuelve al consultar el usuario. | Solo se permite el rol Administrador; un usuario con otro rol no puede ingresar a la aplicacion. | Solo los usuarios activos pueden iniciar sesion. |
| Dato calculado | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | Si | No | No | No |
| Dato sensible (si aplica) | No | No | Si | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |
| Auto generado | Si | No | No | No | No |
| Iniciar sesion | No Requerido | Requerido | Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos usuarios con el mismo nombre de usuario. | Usuario |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Iniciar sesion | Permite que el administrador ingrese a la aplicacion con su usuario y contrasena. | Credenciales | Credenciales | Usuario y contrasena con los que se desea ingresar. | Usuario | Usuario autenticado (id, usuario y rol), sin la contrasena. | P-USU-001 |  | No se recibieron el usuario o la contrasena, o no cumplen la longitud permitida. | Rechazar el inicio de sesion e informar el dato que falta o no es valido. |
|  |  |  |  |  |  |  | P-USU-002 |  | El usuario no existe, esta inactivo o la contrasena no coincide. | Rechazar el inicio de sesion con el mensaje generico "Usuario o contrasena incorrectos". |
|  |  |  |  |  |  |  | P-USU-003 |  | El usuario no tiene el rol Administrador. | Rechazar el inicio de sesion e informar que no tiene permiso para ingresar. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|
| Administrador | Iniciar sesion | Permite que el administrador ingrese a la aplicacion con su usuario y contrasena. | Usuario | Credenciales | Usuario y contrasena ingresados por el administrador. | P-USU-001 | Sesion iniciada |  |
|  |  |  |  |  |  | P-USU-002 |  |  |
|  |  |  |  |  |  | P-USU-003 |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-USU-001 | Asegurar que se reciban el usuario y la contrasena, que el usuario tenga entre 1 y 30 caracteres y que la contrasena no supere los 72 caracteres. | Usuario |  |
| P-USU-002 | Asegurar que el usuario exista, este activo y que la contrasena coincida con la registrada (cifrada con BCrypt). | Usuario |  |
| P-USU-003 | Asegurar que el usuario tenga el rol Administrador para poder ingresar a la aplicacion. | Usuario |  |
