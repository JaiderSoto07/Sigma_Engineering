import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest'
import { ApiError, interpretarRespuesta } from '../http'
import { ID_POR_DEFECTO_BACKEND, desdeDto, haciaDto } from '../adaptadores'
import { autenticacionService } from '@/services/autenticacionService'
import { catalogoService } from '@/services/catalogoService'
import { detalleRecetaService } from '@/modules/detalle-receta/services/detalleRecetaService'
import {
  ID,
  USUARIO_PRUEBA,
  apagarServidor,
  idDetalle,
  iniciarServidorFalso,
  peticiones,
} from '@/__tests__/servidorFalso'

const sobre = (estado, datos, mensajes) => ({
  correlationId: 'c0ffee00-0000-4000-8000-000000000001',
  timestamp: '2026-10-14T16:00:00',
  datos,
  mensajes,
  estado,
})

describe('interpretarRespuesta', () => {
  it('devuelve los datos de un RespuestaOk', () => {
    expect(interpretarRespuesta(200, sobre('OK', [{ id: 'a' }], []))).toEqual([{ id: 'a' }])
  })

  it('una fachada void responde datos: [] y se devuelve una lista vacía', () => {
    expect(interpretarRespuesta(200, sobre('OK', [], []))).toEqual([])
    expect(interpretarRespuesta(204, null)).toEqual([])
  })

  it('un RespuestaError lanza ApiError con el mensaje para el usuario', () => {
    const cuerpo = sobre('ERROR', [], ['La cantidad del ingrediente no puede ser mayor que 10000.'])
    let error
    try {
      interpretarRespuesta(400, cuerpo)
    } catch (e) {
      error = e
    }
    expect(error).toBeInstanceOf(ApiError)
    expect(error.message).toBe('La cantidad del ingrediente no puede ser mayor que 10000.')
    expect(error.status).toBe(400)
    expect(error.mensajes).toEqual(cuerpo.mensajes)
    expect(error.correlationId).toBe(cuerpo.correlationId)
  })

  it('un 5xx sin cuerpo (backend apagado) avisa que no hay conexión', () => {
    expect(() => interpretarRespuesta(502, null)).toThrow(/backend esté encendido/)
  })

  it('un 4xx sin cuerpo usa el código de estado como mensaje', () => {
    expect(() => interpretarRespuesta(404, null)).toThrow('Error 404')
  })

  it('un 401 sin cuerpo (sin sesión o vencida) pide iniciar sesión de nuevo', () => {
    expect(() => interpretarRespuesta(401, null)).toThrow(
      'Tu sesión terminó. Inicia sesión de nuevo.',
    )
  })
})

describe('adaptadores', () => {
  const productoDto = {
    id: 'p1',
    nombre: 'Margarita',
    tipoProducto: { id: 't1', nombre: 'Pizza' },
    tamano: { id: 'z1', tamano: 'Grande' },
    productoInterno: false,
    precio: 25000.0,
    activo: true,
  }

  it('ProductoDto -> modelo plano por ids', () => {
    expect(desdeDto.producto(productoDto)).toEqual({
      id: 'p1',
      nombre: 'Margarita',
      tipoProductoId: 't1',
      tamanoId: 'z1',
      productoInterno: false,
      precio: 25000,
      activo: true,
    })
  })

  it('una relación con el id por defecto del backend queda en null', () => {
    const dto = { ...productoDto, tamano: { id: ID_POR_DEFECTO_BACKEND, tamano: '' } }
    expect(desdeDto.producto(dto).tamanoId).toBeNull()
  })

  it('TamanoDto usa "tamano" como nombre', () => {
    expect(desdeDto.tamano({ id: 'z1', tamano: 'Grande' })).toEqual({ id: 'z1', nombre: 'Grande' })
  })

  it('ProductoInternoDto toma el TipoMedida de su UnidadMedidaDto', () => {
    const dto = {
      id: 'i1',
      nombre: 'Queso',
      perecedero: true,
      vidaUtil: 7,
      tipoMedida: { id: 'u1', unidadMedida: 'g', tipoMedida: 'Peso' },
      activo: true,
    }
    expect(desdeDto.productoInterno(dto)).toMatchObject({
      tipoMedida: 'Peso',
      unidadMedidaId: 'u1',
    })
  })

  it('DetalleRecetaDto -> ids, nombre del ingrediente y abreviatura de la unidad', () => {
    const dto = {
      id: 'd1',
      producto: productoDto,
      productoInterno: { id: 'i1', nombre: 'Masa de Pizza' },
      cantidad: 350.0,
      unidadMedida: { id: 'u1', unidadMedida: 'g', tipoMedida: 'Peso' },
    }
    expect(desdeDto.detalleReceta(dto)).toEqual({
      id: 'd1',
      productoId: 'p1',
      productoInternoId: 'i1',
      productoInternoNombre: 'Masa de Pizza',
      cantidad: 350,
      unidadMedidaId: 'u1',
      unidadMedida: 'g',
    })
  })

  it('agregar ingrediente envía las relaciones como { id } y sin unidad', () => {
    expect(
      haciaDto.detalleReceta({ productoId: 'p1', productoInternoId: 'i1', cantidad: 10 }),
    ).toEqual({
      producto: { id: 'p1' },
      productoInterno: { id: 'i1' },
      cantidad: 10,
    })
  })
})

describe('servicios: rutas y métodos de los controladores del backend', () => {
  beforeEach(() => iniciarServidorFalso())
  afterEach(() => vi.unstubAllGlobals())

  const ultima = () => peticiones.at(-1)

  it('catálogos', async () => {
    await catalogoService.listarTiposProducto()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/tipo-productos' })
    await catalogoService.listarTamanos()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/tamanos' })
    await catalogoService.listarProductosInternosActivos()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/productosInternos' })
    await catalogoService.listarProductosActivos()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/productos' })
  })

  it('DetalleReceta: consultar, agregar, modificar cantidad (PUT) y retirar', async () => {
    await detalleRecetaService.listarDeProductosActivos()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/detallerecetas/activos' })

    await detalleRecetaService.agregarDetalle({
      productoId: ID.producto.hawaiana,
      productoInternoId: ID.interno.masa,
      cantidad: 300,
    })
    expect(ultima()).toEqual({
      metodo: 'POST',
      ruta: '/api/detallerecetas',
      cuerpo: {
        producto: { id: ID.producto.hawaiana },
        productoInterno: { id: ID.interno.masa },
        cantidad: 300,
      },
    })

    await detalleRecetaService.modificarCantidad(idDetalle(1), 400)
    expect(ultima()).toEqual({
      metodo: 'PUT',
      ruta: `/api/detallerecetas/${idDetalle(1)}`,
      cuerpo: { cantidad: 400 },
    })

    await detalleRecetaService.retirarDetalle(idDetalle(1))
    expect(ultima()).toMatchObject({
      metodo: 'DELETE',
      ruta: `/api/detallerecetas/${idDetalle(1)}`,
    })
  })

  it('sesión: consultar el usuario actual (GET) y cerrar sesión (POST /salir)', async () => {
    const usuario = await autenticacionService.obtenerUsuarioActual()
    expect(ultima()).toMatchObject({ metodo: 'GET', ruta: '/api/autenticacion' })
    expect(usuario).toEqual(USUARIO_PRUEBA)

    await autenticacionService.cerrarSesion()
    expect(ultima()).toMatchObject({ metodo: 'POST', ruta: '/api/autenticacion/salir' })
  })

  it('sin sesión el backend responde 401 y se lanza ApiError con ese estado', async () => {
    iniciarServidorFalso({ conSesion: false })
    const error = await autenticacionService.obtenerUsuarioActual().catch((e) => e)
    expect(error).toBeInstanceOf(ApiError)
    expect(error.status).toBe(401)
  })

  it('con el backend apagado se lanza un ApiError con un mensaje claro', async () => {
    apagarServidor()
    const error = await catalogoService.listarTamanos().catch((e) => e)
    expect(error).toBeInstanceOf(ApiError)
    expect(error.message).toMatch(/backend esté encendido/)
  })
})
