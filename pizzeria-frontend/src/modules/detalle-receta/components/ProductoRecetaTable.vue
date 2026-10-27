<script setup>
import BaseButton from '@/components/ui/BaseButton.vue'
import BasePagination from '@/components/ui/BasePagination.vue'
import BaseTable from '@/components/ui/BaseTable.vue'
import StatusBadge from '@/components/ui/StatusBadge.vue'
import { ESTADOS } from '../stores/detalleRecetaStore'

defineProps({
  rows: { type: Array, required: true },
  selectedId: { type: [String, Number], default: null },
  page: { type: Number, required: true },
  totalPages: { type: Number, required: true },
  total: { type: Number, required: true },
  rango: { type: Object, required: true },
})

const emit = defineEmits(['select', 'ver', 'gestionar', 'page-change'])

const columnas = [
  { key: 'nombre', label: 'Nombre' },
  { key: 'tipoProducto', label: 'Tipo de producto', nowrap: true },
  { key: 'cantidadIngredientes', label: 'Cantidad ingredientes', width: '110px' },
  { key: 'estado', label: 'Estado', nowrap: true },
  { key: 'acciones', label: 'Acciones', align: 'center', width: '100px', nowrap: true },
]
</script>

<template>
  <div class="producto-table">
    <BaseTable
      :columns="columnas"
      :rows="rows"
      :selected-key="selectedId"
      caption="Productos con receta"
      clickable
      @row-click="emit('select', $event.id)"
    >
      <template #cell-estado="{ row }">
        <StatusBadge
          :label="row.estado"
          :tone="row.estado === ESTADOS.CONFIGURADA ? 'success' : 'muted'"
        />
      </template>
      <template #cell-acciones="{ row }">
        <div class="producto-table__actions">
          <BaseButton
            variant="icon"
            icon="eye"
            :label="`Ver detalles de ${row.nombre}`"
            @click.stop="emit('ver', row.id)"
          />
          <BaseButton
            variant="icon"
            icon="pencil"
            :label="`Gestionar detalles de ${row.nombre}`"
            @click.stop="emit('gestionar', row.id)"
          />
        </div>
      </template>
      <template #empty>No se encontraron recetas con los filtros aplicados.</template>
    </BaseTable>

    <footer class="producto-table__footer">
      <p class="producto-table__summary">
        Mostrando {{ rango.desde }} - {{ rango.hasta }} de {{ total }} recetas
      </p>
      <BasePagination
        :page="page"
        :total-pages="totalPages"
        @change="emit('page-change', $event)"
      />
    </footer>
  </div>
</template>

<style scoped>
.producto-table__actions {
  display: inline-flex;
  gap: var(--space-2);
}

.producto-table__footer {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  gap: var(--space-4);
  padding: var(--space-8) var(--space-3) var(--space-2);
}

.producto-table__summary {
  font-size: var(--text-md);
  color: var(--color-label);
}
</style>
