package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.front_page.*
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.Post
import com.google.firebase.database.DataSnapshot



class FrontPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var profileReference: DatabaseReference
    private lateinit var postsReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_page)
        setSupportActionBar(toolbar)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference

        //Get the current user
        postsReference = database.child("posts")
        val posts : MutableList<Post> = mutableListOf()
        val fragment:PostListFragment = supportFragmentManager.findFragmentById(R.id.postFragment) as PostListFragment

        val postListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                for (child in dataSnapshot.child("comments").children) {
                    post!!.comments!!.add(child.getValue(Comment::class.java)!!)
                }
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val postKey = dataSnapshot.key
                posts.removeAt(postKey as Int)
                fragment.setPostsObject(posts)
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }
        }
    }

}
