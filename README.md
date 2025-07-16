# UNIR_TFG_Aplicacion_analisis_de_rendimiento_faceit

## Nombre del TFG
**Aplicación de escritorio para la gestión y análisis de datos de FACEIT en Counter-Strike**

## Descripción

Este proyecto es una aplicación desarrollada como Trabajo de Fin de Grado (TFG) en Ingeniería Informática para la Universidad Internacional de La Rioja. Consiste en una aplicación de escritorio que permite consultar, visualizar y analizar información estadística de jugadores de **Counter-Strike** mediante la API oficial de **FACEIT**.

El sistema desarrollado se compone de dos partes:

- **Frontend (Aplicación de Escritorio)**: Desarrollado en **Java 21** y **Java Swing**, consiste en la aplicación final con la que va a interactuar el usuario mediante sus interfaces gráficas, donde se mostrará la información correspondiente al jugador buscado.
- **Backend (API REST)**: Construido con **Python**, utilizando **Flask** y **requests**, se conecta con la API de FACEIT para extraer los datos, los procesa y  expone a través de endpoints de Flask consumidos por la aplicación de escritorio.

## Tecnologías utilizadas para el desarrollo

- **Frontend**:
  - Java 21
  - Java Swing

- **Backend**:
  - Python 3.12
  - Flask
  - Requests

## Estructura del Proyecto


- **backend/**
  - **app/**
    - `routes/` – Endpoints de la API de Flask que exponen los datos
    - `services/` – Lógica para procesar datos de FACEIT
    - `utils/` – Funciones auxiliares (HTTP requests, etc.)
  - `tests/` – Pruebas unitarias del backend

- **frontend/**
  - **src/**
    - `main/java/...` – Controladores, modelos, vistas y servicios
    - `resources/` – Iconos e idiomas
  - `target/` – Contiene el archivo .jar generado

## Requisitos previos
- [Python 3.12](https://www.python.org/downloads/release/python-3120/)
- [Java JDK 21](https://www.oracle.com/es/java/technologies/downloads/#jdk21-windows)
- `pip` para instalar dependencias de Python
- API Key de FACEIT obtenida desde su [portal de desarrolladores](https://developers.faceit.com/start/intro)

## Configuraciones para ejecutar la aplicación:
Abrir símbolo del sistema en la raíz del proyecto y seguir los siguientes pasos o ejecución de comandos:

**Backend del sistema:**
1. Navega al directorio del backend: cd backend/
2. Instala las dependencias necesarias:
   - pip install flask requests
3. Crea un archivo creds.py con tu clave de la API pública de FACEIT que se puede conseguir registrándose en la plataforma FACEIT.com.        
   El archivo simplemente debe contener la siguiente variable con la clave: FACEIT_API_KEY = "TU_CLAVE_API"
5. Ejecuta el backend de la aplicación: python -m app.routes.faceit_routes

**Frontend del sistema:**
1. Asegúrate de tener instalado el JDK 21 de Java para ejecutar el archivo .jar de la aplicación.
2. Navega al directorio donde se encuentra el .jar : cd frontend/target/
3. Ejecutar el .jar : java -jar aplicacion.jar

## Características principales

- Búsqueda de jugadores por nombre de usuario en FACEIT
- Resumen estadístico del jugador
- Estadísticas de métricas de rendimiento del jugador
- Visualización y consulta del historial de partidos del jugador y sus métricas de rendimiento
- Clasificaciones con los jugadores mejor puntuados de la plataforma, permitiendo filtrar por país y región.
- Historial de equipos del jugador.
- Soporte multilenguaje (Español, Inglés y Francés)
- Interfaz intuitiva y sencilla

