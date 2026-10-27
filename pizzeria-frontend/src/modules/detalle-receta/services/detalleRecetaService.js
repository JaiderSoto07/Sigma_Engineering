import { http } from '@/api/http'
import { desdeDto, haciaDto, listaDesdeDto } from '@/api/adaptadores'

const RECURSO = '/detallerecetas'

export const detalleRecetaService = {
  listarDeProductosActivos: () =>
    http.get(`${RECURSO}/activos`).then(listaDesdeDto(desdeDto.detalleReceta)),

  agregarDetalle: async (detalle) => {
    await http.post(RECURSO, haciaDto.detalleReceta(detalle))
  },

  modificarCantidad: async (id, cantidad) => {
    await http.put(`${RECURSO}/${id}`, haciaDto.cantidadDetalleReceta(cantidad))
  },

  retirarDetalle: async (id) => {
    await http.del(`${RECURSO}/${id}`)
  },
}
