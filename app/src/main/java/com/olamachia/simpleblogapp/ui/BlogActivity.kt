package com.olamachia.simpleblogapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.databinding.ActivityBlogBinding
import com.olamachia.simpleblogapp.repository.BlogRepository
import com.olamachia.simpleblogapp.ui.fragments.BlogFragment
import com.olamachia.simpleblogapp.viewmodel.BlogViewModel
import com.olamachia.simpleblogapp.viewmodel.BlogViewModelFactoryProvider

class BlogActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBlogBinding
    lateinit var viewModel: BlogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val blogRepository = BlogRepository()
        val viewModelProviderFactory = BlogViewModelFactoryProvider(blogRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[BlogViewModel::class.java]

        replaceFragment(BlogFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}