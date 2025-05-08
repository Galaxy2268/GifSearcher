package com.galaxy.gifsearcher.giflist.presentation.components

import android.content.ClipData
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.galaxy.gifsearcher.giflist.domain.model.Gif

@Composable
fun GifCard(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    elevation: CardElevation = CardDefaults.cardElevation(8.dp),
    gif: Gif,
    onTap: (() -> Unit)? = null,
    onPress: (() -> Unit)? = null,
    contextMenu: Boolean = false,
){
    val isContextMenuVisible = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = LocalClipboard.current
    val interactionSource = remember { MutableInteractionSource() }


    ElevatedCard(
        modifier = modifier
            .clip(shape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        if(onTap != null || onPress != null || contextMenu) {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                            onPress?.invoke()
                        }
                    },
                    onTap = {
                        onTap?.invoke()
                    },
                    onLongPress = {
                        if (contextMenu) {
                            isContextMenuVisible.value = true
                        }
                    }
                )
            },
        elevation = elevation,
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(gif.url)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = "Gif",
            modifier = Modifier
                .indication(interactionSource, ripple())
                .fillMaxSize()
        )
        DropdownMenu(
            expanded = isContextMenuVisible.value,
            onDismissRequest = { isContextMenuVisible.value = false },
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy url"
                    )
                },
                text = { Text(text = "Copy") },
                onClick = {
                    isContextMenuVisible.value = false
                    val clipData = ClipData.newPlainText("Gif",AnnotatedString(gif.url))
                    clipboardManager.nativeClipboard.setPrimaryClip(clipData)
                    Toast.makeText(
                        context,
                        "Copied",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Share"
                    )
                },
                text = { Text(text = "Share") },
                onClick = {
                    isContextMenuVisible.value = false
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, gif.url)
                    }
                    if(intent.resolveActivity(context.packageManager) != null){
                        context.startActivity(intent)
                    }
                }
            )
        }
    }
}