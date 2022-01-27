package com.olamachia.simpleblogapp.model

data class PostItem(
    val body: String,
    val title: String,
    val id: Int? = null,
    val userId: Int? = null
)