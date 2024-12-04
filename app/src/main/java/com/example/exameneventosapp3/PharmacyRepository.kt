package com.example.exameneventosapp3

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonSyntaxException

class PharmacyRepository {

/*
    fun fetchAndSavePharmacies(
        onPharmaciesFetched: (List<Pharmacy>) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = RetrofitInstance.api.getPharmacies()
        call.enqueue(object : Callback<List<Pharmacy>> {
            override fun onResponse(call: Call<List<Pharmacy>>, response: Response<List<Pharmacy>>) {
                if (response.isSuccessful) {
                    val pharmacies = response.body()
                    pharmacies?.let {
                        savePharmaciesToFirestore(it)
                        onPharmaciesFetched(it)
                    } ?: run {
                        onError("Error: Empty response body")
                    }
                } else {
                    onError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Pharmacy>>, t: Throwable) {
                if (t is JsonSyntaxException) {
                    onError("JSON Parsing Error: ${t.message}")
                } else {
                    onError("Failure: ${t.message}")
                }
            }
        })
    }*/
        fun fetchPharmaciesFromFirestore(
            onPharmaciesFetched: (List<Pharmacy>) -> Unit,
            onError: (String) -> Unit
        ) {
            val db = Firebase.firestore
            db.collection("pharmacies")
                .get()
                .addOnSuccessListener { result ->
                    val pharmacies = result.map { document ->
                        document.toObject(Pharmacy::class.java)
                    }
                    onPharmaciesFetched(pharmacies)
                }
                .addOnFailureListener { exception ->
                    Log.w("PharmacyRepository", "Error getting documents: ", exception)
                    onError(exception.message ?: "Unknown error")
                }
        }

    /*
    private fun savePharmaciesToFirestore(pharmacies: List<Pharmacy>) {
        val db = Firebase.firestore
        val collectionRef = db.collection("pharmacies")

        pharmacies.forEach { pharmacy ->
            collectionRef.add(pharmacy)
                .addOnSuccessListener { documentReference ->
                    Log.d("PharmacyRepository", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("PharmacyRepository", "Error adding document", e)
                }
        }
    }*/
}
