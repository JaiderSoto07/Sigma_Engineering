const UNIDAD_POR_TIPO_MEDIDA = {
  Peso: 'g',
  Volumen: 'ml',
}

const UNIDAD_POR_DEFECTO = 'und'

export function unidadRecetaPara(tipoMedida) {
  return UNIDAD_POR_TIPO_MEDIDA[tipoMedida] ?? UNIDAD_POR_DEFECTO
}
