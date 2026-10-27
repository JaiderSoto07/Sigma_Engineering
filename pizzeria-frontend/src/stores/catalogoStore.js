import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import { catalogoService } from '@/services/catalogoService'
import { TIPOS_CON_RECETA, nombreProducto, puedeTenerReceta } from '@/shared/domain/producto'

export const useCatalogoStore = defineStore('catalogo', () => {
  const tiposProducto = ref([])
  const tamanos = ref([])
  const productosInternos = ref([])
  const productos = ref([])
  const cargado = ref(false)
  const loading = ref(false)
  const error = ref(null)

  async function cargar({ forzar = false } = {}) {
    if (cargado.value && !forzar) return
    loading.value = true
    error.value = null
    try {
      const [tipos, tams, internos, prods] = await Promise.all([
        catalogoService.listarTiposProducto(),
        catalogoService.listarTamanos(),
        catalogoService.listarProductosInternosActivos(),
        catalogoService.listarProductosActivos(),
      ])
      tiposProducto.value = tipos
      tamanos.value = tams
      productosInternos.value = internos
      productos.value = prods
      cargado.value = true
    } catch (e) {
      error.value = e.message
      throw e
    } finally {
      loading.value = false
    }
  }

  const buscar = (lista, id) => lista.value.find((x) => String(x.id) === String(id))

  const tipoProductoPorId = (id) => buscar(tiposProducto, id)
  const tamanoPorId = (id) => buscar(tamanos, id)
  const productoInternoPorId = (id) => buscar(productosInternos, id)

  const nombreDe = (producto) => nombreProducto(producto, tamanos.value)

  const tiposConReceta = computed(() =>
    tiposProducto.value.filter((t) => TIPOS_CON_RECETA.includes(t.nombre)),
  )

  const productosConReceta = computed(() =>
    productos.value.filter((p) => puedeTenerReceta(p, tiposProducto.value)),
  )

  return {
    tiposProducto,
    tamanos,
    productosInternos,
    productos,
    cargado,
    loading,
    error,
    cargar,
    tipoProductoPorId,
    tamanoPorId,
    productoInternoPorId,
    nombreDe,
    tiposConReceta,
    productosConReceta,
  }
})
