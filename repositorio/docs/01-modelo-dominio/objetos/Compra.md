# Compra

[Inicio](../../../README.md) · [Modelo de dominio](../README.md) · [Clase: Compra](../../02-diagrama-clases/clases/Compra.md) · [Entidad MER: Compra](../../03-modelo-entidad-relacion/entidades/Compra.md)

> Es cuando la pizzería le compra insumos o productos a un proveedor.

[Muestreo](#muestreo) · [Caracterización](#caracterización) · [Eventos](#eventos) · [Políticas](#políticas)

## Muestreo

### Atributos

| Atributo | Descripción | Tipo de dato | Obligatorio |
|---|---|---|---|
| Id | Identificador unico de cada compra de la pizzeria |  |  |
| Proveedor | Nombre del proveedor al que se le hizo la compra | Proveedor | Si |
| FechaCompra | Representa la fecha en la que se efectuó la compra | Fecha | Si |
| NumeroFactura | Número de factura que el proveedor entrega al momento de la compra. | Alfanumerico(max 40 caracteres) | Si |
| Total | Representa el valor monetario total de los productos comprados al proveedor. | Decimal Europeo (2) | Si |

### Datos de muestra

_9 registros de ejemplo._

| Id | Proveedor | FechaCompra | NumeroFactura | Total | CombinacionUnica |
|---|---|---|---|---|---|
| 1 | Colanta | 01/09/2026 | FE-1023 | 692500 | Colanta FE-1023 |
| 2 | Harinera del valle | 02/05/2026 | FV-4587 | 551000 | Harinera del valle FV-4587 |
| 3 | Postobon | 10/07/2026 | PO-9012 | 3600 | Postobon PO-9012 |
| 4 | Harinera del valle | 15/08/2026 | FV-5102 | 126000 | Harinera del valle FV-5102 |
| 5 | Colanta | 01/07/2026 | FE-1102 | 3750 | Colanta FE-1102 |
| 6 | Central de Abastos | 15/06/2026 | CA-2201 | 105000 | Central de Abastos CA-2201 |
| 7 | Colanta | 26/09/2026 | FE-1250 | 7500 | Colanta FE-1250 |
| 8 | Central de Abastos | 28/09/2026 | CA-2310 | 36000 | Central de Abastos CA-2310 |
| 9 | Colanta | 30/09/2026 | FE-1263 | 12500 | Colanta FE-1263 |

## Caracterización

### Atributos

| Nombre atributo | Id | Proveedor | FechaCompra | NumeroFactura | Total |
|---|---|---|---|---|---|
| Descripción atributo | Identificador unico de cada compra de la pizzeria | Nombre del proveedor al que se le hizo la compra | Representa la fecha en la que se efectuó la compra | Número de factura que el proveedor entrega al momento de la compra. | Representa el valor monetario total de los productos comprados al proveedor. |
| Identificador del objeto de dominio | Si | No | No | No | No |
| Tipo de dato | Identificador único universal | Proveedor | Fecha | Alfanumerico | Decimal Europeo |
| Obligatorio | Si | Si | Si | Si | Si |
| Longitud mínima (si aplica) |  |  |  | 4 |  |
| Longitud máxima (si aplica) |  |  |  | 40 |  |
| Precisión (si aplica) |  |  |  |  | 2 |
| Rendondeo (si aplica) |  |  |  |  | A la unidad(0) |
| Rango inicial (si aplica) |  |  |  |  | &gt;0 |
| Rango final (si aplica) |  |  |  |  | 5000000 |
| Formato (si aplica) |  |  | dd/mm/yyyy |  |  |
| Reglas especiales (si aplica) |  | Debe corresponder a un Proveedor existente en el sistema | La fecha no puede ser futura. | Dato real tomado de la factura fisica que entrega el proveedor, no es un codigo generado por el sistema. | El valor total de una compra corresponde a la suma de los valores registrados en el atributo PrecioCompra de todos los DetalleCompra asociados a dicha compra. |
| Dato calculado | No | No | No | No | Si |
| Aplica ajuste del dato (si aplica) | No | No | No | No | No |
| Carácter de ajuste (si aplica) |  |  |  |  |  |
| Quitar espacios en blanco al inicio (si aplica) | No | No | No | Si | No |
| Quitar espacios en blanco al fin (si aplica) | No | No | No | Si | No |
| Dato sensible (si aplica) | No | No | No | No | No |
| Valor por defecto (si aplica) | No | No | No | No | No |
| Auto generado | Si | No | No | No | No |
| Registrar compra | Requerido | Requerido | Requerido | Requerido | Requerido |
| Modificar compra | Requerido / No Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / Modificable | No Requerido / No Modificable |
| Eliminar compra | Requerido | No Requerido | No Requerido | No Requerido | No Requerido |
| Consultar compra | No Mostrar | Mostrar | Mostrar | Mostrar | Mostrar |

### Combinaciones únicas

| Nombre combinación única | Descripción | Atributos involucrados |
|---|---|---|
| Combinación única 1 | No pueden existir dos compras con el mismo Proveedor y el mismo numero de factura | Proveedor |
|  |  | NumeroFactura |

### Responsabilidades

| Responsabilidad | Descripción | Entradas · Parámetro | Entradas · Tipo de dato | Entradas · Descripción | Salida · Tipo de Dato | Salida · Descripción | Políticas (Reglas de Negocio) · Política | Políticas (Reglas de Negocio) · Descripción | Excepción · Detalle | Excepción · ¿Qué hago? |
|---|---|---|---|---|---|---|---|---|---|---|
| Registrar compra | Registrar una nueva compra asociada a un proveedor y a la pizzería correspondiente, dejando constancia de la operación de adquisición. | Datos | Compra | Información necesaria para registrar la compra. | Compra | Compra registrada con la información proporcionada. | P-COM-001 | Asegurar que los datos requeridos para registrar una compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los datos ingresados para registrar la compra no son validos. | Rechazar el registro e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-COM-002 | Asegurar que el proveedor asociado a la compra exista y se encuentre registrado en el sistema. | El proveedor asociado a la compra no existe en el sistema. | Rechazar el registro e informar que el proveedor no existe. |
|  |  |  |  |  |  |  | P-COM-007 | Asegurar que no exista otra compra con el mismo Proveedor y el mismo NumeroFactura. | Ya existe otra compra registrada con el mismo Proveedor y el mismo NumeroFactura. | Rechazar el registro e informar que ya existe una compra igual registrada. |
|  |  |  |  |  |  |  | P-COM-008 | Asegurar que el proveedor asociado a la compra no se encuentre retirado o inactivo al momento de registrar la compra. | El proveedor asociado a la compra se encuentra retirado o inactivo. | Rechazar el registro e informar que el proveedor no esta disponible para nuevas compras. |
|  |  |  |  |  |  |  | P-COM-009 | Asegurar que el total de la compra corresponda a la suma de los precios de compra de sus detalles y no supere 5.000.000; el total no lo digita el usuario, se calcula. | El total calculado no corresponde a la suma de los detalles o supera 5.000.000. | Rechazar el registro e informar que el total de la compra no es valido. |
| Modificar compra | Permite corregir la información de una compra existente antes de que tenga movimientos de inventario asociados. | Datos | Compra | Identificador de la compra y datos que se desean modificar. | Compra | Compra modificada correctamente. | P-COM-003 | Asegurar que la compra que se desea modificar se encuentre registrada en el sistema. | La compra no existe con el identificador indicado. | Rechazar la modificación e informar que la compra no existe. |
|  |  |  |  |  |  |  | P-COM-001 | Asegurar que los datos requeridos para registrar una compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Los nuevos datos de la compra no son validos. | Rechazar la modificacion e informar cual dato no es valido. |
|  |  |  |  |  |  |  | P-COM-007 | Asegurar que no exista otra compra con el mismo Proveedor y el mismo NumeroFactura. | La modificacion deja dos compras con el mismo Proveedor y el mismo NumeroFactura. | Rechazar la modificacion e informar la duplicidad. |
|  |  |  |  |  |  |  | P-COM-008 | Asegurar que el proveedor asociado a la compra no se encuentre retirado o inactivo al momento de registrar la compra. | El proveedor indicado se encuentra retirado o inactivo. | Rechazar la modificacion e informar que el proveedor no esta disponible. |
| Eliminar compra | Permite eliminar una compra que no tenga detalles de compra asociados que deban conservarse para mantener la trazabilidad. | Id | UUID | Identificador de la compra que se desea eliminar. | Compra | Compra eliminada correctamente. | P-COM-004 | Asegurar que la compra que se desea eliminar se encuentre registrada en el sistema. | La compra que se desea eliminar no existe. | Rechazar la eliminacion e informar que la compra no existe. |
|  |  |  |  |  |  |  | P-COM-005 | Asegurar que una compra no pueda eliminarse cuando tenga detalles de compra asociados que deban conservarse para mantener la trazabilidad de la adquisición. | La compra tiene detalles de compra asociados que deben conservarse. | Rechazar la eliminacion e informar que la compra tiene detalles asociados. |
| Consultar compra | Consultar la información registrada de una compra para conocer su proveedor, fecha, pizzería y los detalles asociados. | Id | UUID | Identificador que permite localizar la compra que se desea consultar. | Compra | Información de la compra consultada. | P-COM-006 | Asegurar que la compra exista con el identificador indicado | No existe una compra asociada al identificador indicado. | Informar que la compra no existe y no retornar informacion. |

## Eventos

| Actor | Acción | Descripción | Objeto de dominio | Información externa · Información | Información externa · Descripción | Políticas · Número | Sistema Externo | Evento | Aspectos por solucionar |
|---|---|---|---|---|---|---|---|---|---|
| Administrador | Registrar compra | Registrar una nueva compra asociada a un proveedor y a la pizzería correspondiente, dejando constancia de la operación de adquisición. | Compra | Información de la compra | Datos necesarios para registrar la compra, como proveedor, pizzería, fecha y demás información requerida por la operación. | P-COM-001 |  | Compra registrada |  |
|  |  |  |  |  |  | P-COM-002 |  |  |  |
|  |  |  |  |  |  | P-COM-007 |  |  |  |
|  |  |  |  |  |  | P-COM-008 |  |  |  |
|  |  |  |  |  |  | P-COM-009 |  |  |  |
| Administrador | Modificar compra | Permite corregir la información de una compra existente antes de que tenga movimientos de inventario asociados. | Compra | Compras registradas | Permite identificar la compra existente y consultar su información actual antes de realizar la modificación. | P-COM-003 |  | Compra modificada |  |
|  |  |  |  |  |  | P-COM-001 |  |  |  |
|  |  |  |  |  |  | P-COM-007 |  |  |  |
|  |  |  |  |  |  | P-COM-008 |  |  |  |
| Administrador | Eliminar compra | Permite eliminar una compra que no tenga detalles de compra asociados que deban conservarse para mantener la trazabilidad. | Compra | Compras registradas | Permite identificar la compra existente antes de eliminarla. | P-COM-004 |  | Compra eliminada |  |
|  |  |  |  |  |  | P-COM-005 |  |  |  |
| Administrador | Consultar compra | Consultar la información registrada de una compra para conocer su proveedor, fecha, pizzería y los detalles asociados. | Compra | Información de la compra | Identificador o criterios necesarios para localizar la compra y consultar su información. | P-COM-006 |  | Compra consultada |  |

## Políticas

| Numero politica | Descripcion Politica | Objeto Responsable | Regla Referenciada |
|---|---|---|---|
| P-COM-001 | Asegurar que los datos requeridos para registrar una compra sean válidos de acuerdo con el tipo de dato, obligatoriedad, formato y rango correspondiente. | Compra |  |
| P-COM-002 | Asegurar que el proveedor asociado a la compra exista y se encuentre registrado en el sistema. | Proveedor | P-PROV-009 |
| P-COM-003 | Asegurar que la compra que se desea modificar se encuentre registrada en el sistema. | Compra |  |
| P-COM-004 | Asegurar que la compra que se desea eliminar se encuentre registrada en el sistema. | Compra |  |
| P-COM-005 | Asegurar que una compra no pueda eliminarse cuando tenga detalles de compra asociados que deban conservarse para mantener la trazabilidad de la adquisición. | DetalleCompra | P-DC-010 |
| P-COM-006 | Asegurar que la compra exista con el identificador indicado | Compra |  |
| P-COM-007 | Asegurar que no exista otra compra con el mismo Proveedor y el mismo NumeroFactura. | Compra |  |
| P-COM-008 | Asegurar que el proveedor asociado a la compra no se encuentre retirado o inactivo al momento de registrar la compra. | Proveedor | P-PROV-008 |
| P-COM-009 | Asegurar que el total de la compra corresponda a la suma de los precios de compra de sus detalles y no supere 5.000.000; el total no lo digita el usuario, se calcula. | Compra |  |
