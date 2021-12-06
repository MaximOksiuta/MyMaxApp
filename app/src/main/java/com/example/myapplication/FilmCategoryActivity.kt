package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.FilmsOnCategoryScreenAdapter
import com.example.myapplication.databinding.ActivityFilmCategoryBinding
import com.example.myapplication.repository.Repository


class FilmCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmCategoryBinding
    private val filmsAdapter by lazy {
        FilmsOnCategoryScreenAdapter()
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFilmCategoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val repository = Repository()
        binding.rvFilmsInCategory.adapter = filmsAdapter
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.tvCategoryTitle.text = intent.getStringExtra("groupName").toString()
        when (intent.getIntExtra("groupId", 0)){
            0 -> {
                viewModel.getPopular()
            }
            1 -> {
                viewModel.getNowPlaying()
            }
        }
        viewModel.response.observe(this, Observer {
            if (it.isSuccessful){
                it.body()?.let { it1 -> filmsAdapter.setData(it1.films) }
            } else {
                Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvFilmsInCategory.adapter = FilmsOnCategoryScreenAdapter()
    }
}

