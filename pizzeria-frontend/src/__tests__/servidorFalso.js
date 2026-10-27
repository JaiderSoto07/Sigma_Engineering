import { vi } from 'vitest'

const uuid = (n) => `00000000-0000-4000-8000-${String(n).padStart(12, '0')}`

export const ID = {
  tipo: { pizza: uuid(1), bebida: uuid(2), adicion: uuid(3) },
  tamano: { grande: uuid(11), mediana: uuid(12), pequena: uuid(13), noAplica: uuid(14) },
  unidad: { g: uuid(21), ml: uuid(22), und: uuid(23) },
  interno: {
    masa: uuid(31),
    salsa: uuid(32),
    aceite: uuid(33),
    queso: uuid(34),
    albahaca: uuid(35),
    huevo: uuid(36),
  },
  producto: {
    margarita: uuid(41),
    hawaiana: uuid(42),
    extraQueso: uuid(43),
    cocaCola: uuid(44),
  },
  usuario: uuid(51),
}

export const USUARIO_PRUEBA = {
  id: ID.usuario,
  usuario: 'admin@pizzeria.test',
  rol: 'Administrador',
}

let sesionActiva = true

export function cerrarSesionFalsa() {
  sesionActiva = false
}

export const MENSAJES = {
  ingredienteRepetido:
    'No es posible registrar el ingrediente porque el producto interno ya esta registrado en esta receta. Si necesita cambiar la cantidad, modifique el ingrediente existente',
  ingredienteNoExiste:
    'No es posible realizar la operacion porque el ingrediente de la receta indicado no existe. Por favor verifique el ingrediente e intente de nuevo',
}

const UNIDADES = [
  { id: ID.unidad.g, unidadMedida: 'g', tipoMedida: 'Peso' },
  { id: ID.unidad.ml, unidadMedida: 'ml', tipoMedida: 'Volumen' },
  { id: ID.unidad.und, unidadMedida: 'und', tipoMedida: 'Unidad' },
]
const unidad = (abreviatura) => UNIDADES.find((u) => u.unidadMedida === abreviatura)

const TIPOS = [
  { id: ID.tipo.pizza, nombre: 'Pizza' },
  { id: ID.tipo.bebida, nombre: 'Bebida' },
  { id: ID.tipo.adicion, nombre: 'Adicion' },
]

const TAMANOS = [
  { id: ID.tamano.grande, tamano: 'Grande' },
  { id: ID.tamano.mediana, tamano: 'Mediana' },
  { id: ID.tamano.pequena, tamano: 'Pequeña' },
  { id: ID.tamano.noAplica, tamano: 'No aplica' },
]

const interno = (id, nombre, abreviatura) => ({
  id,
  nombre,
  perecedero: false,
  vidaUtil: 0,
  tipoMedida: unidad(abreviatura),
  activo: true,
})

const INTERNOS = [
  interno(ID.interno.masa, 'Masa de Pizza', 'g'),
  interno(ID.interno.salsa, 'Salsa de Tomate', 'g'),
  interno(ID.interno.aceite, 'Aceite de oliva', 'ml'),
  interno(ID.interno.queso, 'Queso', 'g'),
  interno(ID.interno.albahaca, 'Albahaca', 'g'),
  interno(ID.interno.huevo, 'Huevo', 'und'),
]

const producto = (id, nombre, tipo, tamano, precio) => ({
  id,
  nombre,
  tipoProducto: TIPOS.find((t) => t.id === tipo),
  tamano: TAMANOS.find((t) => t.id === tamano),
  productoInterno: false,
  precio,
  activo: true,
})

const PRODUCTOS = [
  producto(ID.producto.margarita, 'Margarita', ID.tipo.pizza, ID.tamano.grande, 25000),
  producto(ID.producto.hawaiana, 'Hawaiana', ID.tipo.pizza, ID.tamano.mediana, 28000),
  producto(ID.producto.extraQueso, 'Extra Queso', ID.tipo.adicion, ID.tamano.noAplica, 4000),
  producto(ID.producto.cocaCola, 'Coca-Cola', ID.tipo.bebida, ID.tamano.noAplica, 3500),
]

const DETALLES_INICIALES = [
  [ID.producto.margarita, ID.interno.masa, 350],
  [ID.producto.margarita, ID.interno.salsa, 80],
  [ID.producto.margarita, ID.interno.aceite, 10],
  [ID.producto.margarita, ID.interno.queso, 100],
  [ID.producto.margarita, ID.interno.albahaca, 5],
  [ID.producto.extraQueso, ID.interno.queso, 50],
]

export const idDetalle = (n) => uuid(100 + n)

let detalles = []
let siguienteDetalle = 0

function detalleDto(d) {
  const p = PRODUCTOS.find((x) => x.id === d.productoId)
  const i = INTERNOS.find((x) => x.id === d.productoInternoId)
  return {
    id: d.id,
    producto: { ...p, tipoProducto: { id: p.tipoProducto.id, nombre: '' } },
    productoInterno: {
      ...i,
      tipoMedida: { id: i.tipoMedida.id, unidadMedida: '', tipoMedida: '' },
    },
    cantidad: d.cantidad,
    unidadMedida: d.unidadMedida,
  }
}

function unidadDeReceta(productoInternoId) {
  const tipo = INTERNOS.find((i) => i.id === productoInternoId).tipoMedida.tipoMedida
  return unidad(tipo === 'Peso' ? 'g' : tipo === 'Volumen' ? 'ml' : 'und')
}

const sobre = (estado, datos, mensajes) => ({
  correlationId: uuid(999),
  timestamp: '2026-10-21T12:00:00',
  datos,
  mensajes,
  estado,
})

const responder = (status, cuerpo) =>
  new Response(JSON.stringify(cuerpo), {
    status,
    headers: { 'content-type': 'application/json' },
  })

const ok = (datos = [], mensajes = []) => responder(200, sobre('OK', datos, mensajes))
const error = (mensaje, status = 400) => responder(status, sobre('ERROR', [], [mensaje]))
const sinCuerpo = (status) => new Response(null, { status })

export const peticiones = []

let falloForzado = null

export function forzarError(condicion, mensaje, status = 400) {
  falloForzado = { condicion, mensaje, status }
}

function atender(metodo, ruta, cuerpo) {
  if (falloForzado?.condicion(metodo, ruta)) {
    const { mensaje, status } = falloForzado
    falloForzado = null
    return error(mensaje, status)
  }

  if (!sesionActiva) return sinCuerpo(401)

  if (metodo === 'GET' && ruta === '/api/autenticacion') return ok([USUARIO_PRUEBA])
  if (metodo === 'POST' && ruta === '/api/autenticacion/salir') {
    sesionActiva = false
    return sinCuerpo(200)
  }

  if (metodo === 'GET') {
    if (ruta === '/api/tipo-productos') return ok(TIPOS)
    if (ruta === '/api/tamanos') return ok(TAMANOS)
    if (ruta === '/api/productosInternos') return ok(INTERNOS)
    if (ruta === '/api/productos') return ok(PRODUCTOS)
    if (ruta === '/api/detallerecetas/activos') return ok(detalles.map(detalleDto))
  }

  if (metodo === 'POST' && ruta === '/api/detallerecetas') {
    const productoId = cuerpo?.producto?.id
    const productoInternoId = cuerpo?.productoInterno?.id
    const repetido = detalles.some(
      (d) => d.productoId === productoId && d.productoInternoId === productoInternoId,
    )
    if (repetido) return error(MENSAJES.ingredienteRepetido)
    detalles.push({
      id: idDetalle(++siguienteDetalle),
      productoId,
      productoInternoId,
      cantidad: cuerpo.cantidad,
      unidadMedida: unidadDeReceta(productoInternoId),
    })
    return ok([], ['Detalle de receta registrado exitosamente'])
  }

  const porId = ruta.match(/^\/api\/detallerecetas\/([\w-]+)$/)
  if (porId && (metodo === 'PUT' || metodo === 'DELETE')) {
    const detalle = detalles.find((d) => d.id === porId[1])
    if (!detalle) return error(MENSAJES.ingredienteNoExiste)
    if (metodo === 'PUT') detalle.cantidad = cuerpo.cantidad
    else detalles = detalles.filter((d) => d !== detalle)
    return ok([], ['OK'])
  }

  return error(`Recurso no encontrado: ${ruta} por el metodo: ${metodo}`, 404)
}

export function iniciarServidorFalso({ conSesion = true } = {}) {
  sesionActiva = conSesion
  siguienteDetalle = 0
  detalles = DETALLES_INICIALES.map(([productoId, productoInternoId, cantidad]) => ({
    id: idDetalle(++siguienteDetalle),
    productoId,
    productoInternoId,
    cantidad,
    unidadMedida: unidadDeReceta(productoInternoId),
  }))
  peticiones.length = 0
  falloForzado = null

  vi.stubGlobal(
    'fetch',
    vi.fn(async (url, opciones = {}) => {
      const metodo = opciones.method ?? 'GET'
      const cuerpo = opciones.body ? JSON.parse(opciones.body) : undefined
      peticiones.push({ metodo, ruta: url, cuerpo })
      return atender(metodo, url, cuerpo)
    }),
  )
}

export function apagarServidor() {
  vi.stubGlobal(
    'fetch',
    vi.fn(async () => new Response(null, { status: 502 })),
  )
}
