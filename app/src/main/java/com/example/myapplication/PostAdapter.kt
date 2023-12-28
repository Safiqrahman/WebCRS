package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val context: Context, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    interface OnItemClickListener {
        fun onImageClick(post: PostEntity)
    }

    private var posts: List<PostEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview, parent, false)
        return PostViewHolder(view)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)

        holder.itemView.findViewById<ImageView>(R.id.image).setOnClickListener {
            listener.onImageClick(post)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<PostEntity>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostEntity) {
            itemView.findViewById<TextView>(R.id.Id).text = "${post.id} :"
            itemView.findViewById<TextView>(R.id.title).text = post.title
            itemView.findViewById<TextView>(R.id.price).text = "Price : ${post.price}₹"
            itemView.findViewById<TextView>(R.id.description).text = "Description : ${post.description}"
            itemView.findViewById<TextView>(R.id.category).text = "Category : ${post.category}"
            itemView.findViewById<TextView>(R.id.rating).text = "${post.rating}"

            Glide.with(itemView.context)
                .load(post.image)
                .into(itemView.findViewById<ImageView>(R.id.image))
        }
    }
}