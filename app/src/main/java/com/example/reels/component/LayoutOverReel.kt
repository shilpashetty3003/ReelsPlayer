package com.example.reels.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reels.R
import com.example.reels.data.Reel
import com.example.reels.ui.theme.ReelsRed

@Composable
fun LayoutOverReel(reel: Reel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        VideoInfoSection(Modifier.weight(1f), reel)
        VideoIconsSection(reel)
    }
}

@Composable
fun VideoInfoSection(modifier: Modifier, reel: Reel) {
    Column(modifier = modifier.padding(8.dp)) {
        FilterTag(text = reel.song, modifier = Modifier)
        Text(
            text = "@${reel.artist}",color= Color.White,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(text = reel.song, style = MaterialTheme.typography.bodySmall,color= Color.White,)
        Text(
            text = "#${reel.artist} #reel #love",color= Color.White,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
        )
    }
}


@Composable
fun FilterTag(text: String, modifier: Modifier) {
    val tagModifier = modifier
        .clickable(onClick = {})
        .clip(RoundedCornerShape(4.dp))
        .alpha(0.4f)
        .background(Color.Black)
        .padding(horizontal = 8.dp, vertical = 4.dp)

    Text(
        text = text,
        color = Color.White,
        modifier = tagModifier,
        style = typography.bodySmall.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun ProfileImageWithFollow(modifier: Modifier, showFollow: Boolean, imageId: Int) {
    if (showFollow) {
        Box(modifier = modifier) {
            ImageWithBorder(imageId = imageId, modifier = modifier)
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(ReelsRed)
                    .align(Alignment.BottomCenter)
            )
        }
    } else {
        ImageWithBorder(imageId = imageId, modifier = modifier)
    }
}

@Composable
fun ImageWithBorder(imageId: Int, modifier: Modifier) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = modifier
            .padding(8.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    1.dp,
                    color = Color.White
                )
            )
    )
}

@Composable
fun VideoIconsSection(
    reel: Reel,

) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ProfileImageWithFollow(
            modifier = Modifier
                .size(64.dp)
                .clickable(onClick = {

                }),
            true,
            reel.imageId
        )
        Spacer(modifier = Modifier.height(20.dp))
        LikeIcon(reel.id)
        Text(
            text = "1M",color= Color.White,
            style = typography.bodySmall.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_comment_dots_solid),
            contentDescription = null,tint= Color.White
        )
        Text(
            text = "2345",color= Color.White,
            style = typography.bodySmall.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )
        Icon(painter = painterResource(id = R.drawable.ic_share_solid), contentDescription = null,tint= Color.White)
        Text(
            text = "23",color= Color.White,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )
        val rotation = remember { Animatable(0f) }
        LaunchedEffect(Unit) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = repeatable(
                    iterations = 10000,
                    animation = tween(durationMillis = 3500, easing = LinearEasing),
                ),
            )
        }
        ProfileImageWithFollow(
            modifier = Modifier
                .size(64.dp)
                .graphicsLayer(rotationZ = rotation.value),
            false,
            reel.imageId
        )
    }
}

@Composable
fun LikeIcon(id: Int) {
    var fav by remember(id) { mutableStateOf(false) }
    val animatedProgress = remember { Animatable(0f) }
    if (!fav) {
        LaunchedEffect(fav) {
            animatedProgress.animateTo(
                targetValue = 1.3f,
                animationSpec = tween(600),
            )
        }
    }
    Icon(
        painter = painterResource(id = R.drawable.ic_heart_solid),
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = { fav = !fav })
            .graphicsLayer(scaleX = animatedProgress.value, scaleY = animatedProgress.value),
        tint = animateColorAsState(if (fav) ReelsRed else Color.White).value
    )
}
