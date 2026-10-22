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

insert into producto (nombre, id_tipo_producto, id_tamano, producto_interno, precio, activo) values
    ('Margarita', (select id_tipo_producto from tipo_producto where nombre = 'Pizza'),
     (select id_tamano from tamano where tamano = 'Grande'),
     0, 25000, 1);

insert into usuario (usuario, clave, rol, activo)
values ('pizzeriaUser', '$2a$10$QpPc2NqXOL6a1rH6BS6ace4rbxzGXnUa53ReTEreSHnC5NF4cF34W', 'Administrador', 1);

-- Productos internos, productos y receta de la Peperoni agregados desde la hoja M- del Excel (2026-10-22).
-- Si tu base ya tiene los datos de arriba, puedes correr solo este bloque.

insert into producto_interno (nombre, perecedero, vida_util, id_unidad_medida, activo) values
('Azucar',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Peperoni',1, 9, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
(N'Piña',1, 6, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Sal',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1),
('Leche',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'ml'), 1),
('Jugo Hit Mora 200 ml',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 1),
('Pepsi 400 ml',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 1),
('Monster 473 ml',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 1),
('Colombiana 400 ml',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 1),
('Mr tea 300 ml',0, 0, (select id_unidad_medida from unidad_medida where unidad_medida = 'und'), 1),
(N'Jamón',1, 10, (select id_unidad_medida from unidad_medida where unidad_medida = 'g'),  1);

insert into producto (nombre, id_tipo_producto, id_tamano, producto_interno, precio, activo) values
    ('Peperoni', (select id_tipo_producto from tipo_producto where nombre = 'Pizza'),
     (select id_tamano from tamano where tamano = 'Mediana'), 0, 23000, 1),
    ('Margarita y Hawaiana', (select id_tipo_producto from tipo_producto where nombre = 'Pizza'),
     (select id_tamano from tamano where tamano = N'Pequeña'), 0, 30000, 1),
    ('Hawaiana', (select id_tipo_producto from tipo_producto where nombre = 'Pizza'),
     (select id_tamano from tamano where tamano = 'Grande'), 0, 35000, 1),
    ('Extra Queso', (select id_tipo_producto from tipo_producto where nombre = 'Adicion'),
     (select id_tamano from tamano where tamano = 'No aplica'), 0, 5000, 1),
    (N'Extra Piña', (select id_tipo_producto from tipo_producto where nombre = 'Adicion'),
     (select id_tamano from tamano where tamano = 'No aplica'), 0, 6000, 1),
    ('Extra Peperoni', (select id_tipo_producto from tipo_producto where nombre = 'Adicion'),
     (select id_tamano from tamano where tamano = 'No aplica'), 0, 9000, 1),
    ('Jugo Hit Mora 200 ml', (select id_tipo_producto from tipo_producto where nombre = 'Bebida'),
     (select id_tamano from tamano where tamano = 'No aplica'), 1, 4000, 1),
    ('Pepsi 400 ml', (select id_tipo_producto from tipo_producto where nombre = 'Bebida'),
     (select id_tamano from tamano where tamano = 'No aplica'), 1, 6000, 1),
    ('Monster 473 ml', (select id_tipo_producto from tipo_producto where nombre = 'Bebida'),
     (select id_tamano from tamano where tamano = 'No aplica'), 1, 5200, 1),
    ('Colombiana 400 ml', (select id_tipo_producto from tipo_producto where nombre = 'Bebida'),
     (select id_tamano from tamano where tamano = 'No aplica'), 1, 4500, 1),
    ('Mr tea 300 ml', (select id_tipo_producto from tipo_producto where nombre = 'Bebida'),
     (select id_tamano from tamano where tamano = 'No aplica'), 1, 4500, 1);

insert into detalle_receta (id_producto, id_producto_interno, cantidad, id_unidad_medida) values
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Masa de Pizza'), 200,
     (select id_unidad_medida from producto_interno where nombre = 'Masa de Pizza')),
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Salsa de Tomate'), 200,
     (select id_unidad_medida from producto_interno where nombre = 'Salsa de Tomate')),
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Aceite de oliva'), 2,
     (select id_unidad_medida from producto_interno where nombre = 'Aceite de oliva')),
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Queso'), 150,
     (select id_unidad_medida from producto_interno where nombre = 'Queso')),
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Albahaca'), 50,
     (select id_unidad_medida from producto_interno where nombre = 'Albahaca')),
    ((select id_producto from producto where nombre = 'Peperoni'),
     (select id_producto_interno from producto_interno where nombre = 'Peperoni'), 80,
     (select id_unidad_medida from producto_interno where nombre = 'Peperoni'));
