package com.olamachia.simpleblogapp.api

import com.olamachia.simpleblogapp.model.CommentsItem
import com.olamachia.simpleblogapp.model.PostItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BlogAPI {

    @GET("/posts")
    suspend fun getPosts() : Response<List<PostItem>>

    @GET("/posts/{id}/comments")
    suspend fun getPostComments(@Path("id")id: Int) : Response<List<CommentsItem>>

    @POST("/comments")
    suspend fun postComment(@Body comment: CommentsItem): Response<CommentsItem>

    @POST("/posts")
    suspend fun uploadPosts(@Body postItem: PostItem) : Response<PostItem>
}