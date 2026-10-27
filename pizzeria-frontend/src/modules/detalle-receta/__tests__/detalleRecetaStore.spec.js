import { describe, it, expect, afterEach, beforeEach, vi } from 'vitest'
import { createPinia, setActivePinia } from 'pinia'
import {
  ID,
  MENSAJES,
  forzarError,
  idDetalle,
  iniciarServidorFalso,
  peticiones,
} from '@/__tests__/servidorFalso'
import { ESTADOS, useDetalleRecetaStore } from '../stores/detalleRecetaStore'

const P = ID.producto
const PI = ID.interno

describe('detalleRecetaStore', () => {
  let store

  beforeEach(async () => {
    iniciarServidorFalso()
    setActivePinia(createPinia())
    store = useDetalleRecetaStore()
    await store.cargar()
  })
  afterEach(() => vi.unstubAllGlobals())

  it('lista solo pizzas y adiciones (las bebidas no tienen receta)', () => {
    expect(store.filas.map((f) => [f.nombre, f.tipoProducto, f.cantidadIngredientes])).toEqual([
      ['Margarita Grande', 'Pizza', 5],
      ['Hawaiana Mediana', 'Pizza', 0],
      ['Extra Queso', 'Adicion', 1],
    ])
    expect(store.filas.some((f) => f.nombre === 'Coca-Cola')).toBe(false)
  })

  it('el estado es derivado de la cantidad de ingredientes', () => {
    expect(store.filas.map((f) => f.estado)).toEqual([
      ESTADOS.CONFIGURADA,
      ESTADOS.SIN_CONFIGURAR,
      ESTADOS.CONFIGURADA,
    ])
    expect(store.productosSinConfigurar.map((f) => f.id)).toEqual([P.hawaiana])
  })

  it('cada detalle trae su ingrediente y la unidad registrada por el backend', () => {
    expect(
      store.detallesDe(P.margarita).map((d) => [d.productoInternoNombre, d.unidadMedida]),
    ).toEqual([
      ['Masa de Pizza', 'g'],
      ['Salsa de Tomate', 'g'],
      ['Aceite de oliva', 'ml'],
      ['Queso', 'g'],
      ['Albahaca', 'g'],
    ])
  })

  it('filtra por texto (sin tildes), tipo de producto y estado', () => {
    store.setFiltro('texto', 'hawai')
    expect(store.filasFiltradas.map((f) => f.nombre)).toEqual(['Hawaiana Mediana'])

    store.limpiarFiltros()
    store.setFiltro('tipoProductoId', ID.tipo.adicion)
    expect(store.filasFiltradas.map((f) => f.nombre)).toEqual(['Extra Queso'])

    store.limpiarFiltros()
    store.setFiltro('estado', ESTADOS.SIN_CONFIGURAR)
    expect(store.filasFiltradas.map((f) => f.nombre)).toEqual(['Hawaiana Mediana'])
  })

  it('al retirar el único ingrediente, el producto pasa a "Sin configurar"', async () => {
    await store.retirarDetalle(idDetalle(6))
    const extraQueso = store.filas.find((f) => f.id === P.extraQueso)
    expect(extraQueso.cantidadIngredientes).toBe(0)
    expect(extraQueso.estado).toBe(ESTADOS.SIN_CONFIGURAR)
  })

  it('guardarCambios agrega las líneas nuevas y modifica solo las cantidades cambiadas', async () => {
    const lineas = [
      {
        key: 'a',
        id: idDetalle(4),
        productoInternoId: PI.queso,
        cantidad: '120',
        original: { cantidad: 100 },
      },
      { key: 'b', id: null, productoInternoId: PI.huevo, cantidad: '2', original: null },
    ]
    const errores = await store.guardarCambios(P.margarita, lineas)

    expect(errores).toEqual({})
    const escrituras = peticiones.filter((p) => p.metodo !== 'GET')
    expect(escrituras.map((p) => [p.metodo, p.ruta])).toEqual([
      ['PUT', `/api/detallerecetas/${idDetalle(4)}`],
      ['POST', '/api/detallerecetas'],
    ])

    const detalles = store.detallesDe(P.margarita)
    expect(detalles).toHaveLength(6)
    expect(detalles.find((d) => d.id === idDetalle(4))).toMatchObject({ cantidad: 120 })
    const huevo = detalles.find((d) => d.productoInternoId === PI.huevo)
    expect(huevo).toMatchObject({ cantidad: 2, unidadMedida: 'und' })
    expect(lineas[1].id).toBe(huevo.id)
  })

  it('muestra en la línea el mensaje que devuelve el backend y la deja pendiente', async () => {
    const lineas = [
      { key: 'x', id: null, productoInternoId: PI.masa, cantidad: '100', original: null },
    ]
    const errores = await store.guardarCambios(P.margarita, lineas)
    expect(errores.x).toBe(MENSAJES.ingredienteRepetido)
    expect(lineas[0].id).toBeNull()
  })

  it('si falla una línea, las demás se guardan y solo la fallida queda pendiente', async () => {
    forzarError((metodo) => metodo === 'PUT', 'Error de prueba del servidor')
    const lineas = [
      {
        key: 'a',
        id: idDetalle(4),
        productoInternoId: PI.queso,
        cantidad: '120',
        original: { cantidad: 100 },
      },
      { key: 'b', id: null, productoInternoId: PI.huevo, cantidad: '2', original: null },
    ]
    const errores = await store.guardarCambios(P.margarita, lineas)
    expect(errores).toEqual({ a: 'Error de prueba del servidor' })
    expect(lineas[0].original.cantidad).toBe(100)
    expect(lineas[1].id).not.toBeNull()
  })

  it('si el backend no responde al cargar, deja el error para mostrarlo', async () => {
    setActivePinia(createPinia())
    forzarError(() => true, 'No fue posible consultar los tipos de producto', 500)
    const otro = useDetalleRecetaStore()
    await otro.cargar()
    expect(otro.error).toBe('No fue posible consultar los tipos de producto')
    expect(otro.loading).toBe(false)
  })
})
