# DetalleReceta

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: DetalleReceta](../../02-diagrama-clases/clases/DetalleReceta.md) · [Entidad MER: DetalleReceta](../../03-modelo-entidad-relacion/entidades/DetalleReceta.md)

> Es cada uno de los ingredientes que se necesitan para preparar una receta, con la cantidad que se necesita de cada uno.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada ingrediente (linea) de una receta |  |  |
| ProductoInterno | Producto interno que se utiliza en la receta | ProductoInterno | Si |
| Cantidad | Cantidad del producto interno | Decimal Europeo (4) | Si |
| UnidadMedida | Unidad de medida del ingrediente; se asigna automaticamente segun el tipo de medida del producto interno (Peso: g, Volumen: ml, Unidad: und) | UnidadMedida | Si |
| Producto | Producto (Pizza o Adicion) al que pertenece esta linea de receta | Producto | Si |

### Datos de muestra

_25 registros de ejemplo._

| Id | ProductoInterno | Cantidad | UnidadMedida | Producto | CombinacionUnica |
|---|---|---|---|---|---|
| 1 | Masa de Pizza | 350 | g | Margarita Grande | Margarita Grande Masa de Pizza |
| 2 | Salsa de Tomate | 300 | g | Margarita Grande | Margarita Grande Salsa de Tomate |
| 3 | Aceite de oliva | 2 | ml | Margarita Grande | Margarita Grande Aceite de oliva |
| 4 | Queso | 100 | g | Margarita Grande | Margarita Grande Queso |
| 5 | Albahaca | 50 | g | Margarita Grande | Margarita Grande Albahaca |
| 6 | Masa de Pizza | 200 | g | Peperoni Mediana | Peperoni Mediana Masa de Pizza |
| 7 | Salsa de Tomate | 200 | g | Peperoni Mediana | Peperoni Mediana Salsa de Tomate |
| 8 | Aceite de oliva | 2 | ml | Peperoni Mediana | Peperoni Mediana Aceite de oliva |
| 9 | Queso | 150 | g | Peperoni Mediana | Peperoni Mediana Queso |
| 10 | Albahaca | 50 | g | Peperoni Mediana | Peperoni Mediana Albahaca |
| 11 | Azucar | 100 | g | Peperoni Mediana | Peperoni Mediana Azucar |
| 12 | Peperoni | 80 | g | Peperoni Mediana | Peperoni Mediana Peperoni |
| 13 | Masa de Pizza | 350 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Masa de Pizza |
| 14 | Salsa de Tomate | 300 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Salsa de Tomate |
| 15 | Aceite de oliva | 2 | ml | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Aceite de oliva |
| 16 | Queso | 100 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Queso |
| 17 | Albahaca | 50 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Albahaca |
| 18 | Piña | 80 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Piña |
| 19 | Sal | 25 | g | Margarita y Hawaiana Pequeña | Margarita y Hawaiana Pequeña Sal |
| 20 | Queso | 50 | g | Extra Queso | Extra Queso Queso |
| 21 | Piña | 50 | g | Extra Piña | Extra Piña Piña |
| 22 | Peperoni | 40 | g | Extra Peperoni | Extra Peperoni Peperoni |
| 23 | Queso | 500 | g | Hawaiana Grande | Hawaiana Grande Queso |
| 24 | Jamón | 100 | g | Hawaiana Grande | Hawaiana Grande Jamón |
| 25 | Piña | 80 | g | Hawaiana Grande | Hawaiana Grande Piña |

## Caracterización

### Atributos

| Nombre atributo | Id | ProductoInterno | Cantidad | UnidadMedida | Producto |
|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada ingrediente (linea) de una receta | Producto interno que se utiliza en la receta | Cantidad del producto interno | Unidad de medida del ingrediente; se asigna automaticamente segun el tipo de medida del producto interno (Peso: g, Volumen: ml, Unidad: und) | Producto (Pizza o Adicion) al que pertenece esta linea de receta |
| Identificador del objeto de dominio | Si | No | No | No | No |
| Tipo de dato | Identificador único universal | ProductoInterno | Decimal Europeo | UnidadMedida | Producto |
| Obligatorio | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |
| Precisión (si aplica) |  |  | 4 |  |  |
| Rendondeo (si aplica) |  |  | A la unidad (0) |  |  |
| Rango inicial (si aplica) |  |  | &gt;0 |  |  |
| Rango final (si aplica) |  |  | 10000 |  |  |
| Formato (si aplica) |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Tiene que ser un ingrediente (producto interno) que ya exista. | La cantidad debe ser mayor a cero. | Se asigna automaticamente segun el tipo de medida del producto interno: Peso = g, Volumen = ml, Unidad = und. El usuario no la ingresa. | Tiene que ser un producto que ya exista, y ese producto tiene que ser una pizza o una adicion: las bebidas no tienen receta en este negocio |
| Dato calculado | No | No | No | Si | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |
| Auto generado | Si | No | No | Si | No |
| Agregar ingrediente a receta | Requerido | Requerido | Requerido | No Requerido | Requerido |
| Modificar cantidad de ingrediente | Requerido / No Modificable | No Requerido / No Modificable | Requerido / Modificable | No Requerido / No Modificable | No Requerido / No Modificable |
| Retirar ingrediente de receta | Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar ingredientes por filtro | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |
| Consultar recetas de productos activos | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |
| Consultar recetas de productos inactivos | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | Un mismo ProductoInterno no puede repetirse dentro de la misma receta (mismo Producto). | Producto |
|  |  | ProductoInterno |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Agregar ingrediente a receta | Permite incorporar un ingrediente a la composición de una receta indicando la cantidad necesaria para su elaboración. | Datos | DetalleReceta | Información necesaria para incorporar el ingrediente a la receta. | DetalleReceta | Detalle de receta registrado con el ingrediente, cantidad, unidad de medida y receta correspondientes. | P-DR-001 | Asegurar que los datos requeridos para registrar un detalle de receta sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar el detalle de receta no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-DR-002 | Asegurar que el producto (pizza o adicion) al que pertenece este detalle de receta exista en el sistema. | El producto (pizza o adicion) al que pertenece el detalle de receta no existe. | Rechazar el registro e informar que el producto no existe. |
|  |  |  |  |  |  |  | P-DR-014 | Asegurar que el producto al que se agrega el ingrediente se encuentre activo. | El producto se encuentra inactivo. | Rechazar el registro e informar que el producto no esta disponible. |
|  |  |  |  |  |  |  | P-DR-009 | Asegurar que el producto asociado al detalle de receta sea una pizza o una adicion: las bebidas no tienen receta, porque no se elaboran, se compran y se venden directamente. | El producto asociado al detalle de receta no es una pizza ni una adicion. | Rechazar el registro e informar que solo las pizzas y adiciones tienen receta. |
|  |  |  |  |  |  |  | P-DR-003 | Asegurar que el producto interno que se asociará al detalle de receta exista en el sistema. | El producto interno que se quiere asociar al detalle de receta no existe. | Rechazar el registro e informar que el producto interno no existe. |
|  |  |  |  |  |  |  | P-DR-011 | Asegurar que el producto interno del ingrediente se encuentre activo. | El producto interno se encuentra inactivo. | Rechazar el registro e informar que el producto interno no esta disponible. |
|  |  |  |  |  |  |  | P-DR-004 | Asegurar que la cantidad registrada para un producto interno sea mayor que cero y como maximo 10.000. | La cantidad no es mayor que cero o supera 10.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DR-005 | Asegurar que la unidad de medida del detalle de receta se asigne automaticamente segun el tipo de medida del producto interno: Peso = g, Volumen = ml, Unidad = und. El usuario no la ingresa. | No fue posible determinar la unidad de medida a partir del producto interno. | Rechazar el registro e informar que el producto interno no tiene una unidad de medida valida. |
|  |  |  |  |  |  |  | P-DR-006 | Asegurar que un producto interno no se encuentre registrado más de una vez dentro de la misma receta. | El producto interno ya se encuentra registrado dentro de la misma receta. | Rechazar el registro e informar que el producto interno ya esta en la receta. |
|  |  |  |  |  |  |  | P-DR-010 | Asegurar que el identificador generado para el detalle de receta no este siendo utilizado por otro detalle de receta. | El identificador generado ya esta siendo utilizado. | Generar un nuevo identificador hasta garantizar que no exista. |
| Modificar cantidad de ingrediente | Permite ajustar la cantidad de un ingrediente requerida para la elaboración de una receta. | Datos | DetalleReceta | Identificador del detalle y nueva información necesaria para modificar la cantidad. | DetalleReceta | Detalle de receta actualizado con la nueva cantidad. | P-DR-007 | Asegurar que el detalle de receta que se desea modificar se encuentre registrado en el sistema. | El detalle de receta que se desea actualizar no existe. | Rechazar la actualizacion e informar que el detalle de receta no existe. |
|  |  |  |  |  |  |  | P-DR-004 | Asegurar que la cantidad registrada para un producto interno sea mayor que cero y como maximo 10.000. | La nueva cantidad no es mayor que cero o supera 10.000. | Rechazar la modificacion e informar el rango permitido. |
| Retirar ingrediente de receta | Permite retirar un ingrediente de la composición de una receta cuando deja de ser requerido para su elaboración. | Id | UUID | Identificador del detalle de receta que se desea retirar. | DetalleReceta | Detalle de receta retirado de la composición. | P-DR-008 | Asegurar que el detalle de receta exista con el identificador indicado | El detalle de receta no existe con el identificador indicado. | Rechazar el retiro e informar que el detalle de receta no existe. |
| Consultar ingredientes por filtro | Permite consultar los ingredientes de recetas que cumplan con los criterios indicados, por ejemplo todos los ingredientes de un producto. | Filtro | DetalleReceta | Criterios de busqueda. | Lista de DetalleReceta | Ingredientes que cumplen con el filtro. | P-DR-012 | Asegurar que la consulta retorne los ingredientes que cumplan con los criterios indicados (producto, producto interno o unidad de medida). | No existen ingredientes que cumplan con el filtro. | Informar que no se encontraron resultados. |
| Consultar recetas de productos activos | Permite consultar las recetas (ingredientes) de los productos que la pizzeria ofrece actualmente. | Ninguno |  | No requiere datos de entrada. | Lista de DetalleReceta | Ingredientes de las recetas de productos activos. | P-DR-013 | Asegurar que se consulten las recetas de los productos activos o de los inactivos, segun lo solicitado. | No existen recetas de productos activos. | Informar que no hay recetas registradas para productos activos. |
| Consultar recetas de productos inactivos | Permite consultar las recetas de los productos que la pizzeria ya no ofrece, para conservar su historial. | Ninguno |  | No requiere datos de entrada. | Lista de DetalleReceta | Ingredientes de las recetas de productos inactivos. | P-DR-013 | Asegurar que se consulten las recetas de los productos activos o de los inactivos, segun lo solicitado. | No existen recetas de productos inactivos. | Informar que no hay recetas registradas para productos inactivos. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Agregar ingrediente a receta | Permite incorporar un ingrediente a la composición de una receta indicando la cantidad necesaria para su elaboración. | DetalleReceta | Receta, ingredientes, unidades de medida, detalles actuales de la receta | Permite identificar la receta, seleccionar un ingrediente válido y verificar su composición actual antes de incorporarlo. | P-DR-001 |  | Ingrediente incorporado a receta |  |
|  |  |  |  |  |  | P-DR-002 |  |  |  |
|  |  |  |  |  |  | P-DR-014 |  |  |  |
|  |  |  |  |  |  | P-DR-009 |  |  |  |
|  |  |  |  |  |  | P-DR-003 |  |  |  |
|  |  |  |  |  |  | P-DR-011 |  |  |  |
|  |  |  |  |  |  | P-DR-004 |  |  |  |
|  |  |  |  |  |  | P-DR-005 |  |  |  |
|  |  |  |  |  |  | P-DR-006 |  |  |  |
|  |  |  |  |  |  | P-DR-010 |  |  |  |
| Administrador | Modificar cantidad de ingrediente | Permite ajustar la cantidad de un ingrediente requerida para la elaboración de una receta. | DetalleReceta | Detalle de receta, ingrediente, cantidad actual | Permite identificar el ingrediente y conocer la cantidad actualmente definida para establecer la nueva cantidad. | P-DR-007 |  | Cantidad requerida de ingrediente actualizada |  |
|  |  |  |  |  |  | P-DR-004 |  |  |  |
| Administrador | Retirar ingrediente de receta | Permite retirar un ingrediente de la composición de una receta cuando deja de ser requerido para su elaboración. | DetalleReceta | Detalle actual de la receta, ingrediente | Permite identificar el ingrediente que será retirado de la composición. | P-DR-008 |  | Ingrediente retirado de receta |  |
| Administrador | Consultar ingredientes por filtro | Permite consultar los ingredientes de recetas que cumplan con los criterios indicados, por ejemplo todos los ingredientes de un producto. |  | Criterios de busqueda | Producto, producto interno o unidad de medida por los que se desea filtrar. | P-DR-012 |  | Ingredientes consultados |  |
| Administrador | Consultar recetas de productos activos | Permite consultar las recetas (ingredientes) de los productos que la pizzeria ofrece actualmente. |  | Recetas registradas | Recetas de los productos con estado activo. | P-DR-013 |  | Recetas de productos activos consultadas |  |
| Administrador | Consultar recetas de productos inactivos | Permite consultar las recetas de los productos que la pizzeria ya no ofrece, para conservar su historial. |  | Recetas registradas | Recetas de los productos con estado inactivo. | P-DR-013 |  | Recetas de productos inactivos consultadas |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-DR-001 | Asegurar que los datos requeridos para registrar un detalle de receta sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | DetalleReceta |  |
| P-DR-002 | Asegurar que el producto (pizza o adicion) al que pertenece este detalle de receta exista en el sistema. | Producto | P-PRO-009 |
| P-DR-003 | Asegurar que el producto interno que se asociará al detalle de receta exista en el sistema. | ProductoInterno | P-PI-007 |
| P-DR-004 | Asegurar que la cantidad registrada para un producto interno sea mayor que cero y como maximo 10.000. | DetalleReceta |  |
| P-DR-005 | Asegurar que la unidad de medida del detalle de receta se asigne automaticamente segun el tipo de medida del producto interno: Peso = g, Volumen = ml, Unidad = und. El usuario no la ingresa. | DetalleReceta |  |
| P-DR-006 | Asegurar que un producto interno no se encuentre registrado más de una vez dentro de la misma receta. | DetalleReceta |  |
| P-DR-007 | Asegurar que el detalle de receta que se desea modificar se encuentre registrado en el sistema. | DetalleReceta |  |
| P-DR-008 | Asegurar que el detalle de receta exista con el identificador indicado | DetalleReceta |  |
| P-DR-009 | Asegurar que el producto asociado al detalle de receta sea una pizza o una adicion: las bebidas no tienen receta, porque no se elaboran, se compran y se venden directamente. | DetalleReceta |  |
| P-DR-010 | Asegurar que el identificador generado para el detalle de receta no este siendo utilizado por otro detalle de receta. | DetalleReceta |  |
| P-DR-011 | Asegurar que el producto interno del ingrediente se encuentre activo. | ProductoInterno | P-PI-006 |
| P-DR-012 | Asegurar que la consulta retorne los ingredientes que cumplan con los criterios indicados (producto, producto interno o unidad de medida). | DetalleReceta |  |
| P-DR-013 | Asegurar que se consulten las recetas de los productos activos o de los inactivos, segun lo solicitado. | DetalleReceta |  |
| P-DR-014 | Asegurar que el producto al que se agrega el ingrediente se encuentre activo. | Producto | P-PRO-011 |
