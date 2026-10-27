<script setup>
import { computed, reactive, ref, watch } from 'vue'
import AppIcon from '@/components/icons/AppIcon.vue'
import BaseButton from '@/components/ui/BaseButton.vue'
import BaseModal from '@/components/ui/BaseModal.vue'
import BaseSelect from '@/components/ui/BaseSelect.vue'
import { useCatalogoStore } from '@/stores/catalogoStore'
import { useDetalleRecetaStore } from '../stores/detalleRecetaStore'
import { formatCantidad, parseCantidad, validarLineas } from '../utils/detalleRecetaValidations'
import DetalleRecetaEditor from './DetalleRecetaEditor.vue'

const emit = defineEmits(['saved'])

const store = useDetalleRecetaStore()
const catalogo = useCatalogoStore()

const productoId = ref('')
const lineas = reactive([])
const errores = ref({})
const erroresServidor = ref({})
const errorProducto = ref('')
const guardando = ref(false)
let siguienteKey = 0

const esConfigurar = computed(() => store.modal.modo === 'configurar')
const titulo = computed(() =>
  esConfigurar.value ? 'Nueva receta' : 'Editar ingredientes de la receta',
)

const opcionesProducto = computed(() =>
  store.productosSinConfigurar.map((f) => ({ value: f.id, label: f.nombre })),
)
const sinProductosDisponibles = computed(() => esConfigurar.value && !opcionesProducto.value.length)
const filaProducto = computed(() =>
  store.filas.find((f) => String(f.id) === String(productoId.value)),
)

function lineaVacia() {
  return {
    key: ++siguienteKey,
    id: null,
    productoInternoId: null,
    cantidad: '',
    original: null,
  }
}

function lineaDesdeDetalle(d) {
  return {
    key: ++siguienteKey,
    id: d.id,
    productoInternoId: d.productoInternoId,
    productoInternoNombre: d.productoInternoNombre,
    cantidad: formatCantidad(d.cantidad, { agrupar: false }),
    unidadMedida: d.unidadMedida,
    original: { cantidad: d.cantidad },
  }
}

function inicializar() {
  errores.value = {}
  erroresServidor.value = {}
  errorProducto.value = ''
  productoId.value = store.modal.productoId ?? ''
  const existentes = productoId.value ? store.detallesDe(productoId.value) : []
  lineas.splice(0, lineas.length, ...existentes.map(lineaDesdeDetalle))
  if (!lineas.length) lineas.push(lineaVacia())
}

watch(
  () => store.modal.abierto,
  (abierto) => abierto && inicializar(),
)

function esLineaModificada(l) {
  if (l.id == null) return true
  return parseCantidad(l.cantidad) !== l.original.cantidad
}

const hayCambios = computed(() => lineas.some(esLineaModificada))

function actualizarLinea(key, campo, valor) {
  const linea = lineas.find((l) => l.key === key)
  if (!linea) return
  linea[campo] = campo === 'cantidad' ? valor : valor === '' ? null : valor

  if (errores.value[key]) {
    const resto = { ...errores.value }
    delete resto[key]
    errores.value = resto
  }
}

function agregarLinea() {
  lineas.push(lineaVacia())
}

function quitarLinea(key) {
  const i = lineas.findIndex((l) => l.key === key && l.id == null)
  if (i !== -1) lineas.splice(i, 1)
}

async function guardar() {
  errorProducto.value = productoId.value === '' ? 'Selecciona un producto.' : ''

  const pendientes = lineas.filter(esLineaModificada)
  errores.value = validarLineas(pendientes, lineas)
  if (errorProducto.value || Object.keys(errores.value).length || !pendientes.length) return

  guardando.value = true
  try {
    erroresServidor.value = await store.guardarCambios(productoId.value, pendientes)
  } finally {
    guardando.value = false
  }

  if (!Object.keys(erroresServidor.value).length) {
    emit('saved', productoId.value)
    store.cerrarModal()
  }
}

const hayErrores = computed(
  () => Object.keys(errores.value).length > 0 || Object.keys(erroresServidor.value).length > 0,
)
</script>

<template>
  <BaseModal
    :open="store.modal.abierto"
    :title="titulo"
    size="lg"
    :persistent="guardando"
    @close="store.cerrarModal()"
  >
    <template #icon>
      <span class="form__badge"><AppIcon name="chef" :size="20" /></span>
    </template>

    <form id="detalle-receta-form" class="form" novalidate @submit.prevent="guardar">
      <section class="form__section">
        <h3 class="form__section-title">Producto</h3>

        <template v-if="esConfigurar">
          <p v-if="!opcionesProducto.length" class="form__notice">
            Todas las pizzas y adiciones registradas ya tienen ingredientes. Para modificar una
            receta existente usa la acción <strong>Editar</strong> de la tabla.
          </p>
          <BaseSelect
            v-else
            v-model="productoId"
            label="Producto (pizza o adición sin ingredientes)"
            :options="opcionesProducto"
            placeholder="Selecciona un producto…"
            :disabled="guardando"
            :error="errorProducto"
          />
        </template>

        <dl v-if="filaProducto" class="form__summary">
          <div v-if="!esConfigurar">
            <dt>Nombre</dt>
            <dd>{{ filaProducto.nombre }}</dd>
          </div>
          <div>
            <dt>Tipo de producto</dt>
            <dd>{{ filaProducto.tipoProducto }}</dd>
          </div>
        </dl>
      </section>

      <section v-if="!sinProductosDisponibles" class="form__section">
        <h3 class="form__section-title">Ingredientes de la receta</h3>
        <DetalleRecetaEditor
          :lineas="lineas"
          :errores="errores"
          :errores-servidor="erroresServidor"
          :productos-internos="catalogo.productosInternos"
          :disabled="guardando"
          @update="actualizarLinea"
          @agregar="agregarLinea"
          @quitar="quitarLinea"
        />
      </section>
    </form>

    <template #footer>
      <p v-if="hayErrores" class="form__footer-error" role="alert">
        Revisa los datos marcados antes de guardar.
      </p>
      <BaseButton variant="outline" :disabled="guardando" @click="store.cerrarModal()">
        {{ sinProductosDisponibles ? 'Cerrar' : 'Cancelar' }}
      </BaseButton>
      <BaseButton
        v-if="!sinProductosDisponibles"
        type="submit"
        form="detalle-receta-form"
        :loading="guardando"
        :disabled="!hayCambios"
      >
        Guardar
      </BaseButton>
    </template>
  </BaseModal>
</template>

<style scoped>
.form {
  display: flex;
  flex-direction: column;
  gap: var(--space-8);
}

.form__badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  color: #fff;
  background: var(--color-primary);
  border-radius: var(--radius-sm);
}

.form__section {
  display: flex;
  flex-direction: column;
  gap: var(--space-4);
}

.form__section-title {
  font-size: var(--text-md);
  font-weight: 600;
  color: var(--color-title-accent);
}

.form__notice {
  padding: var(--space-3) var(--space-4);
  font-size: var(--text-sm);
  line-height: 1.5;
  color: var(--color-label);
  background: var(--color-table-head);
  border-radius: var(--radius-sm);
}

.form__summary {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-8);
  margin: 0;
}

.form__summary dt {
  font-size: var(--text-sm);
  color: var(--color-label);
}

.form__summary dd {
  margin: var(--space-1) 0 0;
  color: var(--color-title-accent);
}

.form__footer-error {
  margin-right: auto;
  align-self: center;
  font-size: var(--text-sm);
  color: var(--color-danger);
}
</style>
