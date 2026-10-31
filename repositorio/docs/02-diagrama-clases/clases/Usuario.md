# Usuario

[Inicio](../../../README.md) · [Diagrama de clases](../README.md) · [Modelo de dominio: Usuario](../../01-modelo-dominio/objetos/Usuario.md) · [Entidad MER: Usuario](../../03-modelo-entidad-relacion/entidades/Usuario.md)

| Campo | Valor |
|---|---|
| Clase | Usuario |
| Estereotipo | class |
| Descripción | Representa a la persona que puede ingresar a la aplicación; solo los usuarios activos con rol Administrador pueden iniciar sesión. |
| Modificador acceso | public |
| Tipo clase | no final |

## Relaciones

| Relación | Tipo relación | Estructura |
|---|---|---|

## Atributos

| Modificador de acceso | Nombre | Tipo de dato | Tipo de dato · ¿Cuál? | Tipo modificación atributo | Tipo acceso atributo | Valor por defecto | Descripción atributo |
|---|---|---|---|---|---|---|---|
| private | id | Otro | UUID | no final | no static | 00000000-0000-0000-0000-000000000000 | Identificador único de cada usuario de la aplicación. |
| private | usuario | String |  | no final | no static | "" (cadena vacía) | Nombre de usuario con el que se inicia sesión; no se puede repetir. |
| private | clave | String |  | no final | no static | "" (cadena vacía) | Contraseña del usuario; se guarda cifrada con BCrypt y nunca se devuelve al consultar el usuario. |
| private | rol | String |  | no final | no static | "" (cadena vacía) | Rol del usuario dentro de la aplicación; solo existe el rol Administrador. |
| private | activo | boolean/Boolean |  | no final | no static | true | Indica si el usuario está activo; un usuario inactivo no puede iniciar sesión. |

## Constructores

| Modificador de acceso | Nombre | Tipo constructor | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|
| private | Usuario | Parametrizado | Constructor privado: crea la instancia de Usuario copiando los valores ya saneados que reunió el Builder. Solo se invoca desde Builder.build(). | builder | Otro | Builder | Por valor | no final | Builder con los valores de los atributos de la clase. |  |

## Métodos accesores

| Modificador de acceso | Nombre | Tipo método accesor | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | getId | getter | no final | Método encargado de obtener el valor del atributo id. |  |  |  |  |  |  | Otro | UUID |  |
| public | getUsuario | getter | no final | Método encargado de obtener el valor del atributo usuario. |  |  |  |  |  |  | String |  |  |
| public | getClave | getter | no final | Método encargado de obtener el valor del atributo clave. |  |  |  |  |  |  | String |  |  |
| public | getRol | getter | no final | Método encargado de obtener el valor del atributo rol. |  |  |  |  |  |  | String |  |  |
| public | isActivo | getter | no final | Método encargado de obtener el valor del atributo activo. |  |  |  |  |  |  | boolean/Boolean |  |  |

## Métodos

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | iniciarSesion | no static | no final | Permite que el administrador ingrese a la aplicación con su usuario y contraseña. | credenciales | Otro | Usuario | Por valor | no final | Usuario y contraseña con los que se desea ingresar. | Otro | Usuario | No se recibieron el usuario o la contraseña, o no cumplen la longitud permitida.<br>El usuario no existe, está inactivo o la contraseña no coincide.<br>El usuario no tiene el rol Administrador. |

## Builder (clase interna public static)

| Modificador de acceso | Nombre | Tipo acceso método | Tipo modificación método | Descripción | Parámetros · Nombre | Parámetros · Tipo de dato | Parámetros · ¿Cuál? | Parámetros · Tipo parámetro | Parámetros · Tipo modificación atributo | Parámetros · Descripción | Tipo dato de retorno · Tipo de dato | Tipo dato de retorno · ¿Cuál? | Excepciones |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| public | Builder | no static | no final | Constructor del Builder: asigna a cada atributo su valor por defecto. |  |  |  |  |  |  |  |  |  |
| public | id | no static | no final | Asigna el identificador recibido; si es nulo, asigna el identificador por defecto (00000000-0000-0000-0000-000000000000). | id | Otro | UUID | Por valor | no final | Valor que se desea asignar al atributo id. | Otro | Builder |  |
| public | usuario | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final; si es nulo, asigna una cadena vacía. | usuario | String |  | Por valor | no final | Valor que se desea asignar al atributo usuario. | Otro | Builder |  |
| public | clave | no static | no final | Asigna el texto recibido tal como llega, sin quitar espacios (hacen parte de la clave); si es nulo, asigna una cadena vacía. | clave | String |  | Por valor | no final | Valor que se desea asignar al atributo clave. | Otro | Builder |  |
| public | rol | no static | no final | Asigna el texto recibido sin espacios en blanco al inicio y al final, con la primera letra en mayúscula y el resto en minúscula; si es nulo o vacío, asigna una cadena vacía. | rol | String |  | Por valor | no final | Valor que se desea asignar al atributo rol. | Otro | Builder |  |
| public | activo | no static | no final | Asigna el valor recibido. | activo | boolean/Boolean |  | Por valor | no final | Valor que se desea asignar al atributo activo. | Otro | Builder |  |
| public | build | no static | no final | Crea la instancia de Usuario con los valores reunidos en el Builder, usando su constructor privado. |  |  |  |  |  |  | Otro | Usuario |  |
