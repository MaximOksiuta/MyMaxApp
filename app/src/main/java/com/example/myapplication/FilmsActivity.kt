package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.CategoriesAdapter
import com.example.myapplication.databinding.ActivityFilmsBinding
import com.example.myapplication.model.FilmGroup
import com.example.myapplication.repository.Repository

class FilmsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmsBinding
    private val categoriesAdapter by lazy {
        CategoriesAdapter()
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        binding.rvCategories.adapter = categoriesAdapter
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getPopular()
        var filmGroups:MutableList<FilmGroup> = mutableListOf()
        viewModel.response.observe(this, Observer {
            if (it.isSuccessful){
                filmGroups.add(it.body()?.let { it1 -> FilmGroup("Popular", it1) }!!)
            } else {
                Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getNowPlaying()
        viewModel.response.observe(this, Observer {
            if (it.isSuccessful){
                filmGroups.add(it.body()?.let { it1 -> FilmGroup("Now Playing", it1) }!!)
            } else {
                Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })
        categoriesAdapter.setData(filmGroups)
    }
}