package com.example.cunsumoapi.service.listener

interface APIListener<T> {
    fun onSuccess(model: T)
    fun onFailure(str: String)
}