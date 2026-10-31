# Usuario

[Inicio](../../../README.md) · [MER](../README.md) · [Modelo de dominio: Usuario](../../01-modelo-dominio/objetos/Usuario.md) · [Clase: Usuario](../../02-diagrama-clases/clases/Usuario.md)

| Campo | Valor |
|---|---|
| Entidad | Usuario |
| Descripción | Representa a la persona que puede ingresar a la aplicación; solo los usuarios activos con rol Administrador pueden iniciar sesión. |

## Atributos

| Nombre | id_usuario | usuario | clave | rol | activo |
|---|---|---|---|---|---|
| Tipo de dato | uniqueidentifier | varchar | varchar | varchar | bit |
| Longitud |  | 30 | 100 | 20 |  |
| Cantidad dígitos parte entera |  |  |  |  |  |
| Cantidad dígitos parte decimal (Precisión) |  |  |  |  |  |
| ¿Es autoincremento? | No | No | No | No | No |
| ¿Es obligatorio? | SI | SI | SI | SI | SI |
| ¿Es llave primeria o es parte de ella? | SI | NO | NO | NO | NO |
| Llave Foránea · ¿Es llave foránea? | NO | NO | NO | NO | NO |
| Llave Foránea · Entidad |  |  |  |  |  |
| Llave Foránea · Columna(s) |  |  |  |  |  |
| Restricciones de unicidad · ¿Posee restricción de unicidad o es parte de ella? | NO | SI | NO | NO | NO |
| Restricciones de unicidad · Nombre restricción unicidad a la que pertenece |  | Unique de columna (sin nombre en el script) |  |  |  |
| Restricciones de chequeo · ¿Posee restricción de chequeo o es parte de ella? | NO | NO | NO | SI | NO |
| Restricciones de chequeo · Nombre restricción chequeo a la que pertenece |  |  |  | ck_usuario_rol |  |
| Restricciones de índice · ¿Posee restricción de índice o es parte de ella? | NO | NO | NO | NO | NO |
| Restricciones de índice · Tipo |  |  |  |  |  |
| Restricciones de índice · Nombre restricción índice a la que pertenece | NO | NO | NO | NO | NO |
| Alias |  |  |  |  |  |
| Descripción del campo | Identificador unico de cada usuario de la aplicacion (Valor por defecto: newid()) | Nombre de usuario con el que se inicia sesion; no se puede repetir | Contrasena del usuario; se guarda cifrada con BCrypt, nunca en texto plano | Rol del usuario dentro de la aplicacion; solo existe el rol Administrador | Indica si el usuario esta activo; un usuario inactivo no puede iniciar sesion |
