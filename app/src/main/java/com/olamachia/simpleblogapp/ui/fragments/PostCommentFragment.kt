package com.olamachia.simpleblogapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.olamachia.simpleblogapp.adapters.CommentsAdapter
import com.olamachia.simpleblogapp.databinding.FragmentPostCommentBinding
import com.olamachia.simpleblogapp.repository.BlogRepository
import com.olamachia.simpleblogapp.viewmodel.BlogViewModel
import com.olamachia.simpleblogapp.viewmodel.BlogViewModelFactoryProvider

class PostCommentFragment : Fragment() {
    private var _binding : FragmentPostCommentBinding? = null
    private val binding get() = _binding!!

//    private lateinit var viewModel: BlogViewModel
//    lateinit var commentsAdapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostCommentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val blogRepository = BlogRepository()
//        val viewModelFactory = BlogViewModelFactoryProvider(blogRepository)
//        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[BlogViewModel::class.java]
//
//        viewModel.commentResponse.observe(viewLifecycleOwner, Observer { commentsResponse ->
//            if (commentsResponse.isSuccessful){
//                commentsAdapter = CommentsAdapter()
//                commentsAdapter.differ.submitList(commentsResponse.body())
//                binding.postCommentFragmentRv.adapter = commentsAdapter
//                binding.postCommentFragmentRv.layoutManager = LinearLayoutManager(activity)
//            } else {
//                Toast.makeText(requireContext(), "Could not get comments", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}