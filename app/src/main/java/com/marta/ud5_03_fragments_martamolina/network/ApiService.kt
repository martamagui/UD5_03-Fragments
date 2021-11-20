package com.marta.ud5_03_fragments_martamolina.network

import retrofit2.Call
import retrofit2.http.GET
import com.marta.ud5_03_fragments_martamolina.model.Result

interface ApiService {
    @GET("?results=500")
    fun get500Users(): Call<Result>
}