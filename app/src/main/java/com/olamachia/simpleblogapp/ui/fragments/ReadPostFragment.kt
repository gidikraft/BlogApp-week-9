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
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.olamachia.simpleblogapp.adapters.CommentsAdapter
import com.olamachia.simpleblogapp.databinding.FragmentReadPostBinding
import com.olamachia.simpleblogapp.model.CommentsItem
import com.olamachia.simpleblogapp.repository.BlogRepository
import com.olamachia.simpleblogapp.ui.activities.BlogActivity
import com.olamachia.simpleblogapp.viewmodel.BlogViewModel
import com.olamachia.simpleblogapp.viewmodel.BlogViewModelFactoryProvider

class ReadPostFragment : Fragment() {
    private var _binding : FragmentReadPostBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BlogViewModel
    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReadPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as BlogActivity).viewModel

        binding.blogTitle.text = viewModel.postHeading[0].uppercase() + viewModel.postHeading.substring(1)
        binding.postBody.text = viewModel.postBody[0].uppercase() + viewModel.postBody.substring(1)

        val commentPostId = viewModel.postId
        viewModel.getPostComments(commentPostId!!)
        viewModel.getPhotos(commentPostId)

        val blogRepository = BlogRepository()
        val viewModelFactory = BlogViewModelFactoryProvider(blogRepository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[BlogViewModel::class.java]

        viewModel.eachPostCommentResponse.observe(viewLifecycleOwner, Observer { commentsResponse ->
            if (commentsResponse.isSuccessful){
                commentsAdapter = CommentsAdapter()
                commentsAdapter.differ.submitList(commentsResponse.body())
                binding.readPostLayoutRv.adapter = commentsAdapter
                binding.readPostLayoutRv.layoutManager = LinearLayoutManager(activity)
            } else {
                Toast.makeText(requireContext(), "Could not get comments", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.photosResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful) {
                Log.i("Photos", "${response.body()}")
                var profilePicture = binding.profilePicture
                Glide.with(this).load(response.body()?.url).into(profilePicture)


//                binding.blogFragmentRv.apply {
////                    adapter = photosAdapter
//                    layoutManager = LinearLayoutManager(activity)
////                    photosAdapter.differ.submitList(response.body())
//                }
            } else {
                Toast.makeText(requireContext(), "Could not get photos", Toast.LENGTH_SHORT).show()
            }
        })

        val addComment = binding.postCommentTI.text
        val comment = CommentsItem(addComment.toString(), "",1,"",1)

        binding.addPostFab.setOnClickListener {
            viewModel.postComments(comment)
            viewModel.addCommentResponse.observe(viewLifecycleOwner, Observer { addCommentResponse ->
                if (addCommentResponse.isSuccessful){
                    addComment?.clear()
                    Snackbar.make(view, "Successfully added comment", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, addCommentResponse.message(), Snackbar.LENGTH_SHORT).show()
                }

            })
        }
    }

}