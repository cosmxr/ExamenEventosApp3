package com.example.exameneventosapp3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Exameneventosapp3Theme {
                val pharmacyRepository = PharmacyRepository()
                val pharmacies = remember { mutableStateListOf<Pharmacy>() }
                var errorMessage by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    pharmacyRepository.fetchPharmaciesFromFirestore(
                        onPharmaciesFetched = { fetchedPharmacies ->
                            pharmacies.clear()
                            pharmacies.addAll(fetchedPharmacies)
                        },
                        onError = { error ->
                            errorMessage = error
                        }
                    )
                }

                Column {
                    Spacer(modifier = Modifier.height(76.dp))
                    Button(
                        onClick = {
                            val intent = Intent(this@MainActivity, MapActivity::class.java)
                            intent.putParcelableArrayListExtra("pharmacies", ArrayList(pharmacies))
                            startActivity(intent)
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Ver Mapa")
                    }
                    PharmacyScreen(
                        pharmacies = pharmacies,
                        errorMessage = errorMessage
                    )
                }
            }
        }
    }
}

@Composable
fun PharmacyScreen(
    pharmacies: List<Pharmacy>,
    errorMessage: String?
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        } else {
            PharmacyList(pharmacies)
        }
    }
}

@Composable
fun PharmacyList(pharmacies: List<Pharmacy>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(pharmacies) { pharmacy ->
            PharmacyCard(pharmacy)
        }
    }
}

@Composable
fun PharmacyCard(pharmacy: Pharmacy) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = pharmacy.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Dirección: ${pharmacy.address}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Codigo Postal: ${pharmacy.postalCode ?: "No disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}