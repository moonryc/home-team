package com.example.hometeam.models

import com.bumptech.glide.Glide
import com.example.hometeam.R

data class Player(
    var strPlayer: String = "",
    val strTeam: String = "",
    val strNationality: String = "",
    val strTeam2: String = "",
    val strSport: String = "",
    val dateBorn: String = "",
    val strNumber: String = "",
    val dateSigned: String = "",
    val strWage: String = "",
    val strBirthLocation: String = "",
    val strDescriptionEN: String = "",
    val strPosition: String = "",
    val strHeight: String = "",
    val strWeight: String = "",
    val strThumb: String = "",
    val strCutout: String = "",
    val strRender: String = "",
    val strBanner: String = "",

    ){

    fun getImage():String{
        if(strThumb !== null){
            return strThumb
        }else if(strCutout !== null){
            return strCutout
        }
        else if(strRender !== null){
            return strRender
        }
        else{
            return ""
        }
    }

}