package com.example.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitexample.databinding.ActivityMainBinding
import com.example.retrofitexample.viewmodel.AppViewModelFactory
import com.example.retrofitexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, AppViewModelFactory())[MainViewModel::class.java]
        getPosts()
    }

    private fun getPosts() {
        viewModel?.getPosts()
        viewModel?.posts?.observe(this) { response ->
            println()
            binding.txtResult.text = response
        }
    }
}
