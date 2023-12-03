package com.task.testchili

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("offset")
    val offset: Int
)
