package com.example.cunsumoapi.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.model.PhotoModel
import com.example.cunsumoapi.view.viewHolder.PhotoViewHolder

class PhotoAdapter : RecyclerView.Adapter<PhotoViewHolder>() {
    private var mList: List<PhotoModel> = arrayListOf()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.lista_photos_fragment, parent, false)
        this.context = parent.context
        return PhotoViewHolder(item)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun updateList(list: List<PhotoModel>) {
        mList = list
        notifyDataSetChanged()
    }
}