package com.galaxy.gifsearcher.giflist.data.remote

import com.google.gson.annotations.SerializedName

data class Response(
    val data: List<Data>
)

data class Data(
    val id: String,
    @SerializedName("images")
    val imageData: ImageData
)

data class ImageData(
    @SerializedName("fixed_height")
    val image: Image
)

data class Image(
    val url: String,
    val height: Float,
    val width: Float
)