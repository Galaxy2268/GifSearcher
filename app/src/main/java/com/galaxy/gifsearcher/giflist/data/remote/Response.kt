package com.galaxy.gifsearcher.giflist.data.remote

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data")
    val data: List<Data>
)

data class Data(
    val id: String,
    @SerializedName("images")
    val image: ImageData
)

data class ImageData(
    @SerializedName("original")
    val ogImage: OgImage
)

data class OgImage(val url: String)