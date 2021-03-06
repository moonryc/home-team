package com.example.hometeam.models


data class Player(
    var strPlayer: String? = "",
    val strTeam: String? = "",
    val strNationality: String? = "",
    val strTeam2: String? = "",
    val strSport: String? = "",
    val dateBorn: String? = "",
    val strNumber: String? = "",
    val dateSigned: String? = "",
    val strWage: String? = "",
    val strBirthLocation: String? = "",
    val strDescriptionEN: String? = "",
    val strPosition: String? = "",
    val strHeight: String? = "",
    val strWeight: String? = "",
    val strThumb: String? = "",
    val strCutout: String? = "",
    val strRender: String? = "",
    val strBanner: String? = "",
) {

    fun getImage(): String {
        return if (strThumb !== "" && strThumb !== null) {
            strThumb
        } else if (strCutout !== "" && strCutout !== null) {
            strCutout
        } else if (strRender !== "" && strRender !== null) {
            strRender
        } else {
            ""
        }
    }

    fun getTeam():String{
        if(strTeam !== null){
            return strTeam.replace("_","")
        }
        return "N/A"
    }

}