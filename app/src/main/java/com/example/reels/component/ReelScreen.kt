package com.example.reels.component

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocal
import com.example.reels.data.DummyData
import com.example.reels.pager.Pager
import com.example.reels.pager.PagerState

@Composable
fun ReelScreen() {
    val reels = DummyData.reels
    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, reels.size - 1)
        }
    }
    Pager(
        state = pagerState,
        orientation = Orientation.Vertical,
        modifier = Modifier.fillMaxSize()
    ) {

        val reel = reels[commingPage]
        val isSelected = pagerState.currentPage == commingPage
        PagerItem(reel = reel, selected = isSelected)
    }
}