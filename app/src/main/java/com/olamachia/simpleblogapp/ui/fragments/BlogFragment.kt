package com.olamachia.simpleblogapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.adapters.PostAdapter
import com.olamachia.simpleblogapp.databinding.FragmentBlogBinding
import com.olamachia.simpleblogapp.model.PostItem
import com.olamachia.simpleblogapp.ui.activities.BlogActivity
import com.olamachia.simpleblogapp.viewmodel.BlogViewModel

class BlogFragment : Fragment() {
    private var _binding: FragmentBlogBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: BlogViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBlogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as BlogActivity).viewModel
        postAdapter = PostAdapter()

        val addPostTitle = binding.addPostTI.text
        val addPost = binding.postContentTI.text
        val posts = PostItem(addPost.toString(), addPostTitle.toString())

        binding.addPostFab.setOnClickListener {
            viewModel.uploadPosts(posts)
            viewModel.addPostItem.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful){
                    Log.d("Response", "${response}and $addPostTitle")

                    addPost!!.clear()
                    addPostTitle!!.clear()
                    Snackbar.make(view, "Successfully added post", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, response.message(), Snackbar.LENGTH_LONG).show()
                }
            })
        }

        viewModel.blogResponse.observe(viewLifecycleOwner, { response ->
            if (response.isSuccessful){
                binding.blogFragmentRv.apply {
                    adapter = postAdapter
                    layoutManager = LinearLayoutManager(activity)
                    postAdapter.differ.submitList(response.body())
                }
            } else {
                Toast.makeText(requireContext(), "Could not get post", Toast.LENGTH_SHORT).show()
            }
        })

//        postAdapter.setOnItemClickListener { replaceFragment(ReadPostFragment()) }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val blogPost = postAdapter.differ.currentList[position]

                viewModel.postBody = blogPost.body
                viewModel.postHeading = blogPost.title
                viewModel.postId = blogPost.id

                replaceFragment(ReadPostFragment())

                }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.blogFragmentRv)
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack("fragment")
        transaction.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
//
//    private fun setUpRecyclerView(){
//        postAdapter = PostAdapter()
//        binding.rvBreakingNews.apply {
//            adapter = postAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }

