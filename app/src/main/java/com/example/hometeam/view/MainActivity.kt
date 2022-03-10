package com.example.hometeam.view

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hometeam.databinding.ActivityMainBinding
import com.example.hometeam.viewmodels.MainViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hometeam.R
import com.example.hometeam.models.PlayerAdapter


class MainActivity : AppCompatActivity() {

    private var viewManager = LinearLayoutManager(this)
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var recycler_view:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.mainViewModel: aka the variable mainViewModel that was declared xml in variable tags
        binding.mainViewModel = viewModel
        //to enable live data
        binding.lifecycleOwner = this


        recycler_view = findViewById(R.id.recycler_view)
        //        val COUNTRIES: Array<String> = arrayOf<String>("hello","asdf")
//        val editText = findViewById(R.id.actv) as AutoCompleteTextView
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,COUNTRIES)
//        editText.setAdapter(adapter)


//        recycler_view.adapter = PlayerAdapter(viewModel.exampleList)
//        recycler_view.layoutManager = LinearLayoutManager(this)
//        recycler_view.setHasFixedSize(true)

        initialiseAdapter()
    }

    private fun initialiseAdapter(){
        recycler_view.layoutManager = viewManager
        recycler_view.setHasFixedSize(true)
        observeData()
    }


    fun observeData() {
        viewModel.playerList.observe(this, Observer {
            Log.i("data", it.toString())
            println("Test")
            recycler_view.adapter = PlayerAdapter(viewModel, it, this)
        })
    }


}