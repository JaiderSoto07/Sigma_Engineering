# Producto

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Producto](../../02-diagrama-clases/clases/Producto.md) · [Entidad MER: Producto](../../03-modelo-entidad-relacion/entidades/Producto.md)

> Son los productos que la pizzería vende, como las pizzas, las bebidas y las adiciones.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada producto que vende la pizzeria |  |  |
| Nombre | Nombre del prodcuto , si es un producto interno entonces el nombre debe coincidir con uno ya existente | Alfanumerico(max 40 caracteres) | Si |
| TipoProducto | Tipo de producto al que pertenece (Pizza, Bebida o Adicion) | TipoProducto | Si |
| Tamaño | Tamaño del producto (Grande, Mediana o Pequeña) para productos que no son pizza el valor es No aplica | Tamaño | Si |
| ProductoInterno | Dato calculado: indica si el producto se vende directamente desde el inventario (por ejemplo una bebida). Es Si cuando tiene un ProductoInternoAsociado y No cuando el asociado es "Sin insumo" | Tipo Logico | Si |
| ProductoInternoAsociado | Producto interno asociado al producto. Si ProductoInterno es Si, es el producto interno con el mismo nombre; si no, es el registro "Sin insumo" | ProductoInterno | Si |
| Precio | Contiene el precio de venta publico del producto | Decimal Europeo (2) | Si |
| Activo | Indica si el producto esta activo y disponible para la venta | Tipo Logico | Si |

### Datos de muestra

_12 registros de ejemplo._

| Id | Nombre | TipoProducto | Tamaño | ProductoInterno | ProductoInternoAsociado | Precio | Activo | Combinacion unica 1 |
|---|---|---|---|---|---|---|---|---|
| 1 | Margarita | Pizza | Grande | No | Sin insumo | 25000 | Si | Margarita Grande |
| 2 | Peperoni | Pizza | Mediana | No | Sin insumo | 23000 | Si | Peperoni Mediana |
| 3 | Margarita y Hawaiana | Pizza | Pequeña | No | Sin insumo | 30000 | Si | Margarita y Hawaiana Pequeña |
| 4 | Extra Queso | Adicion | No aplica | No | Sin insumo | 5000 | Si | Extra Queso |
| 5 | Extra Piña | Adicion | No aplica | No | Sin insumo | 6000 | Si | Extra Piña |
| 6 | Extra Peperoni | Adicion | No aplica | No | Sin insumo | 9000 | Si | Extra Peperoni |
| 7 | Jugo Hit Mora 200 ml | Bebida | No aplica | Si | Jugo Hit Mora 200 ml | 4000 | Si | Jugo Hit Mora 200 ml |
| 8 | Pepsi 400 ml | Bebida | No aplica | Si | Pepsi 400 ml | 6000 | Si | Pepsi 400 ml |
| 9 | Monster 473 ml | Bebida | No aplica | Si | Monster 473 ml | 5200 | Si | Monster 473 ml |
| 10 | Colombiana 400 ml | Bebida | No aplica | Si | Colombiana 400 ml | 4500 | Si | Colombiana 400 ml |
| 11 | Mr tea 300 ml | Bebida | No aplica | Si | Mr tea 300 ml | 4500 | Si | Mr tea 300 ml |
| 12 | Hawaiana | Pizza | Grande | No | Sin insumo | 35000 | Si | Hawaiana Grande |

## Caracterización

### Atributos

| Nombre atributo | Id | Nombre | TipoProducto | Tamaño | ProductoInterno | ProductoInternoAsociado | Precio | Activo |
|---|---|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada producto que vende la pizzeria | Nombre del prodcuto , si es un producto interno entonces el nombre debe coincidir con uno ya existente | Tipo de producto al que pertenece (Pizza, Bebida o Adicion) | Tamaño del producto (Grande, Mediana o Pequeña) para productos que no son pizza el valor es No aplica | Dato calculado: indica si el producto se vende directamente desde el inventario (por ejemplo una bebida). Es Si cuando tiene un ProductoInternoAsociado y No cuando el asociado es "Sin insumo" | Producto interno asociado al producto. Si ProductoInterno es Si, es el producto interno con el mismo nombre; si no, es el registro "Sin insumo" | Contiene el precio de venta publico del producto | Indica si el producto esta activo y disponible para la venta |
| Identificador del objeto de dominio | Si | No | No | No | No | No | No | No |
| Tipo de dato | Identificador único universal | Alfanumerico | TipoProducto | Tamaño | Tipo Logico | ProductoInterno | Decimal Europeo | Tipo Logico |
| Obligatorio | Si | Si | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  | 1 |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  | 40 |  |  |  |  |  |  |
| Precisión (si aplica) |  |  |  |  |  |  | 2 |  |
| Rendondeo (si aplica) |  |  |  |  |  |  | A la unidad (0) |  |
| Rango inicial (si aplica) |  |  |  |  |  |  | &gt;0 |  |
| Rango final (si aplica) |  |  |  |  |  |  | 1000000 |  |
| Formato (si aplica) |  |  |  |  |  |  |  |  |
| Reglas especiales (si aplica) |  | El usuario lo registra. Si el producto es un producto interno, debe existir un producto interno con el mismo nombre. | Referenciado en vivo (HYPERLINK) al catálogo M-TipoProducto. | Referenciado en vivo (HYPERLINK) al catálogo M-Tamaño. Para productos que no son pizza, el valor es No aplica. | El usuario indica si el producto se vende directo del inventario. Si es Si, se asocia el producto interno con el mismo nombre. En la base de datos se calcula a partir del ProductoInternoAsociado. | Se asigna automaticamente: si ProductoInterno es Si, el producto interno con el mismo nombre (debe existir); si es No, el registro "Sin insumo". | Lo registra el usuario al crear el producto. Debe coincidir con el precio vigente en HistoricoPrecio (FechaFin = 01/01/1000). | Se registra como activo (Si). Solo los productos activos se muestran al consultar los productos. |
| Dato calculado | No | No | No | No | Si | Si | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | Si | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | Si | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  | Sin insumo |  | Si |
| Auto generado | Si | No | No | No | No | No | No | No |
| Registrar producto | Requerido | Requerido | Requerido | Requerido | Requerido | No Requerido | Requerido | No Requerido |
| Inactivar producto | Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar productos activos | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar | No Mostrar | Mostrar | No Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos productos con el mismo Nombre y el mismo Tamaño. | Nombre |
|  |  | Tamaño |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar producto | Permite incorporar un nuevo producto a la oferta comercial de la pizzería, definiendo la información necesaria para su identificación y comercialización. | Datos | Producto | Información propia necesaria para registrar el producto. | Producto | Producto registrado con la información proporcionada. | P-PRO-001 | Asegurar que los datos requeridos para registrar un producto sean validos: nombre de 1 a 40 caracteres, tipo de producto y tamano diligenciados, y precio mayor que 0 y como maximo 1.000.000. | Los datos ingresados para registrar el producto no cumplen las reglas establecidas. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-PRO-002 | Asegurar que el identificador generado para el producto no esté siendo utilizado por otro producto. | El identificador generado ya esta siendo utilizado por otro producto. | Generar un nuevo identificador hasta garantizar que no exista. |
|  |  |  |  |  |  |  | P-PRO-003 | Asegurar que no exista otro producto con el mismo Nombre y el mismo Tamaño al registrar el producto. | Ya existe otro producto con el mismo Nombre y el mismo Tamaño. | Rechazar el registro e informar que ya existe un producto igual registrado. |
|  |  |  |  |  |  |  | P-PRO-004 | Asegurar que el tipo de producto utilizado para registrar el producto corresponda a un tipo válido definido por la pizzería. | El tipo de producto utilizado no corresponde a un tipo valido definido por la pizzeria. | Rechazar el registro e informar que el tipo de producto no es valido. |
|  |  |  |  |  |  |  | P-PRO-005 | Asegurar que, cuando el producto sea un producto interno, exista un producto interno con el mismo nombre y se asocie como ProductoInternoAsociado; si no es producto interno, se asocia el registro "Sin insumo". | No existe un producto interno con el mismo nombre del producto. | Rechazar el registro e informar que primero debe registrarse el producto interno. |
|  |  |  |  |  |  |  | P-PRO-006 | Asegurar que el tamaño utilizado para registrar el producto corresponda a un tamaño válido definido por la pizzería. | El tamaño utilizado no corresponde a un tamaño valido definido por la pizzeria. | Rechazar el registro e informar que el tamaño no es valido. |
|  |  |  |  |  |  |  | P-PRO-010 | Al registrar un producto, su precio queda registrado en HistoricoPrecio como precio vigente desde la fecha de registro (FechaFin 01/01/1000). | No fue posible registrar el precio inicial en el historico de precios. | Rechazar el registro completo para que el producto no quede sin precio vigente. |
| Inactivar producto | Permite inactivar un producto que la pizzeria ya no ofrece: no se puede vender, pero se conserva su historial de precios, ventas y recetas. | Id | UUID | Identificador del producto que se desea eliminar. | Producto | Producto inactivado. | P-PRO-007 | Asegurar que el producto que se quiere inactivar se encuentre registrado en el sistema. | El producto no existe con el identificador indicado. | Rechazar la eliminación e informar que el producto no existe. |
|  |  |  |  |  |  |  | P-PRO-012 | Asegurar que el producto que se desea inactivar no se encuentre ya inactivo. | El producto ya se encuentra inactivo. | Informar que el producto ya esta inactivo. |
|  |  |  |  |  |  |  | P-PRO-008 | Asegurar que al inactivar un producto se conserve su informacion historica (precios, ventas y recetas), aunque ya no se ofrezca en la pizzeria. | El producto tiene precios, ventas o recetas registradas. | Inactivar el producto sin borrar su historial. |
| Consultar productos activos | Permite consultar los productos activos que la pizzeria ofrece, por ejemplo para registrar una venta o armar una receta. | Ninguno | - | No requiere datos de entrada. | Lista de Producto | Productos activos con su tipo, tamano y precio. | P-PRO-013 | Asegurar que solo se consulten los productos activos. | No existen productos activos. | Informar que no hay productos activos. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar producto | Permite incorporar un nuevo producto a la oferta comercial de la pizzería, definiendo la información necesaria para su identificación y comercialización. | Producto | Tipo de producto | Permite determinar el tipo de producto que se desea registrar y consultar la información necesaria para su creación. | P-PRO-001 |  | Producto registrado |  |
|  |  |  |  |  |  | P-PRO-002 |  |  |  |
|  |  |  |  |  |  | P-PRO-003 |  |  |  |
|  |  |  |  |  |  | P-PRO-004 |  |  |  |
|  |  |  |  |  |  | P-PRO-005 |  |  |  |
|  |  |  |  |  |  | P-PRO-006 |  |  |  |
|  |  |  |  |  |  | P-PRO-010 |  |  |  |
| Administrador | Inactivar producto | Permite inactivar un producto que la pizzeria ya no ofrece: no se puede vender, pero se conserva su historial de precios, ventas y recetas. | Producto | Identificador del prodcuto e historio de precio de ese producto | Identificar el producto que se quiere eliminar y cual es su informacion historica asociada | P-PRO-007 |  | Producto inactivado |  |
|  |  |  |  |  |  | P-PRO-012 |  |  |  |
|  |  |  |  |  |  | P-PRO-008 |  |  |  |
| Administrador | Consultar productos activos | Permite consultar los productos activos que la pizzeria ofrece, por ejemplo para registrar una venta o armar una receta. |  | Productos registrados | Productos con estado activo. | P-PRO-013 |  | Productos activos consultados |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-PRO-001 | Asegurar que los datos requeridos para registrar un producto sean validos: nombre de 1 a 40 caracteres, tipo de producto y tamano diligenciados, y precio mayor que 0 y como maximo 1.000.000. | Producto |  |
| P-PRO-002 | Asegurar que el identificador generado para el producto no esté siendo utilizado por otro producto. | Producto |  |
| P-PRO-003 | Asegurar que no exista otro producto con el mismo Nombre y el mismo Tamaño al registrar el producto. | Producto |  |
| P-PRO-004 | Asegurar que el tipo de producto utilizado para registrar el producto corresponda a un tipo válido definido por la pizzería. | TipoProducto | P-TP-001 |
| P-PRO-005 | Asegurar que, cuando el producto sea un producto interno, exista un producto interno con el mismo nombre y se asocie como ProductoInternoAsociado; si no es producto interno, se asocia el registro "Sin insumo". | ProductoInterno | P-PI-007 |
| P-PRO-006 | Asegurar que el tamaño utilizado para registrar el producto corresponda a un tamaño válido definido por la pizzería. | Tamaño | P-TAM-001 |
| P-PRO-007 | Asegurar que el producto que se quiere inactivar se encuentre registrado en el sistema. | Producto |  |
| P-PRO-008 | Asegurar que al inactivar un producto se conserve su informacion historica (precios, ventas y recetas), aunque ya no se ofrezca en la pizzeria. | HistoricoPrecio | P-HP-011 |
| P-PRO-009 | Asegurar que el producto exista con el identificador indicado. | Producto |  |
| P-PRO-010 | Al registrar un producto, su precio queda registrado en HistoricoPrecio como precio vigente desde la fecha de registro (FechaFin 01/01/1000). | HistoricoPrecio | P-HP-001 |
| P-PRO-011 | Asegurar que un producto inactivo no pueda venderse ni recibir nuevos ingredientes en su receta. | Producto |  |
| P-PRO-012 | Asegurar que el producto que se desea inactivar no se encuentre ya inactivo. | Producto |  |
| P-PRO-013 | Asegurar que solo se consulten los productos activos. | Producto |  |
