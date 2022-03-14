package com.example.hometeam.view

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hometeam.R
import com.example.hometeam.databinding.ActivityMainBinding
import com.example.hometeam.models.PlayerAdapter
import com.example.hometeam.viewmodels.MainViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity(), PlayerAdapter.OnItemClickListener {


    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: MainViewModel

    private lateinit var selectedPlayerView: View
    private lateinit var searchLayoutParent: View
    private lateinit var ad_view: AdView

    private var viewManager = LinearLayoutManager(this)
    private var isSearchViewActive = true


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
        recyclerView = findViewById(R.id.recycler_view)
        selectedPlayerView = findViewById(R.id.selected_player_layout_parent)
        searchLayoutParent = findViewById(R.id.searchLayoutParent)
        ad_view = findViewById(R.id.ad_view)

        //initialize ad
        val adRequest = AdRequest.Builder().build()
        ad_view.loadAd(adRequest)


        initialiseAdapter()
    }

    /**
     * Returns player to search view if in selected player view, otherwise treat back button as normal press
     */
    override fun onBackPressed() {
        if (!isSearchViewActive) {
            toggleView()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Create menu and initializes search
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val search = menu?.findItem(R.id.nav_search)
        val searchView: SearchView = search?.actionView as SearchView
        searchView.queryHint = "Athlete's name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            /**
             * Do nothing on text change
             */
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            /**
             * On text submit toggle the view if need be fetch players on search,
             * close the keyboard and search
             */
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


    /**
     * initializes the adapter for the playerAdapter
     */
    private fun initialiseAdapter() {
        recyclerView.layoutManager = viewManager
        recyclerView.setHasFixedSize(true)
        observeData()
    }

    /**
     * observe mainViewModel playerlist for changes and update the recyclerView.adapter
     */
    private fun observeData() {
        viewModel.playerList.observe(this, Observer {
            recyclerView.adapter = PlayerAdapter(viewModel, it, this)
        })
    }

    /**
     * Updates viewModel.selectedPlayer to reflect the selected player and toggles the screen view
     */
    override fun onItemClick(position: Int) {
        val clickedItem = viewModel.playerList.value?.player?.get(position)
        if (clickedItem != null) {
            viewModel.selectedPlayer.value = clickedItem
        }
        recyclerView.adapter?.notifyItemChanged(position)
        toggleView()
    }

    /**
     * Toggles between Search view and Selected player view
     */
    private fun toggleView() {
        //Search View
        if (isSearchViewActive) {
            selectedPlayerView.visibility = View.VISIBLE
            searchLayoutParent.visibility = View.INVISIBLE
            isSearchViewActive = false
        } else {
            //Selected player view
            selectedPlayerView.visibility = View.INVISIBLE
            searchLayoutParent.visibility = View.VISIBLE
            isSearchViewActive = true
        }
    }
}