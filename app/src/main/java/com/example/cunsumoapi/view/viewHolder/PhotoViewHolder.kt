package com.example.cunsumoapi.view.viewHolder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.view.PhotoFull
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.listener.APISelecao
import com.example.cunsumoapi.service.model.PhotoModel
import com.squareup.picasso.Picasso

class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var mImageViewPhotos: ImageView = itemView.findViewById(R.id.imageViewPhoto)

    fun bindData(photo: PhotoModel){
        Picasso.get().load(photo.thumbnailUrl).into(mImageViewPhotos);
        mImageViewPhotos.setOnClickListener(View.OnClickListener {
            val intent = Intent(itemView.context, PhotoFull::class.java)
            val bundle = Bundle()
            bundle.putString("url", photo.url)
            intent.putExtras(bundle)
            startActivity(itemView.context,intent,bundle)
        })
    }
}