package com.example.hometeam.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hometeam.databinding.ActivityMainBinding
import com.example.hometeam.viewmodels.MainViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hometeam.R
import com.example.hometeam.models.PlayerAdapter
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity(), PlayerAdapter.OnItemClickListener {

    private var viewManager = LinearLayoutManager(this)
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var recycler_view: RecyclerView
    lateinit var backButton: Button
    lateinit var searchButton: Button
    lateinit var searchField: EditText
    lateinit var selectedPlayerView:CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.mainViewModel: aka the variable mainViewModel that was declared xml in variable tags
        binding.mainViewModel = viewModel
        //to enable live data
        binding.lifecycleOwner = this


        //get Elements
        recycler_view = findViewById(R.id.recycler_view)
        backButton = findViewById(R.id.backButton)
        searchButton = findViewById(R.id.searchButton)
        searchField = findViewById(R.id.searchField)
        selectedPlayerView = findViewById(R.id.selected_player_layout)


        initialiseAdapter()

    }




    private fun initialiseAdapter() {
        recycler_view.layoutManager = viewManager
        recycler_view.setHasFixedSize(true)
        observeData()
    }


    fun observeData() {
        viewModel.playerList.observe(this, Observer {
            recycler_view.adapter = PlayerAdapter(viewModel, it, this, this)
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = viewModel.playerList.value?.player?.get(position)
        if (clickedItem != null) {
            viewModel.selectedPlayer.value = clickedItem
        }
        recycler_view.adapter?.notifyItemChanged(position)
        toggleView()
    }

    fun backButton(view:View) {
        toggleView()
    }

    private fun toggleView(){
        //List View
        if(recycler_view.isVisible){
            recycler_view.visibility = View.GONE
            searchField.visibility = View.GONE
            backButton.visibility = View.VISIBLE
            selectedPlayerView.visibility = View.VISIBLE

        } else{
            //Selected View
            recycler_view.visibility = View.VISIBLE
            searchField.visibility = View.VISIBLE
            backButton.visibility = View.INVISIBLE
            selectedPlayerView.visibility = View.GONE
        }
    }

}