package com.example.kotlinfreegamelist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfreegamelist.databinding.RecyclerRowBinding
import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.util.loadImage
import com.example.kotlinfreegamelist.util.placeHolderProgressBar
import com.example.kotlinfreegamelist.view.GameFeedFragmentDirections

class GameAdapter(private val gameList: ArrayList<GameModel>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerRowBinding.inflate(layoutInflater, parent, false)
        return GameViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val url = gameList[position].thumbnail
        val name = gameList[position].title
        val date = gameList[position].releaseDate
        val uuid = gameList[position].uuid
        val image = holder.binding.imageViewRecyclerRow

        var nameText = holder.binding.gameTitleRecyclerRow.text
        var dateText = holder.binding.gameReleaseDateRecyclerRow.text

        val holderRoot = holder.binding.root
        val holderContext = holder.binding.root.context

        nameText = name
        dateText = date

        holderRoot.setOnClickListener {
            val action = GameFeedFragmentDirections.actionGameFeedFragmentToGameDetailFragment(uuid)
            Navigation.findNavController(it).navigate(action)
        }

        image.loadImage(url, placeHolderProgressBar(holderContext))
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateGameList(newGameList: List<GameModel>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }

}