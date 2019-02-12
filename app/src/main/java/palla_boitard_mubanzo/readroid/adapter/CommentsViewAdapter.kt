package palla_boitard_mubanzo.readroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.comments_scrollview.view.*
import palla_boitard_mubanzo.readroid.R
import palla_boitard_mubanzo.readroid.models.Comment

class CommentsViewAdapter(val comments: MutableList<Comment>, val context : Context):RecyclerView.Adapter<CommentsViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.comments_scrollview, p0, false))
    }

    override fun getItemCount(): Int {
        return this.comments.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentViewType = itemView.cardComment
    }
}