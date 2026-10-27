import { describe, it, expect } from 'vitest'
import {
  MENSAJES_FORMULARIO,
  formatCantidad,
  parseCantidad,
  validarCantidad,
  validarLineas,
} from '../utils/detalleRecetaValidations'
import { unidadRecetaPara } from '../utils/unidadReceta'

describe('parseCantidad (formato de entrada)', () => {
  it.each([
    ['350', 350],
    ['2,5', 2.5],
    ['1.250,75', 1250.75],
    ['0,0001', 0.0001],
  ])('convierte "%s" en %s', (texto, esperado) => {
    expect(parseCantidad(texto)).toBe(esperado)
  })

  it.each(['', 'abc', '2.5', '1,2,3', '-5'])('marca "%s" como formato no legible', (texto) => {
    expect(parseCantidad(texto)).toBeNaN()
  })
})

describe('validarCantidad (AsegurarQueLaCantidadSeaValidaRule y decimal(18,4))', () => {
  it.each(['1', '0,0001', '150', '2,5', '10.000', '9999,9999'])('acepta "%s"', (texto) => {
    expect(validarCantidad(texto)).toBeNull()
  })

  it.each([
    ['', MENSAJES_FORMULARIO.requerido],
    ['   ', MENSAJES_FORMULARIO.requerido],
    ['diez', MENSAJES_FORMULARIO.numero],
    ['-5', MENSAJES_FORMULARIO.numero],
    ['0', MENSAJES_FORMULARIO.mayorQueCero],
    ['0,0000', MENSAJES_FORMULARIO.mayorQueCero],
    ['10000,0001', MENSAJES_FORMULARIO.maximo],
    ['15000', MENSAJES_FORMULARIO.maximo],
    ['2,12345', MENSAJES_FORMULARIO.decimales],
  ])('rechaza "%s"', (texto, mensaje) => {
    expect(validarCantidad(texto)).toBe(mensaje)
  })
})

describe('validarLineas (formulario)', () => {
  const nueva = (key, productoInternoId, cantidad) => ({
    key,
    id: null,
    productoInternoId,
    cantidad,
  })
  const registrada = (key, productoInternoId, cantidad) => ({
    key,
    id: `d-${key}`,
    productoInternoId,
    cantidad,
  })

  it('exige ingrediente y cantidad en las líneas nuevas (la unidad no se diligencia)', () => {
    expect(validarLineas([nueva(1, null, '')])[1]).toEqual({
      productoInternoId: MENSAJES_FORMULARIO.requerido,
      cantidad: MENSAJES_FORMULARIO.requerido,
    })
  })

  it('no permite repetir un ingrediente entre líneas nuevas', () => {
    const errores = validarLineas([nueva(1, 'queso', '100'), nueva(2, 'queso', '50')])
    expect(errores[1].productoInternoId).toBe(MENSAJES_FORMULARIO.repetido)
    expect(errores[2].productoInternoId).toBe(MENSAJES_FORMULARIO.repetido)
  })

  it('no permite agregar un ingrediente que ya está registrado en la receta', () => {
    const todas = [registrada(1, 'queso', '100'), nueva(2, 'queso', '50')]
    const pendientes = [todas[1]]
    expect(validarLineas(pendientes, todas)).toEqual({
      2: { productoInternoId: MENSAJES_FORMULARIO.repetido },
    })
  })

  it('en una línea registrada solo valida la cantidad (el ingrediente no cambia)', () => {
    expect(validarLineas([registrada(1, 'queso', '0')])).toEqual({
      1: { cantidad: MENSAJES_FORMULARIO.mayorQueCero },
    })
  })

  it('líneas correctas no tienen errores', () => {
    const errores = validarLineas([nueva(1, 'queso', '100'), nueva(2, 'masa', '350,5')])
    expect(errores).toEqual({})
  })
})

describe('unidadRecetaPara (la unidad que asigna el backend)', () => {
  it.each([
    ['Peso', 'g'],
    ['Volumen', 'ml'],
    ['Unidad', 'und'],
    ['', 'und'],
  ])('%s -> %s', (tipoMedida, unidad) => {
    expect(unidadRecetaPara(tipoMedida)).toBe(unidad)
  })
})

describe('formatCantidad', () => {
  it('usa coma decimal y muestra hasta 4 decimales, como se guarda en la BD', () => {
    expect(formatCantidad(2.5)).toBe('2,5')
    expect(formatCantidad(350)).toBe('350')
    expect(formatCantidad(0.1234)).toBe('0,1234')
  })
})
