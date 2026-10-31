export const CANTIDAD_MAXIMA = 10000
export const DECIMALES_MAXIMOS = 4

export const MENSAJES_FORMULARIO = {
  requerido: 'Campo obligatorio.',
  numero: 'Ingresa un número (usa coma para decimales).',
  mayorQueCero: 'La cantidad debe ser mayor que cero.',
  maximo: 'La cantidad no puede ser mayor que 10.000.',
  decimales: `La cantidad admite máximo ${DECIMALES_MAXIMOS} decimales.`,
  repetido: 'Este ingrediente ya está en la receta.',
}

const FORMATO_NUMERO = /^(\d{1,3}(\.\d{3})+|\d+)(,\d+)?$/

export function parseCantidad(valor) {
  if (typeof valor === 'number') return valor
  const texto = String(valor ?? '').trim()
  if (!FORMATO_NUMERO.test(texto)) return Number.NaN
  return Number(texto.replaceAll('.', '').replace(',', '.'))
}

function decimalesDe(valor) {
  if (typeof valor === 'number') return (String(valor).split('.')[1] ?? '').length
  return (
    String(valor ?? '')
      .trim()
      .split(',')[1] ?? ''
  ).length
}

const vacio = (valor) => valor === null || valor === undefined || String(valor).trim() === ''

export function validarCantidad(valor) {
  if (vacio(valor)) return MENSAJES_FORMULARIO.requerido
  const cantidad = parseCantidad(valor)
  if (Number.isNaN(cantidad)) return MENSAJES_FORMULARIO.numero
  if (cantidad <= 0) return MENSAJES_FORMULARIO.mayorQueCero
  if (cantidad > CANTIDAD_MAXIMA) return MENSAJES_FORMULARIO.maximo
  if (decimalesDe(valor) > DECIMALES_MAXIMOS) return MENSAJES_FORMULARIO.decimales
  return null
}

export function validarLineas(lineas, todas = lineas) {
  const errores = {}
  for (const linea of lineas) {
    const e = {}
    if (linea.id == null) {
      if (vacio(linea.productoInternoId)) e.productoInternoId = MENSAJES_FORMULARIO.requerido
      else if (
        todas.some(
          (otra) =>
            otra.key !== linea.key &&
            String(otra.productoInternoId) === String(linea.productoInternoId),
        )
      ) {
        e.productoInternoId = MENSAJES_FORMULARIO.repetido
      }
    }
    const errorCantidad = validarCantidad(linea.cantidad)
    if (errorCantidad) e.cantidad = errorCantidad
    if (Object.keys(e).length) errores[linea.key] = e
  }
  return errores
}

export function formatCantidad(cantidad, { agrupar = true } = {}) {
  return new Intl.NumberFormat('es-CO', {
    maximumFractionDigits: DECIMALES_MAXIMOS,
    useGrouping: agrupar,
  }).format(cantidad)
}
