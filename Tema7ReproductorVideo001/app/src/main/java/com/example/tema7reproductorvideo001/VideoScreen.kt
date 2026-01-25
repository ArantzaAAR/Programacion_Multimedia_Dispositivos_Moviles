package com.example.tema7reproductorvideo001

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VideoScreen() {
    val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    /* COMENTO ESTE CÓDIGO, QUE FUNCIONABA CORRECTAMENTE TRAYENDO EL VideoPlayer PARA UTILIZAR EL AndroidView
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Reproductor Media3 (básico)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(12.dp))
        VideoPlayer(
            url = url,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
            //.fillMaxHeight()
        )
    }
    */
    val exoPlayer = rememberVideoPlayer(url)

    var isPlaying by remember { mutableStateOf(false) }

    var isBuffering by remember { mutableStateOf(false) }

    DisposableEffect(exoPlayer) {
        val listener = object : androidx.media3.common.Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                isBuffering = state == androidx.media3.common.Player.STATE_BUFFERING
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            VideoView(
                exoPlayer = exoPlayer,
                modifier = Modifier.matchParentSize()
            )

            if (isBuffering) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
                isPlaying = false
            } else {
                exoPlayer.play()
                isPlaying = true
            }
        }) {
            Text(if (isPlaying) "Pause" else "Play")
        }
    }
}