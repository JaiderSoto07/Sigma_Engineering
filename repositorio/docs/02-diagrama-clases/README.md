# Diagrama de clases

[Inicio](../../README.md) · [Modelo de dominio](../01-modelo-dominio/README.md) · [Modelo entidad-relación](../03-modelo-entidad-relacion/README.md)

Diagrama de clases del sistema y documentación detallada de cada clase: relaciones, atributos, constructores, métodos accesores, métodos de negocio y su `Builder`.

## Diagrama

[![Diagrama de clases](../../assets/diagramas/diagrama-clases.svg)](../../assets/diagramas/diagrama-clases.svg)

> Haz clic en la imagen para verla en tamaño completo. También disponible en [PNG](../../assets/diagramas/diagrama-clases.png).

## Clases

| Clase | Estereotipo | Descripción |
|---|---|---|
| [UnidadMedida](clases/UnidadMedida.md) | class | Representa las unidades utilizadas por la pizzería para cuantificar los ingredientes, productos y demás elementos que requieren control de cantidades. |
| [TipoProducto](clases/TipoProducto.md) | class | Representa la clasificación de cada producto que ofrece la pizzería, indicando si corresponde a una pizza, una bebida o una adición. |
| [ClaseMovimiento](clases/ClaseMovimiento.md) | class | Representa la clase de cada movimiento de inventario, indicando si corresponde a una entrada o a una salida de existencias. |
| [CategoriaOrigen](clases/CategoriaOrigen.md) | class | Representa la categoría de origen de la operación que genera un movimiento de inventario: una compra, una venta o un cambio de producto. |
| [TipoMovimiento](clases/TipoMovimiento.md) | class | Representa el código de una operación concreta que mueve el inventario (cada detalle de compra, consumo de venta o cambio tiene el suyo); pertenece a una categoría de origen y lo comparten la operación y los movimientos que genera. |
| [Tamano](clases/Tamano.md) | class | Representa las distintas presentaciones de tamaño en las que la pizzería puede ofrecer un producto; los productos que no son pizza usan "No aplica". |
| [Proveedor](clases/Proveedor.md) | class | Representa a las personas o empresas que suministran a la pizzería los insumos y productos necesarios para su operación. |
| [ProductoInterno](clases/ProductoInterno.md) | class | Representa cada insumo o producto que la pizzería compra y almacena en su inventario, ya sea que se consuma dentro de una receta o se venda directamente al cliente. |
| [Producto](clases/Producto.md) | class | Representa los artículos que la pizzería comercializa y que pueden ser vendidos al cliente, sirviendo como referencia para identificar y gestionar su oferta comercial. |
| [DetalleReceta](clases/DetalleReceta.md) | class | Representa cada uno de los ingredientes necesarios para elaborar una receta, indicando la cantidad requerida y la unidad de medida utilizada. |
| [Inventario](clases/Inventario.md) | class | Representa las existencias disponibles de los insumos y productos que la pizzería necesita para operar y atender sus ventas. |
| [Lote](clases/Lote.md) | class | Representa un conjunto de unidades de un mismo insumo recibido en una compra o como reemplazo en un cambio, identificado para controlar su cantidad, saldo, fecha de vencimiento y trazabilidad dentro del inventario. |
| [SalidaLote](clases/SalidaLote.md) | class | Representa el retiro de una cantidad de producto de un lote próximo a vencer; un lote puede tener como máximo un retiro y este no genera movimiento de inventario. |
| [Compra](clases/Compra.md) | class | Representa la adquisición de insumos o productos que realiza la pizzería a un proveedor para abastecer las necesidades de su operación. |
| [DetalleCompra](clases/DetalleCompra.md) | class | Representa cada uno de los insumos o productos adquiridos dentro de una compra, especificando qué se compró y en qué cantidad. |
| [DetalleVenta](clases/DetalleVenta.md) | class | Representa cada uno de los productos incluidos en una venta, registrando la cantidad adquirida y el valor correspondiente a cada producto. |
| [ConsumoVenta](clases/ConsumoVenta.md) | class | Representa los productos internos que se consumen por cada detalle de venta, según la receta del producto vendido o su producto interno asociado. |
| [Venta](clases/Venta.md) | class | Representa la operación comercial mediante la cual la pizzería registra los productos adquiridos por un cliente. |
| [HistoricoPrecio](clases/HistoricoPrecio.md) | class | Representa el registro de los precios que han tenido los productos de la pizzería a lo largo del tiempo, permitiendo consultar su evolución histórica. |
| [Cambio](clases/Cambio.md) | class | Representa la operación mediante la cual un insumo no perecedero próximo a vencer se devuelve al proveedor y se reemplaza por uno nuevo, manteniendo la disponibilidad y las condiciones adecuadas de los insumos de la pizzería. |
| [MovimientoInventario](clases/MovimientoInventario.md) | class | Representa cada entrada o salida de existencias sobre un lote, generada por una compra, una venta o un cambio, permitiendo mantener el control y la trazabilidad del inventario. |
| [Usuario](clases/Usuario.md) | class | Representa a la persona que puede ingresar a la aplicación; solo los usuarios activos con rol Administrador pueden iniciar sesión. |
