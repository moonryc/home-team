package com.example.hometeam.models

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hometeam.R
import com.example.hometeam.viewmodels.MainViewModel


class PlayerAdapter(
    private val viewModel: MainViewModel,
    private val playerList: PlayerList,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerAdapter.PlayerViewHolder {

        //turns layout file into viewObject
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)

        return PlayerViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val currentItem = playerList.player?.get(position)

        if (currentItem !== null) {
            holder.name.text = currentItem.strPlayer
            holder.team.text = "Team: ${currentItem.getTeam()}"
            holder.position.text = "Position: ${currentItem.strPosition}"
            holder.sport.text = "Sport: ${currentItem.strSport}"
            holder.nationality.text = "Nationality: ${currentItem.strNationality}"

            Glide.with(holder.itemView.context).load(currentItem.getImage())
                .placeholder(R.drawable.player_image)
                .circleCrop()
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        if (playerList.player === null) {
            viewModel.searchResultsText.value = "NO RESULTS FOUND"
            return 0
        }
        viewModel.searchResultsText.value = ""
        return playerList.player.size
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.player_image_view)
        val name: TextView = itemView.findViewById(R.id.name)
        val team: TextView = itemView.findViewById(R.id.team)
        val position: TextView = itemView.findViewById(R.id.position)
        val sport: TextView = itemView.findViewById(R.id.sport)
        val nationality: TextView = itemView.findViewById(R.id.nationality)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {

//            This method is confusing when adapters nest other adapters.
//            If you are calling this in the context of an Adapter, you probably want to call
//            getBindingAdapterPosition()
//            or if you want the position as RecyclerView sees it, you should call
//            getAbsoluteAdapterPosition()
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}