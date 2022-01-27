package com.olamachia.simpleblogapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.olamachia.simpleblogapp.R
import com.olamachia.simpleblogapp.model.CommentsItem

class CommentsAdapter: RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    inner class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var differCallback = object: DiffUtil.ItemCallback<CommentsItem>(){
        override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.blog_comments_preview, parent, false))
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = differ.currentList[position]
        val commentName = holder.itemView.findViewById<TextView>(R.id.comment_name_tv)
        val commentBody = holder.itemView.findViewById<TextView>(R.id.comment_body_tv)
        val userEmail = holder.itemView.findViewById<TextView>(R.id.email_tv)
        holder.itemView.apply {
            commentName.text = comment.name
            commentBody.text = comment.body
            userEmail.text = comment.email
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}