package com.olamachia.simpleblogapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olamachia.simpleblogapp.repository.BlogRepository

class BlogViewModelFactoryProvider (
    private val blogRepository: BlogRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlogViewModel(blogRepository) as T
    }
}