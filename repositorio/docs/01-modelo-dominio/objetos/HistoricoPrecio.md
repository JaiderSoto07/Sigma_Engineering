# HistoricoPrecio

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: HistoricoPrecio](../../02-diagrama-clases/clases/HistoricoPrecio.md) · [Entidad MER: HistoricoPrecio](../../03-modelo-entidad-relacion/entidades/HistoricoPrecio.md)

> Aquí se guarda el registro de los precios que ha tenido cada producto a lo largo del tiempo, para poder ver cómo han cambiado.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Representa el identificador único de cada historico de precio |  |  |
| Producto | Producto al cual se le realizo un cambio de precio | Producto | Si |
| Precio | Precio correpondiente a un producto | Decimal Europeo (2) | Si |
| FechaInicio | Fecha en la que se dio inicio a el precio de un prodcuto , ya sea que en esta fecha se ingreso por primera vez el producto o en esta fecha se inicio el nuevo precio de un producto | Fecha | Si |
| FechaFin | Fecha en la que finaliza ese precio en especifico | Fecha | No |

### Datos de muestra

_14 registros de ejemplo._

| Id | Producto | Precio | FechaInicio | FechaFin | Combinación única 1 | Combinación única 2 |
|---|---|---|---|---|---|---|
| 1 | Margarita Grande | 25000 | 25/03/2025 | 01/01/1000 | Margarita Grande 25/03/2025 | Margarita Grande 01/01/1000 |
| 2 | Peperoni Mediana | 20000 | 28/04/2025 | 24/07/2025 | Peperoni Mediana 28/04/2025 | Peperoni Mediana 24/07/2025 |
| 3 | Peperoni Mediana | 23000 | 25/07/2026 | 01/01/1000 | Peperoni Mediana 25/07/2026 | Peperoni Mediana 01/01/1000 |
| 4 | Margarita y Hawaiana Pequeña | 30000 | 25/06/2026 | 01/01/1000 | Margarita y Hawaiana Pequeña 25/06/2026 | Margarita y Hawaiana Pequeña 01/01/1000 |
| 5 | Extra Queso | 5000 | 26/06/2026 | 01/01/1000 | Extra Queso 26/06/2026 | Extra Queso 01/01/1000 |
| 6 | Extra Piña | 4000 | 27/06/2026 | 24/07/2026 | Extra Piña 27/06/2026 | Extra Piña 24/07/2026 |
| 7 | Extra Piña | 6000 | 25/07/2026 | 01/01/1000 | Extra Piña 25/07/2026 | Extra Piña 01/01/1000 |
| 8 | Extra Peperoni | 9000 | 25/08/2026 | 01/01/1000 | Extra Peperoni 25/08/2026 | Extra Peperoni 01/01/1000 |
| 9 | Jugo Hit Mora 200 ml | 4000 | 26/08/2026 | 01/01/1000 | Jugo Hit Mora 200 ml 26/08/2026 | Jugo Hit Mora 200 ml 01/01/1000 |
| 10 | Pepsi 400 ml | 6000 | 27/08/2026 | 01/01/1000 | Pepsi 400 ml 27/08/2026 | Pepsi 400 ml 01/01/1000 |
| 11 | Monster 473 ml | 5200 | 28/08/2026 | 01/01/1000 | Monster 473 ml 28/08/2026 | Monster 473 ml 01/01/1000 |
| 12 | Colombiana 400 ml | 4500 | 14/09/2026 | 01/01/1000 | Colombiana 400 ml 14/09/2026 | Colombiana 400 ml 01/01/1000 |
| 13 | Mr tea 300 ml | 4500 | 10/10/2025 | 01/01/1000 | Mr tea 300 ml 10/10/2025 | Mr tea 300 ml 01/01/1000 |
| 14 | Hawaiana Grande | 35000 | 01/09/2026 | 01/01/1000 | Hawaiana Grande 01/09/2026 | Hawaiana Grande 01/01/1000 |

## Caracterización

### Atributos

| Nombre atributo | Id | Producto | Precio | FechaInicio | FechaFin |
|---|---|---|---|---|---|
| Descripción atributo | Representa el identificador único de cada historico de precio | Producto al cual se le realizo un cambio de precio | Precio correpondiente a un producto | Fecha en la que se dio inicio a el precio de un prodcuto , ya sea que en esta fecha se ingreso por primera vez el producto o en esta fecha se inicio el nuevo precio de un producto | Fecha en la que finaliza ese precio en especifico |
| Identificador del objeto de dominio | Si | No | No | No | No |
| Tipo de dato | Identificador único universal | Producto | Decimal Europeo | Fecha | Fecha |
| Obligatorio | Si | Si | Si | Si | No |
| Longitud mínima (si aplica) |  |  |  |  |  |
| Longitud máxima (si aplica) |  |  |  |  |  |
| Precisión (si aplica) |  |  | 2 |  |  |
| Rendondeo (si aplica) |  |  | A la unidad (0) |  |  |
| Rango inicial (si aplica) |  |  | &gt;0 |  |  |
| Rango final (si aplica) |  |  | 1000000 |  |  |
| Formato (si aplica) |  |  |  | dd/mm/yyyy | dd/mm/yyyy |
| Reglas especiales (si aplica) |  | Debe corresponder a un producto existente en la pizzería. | El precio debe ser mayor a cero y como maximo 1.000.000. | Representa la fecha desde la cual rige este precio para el producto. | Si el precio sigue vigente (sin fecha de fin todavia), se registra el valor por defecto "01/01/1000". Cuando el precio termina, la FechaFin debe ser mayor o igual a la FechaInicio. |
| Dato calculado | No | No | No | No | No |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | No | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | No | No |
| Dato sensible (si aplica) | No | No | No | No | No |
| Valor por defecto (si aplica) |  |  |  |  | 01/01/1000 |
| Auto generado | Si | No | No | No | No |
| Registrar precio | Requerido | Requerido | Requerido | Requerido | No Requerido |
| Consultar historico de precios de un producto | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | A un único producto le corresponde una única fecha de inicio, que es en la que se actualizó el precio. | Producto |
|  |  | FechaInicio |
| Combinación única 2 | Un producto solo puede tener un precio vigente: no pueden existir dos registros del mismo Producto con la misma FechaFin. | Producto |
|  |  | FechaFin |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar precio | Permite establecer un nuevo precio para un producto, iniciando su nuevo período de vigencia y conservando el precio anterior para mantener la trazabilidad. | Datos | HistóricoPrecio | Información necesaria para registrar el nuevo precio y su período de vigencia, conservando el precio anterior como parte del historial. | HistóricoPrecio | Registro del nuevo precio con su período de vigencia, y el precio anterior conservado en el historial. | P-HP-001 | Todo registro de histórico de precio debe contar con la información necesaria para identificar el producto, el precio y el periodo de vigencia. | Los datos ingresados para registrar el nuevo precio no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-HP-002 | El producto al que se asocia un histórico de precio debe existir. | El producto al que se asocia el nuevo precio no existe. | Rechazar el registro e informar que el producto no existe. |
|  |  |  |  |  |  |  | P-HP-003 | El precio registrado debe ser mayor que cero y como maximo 1.000.000. | El precio no es mayor que cero o supera 1.000.000. | Rechazar el registro e informar el rango permitido. |
|  |  |  |  |  |  |  | P-HP-004 | La fecha de inicio de vigencia del precio debe ser válida. | La fecha de inicio de vigencia del precio no es valida. | Rechazar el registro e informar que la fecha de inicio no es valida. |
|  |  |  |  |  |  |  | P-HP-005 | La fecha de finalizacion de un precio debe ser mayor o igual a su fecha de inicio; mientras el precio este vigente se registra 01/01/1000. | La fecha de finalizacion es anterior a la fecha de inicio. | Rechazar el registro e informar que las fechas no son validas. |
|  |  |  |  |  |  |  | P-HP-006 | No deben existir dos registros de precio del mismo producto con periodos de vigencia superpuestos. | El nuevo periodo de vigencia se superpone con otro periodo vigente del mismo producto. | Rechazar el registro e informar la superposicion de periodos. |
|  |  |  |  |  |  |  | P-HP-007 | Solo puede existir un precio vigente para un producto en un momento determinado. | Ya existe un precio vigente para el producto en este momento. | Rechazar el registro e informar que ya existe un precio vigente para ese producto. |
|  |  |  |  |  |  |  | P-HP-008 | Al registrar un nuevo precio para un producto, el precio vigente anterior se cierra asignandole como FechaFin el dia anterior al inicio del nuevo precio; el nuevo queda vigente (FechaFin 01/01/1000) y se actualiza el precio del producto. | No fue posible cerrar el precio vigente anterior. | Rechazar el registro para no dejar dos precios vigentes. |
| Consultar historico de precios de un producto | Permite consultar todos los precios que ha tenido un producto, con su periodo de vigencia. | Id | UUID | Identificador del producto. | Lista de HistoricoPrecio | Precios del producto ordenados por fecha de inicio. | P-HP-002 | El producto al que se asocia un histórico de precio debe existir. | El producto no existe. | Informar que el producto no existe. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar precio | Permite establecer un nuevo precio para un producto, iniciando su nuevo período de vigencia y conservando el precio anterior para mantener la trazabilidad. | HistoricoPrecio | Producto, precios registrados | Permite consultar el producto y su historial de precios para determinar el precio vigente y establecer el nuevo período de vigencia sin generar períodos superpuestos. | P-HP-001 |  | Precio registrado | ¿El precio inicial debe registrarse obligatoriamente antes de que un producto pueda venderse? |
|  |  |  |  |  |  | P-HP-002 |  |  |  |
|  |  |  |  |  |  | P-HP-003 |  |  |  |
|  |  |  |  |  |  | P-HP-004 |  |  |  |
|  |  |  |  |  |  | P-HP-005 |  |  |  |
|  |  |  |  |  |  | P-HP-006 |  |  |  |
|  |  |  |  |  |  | P-HP-007 |  |  |  |
|  |  |  |  |  |  | P-HP-008 |  |  |  |
| Administrador | Consultar historico de precios de un producto | Permite consultar todos los precios que ha tenido un producto, con su periodo de vigencia. |  | Historico de precios | Producto del que se desea conocer el historico. | P-HP-002 |  | Historico de precios consultado |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-HP-001 | Todo registro de histórico de precio debe contar con la información necesaria para identificar el producto, el precio y el periodo de vigencia. | HistoricoPrecio |  |
| P-HP-002 | El producto al que se asocia un histórico de precio debe existir. | Producto | P-PRO-009 |
| P-HP-003 | El precio registrado debe ser mayor que cero y como maximo 1.000.000. | HistoricoPrecio |  |
| P-HP-004 | La fecha de inicio de vigencia del precio debe ser válida. | HistoricoPrecio |  |
| P-HP-005 | La fecha de finalizacion de un precio debe ser mayor o igual a su fecha de inicio; mientras el precio este vigente se registra 01/01/1000. | HistoricoPrecio |  |
| P-HP-006 | No deben existir dos registros de precio del mismo producto con periodos de vigencia superpuestos. | HistoricoPrecio |  |
| P-HP-007 | Solo puede existir un precio vigente para un producto en un momento determinado. | HistoricoPrecio |  |
| P-HP-008 | Al registrar un nuevo precio para un producto, el precio vigente anterior se cierra asignandole como FechaFin el dia anterior al inicio del nuevo precio; el nuevo queda vigente (FechaFin 01/01/1000) y se actualiza el precio del producto. | HistoricoPrecio |  |
| P-HP-009 | El precio aplicado en una venta debe corresponder al precio vigente del producto en el momento de realizar la venta. | HistoricoPrecio |  |
| P-HP-010 | Un registro histórico de precio asociado a una venta realizada no debe eliminarse ni modificarse de manera que se pierda la trazabilidad del precio aplicado. | HistoricoPrecio |  |
| P-HP-011 | El historico de precios de un producto debe conservarse aunque el producto sea inactivado. | HistóricoPrecio |  |
