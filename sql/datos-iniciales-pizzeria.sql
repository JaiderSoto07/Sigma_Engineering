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
