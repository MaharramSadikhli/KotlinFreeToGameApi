package com.example.kotlinfreegamelist.adapter

import android.view.View

interface GameClick {
    fun onClickGame(v: View, uuid: Int)
}