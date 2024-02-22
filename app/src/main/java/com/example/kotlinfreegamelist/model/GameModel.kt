package com.example.kotlinfreegamelist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class GameModel(
    @SerializedName("id") @ColumnInfo(name = "id")
    val id: Int,

    @SerializedName("title") @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("thumbnail") @ColumnInfo(name = "thumbnail")
    val thumbnail: String,

    @SerializedName("short_description") @ColumnInfo(name = "short_description")
    val shortDescription: String,

    @SerializedName("game_url") @ColumnInfo(name = "game_url")
    val gameUrl: String,

    @SerializedName("genre") @ColumnInfo(name = "genre")
    val genre: String,

    @SerializedName("platform") @ColumnInfo(name = "platform")
    val platform: String,

    @SerializedName("publisher") @ColumnInfo(name = "publisher")
    val publisher: String,

    @SerializedName("developer") @ColumnInfo(name = "developer")
    val developer: String,

    @SerializedName("release_date") @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @SerializedName("freetogame_profile_url") @ColumnInfo(name = "freetogame_profile_url")
    val freetogameProfileUrl: String,
    ) {
}