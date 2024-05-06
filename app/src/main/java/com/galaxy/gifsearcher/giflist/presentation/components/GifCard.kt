package com.galaxy.gifsearcher.giflist.presentation.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.galaxy.gifsearcher.giflist.domain.model.Gif

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.cardElevation(8.dp),
    gif: Gif,
    animatedContentScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope
){
    with(sharedTransitionScope){
        ElevatedCard(
            modifier = modifier,
            shape = shape,
            elevation = elevation
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(gif.url)
                    .crossfade(true)
                    .placeholderMemoryCacheKey(gif.id)
                    .memoryCacheKey(gif.id)
                    .decoderFactory(ImageDecoderDecoder.Factory())
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(1f)
                    .sharedElement(
                        rememberSharedContentState(key = gif.id),
                        animatedVisibilityScope = animatedContentScope
                    ),
                contentScale = ContentScale.Crop,
            )
        }
    }
}