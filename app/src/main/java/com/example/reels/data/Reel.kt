package com.example.reels.data

data class Reel(
    val id: Int,
    val artist: String,
    val song: String,
    val description: String,
    val imageId: Int,
    val swiped: Boolean = false,
)