<script setup>
import BaseButton from '@/components/ui/BaseButton.vue'
import BaseTable from '@/components/ui/BaseTable.vue'
import { formatCantidad } from '../utils/detalleRecetaValidations'

defineProps({
  detalles: { type: Array, required: true },
})

const emit = defineEmits(['retirar'])

const columnas = [
  { key: 'productoInternoNombre', label: 'Ingrediente' },
  { key: 'cantidad', label: 'Cantidad' },
  { key: 'unidadMedida', label: 'Unidad' },
  { key: 'acciones', label: 'Acciones', align: 'center', width: '90px' },
]
</script>

<template>
  <BaseTable :columns="columnas" :rows="detalles" caption="Ingredientes de la receta">
    <template #cell-cantidad="{ row }">{{ formatCantidad(row.cantidad) }}</template>
    <template #cell-acciones="{ row }">
      <BaseButton
        variant="icon"
        icon="trash"
        danger
        :label="`Retirar ${row.productoInternoNombre} de la receta`"
        @click="emit('retirar', row)"
      />
    </template>
  </BaseTable>
</template>
