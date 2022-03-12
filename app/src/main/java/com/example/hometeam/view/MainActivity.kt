package com.example.hometeam.view

import android.os.Bundle
import android.view.Menu
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity(), PlayerAdapter.OnItemClickListener {

    private var viewManager = LinearLayoutManager(this)
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var recycler_view: RecyclerView

    private lateinit var selectedPlayerView: View
    private lateinit var searchLayoutParent: View

    private var isSearchViewActive = false


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
        selectedPlayerView = findViewById(R.id.selected_player_layout_parent)
        searchLayoutParent = findViewById(R.id.searchLayoutParent)

        initialiseAdapter()
    }


    override fun onBackPressed() {

        if(!isSearchViewActive){
            toggleView()
        }else{
            super.onBackPressed()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)

        val search = menu?.findItem(R.id.nav_search)
        val searchView:SearchView = search?.actionView as SearchView

        searchView.queryHint = "Athlete's name"

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query !== null){

                    if(!isSearchViewActive){
                        toggleView()
                    }

                    viewModel.fetchPlayers(query)
                    searchView.clearFocus()
                    searchView.onActionViewCollapsed()
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
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


    private fun toggleView(){
        //Search View
        if(isSearchViewActive){
            selectedPlayerView.visibility = View.VISIBLE
            searchLayoutParent.visibility = View.INVISIBLE
            isSearchViewActive = false
        } else{
            //Selected player view
            selectedPlayerView.visibility = View.INVISIBLE
            searchLayoutParent.visibility = View.VISIBLE
            isSearchViewActive = true
        }
    }
}