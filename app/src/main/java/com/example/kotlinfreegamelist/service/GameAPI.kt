package com.example.kotlinfreegamelist.service

import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.util.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface GameAPI {

    @GET(Constants.PATH_URL)
    fun getGames(): Single<List<GameModel>>

}