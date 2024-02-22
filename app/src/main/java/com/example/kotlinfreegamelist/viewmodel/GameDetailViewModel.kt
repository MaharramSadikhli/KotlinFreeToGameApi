package com.example.kotlinfreegamelist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.util.GameDbBuilder
import kotlinx.coroutines.launch

class GameDetailViewModel(application: Application): MainViewModel(application) {

    val gameDetail = MutableLiveData<GameModel>()

    fun getGameDetail(uuid: Int) {

        launch {
            val dao = GameDbBuilder(getApplication()).gameDao()
            val getGame = dao.getGame(uuid)

            gameDetail.value = getGame
        }

    }

}