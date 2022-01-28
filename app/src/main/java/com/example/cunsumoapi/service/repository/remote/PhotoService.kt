package com.example.cunsumoapi.service.repository.remote

import com.example.cunsumoapi.service.model.PhotoModel
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface PhotoService {
    @GET("photos")
    fun listar(): Call<List<PhotoModel>>
}