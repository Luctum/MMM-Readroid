package palla_boitard_mubanzo.readroid

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import palla_boitard_mubanzo.readroid.adapters.CommentsViewAdapter
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.FireBasePostHandler
import palla_boitard_mubanzo.readroid.models.User

class PostActivity : AppCompatActivity() {

    private lateinit var fireBaseHandler: FireBasePostHandler
    private var dialog : Dialog? = null
    private val commentsContent: MutableList<Comment> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_layout)
        this.fireBaseHandler = FireBasePostHandler()
        val content = findViewById<TextView>(R.id.postcontent)
        val postTitle = findViewById<TextView>(R.id.postTitle)
        val commentbtn = findViewById<Button>(R.id.commentbtn)
        val author = findViewById<TextView>(R.id.author)
        dialog = Dialog(this)
        content.text = "Lord Have mercy, I get that ich for sally. That me makes me caught up in the middle. Bobby Womack"
        author.text = "Bobby Womack"
        postTitle.text = "Caught Up In The Middle"
        commentbtn.setOnClickListener {
            showpopup(it)
        }
    }

    private fun initRecyclerView(comments: MutableList<Comment>) {
        val recyclerView: RecyclerView = findViewById(R.id.comments_list)
        val commentsAdapter = CommentsViewAdapter(this, comments)
        recyclerView.adapter = commentsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showpopup(view:View) {
        dialog?.setContentView(R.layout.comment_dialog_popup)
        val editText : EditText? = dialog?.findViewById(R.id.inputComment)
        val cancelBtn : Button? = dialog?.findViewById(R.id.cancelBtn)
        val doCommentBtn: Button? = dialog?.findViewById(R.id.doCommentBtn)

        cancelBtn?.setOnClickListener {
            dialog?.dismiss()
        }
        doCommentBtn?.setOnClickListener {
            commentsContent.add(Comment(editText!!.text.toString(), User("josh"), "12345"))
            initRecyclerView(commentsContent)
            dialog?.dismiss()
        }

        dialog?.show()
    }

}