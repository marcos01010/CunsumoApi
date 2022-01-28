package com.example.cunsumoapi.service.model

import com.google.gson.annotations.SerializedName

class PhotoModel {
    @SerializedName("albumId")
    var albumId: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String = ""
}