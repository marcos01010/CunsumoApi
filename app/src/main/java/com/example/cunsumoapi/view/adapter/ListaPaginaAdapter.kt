package com.example.cunsumoapi.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.listener.APISelecao
import com.example.cunsumoapi.view.viewHolder.ListaPaginasViewHolder

class ListaPaginaAdapter(selecao: APISelecao, visitados: List<Int>): RecyclerView.Adapter<ListaPaginasViewHolder>() {
    private var mList: List<Int> = arrayListOf()
    private var mSelecao: APISelecao = selecao
    private var mVisitados = visitados

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPaginasViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.lista_paginas_fragment, parent, false)
        return ListaPaginasViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListaPaginasViewHolder, position: Int) {
        holder.bindData(mList[position],mSelecao, mVisitados)
        Log.i("teste", position.toString())
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun updateList(list: List<Int>) {
        mList = list
        notifyDataSetChanged()
    }
}