import { http } from '@/api/http'
import { desdeDto, listaDesdeDto } from '@/api/adaptadores'

export const catalogoService = {
  listarTiposProducto: () => http.get('/tipo-productos').then(listaDesdeDto(desdeDto.tipoProducto)),
  listarTamanos: () => http.get('/tamanos').then(listaDesdeDto(desdeDto.tamano)),
  listarProductosInternosActivos: () =>
    http.get('/productosInternos').then(listaDesdeDto(desdeDto.productoInterno)),
  listarProductosActivos: () => http.get('/productos').then(listaDesdeDto(desdeDto.producto)),
}
