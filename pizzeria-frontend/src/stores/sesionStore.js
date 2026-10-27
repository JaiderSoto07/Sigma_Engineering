import { computed, ref } from 'vue'
import { defineStore } from 'pinia'
import { ESTADO_SIN_SESION } from '@/api/http'
import { autenticacionService } from '@/services/autenticacionService'

const continuarAunqueElBackendFalle = () => {}

export const useSesionStore = defineStore('sesion', () => {
  const usuario = ref(null)
  const loading = ref(false)
  const error = ref(null)
  const verificada = ref(false)

  const autenticada = computed(() => usuario.value != null)

  async function cargarSesion() {
    loading.value = true
    error.value = null
    try {
      usuario.value = await autenticacionService.obtenerUsuarioActual()
    } catch (e) {
      usuario.value = null
      if (e.status !== ESTADO_SIN_SESION) error.value = e.message
    } finally {
      loading.value = false
      verificada.value = true
    }
  }

  function iniciarSesionConGoogle() {
    autenticacionService.iniciarSesionConGoogle()
  }

  async function cerrarSesion() {
    await autenticacionService.cerrarSesion().catch(continuarAunqueElBackendFalle)
    olvidarSesion()
  }

  function olvidarSesion(mensaje = null) {
    usuario.value = null
    error.value = mensaje
  }

  return {
    usuario,
    loading,
    error,
    verificada,
    autenticada,
    cargarSesion,
    iniciarSesionConGoogle,
    cerrarSesion,
    olvidarSesion,
  }
})
