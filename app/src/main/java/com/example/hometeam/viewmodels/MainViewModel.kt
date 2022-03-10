package com.example.hometeam.viewmodels

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hometeam.models.Player
import com.example.hometeam.models.PlayerList
import com.example.hometeam.models.UserInfo
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainViewModel :ViewModel() {

    var search = ""
    val user = MutableLiveData<UserInfo>()
    val selectedPlayer = MutableLiveData<Player>()


    init {
        user.value = UserInfo("Tony Stark", "https://bit.ly/2zpY4w4")
        selectedPlayer.value = Player()

    }



    fun updateSearch(searchedPlayer:Editable?){
        search = searchedPlayer.toString()
        println("the search was changed!")
    }



    fun fetchPlayer(playerName:Editable?){
        user.value = UserInfo(playerName.toString(), "https://bit.ly/2zpY4w4")
        val url = "https://www.thesportsdb.com/api/v1/json/50130162/searchplayers.php?p=Danny"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()

                println("=========BODY=========")
                println(body)

                val playerList = gson.fromJson(body, PlayerList::class.java)

            }

            override fun onFailure(call: Call, e: IOException) {
                println("============================")
                println("failed to execute request")
                println("============================")
            }

        })
    }
}



