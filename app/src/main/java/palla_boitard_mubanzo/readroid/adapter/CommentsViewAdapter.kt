package palla_boitard_mubanzo.readroid.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import palla_boitard_mubanzo.readroid.R
import palla_boitard_mubanzo.readroid.models.Comment

class CommentsViewAdapter(private val comments: MutableList<Comment>, val context : Context):RecyclerView.Adapter<CommentsViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comments_scrollview, p0, false))
    }

    override fun getItemCount(): Int {
        return this.comments.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.commentAuthor.text = comments[p1].author?.username
        p0.commentItem.text = comments[p1].content
        p0.commentTimestamp.text = comments[p1].timestamp
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentItem: TextView = itemView.findViewById(R.id.commentContent)
        val commentAuthor :  TextView = itemView.findViewById(R.id.commentAuthor)
        val commentTimestamp : TextView = itemView.findViewById(R.id.commentTimestamp)
        val commentViewType : CardView = itemView.findViewById(R.id.cardComment)
    }
}