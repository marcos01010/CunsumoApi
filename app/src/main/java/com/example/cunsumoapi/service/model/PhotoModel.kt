package com.example.cunsumoapi.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
class PhotoModel {

    @SerializedName("albumId")
    var albumId: Int = 0

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String = ""

    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String = ""

    @SerializedName("thumbnailUrl")
    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String = ""
}