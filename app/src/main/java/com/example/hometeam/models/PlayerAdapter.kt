package com.example.hometeam.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hometeam.R
import com.example.hometeam.viewmodels.MainViewModel


class PlayerAdapter(val viewModel:MainViewModel,private val playerList:PlayerList, val context:Context):RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.PlayerViewHolder {

        //turns layout file into viewObject
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)

        return PlayerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val currentItem = playerList.player[position]


        holder.name.text = currentItem.strPlayer
        holder.team.text = "Team: ${currentItem.strTeam}"
        holder.position.text = "Position: ${currentItem.strPosition}"
        holder.sport.text = "Sport: ${currentItem.strSport}"
        holder.nationality.text = "Nationality: ${currentItem.strNationality}"
        if(currentItem.strThumb !== null){
            Glide.with(holder.itemView.context).load(currentItem.strThumb).into(holder.imageView)
        }else if(currentItem.strCutout !== null){
            Glide.with(holder.itemView.context).load(currentItem.strCutout).into(holder.imageView)
        }
        else if(currentItem.strRender !== null){
            Glide.with(holder.itemView.context).load(currentItem.strRender).into(holder.imageView)
        }
        else{
            holder.imageView.setImageResource(R.drawable.player_image)
        }
    }

    override fun getItemCount() = playerList.player.size

    inner class PlayerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView = itemView.findViewById(R.id.player_image_view)
        val name: TextView = itemView.findViewById(R.id.name)
        val team: TextView = itemView.findViewById(R.id.team)
        val position: TextView = itemView.findViewById(R.id.position)
        val sport: TextView = itemView.findViewById(R.id.sport)
        val nationality: TextView = itemView.findViewById(R.id.nationality)
    }

}