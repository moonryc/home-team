package com.example.hometeam.viewmodels

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hometeam.models.PlayerList
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainViewModel : ViewModel() {

    //Used to display the data on screen observed by the MainActivity
    val playerList = MutableLiveData<PlayerList>()
    //What the user is searching
    var search = ""


    init {
    }

    /**
     * Runs each time the user changes the text field, taking the searchedPlayer, paring to a string
     * and formating any spaces into %20 for url queries
     */
    fun updateSearch(searchedPlayer: Editable?) {

        search = searchedPlayer.toString().replace(" ", "%20")

        if (search != "") {
            val url = "https://www.thesportsdb.com/api/v1/json/50130162/searchplayers.php?p=$search"
            val request = Request.Builder().url(url).build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()

                    val playerListResponse = gson.fromJson(body, PlayerList::class.java)

                    playerList.postValue(playerListResponse)
                }

                override fun onFailure(call: Call, e: IOException) {
                    println("============================")
                    println("failed to execute request")
                    println("============================")
                }
            })
        }
    }

}



