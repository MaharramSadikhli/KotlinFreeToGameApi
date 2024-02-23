package com.example.kotlinfreegamelist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfreegamelist.R
import com.example.kotlinfreegamelist.databinding.RecyclerRowBinding
import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.view.GameFeedFragmentDirections

class GameAdapter(private val gameList: ArrayList<GameModel>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>(), GameClick {

    class GameViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, parent, false)
        return GameViewHolder(binding)
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        holder.binding.game = gameList[position]
        holder.binding.clickListener = this

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

    override fun onClickGame(v: View, uuid: Int) {
        val action = GameFeedFragmentDirections.actionGameFeedFragmentToGameDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)

    }

}