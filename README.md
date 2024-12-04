# ExamenEventosApp3

## Descripción

ExamenEventosApp3 es una aplicación móvil desarrollada en Kotlin utilizando Android Studio. La aplicación permite a los usuarios ver una lista de farmacias y su ubicación en un mapa utilizando OpenStreetMap (OSM). Los datos de las farmacias se obtienen de Firestore.

## Funcionalidades

1. **Lista de Farmacias**: La aplicación muestra una lista de farmacias con su nombre, dirección y código postal.
2. **Mapa de Farmacias**: Los usuarios pueden ver la ubicación de las farmacias en un mapa interactivo.
3. **Integración con Firestore**: Los datos de las farmacias se obtienen de Firestore.
4. **Interfaz de Usuario con Jetpack Compose**: La interfaz de usuario está construida utilizando Jetpack Compose para una experiencia moderna y reactiva.

## Estructura del Proyecto

### `MainActivity.kt`

- Inicializa Firebase.
- Obtiene la lista de farmacias desde Firestore.
- Muestra un botón para ver el mapa con las farmacias.
- Muestra la lista de farmacias utilizando Jetpack Compose.

### `MapActivity.kt`

- Muestra un mapa utilizando OpenStreetMap (OSM).
- Añade marcadores en el mapa para cada farmacia.
- Centra el mapa en la ubicación de la primera farmacia o en Zaragoza si no hay farmacias.

### `Pharmacy.kt`

- Clase de datos `Pharmacy` que implementa `Parcelable` para pasar datos entre actividades.

### `PharmacyApiService.kt`

- Interfaz para definir las llamadas a la API de farmacias (comentada en el código).

### `PharmacyRepository.kt`

- Clase para obtener las farmacias desde Firestore.

### `RetrofitInstance.kt`

- Objeto para obtener la instancia de Retrofit (comentado en el código).

### `Theme.kt`

- Define los temas de la aplicación utilizando Jetpack Compose.

## Dependencias

- **OSM**: `osmdroid-android`, `osmdroid-mapsforge`
- **Retrofit**: `retrofit`, `converter-gson`
- **Jetpack Compose**: `compose-bom`, `ui`, `ui-graphics`, `ui-tooling-preview`, `material3`
- **Firebase**: `firebase-bom`, `firebase-analytics`, `firebase-firestore`, `firebase-firestore-ktx`, `firebase-auth-ktx`, `firebase-database-ktx`
- **Otros**: `core-ktx`, `lifecycle-runtime-ktx`, `activity-compose`, `navigation-compose`, `preference-ktx`

## Permisos

La aplicación requiere los siguientes permisos en el archivo `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

link al repositorio: https://github.com/cosmxr/ExamenEventosApp3.git
