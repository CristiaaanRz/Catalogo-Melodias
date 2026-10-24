# Catálogo de Melodías

Proyecto en Java (Consola) con arquitectura MVC para administrar una colección de música. Permite registrar, buscar, editar y analizar melodías.

## Características principales

* **Control de duplicados:** Evita registrar melodías repetidas comparando cantante y año de grabación.


* **Gestión básica:** Registrar, buscar, editar y eliminar canciones por nombre.


* **Consultas y filtros:** Filtrar por cantante, compositor, género o año de grabación.


* **Estadísticas:** Cálculo del valor total de la colección, conteo de cantantes únicos y desglose por formato de audio (`mp3`, `ogg`, `wav`, `flac`).


* **Ordenamiento:** Visualizar la lista organizada por nombre, año, cantante o precio.



## Estructura

```text
src/
└── org/example/catalogomelodias/
    ├── Main.java              # Ejecución principal
    ├── Modelo/                # Catalogo, Melodia, Genero
    ├── Vista/                 # CatalogoView
    └── Controlador/           # Controlador

```

## Cómo ejecutar

1. Clona este repositorio.
2. Ábrelo en IntelliJ IDEA, Eclipse o tu IDE de preferencia.
3. Ejecuta `Main.java` para la versión interactiva por consola.