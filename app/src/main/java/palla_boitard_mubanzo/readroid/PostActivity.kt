package palla_boitard_mubanzo.readroid

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*
import palla_boitard_mubanzo.readroid.adapters.CommentsViewAdapter
import palla_boitard_mubanzo.readroid.models.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class PostActivity : AppCompatActivity() {

    private lateinit var fireBaseHandler: FireBasePostHandler
    private var dialog: Dialog? = null
    private var fireBasePost: FireBasePostHandler = FireBasePostHandler()
    private var commentsContent: HashMap<String, Comment> = HashMap()
    private var postSnapshot: Post? = null
    private var content: TextView? = null
    private var postTitle: TextView? = null
    private var author: TextView? = null


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_layout)
        this.fireBaseHandler = FireBasePostHandler()
        content = findViewById(R.id.postcontent)
        postTitle = findViewById(R.id.postTitle)
        val commentbtn = findViewById<Button>(R.id.commentbtn)
        author = findViewById(R.id.author)
        dialog = Dialog(this)
        val postId = intent.getStringExtra("postId")
        handleComments(postId)
        commentbtn.setOnClickListener {
            showpopup(it, postId)
        }
    }

    private fun initRecyclerView(comments: HashMap<String, Comment>) {
        val recyclerView: RecyclerView = findViewById(R.id.comments_list)
        val commentsAdapter = CommentsViewAdapter(this, comments)
        recyclerView.adapter = commentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    private fun showpopup(view: View, postId: String) {
        dialog?.setContentView(R.layout.comment_dialog_popup)
        val editText: EditText? = dialog?.findViewById(R.id.inputComment)
        val cancelBtn: Button? = dialog?.findViewById(R.id.cancelBtn)
        val doCommentBtn: Button? = dialog?.findViewById(R.id.doCommentBtn)

        cancelBtn?.setOnClickListener {
            dialog?.dismiss()
        }
        doCommentBtn?.setOnClickListener {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val timestamp = current.format(formatter)
            val newComment = Comment(editText?.text.toString(),User("hugo"),timestamp)
            commentsContent[UUID.randomUUID().toString()] = newComment
            this.fireBasePost.create("posts", postId, this.postSnapshot!!)
            dialog?.dismiss()
        }

        dialog?.show()
    }

    private fun handleComments(postId: String) {
        val reference: DatabaseReference = this.fireBasePost.getChild("posts", postId)
        val commentListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                postSnapshot = p0.getValue(Post::class.java)
                author?.text = postSnapshot?.author?.username
                content?.text = postSnapshot?.content
                postTitle?.text = postSnapshot?.title
                commentsContent = postSnapshot?.comments!!
                initRecyclerView(commentsContent)
                Log.d("onDataChange", postSnapshot?.comments!!.toString())
            }
        }
        reference.addValueEventListener(commentListener)
    }


}