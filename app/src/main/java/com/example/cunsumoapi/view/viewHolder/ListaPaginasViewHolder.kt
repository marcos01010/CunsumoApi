package com.example.cunsumoapi.view.viewHolder

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.listener.APISelecao

class ListaPaginasViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var mlblNumeroPagina: TextView = itemView.findViewById(R.id.lblNumeroPagina)
    private val mPreferences: SharedPreferences = itemView.context.getSharedPreferences("preferencias", Context.MODE_PRIVATE)

    fun bindData(pagina: Int, selecao: APISelecao, visitados: List<Int>){
        mlblNumeroPagina.text = pagina.toString()

        if (pagina in visitados){
            itemView.findViewById<ImageView>(R.id.imageViewVisitado).visibility = View.VISIBLE
        }else{
            itemView.findViewById<ImageView>(R.id.imageViewVisitado).visibility = View.GONE
        }

        if(mPreferences.getInt("ultimaPagina",-1) == pagina){
            itemView.findViewById<TextView>(R.id.lblEspacador).visibility = View.GONE
        }else{
            itemView.findViewById<TextView>(R.id.lblEspacador).visibility = View.VISIBLE
        }

        mlblNumeroPagina.setOnClickListener(View.OnClickListener {
            selecao.onSelectPagina(pagina)
            itemView.findViewById<ImageView>(R.id.imageViewVisitado).visibility = View.VISIBLE
        })
    }

}