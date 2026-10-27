import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest'
import { flushPromises, mount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import { createMemoryHistory, createRouter } from 'vue-router'

import App from '../App.vue'
import { protegerRutas, routes } from '../router'
import { autenticacionService } from '../services/autenticacionService'
import { catalogoService } from '../services/catalogoService'
import {
  USUARIO_PRUEBA,
  apagarServidor,
  cerrarSesionFalsa,
  iniciarServidorFalso,
  peticiones,
} from './servidorFalso'

beforeEach(() => {
  iniciarServidorFalso()
  document.body.innerHTML = ''
})
afterEach(() => {
  vi.unstubAllGlobals()
  vi.restoreAllMocks()
})

async function esperar(condicion) {
  await vi.waitFor(async () => {
    await flushPromises()
    if (!condicion()) throw new Error('esperando')
  })
}

async function montarApp(ruta) {
  const router = createRouter({ history: createMemoryHistory(), routes })
  protegerRutas(router)
  const pinia = createPinia()
  setActivePinia(pinia)
  router.push(ruta)
  await router.isReady()
  const wrapper = mount(App, { attachTo: document.body, global: { plugins: [pinia, router] } })
  await flushPromises()
  return { wrapper, router }
}

const alerta = (wrapper) => wrapper.find('[role="alert"]')

describe('Inicio de sesión con Google (pantalla y rutas protegidas)', () => {
  it('sin sesión, una ruta privada lleva al login', async () => {
    iniciarServidorFalso({ conSesion: false })
    const { wrapper, router } = await montarApp('/detalle-receta')
    expect(router.currentRoute.value.name).toBe('login')
    expect(wrapper.text()).toContain('Iniciar sesión con Google')
    expect(alerta(wrapper).exists()).toBe(false)
    wrapper.unmount()
  })

  it('con sesión entra a la ruta pedida y muestra el rol en la barra superior', async () => {
    const { wrapper, router } = await montarApp('/detalle-receta')
    expect(router.currentRoute.value.name).toBe('detalle-receta')
    await esperar(() => wrapper.find('.topbar__user-name').exists())
    expect(wrapper.find('.topbar__user-name').text()).toBe('Administrador')
    wrapper.unmount()
  })

  it('con sesión, /login lleva al inicio', async () => {
    const { wrapper, router } = await montarApp('/login')
    expect(router.currentRoute.value.name).toBe('detalle-receta')
    wrapper.unmount()
  })

  it('a la sesión se le pregunta al backend una sola vez', async () => {
    const { wrapper, router } = await montarApp('/detalle-receta')
    await router.push('/login')
    const consultas = peticiones.filter((p) => p.ruta === '/api/autenticacion')
    expect(consultas).toHaveLength(1)
    wrapper.unmount()
  })

  it('el botón lleva al inicio de sesión de Google', async () => {
    iniciarServidorFalso({ conSesion: false })
    const espia = vi
      .spyOn(autenticacionService, 'iniciarSesionConGoogle')
      .mockImplementation(() => {})
    const { wrapper } = await montarApp('/login')
    await wrapper.find('.login__submit').trigger('click')
    expect(espia).toHaveBeenCalledOnce()
    wrapper.unmount()
  })

  it('si Google no completa el inicio de sesión (/login?error) se avisa', async () => {
    iniciarServidorFalso({ conSesion: false })
    const { wrapper } = await montarApp('/login?error')
    expect(alerta(wrapper).text()).toContain('No fue posible iniciar sesión con Google')
    wrapper.unmount()
  })

  it('con el backend apagado se queda en el login y avisa que no hay conexión', async () => {
    apagarServidor()
    const { wrapper, router } = await montarApp('/detalle-receta')
    expect(router.currentRoute.value.name).toBe('login')
    expect(alerta(wrapper).text()).toMatch(/backend esté encendido/)
    wrapper.unmount()
  })

  it('cerrar sesión avisa al backend y vuelve al login', async () => {
    const { wrapper, router } = await montarApp('/detalle-receta')
    await esperar(() => wrapper.find('.topbar__user-btn').exists())
    await wrapper.find('.topbar__user-btn').trigger('click')
    expect(wrapper.find('#menu-usuario').text()).toContain(USUARIO_PRUEBA.usuario)
    await wrapper.find('[role="menuitem"]').trigger('click')
    await esperar(() => router.currentRoute.value.name === 'login')

    expect(peticiones.at(-1)).toMatchObject({ metodo: 'POST', ruta: '/api/autenticacion/salir' })
    await router.push('/detalle-receta')
    expect(router.currentRoute.value.name).toBe('login')
    wrapper.unmount()
  })

  it('si la sesión vence estando dentro, la siguiente petición lleva al login', async () => {
    const { wrapper, router } = await montarApp('/detalle-receta')
    await esperar(() => wrapper.find('.topbar__user-name').exists())

    cerrarSesionFalsa()
    await catalogoService.listarTamanos().catch(() => {})
    await esperar(() => router.currentRoute.value.name === 'login')
    await esperar(() => alerta(wrapper).exists())
    expect(alerta(wrapper).text()).toContain('Tu sesión terminó')
    wrapper.unmount()
  })
})
