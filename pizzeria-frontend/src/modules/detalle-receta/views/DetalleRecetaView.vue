<script setup>
import { onMounted, reactive, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import BaseButton from '@/components/ui/BaseButton.vue'
import BaseCard from '@/components/ui/BaseCard.vue'
import ConfirmDialog from '@/components/ui/ConfirmDialog.vue'
import EmptyState from '@/components/ui/EmptyState.vue'
import PageHeader from '@/components/ui/PageHeader.vue'
import { useCatalogoStore } from '@/stores/catalogoStore'
import { useDetalleRecetaStore } from '../stores/detalleRecetaStore'
import DetalleRecetaFilters from '../components/DetalleRecetaFilters.vue'
import DetalleRecetaFormModal from '../components/DetalleRecetaFormModal.vue'
import ProductoRecetaPanel from '../components/ProductoRecetaPanel.vue'
import ProductoRecetaTable from '../components/ProductoRecetaTable.vue'

const store = useDetalleRecetaStore()
const catalogo = useCatalogoStore()
const route = useRoute()
const router = useRouter()

const panel = ref(null)
const retiro = reactive({ abierto: false, detalle: null, loading: false, error: '' })

onMounted(async () => {
  await store.cargar()
  const desdeUrl = store.filas.find((f) => String(f.id) === String(route.query.producto))
  if (desdeUrl) store.mostrarProducto(desdeUrl.id)
  else store.seleccionarProducto(store.filasFiltradas[0]?.id ?? null)
})

watch(
  () => store.productoSeleccionadoId,
  (id) => {
    if (id == null || String(route.query.producto) === String(id)) return
    router.replace({ query: { ...route.query, producto: String(id) } })
  },
)

function ver(id) {
  store.seleccionarProducto(id)
  if (window.matchMedia?.('(max-width: 1360px)').matches) {
    panel.value?.$el?.scrollIntoView?.({ behavior: 'smooth', block: 'start' })
  }
}

function gestionar(id) {
  store.seleccionarProducto(id)
  store.abrirGestionar(id)
}

function onSaved(productoId) {
  store.seleccionarProducto(productoId)
}

function pedirRetiro(detalle) {
  Object.assign(retiro, { abierto: true, detalle, loading: false, error: '' })
}

async function confirmarRetiro() {
  retiro.loading = true
  retiro.error = ''
  try {
    await store.retirarDetalle(retiro.detalle.id)
    retiro.abierto = false
  } catch (e) {
    retiro.error = e.message
  } finally {
    retiro.loading = false
  }
}
</script>

<template>
  <div class="recetas">
    <PageHeader title="Recetas" subtitle="Gestiona las recetas de pizzas y adiciones.">
      <template #actions>
        <BaseButton icon="plus" :disabled="store.loading" @click="store.abrirConfigurar()">
          Nueva receta
        </BaseButton>
      </template>
    </PageHeader>

    <BaseCard v-if="store.error" class="recetas__error">
      <EmptyState
        icon="alert"
        title="No fue posible cargar la información"
        :description="store.error"
      >
        <BaseButton variant="outline" icon="refresh" @click="store.cargar()">Reintentar</BaseButton>
      </EmptyState>
    </BaseCard>

    <div v-else class="recetas__grid">
      <BaseCard class="recetas__list">
        <DetalleRecetaFilters
          :filtros="store.filtros"
          :tipos-producto="catalogo.tiposConReceta"
          @update="store.setFiltro"
          @limpiar="store.limpiarFiltros()"
        />
        <p v-if="store.loading" class="recetas__loading" role="status">Cargando recetas…</p>
        <ProductoRecetaTable
          v-else
          :rows="store.filasPagina"
          :selected-id="store.productoSeleccionadoId"
          :page="store.pagina"
          :total-pages="store.totalPaginas"
          :total="store.total"
          :rango="store.rango"
          @select="store.seleccionarProducto"
          @ver="ver"
          @gestionar="gestionar"
          @page-change="store.irAPagina"
        />
      </BaseCard>

      <BaseCard ref="panel" class="recetas__panel">
        <ProductoRecetaPanel
          :fila="store.filaSeleccionada"
          :detalles="store.detallesSeleccionado"
          @gestionar="gestionar"
          @retirar="pedirRetiro"
        />
      </BaseCard>
    </div>

    <DetalleRecetaFormModal @saved="onSaved" />

    <ConfirmDialog
      :open="retiro.abierto"
      title="Retirar ingrediente"
      :message="
        retiro.detalle
          ? `¿Retirar «${retiro.detalle.productoInternoNombre}» de la receta de ${store.filaSeleccionada?.nombre}?`
          : ''
      "
      confirm-label="Retirar"
      :loading="retiro.loading"
      :error="retiro.error"
      @confirm="confirmarRetiro"
      @cancel="retiro.abierto = false"
    />
  </div>
</template>

<style scoped>
.recetas {
  display: flex;
  flex-direction: column;
  gap: var(--space-6);
}

.recetas__grid {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 0.8fr);
  align-items: start;
  gap: var(--space-4);
}

.recetas__list {
  display: flex;
  flex-direction: column;
  gap: var(--space-4);
}

.recetas__panel {
  padding: var(--space-5);
}

.recetas__loading {
  padding: var(--space-8);
  text-align: center;
  color: var(--color-text-muted);
}

@media (max-width: 1360px) {
  .recetas__grid {
    grid-template-columns: 1fr;
  }
}
</style>
