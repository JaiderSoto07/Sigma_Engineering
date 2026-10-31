# Sistema de inventario para pizzería — Documentación de diseño

Documentación del proyecto final de **DOO** (Jaider y Laura): análisis y diseño de un sistema para gestionar el inventario, las compras, las ventas y las recetas de una pizzería.

![Diagrama de clases](assets/diagramas/diagrama-clases.svg)

## Contenido

| # | Documento | Qué contiene |
|---|---|---|
| 1 | [Modelo de dominio](docs/01-modelo-dominio/README.md) | Diagrama de línea de vida, objetos de dominio, reglas de negocio y, por objeto: muestreo, caracterización, eventos y políticas. |
| 2 | [Diagrama de clases](docs/02-diagrama-clases/README.md) | Diagrama UML y documentación de cada clase: relaciones, atributos, constructores, métodos y `Builder`. |
| 3 | [Modelo entidad-relación (MER)](docs/03-modelo-entidad-relacion/README.md) | Diagrama del modelo de datos y diccionario de datos de cada entidad (tipos, llaves y restricciones). |

## Diagramas

| Diagrama | SVG (vectorial) | PNG |
|---|---|---|
| Modelo de dominio | [modelo-dominio.svg](assets/diagramas/modelo-dominio.svg) | [modelo-dominio.png](assets/diagramas/modelo-dominio.png) |
| Diagrama de clases | [diagrama-clases.svg](assets/diagramas/diagrama-clases.svg) | [diagrama-clases.png](assets/diagramas/diagrama-clases.png) |
| Modelo entidad-relación | [mer.svg](assets/diagramas/mer.svg) | [mer.png](assets/diagramas/mer.png) |

## Estructura del repositorio

```
.
├── README.md                         ← este archivo
├── docs/
│   ├── 01-modelo-dominio/
│   │   ├── README.md                 ← diagrama, objetos y reglas de negocio
│   │   └── objetos/*.md              ← una página por objeto de dominio
│   ├── 02-diagrama-clases/
│   │   ├── README.md                 ← diagrama y listado de clases
│   │   └── clases/*.md               ← una página por clase
│   └── 03-modelo-entidad-relacion/
│       ├── README.md                 ← diagrama y listado de entidades
│       └── entidades/*.md            ← una página por entidad
├── assets/diagramas/                 ← imágenes de los diagramas (SVG y PNG)
└── fuentes/                          ← archivos originales (Excel y draw.io)
```

## Archivos fuente

La carpeta [`fuentes/`](fuentes) conserva los archivos originales a partir de los cuales se generó esta documentación:

- `ModeloDominio.xlsx` — modelo de dominio (muestreo, caracterización, eventos y políticas).
- `DocumentacionClases.xlsx` — documentación del diagrama de clases.
- `Mer.xlsx` — diccionario de datos del modelo entidad-relación.
- `*.drawio.svg` — diagramas editables: se pueden abrir y modificar directamente en [draw.io](https://app.diagrams.net).
