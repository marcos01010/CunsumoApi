package com.example.cunsumoapi.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    private val mAdapter = PhotoAdapter()
    private lateinit var mAdapterPaginas: ListaPaginaAdapter
    private lateinit var btnAatualizar: Button
    private var mListaVisitados = arrayListOf<Int>()
    private val QUANTIDADE_PAGINAS: Int = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mMainViewModel.doListar()
        mMainViewModel.countPhotos()

        btnAatualizar = findViewById<Button>(R.id.btnAtualizar)

        loadAdapter().observer().loadHandle().loadRecyclers()
    }

    private fun loadRecyclers(){
        val recycler: RecyclerView = findViewById(R.id.photosRecycler)
        recycler.layoutManager = GridLayoutManager(this,5, RecyclerView.VERTICAL,false)
        recycler.adapter = mAdapter

        val recyclerPaginas: RecyclerView = findViewById(R.id.listaPaginas)
        recyclerPaginas.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        recyclerPaginas.adapter = mAdapterPaginas
    }

    private fun loadHandle():MainActivity{
        btnAatualizar.setOnClickListener(View.OnClickListener {
           mMainViewModel.listarFromWeb()
        })
        return this
    }

    private fun observer():MainActivity{
        mMainViewModel.listar.observe(this, Observer {
            if(it != null){
                mAdapter.updateList(it)
            }
        })
        mMainViewModel.paginas.observe(this, Observer {
            mAdapterPaginas.updateList(it)
        })
        return this
    }

    private fun loadAdapter():MainActivity{
        mAdapterPaginas = ListaPaginaAdapter(object : APISelecao{
            override fun onSelectPagina(paginasStart: Int) {
                val mPreferences: SharedPreferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE)
                mMainViewModel.doListar(QUANTIDADE_PAGINAS,paginasStart)
                mListaVisitados.add(paginasStart)
            }
        },mListaVisitados)
        return this
    }
}