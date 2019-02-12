package palla_boitard_mubanzo.readroid.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import palla_boitard_mubanzo.readroid.R
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.Post

class PostAdapter(private val context : Context, private val posts: MutableList<Post>):RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var tag: String = "PostsHolder"


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Log.d(tag, "OnBindViewHolder started")
        p0.postAuthor.text = posts[p1].author?.username
        p0.postContent.text = posts[p1].content
        p0.postTimestamp.text = posts[p1].timestamp
        p0.postTitle.text = posts[p1].title
        Log.d(tag, "OnBindViewHolder started")
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.post_card, p0, false))
    }

    override fun getItemCount(): Int {
        return this.posts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postContent: TextView = itemView.findViewById(R.id.postContent)
        val postAuthor :  TextView = itemView.findViewById(R.id.postAuthor)
        val postTimestamp : TextView = itemView.findViewById(R.id.postTimestamp)
        val postTitle : TextView = itemView.findViewById(R.id.postTitle)
    }
}