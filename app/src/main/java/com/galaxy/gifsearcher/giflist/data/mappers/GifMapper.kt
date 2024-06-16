package com.galaxy.gifsearcher.giflist.data.mappers

import com.galaxy.gifsearcher.giflist.data.remote.Data
import com.galaxy.gifsearcher.giflist.domain.model.Gif

fun Data.toGif(): Gif{
    return Gif(
        url = this.imageData.image.url,
        width = this.imageData.image.width,
        height = this.imageData.image.height
    )
}