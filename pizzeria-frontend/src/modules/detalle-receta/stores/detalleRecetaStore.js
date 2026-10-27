import { computed, reactive, ref } from 'vue'
import { defineStore } from 'pinia'
import { useCatalogoStore } from '@/stores/catalogoStore'
import { detalleRecetaService } from '../services/detalleRecetaService'
import { parseCantidad } from '../utils/detalleRecetaValidations'

export const ESTADOS = {
  CONFIGURADA: 'Configurada',
  SIN_CONFIGURAR: 'Sin configurar',
}

const TAMANO_PAGINA = 9

const normalizar = (texto) =>
  String(texto ?? '')
    .normalize('NFD')
    .replace(/[̀-ͯ]/g, '')
    .toLowerCase()
    .trim()

const mismoId = (a, b) => a != null && b != null && String(a) === String(b)

export const useDetalleRecetaStore = defineStore('detalleReceta', () => {
  const catalogo = useCatalogoStore()

  const detalles = ref([])
  const filtros = reactive({ texto: '', tipoProductoId: '', estado: '' })
  const pagina = ref(1)
  const productoSeleccionadoId = ref(null)
  const loading = ref(false)
  const error = ref(null)
  const modal = reactive({ abierto: false, modo: 'gestionar', productoId: null })

  const cantidadDetallesDe = (productoId) =>
    detalles.value.filter((d) => mismoId(d.productoId, productoId)).length

  const filas = computed(() =>
    catalogo.productosConReceta.map((producto) => {
      const cantidad = cantidadDetallesDe(producto.id)
      return {
        id: producto.id,
        nombre: catalogo.nombreDe(producto),
        tipoProducto: catalogo.tipoProductoPorId(producto.tipoProductoId)?.nombre ?? '',
        tipoProductoId: producto.tipoProductoId,
        cantidadIngredientes: cantidad,
        estado: cantidad > 0 ? ESTADOS.CONFIGURADA : ESTADOS.SIN_CONFIGURAR,
      }
    }),
  )

  const filasFiltradas = computed(() => {
    const texto = normalizar(filtros.texto)
    return filas.value.filter(
      (f) =>
        (!texto || normalizar(f.nombre).includes(texto)) &&
        (!filtros.tipoProductoId || mismoId(f.tipoProductoId, filtros.tipoProductoId)) &&
        (!filtros.estado || f.estado === filtros.estado),
    )
  })

  const total = computed(() => filasFiltradas.value.length)
  const totalPaginas = computed(() => Math.max(1, Math.ceil(total.value / TAMANO_PAGINA)))
  const filasPagina = computed(() => {
    const inicio = (pagina.value - 1) * TAMANO_PAGINA
    return filasFiltradas.value.slice(inicio, inicio + TAMANO_PAGINA)
  })
  const rango = computed(() => {
    if (!total.value) return { desde: 0, hasta: 0 }
    const desde = (pagina.value - 1) * TAMANO_PAGINA + 1
    return { desde, hasta: Math.min(desde + TAMANO_PAGINA - 1, total.value) }
  })

  const filaSeleccionada = computed(
    () => filas.value.find((f) => mismoId(f.id, productoSeleccionadoId.value)) ?? null,
  )

  function detallesDe(productoId) {
    return detalles.value
      .filter((d) => mismoId(d.productoId, productoId))
      .map((d) => ({
        ...d,
        tipoMedida: catalogo.productoInternoPorId(d.productoInternoId)?.tipoMedida ?? '',
      }))
  }

  const detallesSeleccionado = computed(() => detallesDe(productoSeleccionadoId.value))

  const productosSinConfigurar = computed(() =>
    filas.value.filter((f) => f.estado === ESTADOS.SIN_CONFIGURAR),
  )

  async function cargar() {
    loading.value = true
    error.value = null
    try {
      await catalogo.cargar()
      detalles.value = await detalleRecetaService.listarDeProductosActivos()
    } catch (e) {
      error.value = e.message
    } finally {
      loading.value = false
    }
  }

  async function recargarDetalles() {
    detalles.value = await detalleRecetaService.listarDeProductosActivos()
  }

  function seleccionarProducto(id) {
    productoSeleccionadoId.value = id
  }

  function setFiltro(campo, valor) {
    filtros[campo] = valor
    pagina.value = 1
  }

  function limpiarFiltros() {
    filtros.texto = ''
    filtros.tipoProductoId = ''
    filtros.estado = ''
    pagina.value = 1
  }

  function irAPagina(n) {
    pagina.value = Math.min(Math.max(1, n), totalPaginas.value)
  }

  function mostrarProducto(id) {
    let indice = filasFiltradas.value.findIndex((f) => mismoId(f.id, id))
    if (indice === -1) {
      limpiarFiltros()
      indice = filasFiltradas.value.findIndex((f) => mismoId(f.id, id))
    }
    if (indice !== -1) pagina.value = Math.floor(indice / TAMANO_PAGINA) + 1
    seleccionarProducto(id)
  }

  function abrirConfigurar() {
    Object.assign(modal, { abierto: true, modo: 'configurar', productoId: null })
  }

  function abrirGestionar(productoId) {
    Object.assign(modal, { abierto: true, modo: 'gestionar', productoId })
  }

  function cerrarModal() {
    modal.abierto = false
  }

  async function guardarCambios(productoId, lineas) {
    const errores = {}
    const agregadas = []

    for (const linea of lineas) {
      const cantidad = parseCantidad(linea.cantidad)
      try {
        if (linea.id == null) {
          await detalleRecetaService.agregarDetalle({
            productoId,
            productoInternoId: linea.productoInternoId,
            cantidad,
          })
          linea.original = { cantidad }
          agregadas.push(linea)
          continue
        }
        if (cantidad !== linea.original.cantidad) {
          await detalleRecetaService.modificarCantidad(linea.id, cantidad)
          linea.original.cantidad = cantidad
        }
      } catch (e) {
        errores[linea.key] = e.message
      }
    }

    await recargarDetalles()
    for (const linea of agregadas) {
      linea.id =
        detalles.value.find(
          (d) =>
            mismoId(d.productoId, productoId) &&
            mismoId(d.productoInternoId, linea.productoInternoId),
        )?.id ?? null
    }
    return errores
  }

  async function retirarDetalle(id) {
    await detalleRecetaService.retirarDetalle(id)
    await recargarDetalles()
  }

  return {
    detalles,
    filtros,
    pagina,
    productoSeleccionadoId,
    loading,
    error,
    modal,
    filas,
    filasFiltradas,
    filasPagina,
    total,
    totalPaginas,
    rango,
    filaSeleccionada,
    detallesSeleccionado,
    productosSinConfigurar,
    detallesDe,
    cantidadDetallesDe,
    cargar,
    seleccionarProducto,
    setFiltro,
    limpiarFiltros,
    irAPagina,
    mostrarProducto,
    abrirConfigurar,
    abrirGestionar,
    cerrarModal,
    guardarCambios,
    retirarDetalle,
  }
})
