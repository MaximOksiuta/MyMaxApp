package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Films
import com.example.myapplication.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val response: MutableLiveData<Response<Films>> = MutableLiveData()

    fun getPopular(){
        viewModelScope.launch{
            val newResponse = repository.getPopular()
            response.value = newResponse
        }
    }

    fun getNowPlaying(){
        viewModelScope.launch{
            val newResponse = repository.getNowPlaying()
            response.value = newResponse
        }
    }
}