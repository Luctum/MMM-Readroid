package palla_boitard_mubanzo.readroid.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import palla_boitard_mubanzo.readroid.R
import palla_boitard_mubanzo.readroid.models.Comment

class CommentsViewAdapter(private val context : Context, private val comments: HashMap<String, Comment>):RecyclerView.Adapter<CommentsViewAdapter.ViewHolder>() {
    private var tag: String = "CommentsViewHolder"

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comments_scrollview, p0, false))
    }

    override fun getItemCount(): Int {
        return this.comments.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Log.d(tag, "OnBindViewHolder started")
        val key = comments.keys.elementAt(p1)
        p0.commentAuthor.text = comments[key]?.author?.username
        p0.commentItem.text = comments[key]?.content
        p0.commentTimestamp.text = comments[key]?.timestamp
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentItem: TextView = itemView.findViewById(R.id.commentContent)
        val commentAuthor :  TextView = itemView.findViewById(R.id.commentAuthor)
        val commentTimestamp : TextView = itemView.findViewById(R.id.commentTimestamp)
    }
}