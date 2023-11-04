package com.example.reels.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.reels.data.DummyData
import com.example.reels.data.Reel


@Composable
fun PagerItem(reel: Reel,selected:Boolean){
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
    ) {
        ReelsPlayer(context, DummyData.video[reel.id % 3], selected)
        LayoutOverReel(reel)
    }
}