package com.example.kotlinfreegamelist.service

import com.example.kotlinfreegamelist.model.GameModel
import com.example.kotlinfreegamelist.util.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceAPI {

    private val builder = Retrofit.Builder()

    private val retrofit = builder
        .baseUrl(Constants.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val service = retrofit.create(GameAPI::class.java)

    fun getData(): Single<List<GameModel>> {
        return service.getGames()
    }

}