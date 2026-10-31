# Pizzería UCO

## Descripción

Pizzería UCO es un sistema de información para la administración de una pizzería. Organiza en un solo lugar los productos que se venden, los insumos con que se preparan, las compras a proveedores, el inventario y la evolución de los precios.

En muchos negocios pequeños esta información se lleva de manera informal. Por eso es difícil saber cuánto se consume de cada insumo, qué insumos están próximos a vencer o cuál es el costo real de preparar cada producto. Este sistema busca resolver esa situación con información ordenada y confiable.

El sistema está diseñado para una sola pizzería y lo opera su administrador.

## Modelo del negocio

Antes de la construcción del software, el negocio se modeló por completo en Excel. Para cada parte del negocio se registraron datos de ejemplo, las características de cada dato, las operaciones que realiza el administrador y las reglas que deben cumplirse.

Las partes principales del sistema son las siguientes:

| Área | Qué incluye | Para qué sirve |
|---|---|---|
| Catálogos | Unidades de medida, tipos de producto, tamaños | Define la información base sobre la que se construye todo lo demás |
| Productos | Pizzas, bebidas y adiciones del menú | Registra lo que la pizzería vende |
| Insumos | Masa, queso, salsas, bebidas, entre otros | Registra lo que la pizzería compra y almacena |
| Recetas | Ingredientes y cantidades de cada producto | Relaciona lo que se vende con lo que se consume |
| Compras | Proveedores, compras y su detalle | Registra qué se compra, a quién y a qué precio |
| Inventario | Lotes, movimientos y existencias | Indica cuánto hay disponible y cuándo vence |
| Ventas | Ventas y su detalle | Registra lo que se vende a los clientes |
| Precios | Histórico de precios | Conserva los precios que ha tenido cada producto |

Durante el diseño se tomaron, entre otras, estas decisiones:

- Las compras a proveedores se pagan de contado, por lo que el sistema no maneja créditos.
- El inventario se controla por lotes. Siempre se usa primero el lote que ingresó primero.
- Los insumos próximos a vencer pueden cambiarse por otros en buen estado.
- Se distinguen dos clases de productos: los que se preparan, como las pizzas, y los de venta directa, como las gaseosas, que se compran y se venden sin transformación.

## Funcionalidad entregada: Gestión de Recetas

La funcionalidad que se entrega en esta versión es la gestión de recetas. Con ella el administrador define qué ingredientes lleva cada pizza y cada adición.

Se eligió como primera entrega porque la receta es la que conecta lo que la pizzería vende con lo que consume. Sobre ella se apoyarán el descuento automático del inventario en cada venta y el cálculo de costos.

### Ejemplo de receta: Margarita Grande

| Ingrediente | Cantidad | Unidad |
|---|---|---|
| Masa de pizza | 350 | g |
| Salsa de tomate | 300 | g |
| Queso | 100 | g |
| Albahaca | 50 | g |
| Aceite de oliva | 2 | ml |

### Operaciones disponibles

| Operación | Descripción |
|---|---|
| Ingreso al sistema | El administrador accede con su usuario y contraseña |
| Agregar ingrediente | Se incorpora un ingrediente a la receta de un producto, indicando su cantidad |
| Modificar cantidad | Se ajusta la cantidad de un ingrediente que ya está en la receta |
| Retirar ingrediente | Se elimina de la receta un ingrediente que ya no se utiliza |
| Consultar recetas | Se consultan todas las recetas, o se buscan por producto o por ingrediente |
| Consultar por estado | Se consultan aparte las recetas de los productos activos y las de los inactivos |

### Reglas del negocio

El sistema valida cada operación antes de guardarla. Si alguna regla no se cumple, no se registra ningún cambio y se muestra un mensaje que explica el motivo.

| Regla | Explicación |
|---|---|
| El producto debe existir | Solo se arman recetas de productos registrados en el menú |
| El ingrediente debe existir | Solo se usan insumos registrados en la pizzería |
| La cantidad debe ser válida | Debe ser mayor que cero y no superar 10.000 |
| Sin ingredientes repetidos | Un ingrediente aparece una sola vez en cada receta. Para aumentarlo, se modifica su cantidad |
| Solo pizzas y adiciones | Las bebidas no tienen receta porque no se preparan |
| Unidad asignada por el sistema | La unidad se define según el tipo de ingrediente, como se ve en la tabla siguiente |

| Tipo de ingrediente | Unidad asignada |
|---|---|
| Se pesa | Gramos (g) |
| Es líquido | Mililitros (ml) |
| Se cuenta | Unidades (und) |

Al asignar la unidad de forma automática, todas las recetas registran los ingredientes de la misma manera. Por esa razón, al modificar un ingrediente solo se cambia su cantidad.

El sistema incluye productos, insumos y recetas de ejemplo, como la Margarita Grande y la Peperoni Mediana, para poder probarlo desde el inicio.

## Tecnologías utilizadas

| Componente | Tecnología |
|---|---|
| Backend | Java con Spring Boot |
| Base de datos | SQL Server |
| Frontend | Vue con Vite |
| Pruebas de servicios | Bruno |

## Instalación y ejecución

1. En SQL Server, ejecutar en orden los scripts de la carpeta `sql`: `crear-tablas-pizzeria.sql`, `crear-usuario-pizzeria.sql` y `datos-iniciales-pizzeria.sql`.
2. Ejecutar el backend. El servicio queda disponible en `http://localhost:986`.
3. En la carpeta del frontend, ejecutar `npm install` y luego `npm run dev`.
4. Abrir `http://localhost:5173` e ingresar con el usuario administrador. Las credenciales se solicitan al equipo del proyecto.

## Próximas etapas

La gestión de recetas es la base de los siguientes módulos:

| Módulo | Relación con las recetas |
|---|---|
| Ventas | Al vender un producto, se descontarán del inventario los ingredientes de su receta |
| Compras e inventario | Permitirán conocer las existencias de cada insumo y sus fechas de vencimiento |
| Histórico de precios | Registrará la evolución del precio de cada producto |

---

Proyecto académico, Universidad Católica de Oriente.
