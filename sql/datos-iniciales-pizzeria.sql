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
