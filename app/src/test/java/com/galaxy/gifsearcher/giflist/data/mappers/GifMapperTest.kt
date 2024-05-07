package com.galaxy.gifsearcher.giflist.data.mappers

import com.galaxy.gifsearcher.giflist.data.remote.Data
import com.galaxy.gifsearcher.giflist.data.remote.Image
import com.galaxy.gifsearcher.giflist.data.remote.ImageData
import com.galaxy.gifsearcher.giflist.domain.model.Gif
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GifMapperTest{
    private val imageData = ImageData(Image("image"))
    private val data = listOf(
        Data("0",imageData),
        Data("1",imageData),
        Data("2",imageData),
        Data("3",imageData),
        Data("4",imageData),
    )

    @Test
    fun `Should correctly map list of data to list of gifs`(){
        val gifs = data.map { it.toGif() }
        assertThat(gifs.first()::class == Gif::class && gifs.first().url == "image").isTrue()
    }
}
