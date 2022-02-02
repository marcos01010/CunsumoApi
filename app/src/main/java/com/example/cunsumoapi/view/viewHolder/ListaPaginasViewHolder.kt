package com.example.cunsumoapi.view.viewHolder

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.repository.PhotoRepository

class ListaPaginasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var mlblNumeroPagina: TextView = itemView.findViewById(R.id.lblNumeroPagina)
    private val mPreferences: SharedPreferences = itemView.context.getSharedPreferences("preferencias", Context.MODE_PRIVATE)
    private val mPhotoRepository = PhotoRepository(itemView.context)

    private val mPagina = MutableLiveData<Int>()
    var pagina: LiveData<Int> = mPagina

    fun bindData(pagina: Int){
        mlblNumeroPagina.text = pagina.toString()
        mlblNumeroPagina.setOnClickListener(View.OnClickListener {
            mPagina.value = pagina
        })
    }
}