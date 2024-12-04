package com.example.exameneventosapp3

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

//actividad que muestra un mapa con las farmacias
class MapActivity : ComponentActivity() {
    private lateinit var mapView: MapView
    private val pharmacies: List<Pharmacy> by lazy {
        intent.getParcelableArrayListExtra<Pharmacy>("pharmacies") ?: emptyList()
    }
//se crea el mapa
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        mapView = MapView(this)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        setContent {
            MapScreen(mapView)
        }
//se añaden los marcadores de las farmacias
        pharmacies.forEach { pharmacy ->
            val marker = Marker(mapView)
            marker.position = GeoPoint(pharmacy.latitude, pharmacy.longitude)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = pharmacy.title
            mapView.overlays.add(marker)
        }

        if (pharmacies.isNotEmpty()) {
            val firstPharmacy = pharmacies.first()
            mapView.controller.setZoom(12.0)
            mapView.controller.setCenter(GeoPoint(firstPharmacy.latitude, firstPharmacy.longitude))
        } else {
            // Default, location
            val zaragoza = GeoPoint(41.648823, -0.889085)
            mapView.controller.setZoom(12.0)
            mapView.controller.setCenter(zaragoza)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDetach()
    }
}

@Composable
fun MapScreen(mapView: MapView) {
    AndroidView(factory = { mapView })
}