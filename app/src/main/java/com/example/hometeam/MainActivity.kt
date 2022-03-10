package com.example.hometeam

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hometeam.databinding.ActivityMainBinding
import com.example.hometeam.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.mainViewModel: aka the variable mainViewModel that was declared xml in variable tags
        binding.mainViewModel = viewmodel
        //to enable live data
        binding.lifecycleOwner = this
    }
}