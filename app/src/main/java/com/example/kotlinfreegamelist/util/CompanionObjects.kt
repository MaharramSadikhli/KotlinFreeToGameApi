package com.example.kotlinfreegamelist.util

import android.content.Context
import androidx.room.Room
import com.example.kotlinfreegamelist.model.GameDB

class CompanionObjects {

    // for game db
    companion object {

        @Volatile
        private var INSTANCE_DB: GameDB? = null

        private val lock = Any()

        operator fun invoke(context: Context) = INSTANCE_DB ?: synchronized(lock) {
            INSTANCE_DB ?: createDB(context).also {
                INSTANCE_DB = it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext, GameDB::class.java, "game_db"
        ).build()

    }

}