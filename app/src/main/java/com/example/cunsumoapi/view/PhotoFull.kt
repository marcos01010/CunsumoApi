package com.example.cunsumoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.cunsumoapi.R
import com.squareup.picasso.Picasso

class PhotoFull : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_full)

        var mImageViewPhotoFull: ImageView = findViewById(R.id.photoFull)

        val bundle = intent.extras
        if(bundle != null){
            val url = bundle.getString("url")
            Picasso.get().load(url).into(mImageViewPhotoFull);
        }

    }
}