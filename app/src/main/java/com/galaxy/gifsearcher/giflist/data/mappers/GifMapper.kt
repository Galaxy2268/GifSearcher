package com.galaxy.gifsearcher.giflist.data.mappers

import com.galaxy.gifsearcher.giflist.data.remote.Data
import com.galaxy.gifsearcher.giflist.domain.model.Gif

fun Data.toGif(): Gif{
    return Gif(
        id = this.id,
        url = this.image.image.url,
        width = this.image.image.width,
        height = this.image.image.height
    )
}