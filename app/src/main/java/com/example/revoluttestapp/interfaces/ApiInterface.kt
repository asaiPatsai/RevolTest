package com.example.revoluttestapp.interfaces

import com.example.revoluttestapp.models.RatesResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("latest")
    fun getRates(@Query(value = "base")  base: String):  Call<RatesResponse>
}