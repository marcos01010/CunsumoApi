package com.example.cunsumoapi.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cunsumoapi.service.listener.APIListener
import com.example.cunsumoapi.service.model.PhotoModel
import com.example.cunsumoapi.service.repository.PhotoRepository
import java.util.ArrayList

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val mPhotoRepository = PhotoRepository(application)
    private val mPreferences: SharedPreferences = application.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
    private val mQuantidadePhotos = MutableLiveData<List<Int>>()
    var paginas: LiveData<List<Int>> = mQuantidadePhotos

    private val mListar = MutableLiveData<List<PhotoModel>>()
    var listar: LiveData<List<PhotoModel>> = mListar

    fun doListar(){
        mListar.value = mPhotoRepository.listar()
    }
    fun doListar(quantidadePaginas: Int,paginaStart: Int){
        mListar.value = mPhotoRepository.listar(quantidadePaginas,paginaStart)
    }

    fun listarFromWeb(){
        mPhotoRepository.listar(object : APIListener<List<PhotoModel>>{
            override fun onSuccess(model: List<PhotoModel>) {
                mListar.value = model
                mPhotoRepository.save(model)
            }

            override fun onFailure(str: String) {
                mListar.value = null
            }

        })
    }

    fun countPhotos(){
        val totalPhotos = mPhotoRepository.countPhotos()
        val totalPorPaginas = 50
        var listaPaginas: ArrayList<Int> = arrayListOf()
        for (i in 1..(totalPhotos/totalPorPaginas)){
            listaPaginas.add(i)
        }
        mPreferences.edit().putInt("ultimaPagina", totalPhotos/totalPorPaginas).apply()
        mQuantidadePhotos.value = listaPaginas
    }


}