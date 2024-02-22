package com.example.kotlinfreegamelist.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {

    @Query(value = "SELECT * FROM gamemodel")
    suspend fun getAllGames(): List<GameModel>

    @Query(value = "SELECT * FROM gamemodel WHERE uuid = :id")
    suspend fun getGame(id: Int): GameModel

    @Query("DELETE FROM gamemodel")
    suspend fun deleteAllGames()


    @Insert
    suspend fun insertGame(vararg gameModel: GameModel): List<Long>

}