import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest'
import { flushPromises, mount } from '@vue/test-utils'
import { createPinia } from 'pinia'
import { createMemoryHistory, createRouter } from 'vue-router'

import App from '../App.vue'
import { routes } from '../router'
import { MENSAJES_FORMULARIO } from '../modules/detalle-receta/utils/detalleRecetaValidations'
import { ID, iniciarServidorFalso, peticiones } from './servidorFalso'

async function esperar(condicion) {
  await vi.waitFor(async () => {
    await flushPromises()
    if (!condicion()) throw new Error('esperando')
  })
}

async function montarApp(ruta = '/') {
  const router = createRouter({ history: createMemoryHistory(), routes })
  router.push(ruta)
  await router.isReady()
  const wrapper = mount(App, {
    attachTo: document.body,
    global: { plugins: [createPinia(), router] },
  })
  await esperar(() => wrapper.text().includes('Detalle de la receta'))
  return { wrapper, router }
}

const botonEn = (raiz, texto) =>
  [...raiz.querySelectorAll('button')].find((b) => b.textContent.includes(texto))

function escribirCantidad(modal, indice, valor) {
  const input = modal.querySelectorAll('.editor input')[indice]
  input.value = valor
  input.dispatchEvent(new Event('input'))
}

function elegirIngrediente(modal, indice, id) {
  const select = modal.querySelectorAll('.editor select')[indice]
  select.value = id
  select.dispatchEvent(new Event('change'))
}

const escrituras = () => peticiones.filter((p) => p.metodo !== 'GET')

describe('Recetas', () => {
  beforeEach(() => {
    iniciarServidorFalso()
    document.body.innerHTML = ''
  })
  afterEach(() => vi.unstubAllGlobals())

  it('redirige a /detalle-receta y lista solo pizzas y adiciones', async () => {
    const { wrapper, router } = await montarApp('/')

    expect(router.currentRoute.value.name).toBe('detalle-receta')
    expect(wrapper.text()).toContain('Margarita Grande')
    expect(wrapper.text()).toContain('Hawaiana Mediana')
    expect(wrapper.text()).toContain('Extra Queso')
    expect(wrapper.text()).not.toContain('Coca-Cola')
    expect(wrapper.text()).toContain('Mostrando 1 - 3 de 3 recetas')
    wrapper.unmount()
  })

  it('el menú lateral solo ofrece Recetas', async () => {
    const { wrapper } = await montarApp('/')
    const opciones = wrapper.findAll('.sidebar nav li').map((li) => li.text())
    expect(opciones).toEqual(['Recetas'])
    wrapper.unmount()
  })

  it('/productos ya no existe: lleva a Recetas', async () => {
    const { wrapper, router } = await montarApp('/productos')
    expect(router.currentRoute.value.name).toBe('detalle-receta')
    wrapper.unmount()
  })

  it('selecciona el producto indicado en la URL y muestra sus ingredientes', async () => {
    const { wrapper } = await montarApp(`/detalle-receta?producto=${ID.producto.extraQueso}`)
    const panel = wrapper.find('.recetas__panel')
    expect(panel.text()).toContain('Extra Queso')
    expect(panel.text()).toContain('Queso')
    wrapper.unmount()
  })

  it('el editor no permite elegir la unidad: muestra la que asigna el backend', async () => {
    const { wrapper } = await montarApp(`/detalle-receta?producto=${ID.producto.margarita}`)
    await wrapper.find('[aria-label="Gestionar detalles de Margarita Grande"]').trigger('click')
    await flushPromises()
    const modal = document.body.querySelector('.modal')
    const unidades = () =>
      [...modal.querySelectorAll('.editor__unidad')].map((u) => u.textContent.trim())

    expect(unidades()).toEqual(['g', 'g', 'ml', 'g', 'g'])
    expect(modal.querySelectorAll('.editor select')).toHaveLength(0)

    botonEn(modal, 'Agregar ingrediente').click()
    await flushPromises()
    expect(unidades().at(-1)).toBe('—')
    elegirIngrediente(modal, 0, ID.interno.huevo)
    await flushPromises()
    expect(unidades().at(-1)).toBe('und')
    wrapper.unmount()
  })

  it('no envía nada si alguna línea tiene errores (repetido, cero, más de 10.000)', async () => {
    const { wrapper } = await montarApp(`/detalle-receta?producto=${ID.producto.margarita}`)
    await wrapper.find('[aria-label="Gestionar detalles de Margarita Grande"]').trigger('click')
    await flushPromises()
    const modal = document.body.querySelector('.modal')

    escribirCantidad(modal, 0, '15000')
    botonEn(modal, 'Agregar ingrediente').click()
    await flushPromises()
    elegirIngrediente(modal, 0, ID.interno.queso)
    escribirCantidad(modal, 5, '0')
    await flushPromises()

    botonEn(modal, 'Guardar').click()
    await flushPromises()

    const errores = [...modal.querySelectorAll('.field-error')].map((e) => e.textContent.trim())
    expect(errores).toEqual([
      MENSAJES_FORMULARIO.maximo,
      MENSAJES_FORMULARIO.repetido,
      MENSAJES_FORMULARIO.mayorQueCero,
    ])
    expect(escrituras()).toEqual([])
    expect(modal.textContent).toContain('Revisa los datos marcados antes de guardar.')
    wrapper.unmount()
  })

  it('nueva receta: elige un producto sin configurar, agrega ingredientes y los guarda', async () => {
    const { wrapper } = await montarApp('/detalle-receta')
    botonEn(document.body, 'Nueva receta').click()
    await flushPromises()
    const modal = document.body.querySelector('.modal')

    const productos = [...modal.querySelectorAll('select')][0]
    const opciones = [...productos.querySelectorAll('option')].map((o) => o.textContent.trim())
    expect(opciones).toEqual(['Selecciona un producto…', 'Hawaiana Mediana'])
    productos.value = ID.producto.hawaiana
    productos.dispatchEvent(new Event('change'))
    await flushPromises()

    elegirIngrediente(modal, 0, ID.interno.masa)
    escribirCantidad(modal, 0, '300')
    botonEn(modal, 'Agregar ingrediente').click()
    await flushPromises()
    elegirIngrediente(modal, 1, ID.interno.aceite)
    escribirCantidad(modal, 1, '12,5')
    await flushPromises()

    botonEn(modal, 'Guardar').click()
    await esperar(() => !document.body.querySelector('.modal'))

    expect(escrituras().map((p) => p.cuerpo)).toEqual([
      {
        producto: { id: ID.producto.hawaiana },
        productoInterno: { id: ID.interno.masa },
        cantidad: 300,
      },
      {
        producto: { id: ID.producto.hawaiana },
        productoInterno: { id: ID.interno.aceite },
        cantidad: 12.5,
      },
    ])
    await esperar(() => wrapper.find('.recetas__panel').text().includes('Hawaiana Mediana'))
    const panel = wrapper.find('.recetas__panel').text()
    expect(panel).toContain('Masa de Pizza')
    expect(panel).toContain('Aceite de oliva')
    wrapper.unmount()
  })

  it('retira un ingrediente desde el panel, con confirmación', async () => {
    const { wrapper } = await montarApp(`/detalle-receta?producto=${ID.producto.extraQueso}`)

    await wrapper.find('[aria-label="Retirar Queso de la receta"]').trigger('click')
    const confirmar = [...document.body.querySelectorAll('button')].find(
      (b) => b.textContent.trim() === 'Retirar',
    )
    confirmar.click()
    await esperar(() => wrapper.find('.recetas__panel').text().includes('Sin configurar'))
    expect(escrituras().map((p) => p.metodo)).toEqual(['DELETE'])
    wrapper.unmount()
  })
})
