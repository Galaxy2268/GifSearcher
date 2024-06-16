package com.galaxy.gifsearcher.giflist.domain.model

import java.util.UUID

data class Gif(
    val id: String = UUID.randomUUID().toString(),
    val url: String,
    val height: Float,
    val width: Float
)
