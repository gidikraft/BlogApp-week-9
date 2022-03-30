package com.olamachia.simpleblogapp.repository

import com.olamachia.simpleblogapp.api.RetrofitInstance
import com.olamachia.simpleblogapp.model.CommentsItem
import com.olamachia.simpleblogapp.model.PostItem

class BlogRepository {
    suspend fun getPost() = RetrofitInstance.api.getPosts()

    suspend fun postComment(comment: CommentsItem) = RetrofitInstance.api.postComment(comment)

    suspend fun getPostComments(id: Int) = RetrofitInstance.api.getPostComments(id)

    suspend fun uploadPosts(postItem: PostItem) = RetrofitInstance.api.uploadPosts(postItem)

    suspend fun getPhotos(id: Int) = RetrofitInstance.api.getPhotos(id)

}