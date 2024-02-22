package com.example.kotlinfreegamelist.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameModel::class], version = 1)
abstract class GameDB: RoomDatabase() {

    abstract fun gameDao(): GameDao

}