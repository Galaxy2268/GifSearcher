package com.task.testchili

import com.google.gson.annotations.SerializedName
//final DataResult class in which we have mutableList of DataObject and pagination data class
data class DataResult(@SerializedName("data") val res: MutableList<DataObjet>, val pagination: Pagination)

data class DataObjet(@SerializedName("images")val images: DataImage)

data class DataImage(@SerializedName("original")val ogImage: OgImage)

data class OgImage(val url: String)

//data classes to get gifs url, path: data->images->original->url
