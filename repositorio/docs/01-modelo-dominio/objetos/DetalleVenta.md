# DetalleVenta

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: DetalleVenta](../../02-diagrama-clases/clases/DetalleVenta.md) · [Entidad MER: DetalleVenta](../../03-modelo-entidad-relacion/entidades/DetalleVenta.md)

> Es cada uno de los productos que se vendieron en una venta, con la cantidad y el valor de cada uno.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico del detalle  venta dentro de la pizzeria |  |  |
| Venta | Venta a la que pertenece; se identifica con su numero de factura | Venta | Si |
| Cantidad | Cantidad del producto que pide el cliente | Entero | Si |
| Producto | Producto  que ofrece la pizzeria | Producto | Si |
| PrecioProducto | Precio de cada producto | Decimal Europeo (2) | Si |
| Subtotal | Por cada cantidad que pide el cliente de un producto se calcula un total | Decimal Europeo | Si |

### Datos de muestra

_5 registros de ejemplo._

| Id | Venta | Cantidad | Producto | PrecioProducto | Subtotal | CombinacionUnica |
|---|---|---|---|---|---|---|
| 1 | FV-000001 | 1 | Margarita Grande | 25000 | 25000 | FV-000001  Margarita Grande |
| 2 | FV-000002 | 2 | Peperoni Mediana | 23000 | 46000 | FV-000002  Peperoni Mediana |
| 3 | FV-000002 | 2 | Margarita y Hawaiana Pequeña | 30000 | 60000 | FV-000002  Margarita y Hawaiana Pequeña |
| 4 | FV-000003 | 1 | Extra Queso | 5000 | 5000 | FV-000003  Extra Queso |
| 5 | FV-000004 | 1 | Hawaiana Grande | 35000 | 35000 | FV-000004  Hawaiana Grande |

## Caracterización

### Atributos

| Nombre atributo | Id | Venta | Cantidad | Producto | PrecioProducto | Subtotal |
|---|---|---|---|---|---|---|
| Descripción atributo | Identificador unico del detalle  venta dentro de la pizzeria | Venta a la que pertenece; se identifica con su numero de factura | Cantidad del producto que pide el cliente | Producto  que ofrece la pizzeria | Precio de cada producto | Por cada cantidad que pide el cliente de un producto se calcula un total |
| Identificador del objeto de dominio | Si | No | No | No | No | No |
| Tipo de dato | Identificador unico universal | Venta | Entero | Producto | Decimal Europeo | Decimal Europeo |
| Obligatorio | Si | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |  |
| Precisión (si aplica) |  |  |  |  | 2 | 2 |
| Rendondeo (si aplica) |  |  |  |  | A la unidad (0) | A la unidad (0) |
| Rango inicial (si aplica) |  |  | &gt;0 |  | &gt;0 | &gt;0 |
| Rango final (si aplica) |  |  | 1000 |  | 1000000 | 1000000 |
| Formato (si aplica) |  |  |  |  |  |  |
| Reglas especiales (si aplica) |  | Debe corresponder a una Venta existente. | La cantidad es un numero entero mayor a cero y como maximo 1000. | Debe corresponder a un Producto existente. | Se toma del precio vigente del producto en la fecha de la venta (HistoricoPrecio) y se guarda en el detalle, para conservar el precio con el que se vendio aunque despues cambie. | Debe calcularse siempre como cantidad pedida por el cliente de ese producto por el precio del producto |
| Dato calculado | No | No | No | No | Si | Si |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  |  |  |
| Auto generado | Si | No | No | No | No | No |
| Agregar producto a venta | Requerido | Requerido | Requerido | Requerido | Requerido | Requerido |
| Modificar cantidad de producto | Requerido / No Modificable | No Requerido / No Modificable | Requerido / Modificable | No Requerido / No Modificable | No Requerido / No Modificable | No Requerido / No Modificable |
| Retirar producto de la venta | Requerido | No Requerido | No Requerido | No Requerido | No Requerido | No Requerido |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | Un mismo Producto no se puede repetir dentro de la misma Venta. | Venta |
|  |  | Producto |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Agregar producto a venta | Permite incorporar a la venta un producto solicitado | Datos | DetalleVenta | Información necesaria para incorporar el producto a la venta. | DetalleVenta | Detalle de venta creado con el producto, cantidad, precio y subtotal correspondientes. | P-DV-005 | La venta a la que pertenece el detalle debe existir. | La venta a la que se agrega el producto no existe. | Rechazar el registro e informar que la venta no existe. |
|  |  |  |  |  |  |  | P-DV-009 | Un detalle de una venta confirmada no puede ser modificado ni eliminado. | La venta ya fue confirmada. | Rechazar el cambio e informar que una venta confirmada no se puede modificar. |
|  |  |  |  |  |  |  | P-DV-001 | Todo detalle de venta debe contar con la información necesaria para identificar el producto, la cantidad y el valor de la venta. | La informacion del detalle de venta no es valida o esta incompleta. | Rechazar la operacion e informar la informacion invalida o faltante. |
|  |  |  |  |  |  |  | P-DV-002 | El producto asociado a un detalle de venta debe existir y encontrarse activo. | El producto no existe o se encuentra inactivo. | Rechazar el registro e informar que el producto no esta disponible para la venta. |
|  |  |  |  |  |  |  | P-DV-003 | La cantidad registrada en un detalle de venta debe ser un numero entero mayor que cero y como maximo 1000. | La cantidad no es un numero entero entre 1 y 1000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DV-004 | El precio registrado en el detalle de venta debe ser válido para el producto vendido en el momento de realizar la venta. | El precio registrado no corresponde al precio vigente del producto en el momento de la venta. | Rechazar la operacion e informar que el precio no es valido. |
|  |  |  |  |  |  |  | P-DV-006 | No deben existir dos detalles que representen el mismo producto dentro de una misma venta; si se requiere más cantidad, debe actualizarse la cantidad del detalle existente. | El producto ya esta en la venta. | Rechazar el registro e indicar que se modifique la cantidad del detalle existente. |
|  |  |  |  |  |  |  | P-DV-013 | El subtotal del detalle se calcula como la cantidad por el precio del producto; no lo digita el usuario. | El subtotal no corresponde a la cantidad por el precio. | Recalcular el subtotal a partir de la cantidad y el precio. |
| Modificar cantidad de producto | Permite ajustar la cantidad de un producto incluido en la venta | Datos | DetalleVenta | Identificador del detalle y nueva información necesaria para modificar la cantidad. | DetalleVenta | Detalle de venta actualizado con la nueva cantidad y el subtotal recalculado. | P-DV-007 | El detalle de venta que se pretenda modificar debe existir. | El detalle de venta que se pretende modificar no existe. | Rechazar la modificacion e informar que el detalle de venta no existe. |
|  |  |  |  |  |  |  | P-DV-009 | Un detalle de una venta confirmada no puede ser modificado ni eliminado. | La venta ya fue confirmada. | Rechazar el cambio e informar que una venta confirmada no se puede modificar. |
|  |  |  |  |  |  |  | P-DV-003 | La cantidad registrada en un detalle de venta debe ser un numero entero mayor que cero y como maximo 1000. | La nueva cantidad no es un numero entero entre 1 y 1000. | Rechazar la modificacion e informar el rango permitido. |
|  |  |  |  |  |  |  | P-DV-013 | El subtotal del detalle se calcula como la cantidad por el precio del producto; no lo digita el usuario. | El subtotal no corresponde a la cantidad por el precio. | Recalcular el subtotal a partir de la cantidad y el precio. |
| Retirar producto de la venta | Permite quitar un producto de una venta que todavia no ha sido confirmada. | Id | UUID | Identificador del detalle de venta. | DetalleVenta | Detalle de venta retirado. | P-DV-008 | El detalle de venta que se pretenda eliminar debe existir. | El detalle de venta que se desea retirar no existe. | Rechazar la operacion e informar que el detalle no existe. |
|  |  |  |  |  |  |  | P-DV-009 | Un detalle de una venta confirmada no puede ser modificado ni eliminado. | La venta ya fue confirmada. | Rechazar el cambio e informar que una venta confirmada no se puede modificar. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|
| Administrador | Agregar producto a venta | Permite incorporar a la venta un producto solicitado | DetalleVenta | Productos disponibles, precio vigente | Permite seleccionar un producto válido y obtener el precio que corresponde al momento de la venta. | P-DV-005 | Producto agregado a la venta | ¿Se permitirá agregar productos al detalle de venta si el sistema detecta que el inventario virtual está en cero? Respuesta: Si. El inventario no se valida al agregar productos, sino al confirmar la venta (P-VEN-005 y P-VEN-013). |
|  |  |  |  |  |  | P-DV-009 |  |  |
|  |  |  |  |  |  | P-DV-001 |  |  |
|  |  |  |  |  |  | P-DV-002 |  |  |
|  |  |  |  |  |  | P-DV-003 |  |  |
|  |  |  |  |  |  | P-DV-004 |  |  |
|  |  |  |  |  |  | P-DV-006 |  |  |
|  |  |  |  |  |  | P-DV-013 |  |  |
| Administrador | Modificar cantidad de producto | Permite ajustar la cantidad de un producto incluido en la venta | DetalleVenta | Detalles actuales de la venta | Permite consultar la información existente del detalle antes de realizar la modificación. | P-DV-007 | Cantidad de producto modificada |  |
|  |  |  |  |  |  | P-DV-009 |  |  |
|  |  |  |  |  |  | P-DV-003 |  |  |
|  |  |  |  |  |  | P-DV-013 |  |  |
| Administrador | Retirar producto de la venta | Permite quitar un producto de una venta que todavia no ha sido confirmada. |  | Detalle de venta | Detalle que se desea quitar de la venta. | P-DV-008 | Producto retirado de la venta |  |
|  |  |  |  |  |  | P-DV-009 |  |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-DV-001 | Todo detalle de venta debe contar con la información necesaria para identificar el producto, la cantidad y el valor de la venta. | DetalleVenta |  |
| P-DV-002 | El producto asociado a un detalle de venta debe existir y encontrarse activo. | Producto | P-PRO-011 |
| P-DV-003 | La cantidad registrada en un detalle de venta debe ser un numero entero mayor que cero y como maximo 1000. | DetalleVenta |  |
| P-DV-004 | El precio registrado en el detalle de venta debe ser válido para el producto vendido en el momento de realizar la venta. | HistoricoPrecio | P-HP-009 |
| P-DV-005 | La venta a la que pertenece el detalle debe existir. | Venta | P-VEN-007 |
| P-DV-006 | No deben existir dos detalles que representen el mismo producto dentro de una misma venta; si se requiere más cantidad, debe actualizarse la cantidad del detalle existente. | DetalleVenta |  |
| P-DV-007 | El detalle de venta que se pretenda modificar debe existir. | DetalleVenta |  |
| P-DV-008 | El detalle de venta que se pretenda eliminar debe existir. | DetalleVenta |  |
| P-DV-009 | Un detalle de una venta confirmada no puede ser modificado ni eliminado. | Venta | P-VEN-009 |
| P-DV-010 | La cantidad vendida de un producto debe ser compatible con la disponibilidad de inventario cuando la venta sea confirmada. | MovimientoInventario | P-VEN-005 |
| P-DV-011 | Cuando el producto se vende directo del inventario (sin receta, por ejemplo una bebida), el consumo se registra sobre su producto interno asociado. | ConsumoVenta | P-CV-002 |
| P-DV-012 | Asegurar que el detalle de la venta exista con el identificador indicado | DetalleVenta |  |
| P-DV-013 | El subtotal del detalle se calcula como la cantidad por el precio del producto; no lo digita el usuario. | DetalleVenta |  |
