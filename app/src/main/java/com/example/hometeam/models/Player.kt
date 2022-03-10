package com.example.hometeam.models

data class Player(
    val idPlayer:String = "",
    val idTeam: String = "",
    val idTeam2: String = "",
    val idTeamNational: String = "",
    val idSoccerXML: String = "",
    val idAPIfootball: String = "",
    val idPlayerManager: String = "",
    val strNationality: String = "",
    val strPlayer: String = "",
    val strTeam: String = "",
    val strTeam2: String = "",
    val strSport: String = "",
    val intSoccerXMLTeamID: String = "",
    val dateBorn: String = "",
    val strNumber: String = "",
    val dateSigned: String = "",
    val strSigning: String = "",
    val strWage: String = "",
    val strOutfitter: String = "",
    val strKit: String = "",
    val strAgent: String = "",
    val strBirthLocation: String = "",
    val strDescriptionEN: String = "",
    val strDescriptionDE: String = "",
    val strDescriptionFR: String = "",
    val strDescriptionCN: String = "",
    val strDescriptionIT: String = "",
    val strDescriptionJP: String = "",
    val strDescriptionRU: String = "",
    val strDescriptionES: String = "",
    val strDescriptionPT: String = "",
    val strDescriptionSE: String = "",
    val strDescriptionNL: String = "",
    val strDescriptionHU: String = "",
    val strDescriptionNO: String = "",
    val strDescriptionIL: String = "",
    val strDescriptionPL: String = "",
    val strGender: String = "",
    val strSide: String = "",
    val strPosition: String = "",
    val strCollege: String = "",
    val strFacebook: String = "",
    val strWebsite: String = "",
    val strTwitter: String = "",
    val strInstagram: String = "",
    val strYoutube: String = "",
    val strHeight: String = "",
    val strWeight: String = "",
    val intLoved: String = "",
    val strThumb: String = "",
    val strCutout: String = "",
    val strRender: String = "",
    val strBanner: String = "",
    val strFanart1: String = "",
    val strFanart2: String = "",
    val strFanart3: String = "",
    val strFanart4: String = "",
    val strCreativeCommons: String = "",
    val strLocked: String = ""
) {

    fun getName():String{
        return strPlayer;
    }

    fun getSport():String{
        return strSport
    }


}