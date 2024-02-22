package com.example.kotlinfreegamelist.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.room.Room
import com.example.kotlinfreegamelist.model.GameDB

private val LOCK = Any()

class GameDbBuilder {

    // for game db
    companion object {

        @Volatile
        private var INSTANCE_DB: GameDB? = null


        operator fun invoke(context: Context) = INSTANCE_DB ?: synchronized(LOCK) {
            INSTANCE_DB ?: createDB(context).also {
                INSTANCE_DB = it
            }
        }

        private fun createDB(context: Context) = Room.databaseBuilder(
            context.applicationContext, GameDB::class.java, "game_db"
        ).build()

    }


}

class SharedPreferencesForTime {

    companion object {

        private var sharedPreferencesForTime: SharedPreferences? = null

        @Volatile
        private var INSTANCE_SPT: SharedPreferencesForTime? = null

        operator fun invoke(context: Context): SharedPreferencesForTime = INSTANCE_SPT ?: synchronized(LOCK) {
            INSTANCE_SPT ?: createSPT(context).also {
                INSTANCE_SPT = it
            }
        }

        private fun createSPT(context: Context): SharedPreferencesForTime {
            sharedPreferencesForTime = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesForTime()
        }

    }

    fun saveTime(time: Long) {
        sharedPreferencesForTime?.edit(commit = true) {
            putLong(Constants.TIME, time)
        }
    }

    fun getTime() = sharedPreferencesForTime?.getLong(Constants.TIME, 0)


}