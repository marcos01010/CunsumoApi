package com.example.cunsumoapi.service.repository

import android.content.Context
import com.example.cunsumoapi.service.listener.APIListener
import com.example.cunsumoapi.service.model.PhotoModel
import com.example.cunsumoapi.service.repository.local.PhotoDb
import com.example.cunsumoapi.service.repository.remote.PhotoService
import com.example.cunsumoapi.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoRepository(val context: Context) {
    private val mRemote = RetrofitClient.createService(PhotoService::class.java)
    private val mDb = PhotoDb.getDatabase(context).PhotoDAO()

    fun listar(listener: APIListener<List<PhotoModel>>){
        val call: Call<List<PhotoModel>> = mRemote.listar()

        call.enqueue(object: Callback<List<PhotoModel>>{
            override fun onResponse(call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                if(response.code() != 200){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                }else{
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                listener.onFailure("Erro inesperado")
            }
        })
    }

    fun listar() = mDb.list()

    fun listar(quantidadePaginas: Int,paginaStart: Int) = mDb.list(quantidadePaginas,paginaStart)

    fun save(list: List<PhotoModel>){
        mDb.clear()
        mDb.save(list)
    }

    fun countPhotos() = mDb.countPhotos()
}