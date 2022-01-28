package com.example.cunsumoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel
    private val mAdapter = PhotoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel.doListar()

        val recycler = findViewById<RecyclerView>(R.id.photosRecycler)
        recycler.layoutManager = GridLayoutManager(this,5,RecyclerView.VERTICAL,false)
        recycler.adapter = mAdapter

        observer()
    }

    private fun observer(){
        mMainViewModel.listar.observe(this, Observer {
            if(it != null){
                mAdapter.updateList(it)
            }else{
                var s = ""
            }
        })
    }

}