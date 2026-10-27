<script setup>
import AppIcon from '@/components/icons/AppIcon.vue'
import BaseButton from '@/components/ui/BaseButton.vue'
import EmptyState from '@/components/ui/EmptyState.vue'
import StatusBadge from '@/components/ui/StatusBadge.vue'
import { ESTADOS } from '../stores/detalleRecetaStore'
import DetalleRecetaTable from './DetalleRecetaTable.vue'

defineProps({
  fila: { type: Object, default: null },
  detalles: { type: Array, required: true },
})

const emit = defineEmits(['gestionar', 'retirar'])
</script>

<template>
  <div class="panel">
    <EmptyState
      v-if="!fila"
      icon="chef"
      title="Selecciona una receta"
      description="Elige un producto de la tabla para ver sus ingredientes."
    />

    <template v-else>
      <header class="panel__header">
        <div class="panel__title-wrap">
          <span class="panel__badge"><AppIcon name="chef" :size="22" /></span>
          <h2 class="panel__title">Detalle de la receta</h2>
        </div>
        <BaseButton variant="outline" icon="pencil" @click="emit('gestionar', fila.id)">
          Editar
        </BaseButton>
      </header>

      <dl class="panel__grid">
        <div>
          <dt>Nombre</dt>
          <dd>{{ fila.nombre }}</dd>
        </div>
        <div>
          <dt>Cantidad de ingredientes</dt>
          <dd>{{ fila.cantidadIngredientes }}</dd>
        </div>
        <div>
          <dt>Tipo de producto</dt>
          <dd>{{ fila.tipoProducto }}</dd>
        </div>
        <div>
          <dt>Estado</dt>
          <dd>
            <StatusBadge
              :label="fila.estado"
              :tone="fila.estado === ESTADOS.CONFIGURADA ? 'success' : 'muted'"
            />
          </dd>
        </div>
      </dl>

      <section class="panel__section" aria-labelledby="ingredientes-title">
        <div class="panel__title-wrap">
          <span class="panel__badge"><AppIcon name="list" :size="22" /></span>
          <h2 id="ingredientes-title" class="panel__title">Ingredientes de la receta</h2>
        </div>

        <DetalleRecetaTable
          v-if="detalles.length"
          :detalles="detalles"
          @retirar="emit('retirar', $event)"
        />
        <EmptyState
          v-else
          icon="list"
          title="Sin ingredientes"
          description="Este producto aún no tiene ingredientes registrados."
        >
          <BaseButton variant="outline" icon="plus" @click="emit('gestionar', fila.id)">
            Agregar ingredientes
          </BaseButton>
        </EmptyState>
      </section>
    </template>
  </div>
</template>

<style scoped>
.panel {
  padding: var(--space-2);
}

.panel__header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: var(--space-4);
}

.panel__title-wrap {
  display: flex;
  align-items: center;
  gap: var(--space-4);
}

.panel__badge {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 36px;
  height: 36px;
  color: #fff;
  background: var(--color-primary);
  border-radius: var(--radius-sm);
}

.panel__title {
  font-size: var(--text-lg);
  font-weight: 700;
  color: var(--color-title-accent);
}

.panel__grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--space-5) var(--space-6);
  margin: var(--space-6) 0 0;
  padding: 0 var(--space-1) var(--space-8);
  border-bottom: 1px solid var(--color-border);
}

.panel__grid dt {
  margin-bottom: var(--space-1);
  font-size: var(--text-base);
  color: var(--color-label);
}

.panel__grid dd {
  margin: 0;
  font-size: var(--text-md);
  color: var(--color-title-accent);
}

.panel__section {
  display: flex;
  flex-direction: column;
  gap: var(--space-5);
  margin-top: var(--space-6);
}
</style>
