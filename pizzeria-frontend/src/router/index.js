import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '@/layouts/MainLayout.vue'
import { alSesionVencida } from '@/api/http'
import { useSesionStore } from '@/stores/sesionStore'
import { RUTA_INICIO } from './inicio'

export const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/modules/autenticacion/views/LoginView.vue'),
    meta: { title: 'Iniciar sesión', publica: true },
  },
  {
    path: '/',
    component: MainLayout,
    children: [
      { path: '', redirect: { name: 'detalle-receta' } },
      {
        path: 'detalle-receta',
        name: 'detalle-receta',
        component: () => import('@/modules/detalle-receta/views/DetalleRecetaView.vue'),
        meta: { title: 'Recetas' },
      },
    ],
  },
  { path: '/:pathMatch(.*)*', redirect: { name: 'detalle-receta' } },
]

export function protegerRutas(router) {
  router.beforeEach(async (to) => {
    const sesion = useSesionStore()
    if (!sesion.verificada) await sesion.cargarSesion()
    const publica = to.matched.some((r) => r.meta.publica)

    if (!publica && !sesion.autenticada) {
      return { name: 'login' }
    }
    if (to.name === 'login' && sesion.autenticada) return RUTA_INICIO
    return true
  })

  alSesionVencida((error) => {
    const sesion = useSesionStore()
    if (!sesion.autenticada) return
    sesion.olvidarSesion(error.message)
    router.replace({ name: 'login' })
  })
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

protegerRutas(router)

router.afterEach((to) => {
  document.title = to.meta.title ? `${to.meta.title} · Pizzería UCO` : 'Pizzería UCO'
})

export default router
