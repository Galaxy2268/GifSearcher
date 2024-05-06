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
    @SerializedName("fixed_height")
    val image: Image
)

data class Image(val url: String)