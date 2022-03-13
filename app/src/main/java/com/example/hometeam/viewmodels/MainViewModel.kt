package com.example.hometeam.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hometeam.models.Player
import com.example.hometeam.models.PlayerList
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.lang.Exception


class MainViewModel : ViewModel() {

    val playerList = MutableLiveData<PlayerList>()
    var selectedPlayer=MutableLiveData<Player>()
    var searchResultsText = MutableLiveData<String>()
    var initialLoad = true

    init {
        selectedPlayer.value = Player()
        searchResultsText.value = "SEARCH A PLAYER"
        fetchPlayers("Ryan")
    }


    /**
     * Gets a list of players based off of the search query and updates the playerList
     */
    fun fetchPlayers(search:String){
        val tempSearch = search.trim().replace(" ", "%20")
        if (tempSearch !== "") {
            val url = "https://www.thesportsdb.com/api/v1/json/50130162/searchplayers.php?p=$tempSearch"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()

                    try{
                        val playerListResponse = gson.fromJson(body, PlayerList::class.java)
                        playerList.postValue(playerListResponse)

                    }catch(e:Exception){
                        var arrayList:ArrayList<Player> = ArrayList(1)
                        var tempPlayerList = PlayerList(arrayList)
                        playerList.postValue(tempPlayerList)
                    }
                    collectSearchData(tempSearch)
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("============================")
                    println("failed to execute request")
                    println("============================")
                }
            })
        }
    }

    /**
     * Sends search query to a backend MySQL to collect anylytics to see what player names/searches are most common
     * visit https://home-team-tracker.herokuapp.com/ to see data
     */
    fun collectSearchData(playerSearch:String){
        if(initialLoad){
            initialLoad= false
            return
        }
        val url = "https://home-team-tracker.herokuapp.com/api/addplayer/${playerSearch}"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
               println(response.code)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("============================")
                println("failed to execute request")
                println("============================")
            }
        })
    }

}



