export const TAMANO_NO_APLICA = 'No aplica'

export const TIPOS_CON_RECETA = ['Pizza', 'Adicion']

const buscarPorId = (lista, id) => lista.find((x) => String(x.id) === String(id))

export function nombreProducto(producto, tamanos) {
  if (!producto) return ''
  const tamano = buscarPorId(tamanos, producto.tamanoId)
  return tamano && tamano.nombre !== TAMANO_NO_APLICA
    ? `${producto.nombre} ${tamano.nombre}`
    : producto.nombre
}

export function puedeTenerReceta(producto, tiposProducto) {
  if (!producto) return false
  const tipo = buscarPorId(tiposProducto, producto.tipoProductoId)
  return Boolean(tipo && TIPOS_CON_RECETA.includes(tipo.nombre))
}
