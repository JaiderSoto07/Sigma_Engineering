<script setup>
import SidebarItem from './SidebarItem.vue'

defineProps({
  open: { type: Boolean, default: false },
})

const emit = defineEmits(['close'])

const grupos = [[{ label: 'Recetas', icon: 'chef', to: '/detalle-receta' }]]
</script>

<template>
  <div v-if="open" class="backdrop" @click="emit('close')" />
  <aside class="sidebar" :class="{ 'is-open': open }">
    <p class="sidebar__brand">PIZZERÍA UCO</p>
    <nav aria-label="Menú principal" @click="emit('close')">
      <ul v-for="(grupo, i) in grupos" :key="i" class="sidebar__group">
        <SidebarItem v-for="item in grupo" :key="item.label" v-bind="item" />
      </ul>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  position: fixed;
  inset: 0 auto 0 0;
  z-index: 40;
  width: var(--sidebar-width);
  padding: var(--space-8) var(--space-3);
  overflow-y: auto;
  background: linear-gradient(180deg, var(--color-sidebar-from), var(--color-sidebar-to));
}

.sidebar__brand {
  margin: var(--space-6) 0 var(--space-8);
  font-size: var(--text-lg);
  font-weight: 700;
  letter-spacing: 0.02em;
  text-align: center;
  color: #fff;
}

.sidebar__group {
  margin: 0;
  padding: var(--space-3) 0;
  list-style: none;
}

.sidebar__group + .sidebar__group {
  border-top: 1px solid rgba(255, 255, 255, 0.15);
}

.backdrop {
  display: none;
}

@media (max-width: 960px) {
  .sidebar {
    transform: translateX(-100%);
    transition: transform 0.2s;
  }

  .sidebar.is-open {
    transform: translateX(0);
  }

  .backdrop {
    position: fixed;
    inset: 0;
    z-index: 39;
    display: block;
    background: rgba(17, 24, 39, 0.4);
  }
}
</style>
