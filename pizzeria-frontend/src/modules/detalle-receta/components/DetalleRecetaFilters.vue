<script setup>
import { computed } from 'vue'
import BaseButton from '@/components/ui/BaseButton.vue'
import BaseSearchInput from '@/components/ui/BaseSearchInput.vue'
import BaseSelect from '@/components/ui/BaseSelect.vue'
import { ESTADOS } from '../stores/detalleRecetaStore'

const props = defineProps({
  filtros: { type: Object, required: true },
  tiposProducto: { type: Array, required: true },
})

const emit = defineEmits(['update', 'limpiar'])

const opcionesTipo = computed(() =>
  props.tiposProducto.map((t) => ({ value: t.id, label: t.nombre })),
)
const opcionesEstado = Object.values(ESTADOS).map((e) => ({ value: e, label: e }))

const campo = (nombre) =>
  computed({
    get: () => props.filtros[nombre],
    set: (valor) => emit('update', nombre, valor),
  })

const texto = campo('texto')
const tipoProductoId = campo('tipoProductoId')
const estado = campo('estado')
</script>

<template>
  <div class="filters">
    <div class="filters__search">
      <BaseSearchInput v-model="texto" label="Buscar receta" placeholder="Buscar receta…" />
    </div>
    <BaseSelect
      v-model="tipoProductoId"
      label="Tipo de producto"
      :options="opcionesTipo"
      placeholder="Todos"
    />
    <BaseSelect v-model="estado" label="Estado" :options="opcionesEstado" placeholder="Todos" />
    <div class="filters__actions">
      <BaseButton variant="outline" icon="refresh" @click="emit('limpiar')">
        Limpiar filtros
      </BaseButton>
    </div>
  </div>
</template>

<style scoped>
.filters {
  display: grid;
  grid-template-columns: minmax(0, 1.9fr) minmax(0, 1fr) minmax(0, 1fr);
  align-items: end;
  gap: var(--space-4);
}

.filters__actions {
  grid-column: 1 / -1;
}

@media (max-width: 640px) {
  .filters {
    grid-template-columns: 1fr 1fr;
  }

  .filters__search {
    grid-column: 1 / -1;
  }
}
</style>
