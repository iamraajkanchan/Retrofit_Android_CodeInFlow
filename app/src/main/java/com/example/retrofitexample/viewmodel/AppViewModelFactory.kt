package com.example.retrofitexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel() as T
        } else {
            throw NullPointerException("${modelClass.name} is an incompatible ViewModel")
        }
    }
}