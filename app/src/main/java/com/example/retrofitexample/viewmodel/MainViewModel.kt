package com.example.retrofitexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitexample.repository.AppRepository
import com.example.retrofitexample.retrofit.ApiService
import com.example.retrofitexample.retrofit.RetrofitBuilder

class MainViewModel : ViewModel() {
    private val retrofit = RetrofitBuilder.getInstance()
    private val apiService: ApiService = retrofit.create(ApiService::class.java)
    private val repository = AppRepository(apiService)
    private val _posts: MutableLiveData<String> = MutableLiveData()
    val posts: LiveData<String> get() = _posts
    fun getPosts() {
        _posts.value = repository.getPosts()
    }
}