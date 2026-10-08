
use Pizzeria;
go

create table unidad_medida (
id_unidad_medida uniqueidentifier primary key default newid(),
unidad_medida varchar(4) not null unique,
tipo_medida varchar(10) not null,

constraint ck_unidad_medida_tipo_medida
  check (tipo_medida in ('Peso', 'Volumen', 'Unidad'))
);

create table tipo_producto (
id_tipo_producto uniqueidentifier primary key default newid(),
nombre varchar(20) not null unique
);

create table tamano (
id_tamano uniqueidentifier primary key default newid(),
tamano varchar(20) not null unique
);

create table clase_movimiento (
id_clase_movimiento uniqueidentifier primary key default newid(),
nombre varchar(10) not null unique,

constraint ck_clase_movimiento_nombre
  check (nombre in ('Entrada', 'Salida'))
);

create table categoria_origen (
id_categoria_origen uniqueidentifier primary key default newid(),
nombre varchar(10) not null unique,

constraint ck_categoria_origen_nombre
  check (nombre in ('Compra', 'Venta', 'Cambio'))
);

create table proveedor (
id_proveedor uniqueidentifier primary key default newid(),
nombre_empresa varchar(60) not null unique,
nit varchar(15) not null unique,
contacto varchar(10) not null,
activo bit not null default 1
);


create table producto_interno (
id_producto_interno uniqueidentifier primary key default newid(),
nombre varchar(40) not null unique,
perecedero bit not null,
vida_util int not null default 0,
id_unidad_medida uniqueidentifier not null,
activo bit not null default 1,

constraint fk_producto_interno_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint ck_producto_interno_vida_util
  check ((perecedero = 1 and vida_util between 1 and 400)
      or (perecedero = 0 and vida_util = 0))
);

create table producto (
id_producto uniqueidentifier primary key default newid(),
nombre varchar(40) not null,
id_tipo_producto uniqueidentifier not null,
id_tamano uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
producto_interno as (case when id_producto_interno = '00000000-0000-0000-0000-000000000000' then 0 else 1 end),
precio decimal(18,2) not null,
activo bit not null default 1,

constraint fk_producto_id_tipo_producto
  foreign key (id_tipo_producto) references tipo_producto(id_tipo_producto),
constraint fk_producto_id_tamano
  foreign key (id_tamano) references tamano(id_tamano),
constraint fk_producto_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint uk_producto_nombre_tamano
  unique (nombre, id_tamano),
constraint ck_producto_precio
  check (precio > 0 and precio <= 1000000)
);

create table historico_precio (
id_historico_precio uniqueidentifier primary key default newid(),
id_producto uniqueidentifier not null,
precio decimal(18,2) not null,
fecha_inicio date not null,
fecha_fin date not null default '1000-01-01',

constraint fk_historico_precio_id_producto
  foreign key (id_producto) references producto(id_producto),
constraint uk_historico_precio_producto_fecha_inicio
  unique (id_producto, fecha_inicio),
constraint uk_historico_precio_producto_fecha_fin
  unique (id_producto, fecha_fin),
constraint ck_historico_precio_precio
  check (precio > 0 and precio <= 1000000),
constraint ck_historico_precio_fechas
  check (fecha_fin = '1000-01-01' or fecha_fin >= fecha_inicio)
);


create table detalle_receta (
id_detalle_receta uniqueidentifier primary key default newid(),
id_producto uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,

constraint fk_detalle_receta_id_producto
  foreign key (id_producto) references producto(id_producto)
  on delete cascade,
constraint fk_detalle_receta_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_detalle_receta_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint uk_detalle_receta_producto_producto_interno
  unique (id_producto, id_producto_interno),
constraint ck_detalle_receta_cantidad
  check (cantidad > 0 and cantidad <= 10000)
);

create table inventario (
id_inventario uniqueidentifier primary key default newid(),
id_producto_interno uniqueidentifier not null unique,
cantidad_total decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
stock_minimo decimal(18,4) not null default 1,

constraint fk_inventario_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_inventario_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint ck_inventario_cantidad_total
  check (cantidad_total >= 0 and cantidad_total <= 100000),
constraint ck_inventario_stock_minimo
  check (stock_minimo > 0 and stock_minimo <= 100000)
);


create table tipo_movimiento (
id_tipo_movimiento uniqueidentifier primary key default newid(),
id_categoria_origen uniqueidentifier not null,

constraint fk_tipo_movimiento_id_categoria_origen
  foreign key (id_categoria_origen) references categoria_origen(id_categoria_origen)
);

create table lote (
id_lote uniqueidentifier primary key default newid(),
numero_lote int not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
saldo decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
fecha_vencimiento date not null,
disponible as (case when saldo > 0 then 1 else 0 end),

constraint fk_lote_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_lote_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint uk_lote_producto_numero_lote
  unique (id_producto_interno, numero_lote),
constraint ck_lote_numero_lote
  check (numero_lote >= 1),
constraint ck_lote_cantidad
  check (cantidad >= 0 and cantidad <= 10000),
constraint ck_lote_saldo
  check (saldo >= 0 and saldo <= cantidad)
);

create table movimiento_inventario (
id_movimiento_inventario uniqueidentifier primary key default newid(),
id_clase_movimiento uniqueidentifier not null,
id_tipo_movimiento uniqueidentifier not null,
cantidad decimal(18,4) not null,
fecha_movimiento date not null,
id_lote uniqueidentifier not null,

constraint fk_movimiento_inventario_id_clase_movimiento
  foreign key (id_clase_movimiento) references clase_movimiento(id_clase_movimiento),
constraint fk_movimiento_inventario_id_tipo_movimiento
  foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
constraint fk_movimiento_inventario_id_lote
  foreign key (id_lote) references lote(id_lote),
constraint ck_movimiento_inventario_cantidad
  check (cantidad > 0 and cantidad <= 10000)
);


create table salida_lote (
id_salida_lote uniqueidentifier primary key default newid(),
id_lote uniqueidentifier not null unique,
cantidad decimal(18,4) not null,
fecha_movimiento date not null,

constraint fk_salida_lote_id_lote
  foreign key (id_lote) references lote(id_lote),
constraint ck_salida_lote_cantidad
  check (cantidad > 0 and cantidad <= 10000)
);

create table compra (
id_compra uniqueidentifier primary key default newid(),
id_proveedor uniqueidentifier not null,
fecha_compra date not null,
numero_factura varchar(40) not null,
total decimal(18,2) not null,

constraint fk_compra_id_proveedor
  foreign key (id_proveedor) references proveedor(id_proveedor),
constraint uk_compra_proveedor_numero_factura
  unique (id_proveedor, numero_factura),
constraint ck_compra_total
  check (total > 0 and total <= 5000000)
);

create table detalle_compra (
id_detalle_compra uniqueidentifier primary key default newid(),
id_compra uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
precio_compra decimal(18,2) not null,
fecha_vencimiento date not null default '1000-01-01',
id_tipo_movimiento uniqueidentifier not null unique,

constraint fk_detalle_compra_id_compra
  foreign key (id_compra) references compra(id_compra),
constraint fk_detalle_compra_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_detalle_compra_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint fk_detalle_compra_id_tipo_movimiento
  foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
constraint uk_detalle_compra_compra_producto_fecha
  unique (id_compra, id_producto_interno, fecha_vencimiento),
constraint ck_detalle_compra_cantidad
  check (cantidad > 0 and cantidad <= 100000),
constraint ck_detalle_compra_precio_compra
  check (precio_compra >= 0 and precio_compra <= 5000000)
);

create table cambio (
id_cambio uniqueidentifier primary key default newid(),
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
fecha_vencimiento date not null,
fecha_cambio date not null,
id_tipo_movimiento uniqueidentifier not null unique,

constraint fk_cambio_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_cambio_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint fk_cambio_id_tipo_movimiento
  foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
constraint uk_cambio_producto_fecha_cambio
  unique (id_producto_interno, fecha_cambio),
constraint ck_cambio_cantidad
  check (cantidad > 0 and cantidad <= 10000),
constraint ck_cambio_fechas
  check (fecha_vencimiento > fecha_cambio)
);

create table venta (
id_venta uniqueidentifier primary key default newid(),
fecha date not null,
hora time(0) not null,
factura varchar(9) not null unique,
cliente varchar(10) not null default '2222222222',
total decimal(18,2) not null,

constraint ck_venta_factura
  check (factura like 'FV-[0-9][0-9][0-9][0-9][0-9][0-9]'),
constraint ck_venta_total
  check (total > 0 and total <= 1000000)
);

create table detalle_venta (
id_detalle_venta uniqueidentifier primary key default newid(),
id_venta uniqueidentifier not null,
id_producto uniqueidentifier not null,
cantidad int not null,
precio_producto decimal(18,2) not null,
subtotal as (cantidad * precio_producto),

constraint fk_detalle_venta_id_venta
  foreign key (id_venta) references venta(id_venta),
constraint fk_detalle_venta_id_producto
  foreign key (id_producto) references producto(id_producto),
constraint uk_detalle_venta_venta_producto
  unique (id_venta, id_producto),
constraint ck_detalle_venta_cantidad
  check (cantidad > 0 and cantidad <= 1000),
constraint ck_detalle_venta_precio_producto
  check (precio_producto > 0 and precio_producto <= 1000000)
);


create table consumo_venta (
id_consumo_venta uniqueidentifier primary key default newid(),
id_detalle_venta uniqueidentifier not null,
id_producto_interno uniqueidentifier not null,
cantidad decimal(18,4) not null,
id_unidad_medida uniqueidentifier not null,
id_tipo_movimiento uniqueidentifier not null unique,


constraint fk_consumo_venta_id_detalle_venta
  foreign key (id_detalle_venta) references detalle_venta(id_detalle_venta),
constraint fk_consumo_venta_id_producto_interno
  foreign key (id_producto_interno) references producto_interno(id_producto_interno),
constraint fk_consumo_venta_id_unidad_medida
  foreign key (id_unidad_medida) references unidad_medida(id_unidad_medida),
constraint fk_consumo_venta_id_tipo_movimiento
  foreign key (id_tipo_movimiento) references tipo_movimiento(id_tipo_movimiento),
constraint uk_consumo_venta_detalle_venta_producto_interno
  unique (id_detalle_venta, id_producto_interno),
constraint ck_consumo_venta_cantidad
    check (cantidad > 0 and cantidad <= 1000000)
);
go
