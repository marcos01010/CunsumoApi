package com.example.cunsumoapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cunsumoapi.R
import com.example.cunsumoapi.service.listener.APISelecao
import com.example.cunsumoapi.view.adapter.ListaPaginaAdapter
import com.example.cunsumoapi.view.adapter.PhotoAdapter
import com.example.cunsumoapi.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mAdapter: PhotoAdapter
    private val mAdapterPaginas = ListaPaginaAdapter()
    private lateinit var btnAatualizar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = PhotoAdapter(object : APISelecao{
            override fun onSelectPagina() {
            }
        })

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel.doListar()

        val recycler: RecyclerView = findViewById(R.id.photosRecycler)
        recycler.layoutManager = GridLayoutManager(this,5, RecyclerView.VERTICAL,false)
        recycler.adapter = mAdapter

        val recyclerPaginas: RecyclerView = findViewById(R.id.listaPaginas)
        recyclerPaginas.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        recyclerPaginas.adapter = mAdapterPaginas

        mMainViewModel.countPhotos()

        btnAatualizar = findViewById<Button>(R.id.btnAtualizar)

        clickHandle()
        observer()
    }

    private fun clickHandle(){
        btnAatualizar.setOnClickListener(View.OnClickListener {
           mMainViewModel.listarFromWeb()
        })
    }

    private fun observer(){
        mMainViewModel.listar.observe(this, Observer {
            if(it != null){
                mAdapter.updateList(it)
            }else{
                var s = ""
            }
        })
        mMainViewModel.paginas.observe(this, Observer {
            mAdapterPaginas.updateList(it)
        })
    }

}