package com.olamachia.simpleblogapp.model

data class PhotosItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)