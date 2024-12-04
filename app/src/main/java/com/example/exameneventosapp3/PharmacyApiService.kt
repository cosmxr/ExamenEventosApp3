package com.example.exameneventosapp3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PharmacyApiService {
    @GET("sede/servicio/farmacia")
    fun getPharmacies(
        @Query("fl") fl: String = "id,title,telefonos,email,fax,horario,descripcion,servicios,url,clasificacion,calle",
        @Query("rows") rows: Int = 20,
        @Query("srsname") srsname: String = "wgs84",
        @Query("tipo") tipo: String = "guardia"
    ): Call<List<Pharmacy>>
}
