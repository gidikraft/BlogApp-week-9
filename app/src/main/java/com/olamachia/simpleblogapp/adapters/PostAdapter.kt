package com.olamachia.simpleblogapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.PostItem

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var differCallback = object : DiffUtil.ItemCallback<PostItem>(){
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return  PostViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.blog_post_preview,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]
        val heading = holder.itemView.findViewById<TextView>(R.id.heading_tv)
        val body = holder.itemView.findViewById<TextView>(R.id.description_tv)
        val user = holder.itemView.findViewById<TextView>(R.id.user_id_tv)
        holder.itemView.apply {
            heading.text = post.title
            body.text = post.body
            user.text = post.userId.toString()
//            setOnClickListener {
//                onItemClickListener?.let { it(post) }
//            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
//
//    private var onItemClickListener: ((PostItem) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (PostItem) -> Unit) {
//        onItemClickListener = listener
//    }
}