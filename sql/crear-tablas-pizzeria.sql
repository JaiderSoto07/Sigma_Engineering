-- Crea las tablas de la base Pizzeria a partir de las clases del paquete entidad.
-- Se ejecuta DESPUES de crear-usuario-pizzeria.sql, una sola vez.
--
-- Convenciones:
--   - Nombres en minuscula separados por guion bajo (detalle_venta, precio_producto).
--   - La llave primaria se llama id_<tabla> y es uniqueidentifier, porque las entidades usan UUID.
--   - La llave foranea se llama igual que la llave primaria a la que apunta.
--   - Cantidades con 4 decimales (gramos, litros...), dinero con 2 decimales.
--   - on delete cascade solo de encabezado a detalle (compra, venta, receta del producto);
--     en el resto no se deja borrar un registro que otro este usando.

use Pizzeria;
go

-- ============================================================
-- Catalogos
-- ============================================================

create table unidad_medida (
id_unidad_medida uniqueidentifier primary key default newid(),
unidad_medida varchar(50) not null unique,
tipo_medida varchar(50) not null
);

create table tipo_producto (
id_tipo_producto uniqueidentifier primary key default newid(),
nombre varchar(50) not null unique
);

create table tamano (
id_tamano uniqueidentifier primary key default newid(),
tamano varchar(50) not null unique
);

create table tipo_movimiento (
id_tipo_movimiento uniqueidentifier primary key default newid(),
nombre varchar(50) not null unique
);

create table origen (
id_origen uniqueidentifier primary key default newid(),
nombre varchar(50) not null unique
);

create table proveedor (
id_proveedor uniqueidentifier primary key default newid(),
nombre_empresa varchar(100) not null,
contacto varchar(100) not null
);

-- ============================================================
-- Productos
-- ============================================================

create table producto_interno (
id_producto_interno uniqueidentifier primary key default newid(),
nombre varchar(100) not null,
perecedero bit not null,
vida_util int not null default 0,
id_unidad_medida uniqueidentifier not null,

constraint fk_producto_interno_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

create table producto (
id_producto uniqueidentifier primary key default newid(),
nombre varchar(100) not null,
id_tipo_producto uniqueidentifier not null,
id_tamano uniqueidentifier not null,
producto_interno bit not null,
precio decimal(18,2) not null,

constraint fk_producto_id_tipo_producto
  foreign key (id_tipo_producto) references tipo_producto(id_tipo_producto),
constraint fk_producto_id_tamano
  foreign key (id_tamano) references tamano(id_tamano)
);

create table historico_precio (
id_historico_precio uniqueidentifier primary key default newid(),
id_producto uniqueidentifier not null,
precio decimal(18,2) not null,
fecha_inicio date not null,
fecha_fin date,

constraint fk_historico_precio_id_producto
  foreign key (id_producto) references producto(id_producto)
);

create table detalle_receta (
id_detalle_receta uniqueidentifier primary key default newid(),
id_producto uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,

constraint fk_detalle_receta_id_producto
  foreign key (id_producto) references producto(id_producto)
  on delete cascade
  on update cascade,
constraint fk_detalle_receta_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_detalle_receta_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

-- ============================================================
-- Inventario
-- ============================================================

create table inventario (
id_inventario uniqueidentifier primary key default newid(),
id_producto_interno uniqueidentifier not null unique,
cantidad_total decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
stock_minimo decimal(18,4) not null,

constraint fk_inventario_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_inventario_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

create table movimiento_inventario (
id_movimiento_inventario uniqueidentifier primary key default newid(),
id_tipo_movimiento uniqueidentifier not null,
id_origen uniqueidentifier not null,
codigo_operacion varchar(50) not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
fecha_movimiento date not null,
id_lote uniqueidentifier,

constraint fk_movimiento_inventario_id_tipo_movimiento
  foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
constraint fk_movimiento_inventario_id_origen
  foreign key (id_origen) references origen(id_origen),
constraint fk_movimiento_inventario_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_movimiento_inventario_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

create table lote (
id_lote uniqueidentifier primary key default newid(),
id_movimiento_inventario uniqueidentifier not null,
numero_lote int not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
saldo decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
fecha_vencimiento date,
disponible bit not null default 1,

constraint fk_lote_id_movimiento_inventario
  foreign key (id_movimiento_inventario) references movimiento_inventario(id_movimiento_inventario),
constraint fk_lote_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_lote_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

-- movimiento_inventario y lote se apuntan entre si,
-- por eso esta llave se agrega cuando ya existen las dos tablas
alter table movimiento_inventario add
constraint fk_movimiento_inventario_id_lote
  foreign key (id_lote) references lote(id_lote);

-- ============================================================
-- Compras y cambios
-- ============================================================

create table compra (
id_compra uniqueidentifier primary key default newid(),
id_proveedor uniqueidentifier not null,
fecha_compra date not null,
numero_factura varchar(50) not null,
total decimal(18,2) not null,

constraint fk_compra_id_proveedor
  foreign key (id_proveedor) references proveedor(id_proveedor)
);

create table detalle_compra (
id_detalle_compra uniqueidentifier primary key default newid(),
id_compra uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
precio_compra decimal(18,2) not null,
fecha_vencimiento date,

constraint fk_detalle_compra_id_compra
  foreign key (id_compra) references compra(id_compra)
  on delete cascade
  on update cascade,
constraint fk_detalle_compra_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_detalle_compra_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

create table cambio (
id_cambio uniqueidentifier primary key default newid(),
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
fecha_vencimiento date,
fecha_cambio date not null,

constraint fk_cambio_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_cambio_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida)
);

-- ============================================================
-- Ventas
-- ============================================================

create table venta (
id_venta uniqueidentifier primary key default newid(),
fecha date not null,
hora time(0) not null,
cliente varchar(20) not null default '2222222222',
total decimal(18,2) not null
);

create table detalle_venta (
id_detalle_venta uniqueidentifier primary key default newid(),
id_venta uniqueidentifier not null,
id_producto uniqueidentifier not null,
cantidad int not null,
precio_producto decimal(18,2) not null,
subtotal decimal(18,2) not null,

constraint fk_detalle_venta_id_venta
  foreign key (id_venta) references venta(id_venta)
  on delete cascade
  on update cascade,
constraint fk_detalle_venta_id_producto
  foreign key (id_producto) references producto(id_producto)
);
go
