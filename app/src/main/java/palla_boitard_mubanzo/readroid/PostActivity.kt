package palla_boitard_mubanzo.readroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.TextView
import palla_boitard_mubanzo.readroid.adapters.CommentsViewAdapter
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.FireBasePostHandler
import palla_boitard_mubanzo.readroid.models.User

class PostActivity : AppCompatActivity() {

    private lateinit var fireBaseHandler: FireBasePostHandler
    private var tag: String = "PostActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        val postId = this.intent.getIntExtra("postId", -1)
        if(postId == -1){

        }else{
            println(postId)
        }
        super.onCreate(savedInstanceState)
        Log.d(tag, "OnCreate started")
        setContentView(R.layout.post_layout)
        this.fireBaseHandler = FireBasePostHandler()
        var content = findViewById<TextView>(R.id.postcontent)
        val postTitle = findViewById<TextView>(R.id.postTitle)
        val commentbtn = findViewById<Button>(R.id.commentbtn)
        val author = findViewById<TextView>(R.id.author)
        content.text = "Lord Have mercy, I get that ich for sally. That me makes me caught up in the middle. Bobby Womack"
        author.text = "Bobby Womack"
        postTitle.text = "Caught Up In The Middle"
        val commentsContent : MutableList<Comment> = mutableListOf()
        commentsContent.add(Comment("yo", User("josh", "josh@gmail.com"), "12443213"))
        initRecyclerView(commentsContent)
    }

    fun initRecyclerView(comments: MutableList<Comment>) {
        val recyclerView: RecyclerView = findViewById(R.id.comments_list)
        val commentsAdapter = CommentsViewAdapter(this, comments)
        recyclerView.adapter = commentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}