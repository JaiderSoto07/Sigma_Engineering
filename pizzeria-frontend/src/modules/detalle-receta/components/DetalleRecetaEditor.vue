<script setup>
import { computed } from 'vue'
import AppIcon from '@/components/icons/AppIcon.vue'
import BaseButton from '@/components/ui/BaseButton.vue'
import BaseSelect from '@/components/ui/BaseSelect.vue'
import { unidadRecetaPara } from '../utils/unidadReceta'

const props = defineProps({
  lineas: { type: Array, required: true },
  errores: { type: Object, default: () => ({}) },
  erroresServidor: { type: Object, default: () => ({}) },
  productosInternos: { type: Array, required: true },
  disabled: { type: Boolean, default: false },
})

const emit = defineEmits(['update', 'agregar', 'quitar'])

const internoDe = (linea) =>
  props.productosInternos.find((p) => String(p.id) === String(linea.productoInternoId))

const opcionesIngrediente = computed(() =>
  props.productosInternos.map((p) => ({ value: p.id, label: p.nombre })),
)

const nombreIngrediente = (linea) => linea.productoInternoNombre || internoDe(linea)?.nombre

function unidadDe(linea) {
  if (linea.id != null) return linea.unidadMedida
  const interno = internoDe(linea)
  return interno ? unidadRecetaPara(interno.tipoMedida) : ''
}
</script>

<template>
  <div class="editor">
    <div class="editor__head" aria-hidden="true">
      <span>Ingrediente</span>
      <span>Cantidad</span>
      <span>Unidad</span>
      <span />
    </div>

    <ul class="editor__list">
      <li v-for="(linea, i) in lineas" :key="linea.key" class="editor__row">
        <div class="editor__cell">
          <p v-if="linea.id != null" class="editor__fixed">
            {{ nombreIngrediente(linea) }}
          </p>
          <BaseSelect
            v-else
            :model-value="linea.productoInternoId ?? ''"
            :label="`Ingrediente ${i + 1}`"
            hide-label
            :options="opcionesIngrediente"
            placeholder="Selecciona…"
            :disabled="disabled"
            :error="errores[linea.key]?.productoInternoId"
            @update:model-value="emit('update', linea.key, 'productoInternoId', $event)"
          />
        </div>

        <div class="editor__cell">
          <label class="sr-only" :for="`cantidad-${linea.key}`">Cantidad {{ i + 1 }}</label>
          <input
            :id="`cantidad-${linea.key}`"
            class="input"
            type="text"
            inputmode="decimal"
            placeholder="Ej: 150 o 2,5"
            :value="linea.cantidad"
            :disabled="disabled"
            :aria-invalid="errores[linea.key]?.cantidad ? 'true' : undefined"
            @input="emit('update', linea.key, 'cantidad', $event.target.value)"
          />
          <p v-if="errores[linea.key]?.cantidad" class="field-error">
            {{ errores[linea.key].cantidad }}
          </p>
        </div>

        <div class="editor__cell">
          <p
            class="editor__fixed editor__unidad"
            :aria-label="`Unidad ${i + 1}: ${unidadDe(linea) || 'según el ingrediente'}`"
            title="La unidad la asigna el sistema según el ingrediente"
          >
            {{ unidadDe(linea) || '—' }}
          </p>
        </div>

        <div class="editor__cell editor__cell--action">
          <BaseButton
            v-if="linea.id == null"
            variant="icon"
            icon="x"
            label="Quitar línea nueva"
            :disabled="disabled"
            @click="emit('quitar', linea.key)"
          />
        </div>

        <p v-if="erroresServidor[linea.key]" class="editor__server-error" role="alert">
          <AppIcon name="alert" :size="16" /> {{ erroresServidor[linea.key] }}
        </p>
      </li>
    </ul>

    <BaseButton variant="outline" icon="plus" :disabled="disabled" @click="emit('agregar')">
      Agregar ingrediente
    </BaseButton>
  </div>
</template>

<style scoped>
.editor {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: var(--space-4);
}

.editor__head,
.editor__row {
  display: grid;
  grid-template-columns: minmax(0, 2fr) minmax(0, 1fr) minmax(0, 1fr) 40px;
  gap: var(--space-3);
  width: 100%;
}

.editor__head {
  padding: var(--space-3);
  font-size: var(--text-sm);
  font-weight: 600;
  color: var(--color-title-accent);
  background: var(--color-table-head);
  border-radius: var(--radius-sm);
}

.editor__list {
  display: flex;
  flex-direction: column;
  gap: var(--space-3);
  width: 100%;
  margin: 0;
  padding: 0;
  list-style: none;
}

.editor__row {
  padding: 0 var(--space-3) var(--space-3);
  border-bottom: 1px solid var(--color-border);
}

.editor__fixed {
  display: flex;
  align-items: center;
  height: 44px;
  color: var(--color-text-strong);
}

.editor__unidad {
  color: var(--color-text-muted);
}

.editor__cell--action {
  display: flex;
  align-items: flex-start;
  padding-top: 5px;
}

.editor__server-error {
  display: flex;
  grid-column: 1 / -1;
  align-items: center;
  gap: var(--space-2);
  font-size: var(--text-sm);
  color: var(--color-danger);
}

@media (max-width: 640px) {
  .editor__head {
    display: none;
  }

  .editor__row {
    grid-template-columns: 1fr 1fr 40px;
  }

  .editor__row > .editor__cell:first-child {
    grid-column: 1 / -1;
  }
}
</style>
