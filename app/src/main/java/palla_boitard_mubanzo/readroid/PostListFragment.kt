package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import palla_boitard_mubanzo.readroid.adapters.PostAdapter
import palla_boitard_mubanzo.readroid.models.Post
import palla_boitard_mubanzo.readroid.listeners.RecyclerPostClickListener


class PostListFragment : Fragment() {

    var posts: MutableList<Post> = arrayListOf()
    lateinit var recyclerView: RecyclerView;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_post_list, container, false)
        // Inflate the layout for this fragment
        initRecyclerView(v)
        return v
    }

    fun setPostsObject(posts: MutableList<Post>){
        this.posts = posts
        val postAdapter = this.recyclerView.adapter as PostAdapter
        postAdapter.setPosts(posts)
        recyclerView.addOnItemTouchListener(
            RecyclerPostClickListener(this.posts,this.context!!, object : RecyclerPostClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val intent = Intent(view.context, PostActivity::class.java)
                    intent.putExtra("postId", posts[position].id)
                    startActivity(intent)
                }
            })
        )
    }

    fun initRecyclerView(v: View) {
        this.recyclerView = v.findViewById(R.id.postsList)
        val postsAdapter = PostAdapter(v.context, mutableListOf())
        recyclerView.adapter = postsAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(v.context)
    }
}