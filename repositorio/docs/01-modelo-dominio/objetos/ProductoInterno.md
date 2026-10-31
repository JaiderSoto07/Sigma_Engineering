# ProductoInterno

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: ProductoInterno](../../02-diagrama-clases/clases/ProductoInterno.md) · [Entidad MER: ProductoInterno](../../03-modelo-entidad-relacion/entidades/ProductoInterno.md)

> Es cada insumo o producto que la pizzería compra y guarda en su inventario, sin importar si se usa para preparar algo o si se vende directamente al cliente.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada producto interno dentro de la pizzeria |  |  |
| Nombre | Nombre de cada producto interno (ingrediente o bebida almacenada) | Alfanumerico(max 40 caracteres) | Si |
| Perecedero | Cuando se crea el ingrediente se debe decir si es un producto perecedero o no | Tipo Logico | Si |
| VidaUtil | Vida util en dias: de 1 a 400 si el producto es perecedero y 0 si no lo es | Entero | Si |
| TipoMedida | Unidad de medida (UnidadMedida) en la que se maneja el producto interno; su tipo (Peso, Volumen o Unidad) define la unidad de las recetas | UnidadMedida | Si |
| Activo | Indica si el producto interno esta activo y disponible para usarse | Tipo Logico | Si |

### Datos de muestra

_17 registros de ejemplo._

| Id | Nombre | Perecedero | VidaUtil | TipoMedida | Activo | Combinacion Unica |
|---|---|---|---|---|---|---|
| 1 | Masa de Pizza | No | 0 | g | Si | Masa de Pizza |
| 2 | Salsa de Tomate | No | 0 | g | Si | Salsa de Tomate |
| 3 | Aceite de oliva | No | 0 | ml | Si | Aceite de oliva |
| 4 | Queso | Si | 7 | g | Si | Queso |
| 5 | Albahaca | Si | 8 | g | Si | Albahaca |
| 6 | Azucar | No | 0 | g | Si | Azucar |
| 7 | Peperoni | Si | 9 | g | Si | Peperoni |
| 8 | Piña | Si | 6 | g | Si | Piña |
| 9 | Sal | No | 0 | g | Si | Sal |
| 10 | Leche | No | 0 | ml | Si | Leche |
| 11 | Jugo Hit Mora 200 ml | No | 0 | und | Si | Jugo Hit Mora 200 ml |
| 12 | Pepsi 400 ml | No | 0 | und | Si | Pepsi 400 ml |
| 13 | Monster 473 ml | No | 0 | und | Si | Monster 473 ml |
| 14 | Colombiana 400 ml | No | 0 | und | Si | Colombiana 400 ml |
| 15 | Mr tea 300 ml | No | 0 | und | Si | Mr tea 300 ml |
| 16 | Jamón | Si | 10 | g | Si | Jamón |
| 0 | Sin insumo | No | 0 | und | No | Sin insumo |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre | Perecedero | VidaUtil | TipoMedida | Activo |
|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada producto interno dentro de la pizzeria | Nombre de cada producto interno (ingrediente o bebida almacenada) | Cuando se crea el ingrediente se debe decir si es un producto perecedero o no | Vida util en dias: de 1 a 400 si el producto es perecedero y 0 si no lo es | Unidad de medida (UnidadMedida) en la que se maneja el producto interno; su tipo (Peso, Volumen o Unidad) define la unidad de las recetas | Indica si el producto interno esta activo y disponible para usarse |
| Identificador del objeto de dominio | Si | No | No | No | No | No |
| Tipo de dato | Identificador unico universal | Alfanumerico | Tipo Logico | Entero | UnidadMedida | Tipo Logico |
| Obligatorio | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  | 1 |  |  |  |  |
| Longitud máxima (si aplica) |  | 40 |  |  |  |  |
| Precisión (si aplica) |  |  |  |  |  |  |
| Rendondeo (si aplica) |  |  |  |  |  |  |
| Rango inicial (si aplica) |  |  |  | 0 |  |  |
| Rango final (si aplica) |  |  |  | 400 |  |  |
| Formato (si aplica) |  |  |  |  |  |  |
| Reglas especiales (si aplica) | Existe un registro especial "Sin insumo" con el identificador por defecto (00000000-0000-0000-0000-000000000000) e inactivo; lo usan los productos que no corresponden a un producto interno. |  | Indica si el producto se descompone con el tiempo. De ahi depende como se saca la fecha de vencimiento en Lote. | Si el producto es perecedero la vida util va de 1 a 400 dias; si no es perecedero debe ser 0. | Debe corresponder a una UnidadMedida existente. Su tipo de medida (Peso, Volumen o Unidad) define la unidad en que se registran las recetas (g, ml o und). | Se registra como activo (Si). Solo los productos internos activos se pueden usar en recetas y compras. |
| Dato calculado | No | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) | No | No | No | No | No | No |
| Quitar espacios en blanco al inicio (si aplica) | No | Si | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | Si | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  | 0 |  | Si |
| Auto generado | Si | No | No | No | No | No |
| Registrar producto interno | Requerido | Requerido | Requerido | Requerido | Requerido | No Requerido |
| Actualizar producto interno | Requerido / No Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / No Modificable |
| Inactivar producto interno | Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar productos internos activos | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos productos internos con el mismo Nombre. | Nombre |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar producto interno | Permite incorporar un nuevo producto interno (ingrediente o bebida almacenada) al inventario de la pizzeria. | Datos | ProductoInterno | Información propia necesaria para registrar el producto interno. | ProductoInterno | Producto interno registrado con la información proporcionada. | P-PI-001 | Asegurar que los datos requeridos para registrar un producto interno sean validos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar el producto interno no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-PI-002 | Asegurar que no exista otro producto interno con el mismo Nombre al registrarlo. | Ya existe otro producto interno registrado con el mismo Nombre. | Rechazar el registro e informar que ya existe un producto interno con ese Nombre. |
|  |  |  |  |  |  |  | P-PI-003 | Asegurar que la VidaUtil este entre 1 y 400 dias cuando el producto interno sea perecedero, y que sea 0 cuando no lo sea. | La vida util no esta entre 1 y 400 dias para un producto perecedero, o no es 0 para uno no perecedero. | Rechazar el registro e informar el valor permitido. |
|  |  |  |  |  |  |  | P-PI-004 | Asegurar que la TipoMedida corresponda a una unidad de medida existente del catalogo. | La TipoMedida indicada no corresponde a una unidad de medida existente. | Rechazar el registro e informar que la unidad de medida no es valida. |
|  |  |  |  |  |  |  | P-PI-010 | Al registrar un producto interno se crea su registro de inventario con cantidad total 0 y stock minimo 1. | No fue posible crear el registro de inventario del producto interno. | Rechazar el registro completo para que el producto interno no quede sin inventario. |
| Actualizar producto interno | Permite modificar la informacion de un producto interno existente. | Datos | ProductoInterno | Identificador del producto interno y datos que se desean actualizar. | ProductoInterno | Producto interno actualizado. | P-PI-005 | Asegurar que el producto interno que se desea modificar se encuentre registrado en el sistema. | El producto interno no existe en el sistema. | Rechazar la actualización e informar que el producto interno no existe. |
|  |  |  |  |  |  |  | P-PI-009 | Asegurar que el registro especial "Sin insumo" no pueda modificarse, inactivarse ni usarse en recetas o compras. | Se intento modificar el registro "Sin insumo". | Rechazar la modificacion e informar que ese registro no se puede modificar. |
|  |  |  |  |  |  |  | P-PI-001 | Asegurar que los datos requeridos para registrar un producto interno sean validos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los nuevos datos del producto interno no son validos. | Rechazar la modificacion e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-PI-002 | Asegurar que no exista otro producto interno con el mismo Nombre al registrarlo. | El nuevo Nombre ya lo tiene otro producto interno. | Rechazar la modificacion e informar la duplicidad. |
|  |  |  |  |  |  |  | P-PI-003 | Asegurar que la VidaUtil este entre 1 y 400 dias cuando el producto interno sea perecedero, y que sea 0 cuando no lo sea. | La nueva vida util no corresponde a si el producto es perecedero o no. | Rechazar la modificacion e informar el valor permitido. |
| Inactivar producto interno | Permite inactivar un producto interno que la pizzeria ya no usa: no se puede usar en nuevas recetas ni compras, pero se conserva su historial. | Id | UUID | Identificador del producto interno que se desea eliminar. | ProductoInterno | Producto interno inactivado. | P-PI-007 | Asegurar que el producto interno exista con el identificador indicado. | El producto interno que se desea inactivar no existe. | Rechazar la inactivacion e informar que el producto interno no existe. |
|  |  |  |  |  |  |  | P-PI-008 | Asegurar que el producto interno que se desea inactivar no se encuentre ya inactivo. | El producto interno ya se encuentra inactivo. | Informar que el producto interno ya esta inactivo. |
|  |  |  |  |  |  |  | P-PI-009 | Asegurar que el registro especial "Sin insumo" no pueda modificarse, inactivarse ni usarse en recetas o compras. | Se intento inactivar el registro "Sin insumo". | Rechazar la inactivacion e informar que ese registro no se puede inactivar. |
|  |  |  |  |  |  |  | P-PI-006 | Asegurar que un producto interno inactivo no pueda usarse en nuevas recetas ni en nuevas compras; al inactivarlo se conservan sus lotes, movimientos, compras y recetas registrados. | El producto interno tiene lotes, compras o recetas registradas. | Inactivar el producto interno sin borrar su historial. |
| Consultar productos internos activos | Permite consultar los productos internos activos, por ejemplo para escoger los ingredientes de una receta. | Ninguno | - | No requiere datos de entrada. | Lista de ProductoInterno | Productos internos activos. | P-PI-011 | Asegurar que solo se consulten los productos internos activos. | No existen productos internos activos. | Informar que no hay productos internos activos. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar producto interno | Permite incorporar un nuevo producto interno (ingrediente o bebida almacenada) al inventario de la pizzeria. | ProductoInterno | UnidadMedida | Permite seleccionar una unidad de medida valida para el producto interno. | P-PI-001 |  | Producto interno registrado |  |
|  |  |  |  |  |  | P-PI-002 |  |  |  |
|  |  |  |  |  |  | P-PI-003 |  |  |  |
|  |  |  |  |  |  | P-PI-004 |  |  |  |
|  |  |  |  |  |  | P-PI-010 |  |  |  |
| Administrador | Actualizar producto interno | Permite modificar la informacion de un producto interno existente. | ProductoInterno | Productos internos registrados | Permite identificar el producto interno existente antes de modificarlo. | P-PI-005 |  | Producto interno actualizado |  |
|  |  |  |  |  |  | P-PI-009 |  |  |  |
|  |  |  |  |  |  | P-PI-001 |  |  |  |
|  |  |  |  |  |  | P-PI-002 |  |  |  |
|  |  |  |  |  |  | P-PI-003 |  |  |  |
| Administrador | Inactivar producto interno | Permite inactivar un producto interno que la pizzeria ya no usa: no se puede usar en nuevas recetas ni compras, pero se conserva su historial. | ProductoInterno |  | Permite identificar el producto interno existente antes de eliminarlo. | P-PI-007 |  | Producto interno inactivado |  |
|  |  |  |  |  |  | P-PI-008 |  |  |  |
|  |  |  |  |  |  | P-PI-009 |  |  |  |
|  |  |  |  |  |  | P-PI-006 |  |  |  |
| Administrador | Consultar productos internos activos | Permite consultar los productos internos activos, por ejemplo para escoger los ingredientes de una receta. |  | Productos internos registrados | Productos internos con estado activo. | P-PI-011 |  | Productos internos activos consultados |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-PI-001 | Asegurar que los datos requeridos para registrar un producto interno sean validos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | ProductoInterno |  |
| P-PI-002 | Asegurar que no exista otro producto interno con el mismo Nombre al registrarlo. | ProductoInterno |  |
| P-PI-003 | Asegurar que la VidaUtil este entre 1 y 400 dias cuando el producto interno sea perecedero, y que sea 0 cuando no lo sea. | ProductoInterno |  |
| P-PI-004 | Asegurar que la TipoMedida corresponda a una unidad de medida existente del catalogo. | UnidadMedida | P-UM-002 |
| P-PI-005 | Asegurar que el producto interno que se desea modificar se encuentre registrado en el sistema. | ProductoInterno |  |
| P-PI-006 | Asegurar que un producto interno inactivo no pueda usarse en nuevas recetas ni en nuevas compras; al inactivarlo se conservan sus lotes, movimientos, compras y recetas registrados. | ProductoInterno |  |
| P-PI-007 | Asegurar que el producto interno exista con el identificador indicado. | ProductoInterno |  |
| P-PI-008 | Asegurar que el producto interno que se desea inactivar no se encuentre ya inactivo. | ProductoInterno |  |
| P-PI-009 | Asegurar que el registro especial "Sin insumo" no pueda modificarse, inactivarse ni usarse en recetas o compras. | ProductoInterno |  |
| P-PI-010 | Al registrar un producto interno se crea su registro de inventario con cantidad total 0 y stock minimo 1. | Inventario | P-INV-005 |
| P-PI-011 | Asegurar que solo se consulten los productos internos activos. | ProductoInterno |  |
