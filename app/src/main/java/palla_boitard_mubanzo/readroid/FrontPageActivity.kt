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
import android.content.DialogInterface
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.widget.EditText
import palla_boitard_mubanzo.readroid.models.FireBasePostHandler
import palla_boitard_mubanzo.readroid.models.User


class FrontPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var profileReference: DatabaseReference
    private lateinit var postsReference: DatabaseReference
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_page)
        setSupportActionBar(toolbar)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference

        //Get the current User
        val profileReference: DatabaseReference = database.child("users").child(auth.currentUser!!.uid)
        val profileListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
               user = dataSnapshot.getValue(User::class.java)!!
            }
        }
        profileReference.addListenerForSingleValueEvent(profileListener)


        val dialog = AlertDialog.Builder(this)
            .setTitle("Ajouter un nouveau Post")
            .setView(R.layout.post_add)
            .setPositiveButton("Add") { dialog, id ->
                val alert = dialog as AlertDialog
                val title = alert.findViewById<EditText>(R.id.titleForm)
                val content = alert.findViewById<EditText>(R.id.contentForm)
                this.database.child("posts").push().setValue(Post(this.user,content!!.text.toString(),title!!.text.toString(),"10/10/2010"))
            }
            .setNegativeButton("Cancel", null)
            .create()

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
                //val postKey = dataSnapshot.key
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }
        }
        postsReference.addChildEventListener(postListener)

        val addPost: Button = findViewById<Button>(R.id.addPostButton)
        addPost.setOnClickListener{
            dialog.show()
        }

    }

}
