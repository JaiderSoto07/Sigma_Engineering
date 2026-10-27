export const ID_POR_DEFECTO_BACKEND = '00000000-0000-0000-0000-000000000000'

const idDe = (objeto) => (objeto?.id && objeto.id !== ID_POR_DEFECTO_BACKEND ? objeto.id : null)

const referencia = (id) => (id == null || id === '' ? null : { id })

export const desdeDto = {
  usuario: (dto) => ({ id: dto.id, usuario: dto.usuario, rol: dto.rol }),

  tipoProducto: (dto) => ({ id: dto.id, nombre: dto.nombre }),

  tamano: (dto) => ({ id: dto.id, nombre: dto.tamano }),

  productoInterno: (dto) => ({
    id: dto.id,
    nombre: dto.nombre,
    perecedero: dto.perecedero,
    vidaUtil: dto.vidaUtil,
    tipoMedida: dto.tipoMedida?.tipoMedida ?? '',
    unidadMedidaId: idDe(dto.tipoMedida),
    activo: dto.activo,
  }),

  producto: (dto) => ({
    id: dto.id,
    nombre: dto.nombre,
    tipoProductoId: idDe(dto.tipoProducto),
    tamanoId: idDe(dto.tamano),
    productoInterno: dto.productoInterno,
    precio: Number(dto.precio),
    activo: dto.activo,
  }),

  detalleReceta: (dto) => ({
    id: dto.id,
    productoId: idDe(dto.producto),
    productoInternoId: idDe(dto.productoInterno),
    productoInternoNombre: dto.productoInterno?.nombre ?? '',
    cantidad: Number(dto.cantidad),
    unidadMedidaId: idDe(dto.unidadMedida),
    unidadMedida: dto.unidadMedida?.unidadMedida ?? '',
  }),
}

export const listaDesdeDto = (adaptador) => (lista) => (lista ?? []).map(adaptador)

export const haciaDto = {
  detalleReceta: (datos) => ({
    producto: referencia(datos.productoId),
    productoInterno: referencia(datos.productoInternoId),
    cantidad: datos.cantidad,
  }),

  cantidadDetalleReceta: (cantidad) => ({ cantidad }),
}
