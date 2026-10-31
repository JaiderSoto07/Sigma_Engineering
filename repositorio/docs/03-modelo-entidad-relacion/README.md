# Modelo entidad-relación (MER)

[Inicio](../../README.md) · [Modelo de dominio](../01-modelo-dominio/README.md) · [Diagrama de clases](../02-diagrama-clases/README.md)

Modelo de datos relacional del sistema y diccionario de datos de cada entidad: tipos, llaves primarias y foráneas, restricciones de unicidad, chequeo e índices.

## Diagrama

[![Modelo entidad-relación](../../assets/diagramas/mer.svg)](../../assets/diagramas/mer.svg)

> Haz clic en la imagen para verla en tamaño completo. También disponible en [PNG](../../assets/diagramas/mer.png).

## Entidades

| Entidad | Descripción |
|---|---|
| [Producto](entidades/Producto.md) | Representa los artículos que la pizzería comercializa y que pueden ser vendidos al cliente, sirviendo como referencia para identificar y gestionar su oferta comercial. |
| [Proveedor](entidades/Proveedor.md) | Representa a las personas o empresas que suministran a la pizzería los insumos y productos necesarios para su operación. |
| [UnidadMedida](entidades/UnidadMedida.md) | Representa las unidades utilizadas por la pizzería para cuantificar los ingredientes, productos y demás elementos que requieren control de cantidades. |
| [Compra](entidades/Compra.md) | Representa la adquisición de insumos o productos que realiza la pizzería a un proveedor para abastecer las necesidades de su operación. |
| [HistoricoPrecio](entidades/HistoricoPrecio.md) | Representa el registro de los precios que han tenido los productos de la pizzería a lo largo del tiempo, permitiendo consultar su evolución histórica. |
| [Venta](entidades/Venta.md) | Representa la operación comercial mediante la cual la pizzería registra los productos adquiridos por un cliente. |
| [DetalleCompra](entidades/DetalleCompra.md) | Representa cada uno de los insumos o productos adquiridos dentro de una compra, especificando qué se compró y en qué cantidad. |
| [DetalleVenta](entidades/DetalleVenta.md) | Representa cada uno de los productos incluidos en una venta, registrando la cantidad adquirida y el valor correspondiente a cada producto. |
| [DetalleReceta](entidades/DetalleReceta.md) | Representa cada uno de los ingredientes necesarios para elaborar una receta, indicando la cantidad requerida y la unidad de medida utilizada. |
| [MovimientoInventario](entidades/MovimientoInventario.md) | Representa cada entrada o salida de existencias sobre un lote, generada por una compra, una venta o un cambio, permitiendo mantener el control y la trazabilidad del inventario. |
| [Inventario](entidades/Inventario.md) | Representa las existencias disponibles de los insumos y productos que la pizzería necesita para operar y atender sus ventas. |
| [Lote](entidades/Lote.md) | Representa un conjunto de unidades de un mismo insumo recibido en una compra o como reemplazo en un cambio, identificado para controlar su cantidad, saldo, fecha de vencimiento y trazabilidad dentro del inventario. |
| [ProductoInterno](entidades/ProductoInterno.md) | Representa cada insumo o producto que la pizzeria compra y almacena en su inventario, ya sea que se consuma dentro de una receta o se venda directamente al cliente. |
| [TipoProducto](entidades/TipoProducto.md) | Representa la clasificacion de cada producto que ofrece la pizzeria, indicando si corresponde a una pizza, una bebida o una adicion. |
| [ClaseMovimiento](entidades/ClaseMovimiento.md) | Representa la clase de cada movimiento de inventario, indicando si corresponde a una entrada o a una salida de existencias. |
| [Tamaño](entidades/Tamano.md) | Representa las distintas presentaciones de tamaño en las que la pizzería puede ofrecer un producto; los productos que no son pizza usan "No aplica". |
| [Cambio](entidades/Cambio.md) | Representa la operación mediante la cual un insumo no perecedero próximo a vencer se devuelve al proveedor y se reemplaza por uno nuevo, manteniendo la disponibilidad y las condiciones adecuadas de los insumos de la pizzería. |
| [CategoriaOrigen](entidades/CategoriaOrigen.md) | Representa la categoría de origen de la operación que genera un movimiento de inventario: una compra, una venta o un cambio de producto. |
| [TipoMovimiento](entidades/TipoMovimiento.md) | Representa el código de una operación concreta que mueve el inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo); pertenece a una categoría de origen y lo comparten la operación y los movimientos que genera. |
| [ConsumoVenta](entidades/ConsumoVenta.md) | Representa los productos internos que se consumen por cada detalle de venta, según la receta del producto vendido o su producto interno asociado. |
| [SalidaLote](entidades/SalidaLote.md) | Representa el retiro de una cantidad de producto de un lote próximo a vencer; un lote puede tener como máximo un retiro y este no genera movimiento de inventario. |
| [Usuario](entidades/Usuario.md) | Representa a la persona que puede ingresar a la aplicación; solo los usuarios activos con rol Administrador pueden iniciar sesión. |
