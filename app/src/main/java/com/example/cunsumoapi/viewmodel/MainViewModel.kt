package com.example.cunsumoapi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cunsumoapi.service.listener.APIListener
import com.example.cunsumoapi.service.model.PhotoModel
import com.example.cunsumoapi.service.repository.PhotoRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val mPhotoRepository = PhotoRepository(application)

    private val mListar = MutableLiveData<List<PhotoModel>>()
    var listar: LiveData<List<PhotoModel>> = mListar

    fun doListar(){
        mPhotoRepository.listar(object : APIListener<List<PhotoModel>>{
            override fun onSuccess(model: List<PhotoModel>) {
                mListar.value = model
            }

            override fun onFailure(str: String) {
                mListar.value = null
            }

        })
    }
}