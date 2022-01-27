package com.olamachia.simpleblogapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olamachia.simpleblogapp.model.CommentsItem
import com.olamachia.simpleblogapp.model.PostItem
import com.olamachia.simpleblogapp.repository.BlogRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class BlogViewModel(private val blogRepository: BlogRepository) : ViewModel() {
    var blogResponse : MutableLiveData<Response<List<PostItem>>> = MutableLiveData()
    var eachPostCommentResponse: MutableLiveData<Response<List<CommentsItem>>> = MutableLiveData()
    var addPostItem: MutableLiveData<Response<PostItem>> = MutableLiveData()
    var addCommentResponse: MutableLiveData<Response<CommentsItem>> = MutableLiveData()

    var postHeading = ""
    var postBody = ""
    var postId: Int? = null

    init {
        getBlogPost()
    }
    private fun getBlogPost() = viewModelScope.launch {
        val response = blogRepository.getPost()
        blogResponse.value = response
    }

    fun getPostComments(id: Int) = viewModelScope.launch {
        val response = blogRepository.getPostComments(postId!!)
        eachPostCommentResponse.value = response
    }

    fun uploadPosts(postItem: PostItem) = viewModelScope.launch {
        val response = blogRepository.uploadPosts(postItem)
        addPostItem.value = response
    }

    fun postComments(comment: CommentsItem) = viewModelScope.launch {
        val response = blogRepository.postComment(comment)
        addCommentResponse.value = response
    }

}