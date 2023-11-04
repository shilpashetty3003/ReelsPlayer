package com.example.reels.component

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@Composable
fun ReelsPlayer(context: Context, url: String, selected: Boolean) {
    val reelsPlayer = remember {

        SimpleExoPlayer.Builder(context).build().apply {
            val mediaSource = ProgressiveMediaSource.Factory(
                DefaultDataSourceFactory(context, "ReelPlayer")
            ).createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${url}")))

            this.setMediaSource(mediaSource, true)
            this.prepare()
        }
    }
    reelsPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    reelsPlayer.repeatMode = Player.REPEAT_MODE_ONE
    AndroidView({

        PlayerView(it).apply {
            useController = false
            player = reelsPlayer
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        }
    })
    reelsPlayer.playWhenReady = selected

    DisposableEffect(key1 = url, effect = {
        onDispose {
            reelsPlayer.release()
        }
    })

}