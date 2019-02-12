package palla_boitard_mubanzo.readroid

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import palla_boitard_mubanzo.readroid.adapter.CommentsViewAdapter
import palla_boitard_mubanzo.readroid.adapter.PostAdapter
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.Post
import palla_boitard_mubanzo.readroid.models.User

class PostListFragment : Fragment() {

    var posts: MutableList<Post> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_post_list, container, false)
        // Inflate the layout for this fragment
        initRecyclerView(v, posts)
        return v
    }

    fun setPostsObject(posts: MutableList<Post>){
        this.posts = posts
    }

    fun initRecyclerView(v: View, posts: MutableList<Post>) {
        val recyclerView: RecyclerView = v.findViewById(R.id.postsList)
        this.posts.add(Post( User("Josh", "josh@gmail.com"), "12a3aa443213", "swag","19/03/2018"))
        this.posts.add(Post( User("Abbie", "josh@gmail.com"), "12443213", "roulette","13/05/1925"))
        this.posts.add(Post( User("Gramdoul", "josh@gmail.com"), "1244321ezae3", "saucisse","12/02/2019"))
        val postsAdapter = PostAdapter(v.context, posts)
        recyclerView.adapter = postsAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(v.context)
    }
}