use Pizzeria;
go

insert into unidad_medida (unidad_medida, tipo_medida) values
('g',   'Peso'),
('kg',  'Peso'),
('ml',  'Volumen'),
('l',   'Volumen'),
('und', 'Unidad');

insert into clase_movimiento (nombre) values
('Entrada'),
('Salida');

insert into categoria_origen (nombre) values
('Compra'),
('Venta'),
('Cambio');

insert into producto_interno (id_producto_interno, nombre, perecedero, vida_util, id_unidad_medida, activo)
values ('00000000-0000-0000-0000-000000000000', 'Sin insumo', 0, 0,
        (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 0);

insert into tipo_producto (nombre) values
('Pizza'),
('Bebida'),
('Adicion');

insert into tamano (tamano) values
('Grande'),
('Mediana'),
(N'Pequeña'),
('No aplica');

insert into producto_interno (nombre, perecedero, vida_util, id_unidad_medida, activo) values
('Masa de Pizza',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Salsa de Tomate',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Aceite de oliva',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'ml'), 1),
('Queso',1, 7, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Albahaca',1, 8, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1);


insert into producto (nombre, id_tipo_producto, id_tamano, id_producto_interno, precio, activo) values
    ('Margarita',
     (select id_tipo_producto from tipo_producto where nombre = 'Pizza'),
     (select id_tamano from tamano where tamano = 'Grande'),
     (select id_producto_interno from producto_interno where nombre = 'Sin insumo'),
     25000, 1);

insert into usuario (usuario, clave, rol, activo)
values ('pizzeriaUser', '$2a$10$QpPc2NqXOL6a1rH6BS6ace4rbxzGXnUa53ReTEreSHnC5NF4cF34W', 'Administrador', 1);

