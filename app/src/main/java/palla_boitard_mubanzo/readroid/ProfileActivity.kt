package palla_boitard_mubanzo.readroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.front_page.*
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.Post
import palla_boitard_mubanzo.readroid.models.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var profileReference: DatabaseReference
    private lateinit var postsReference: DatabaseReference
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_page)
        var pseudoText = findViewById<TextView>(R.id.pseudo)
        var profileImg = findViewById<ImageView>(R.id.imgProfile)
        Picasso.get().load("https://vignette.wikia.nocookie.net/hamtaro/images/1/1d/HamtaroN.png/revision/latest/scale-to-width-down/350?cb=20150928215408").into(profileImg);
        pseudoText.text = this.intent.getStringExtra("profileName")

        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference
        postsReference = database.child("posts")
        val posts : MutableList<Post> = mutableListOf()
        val fragment:PostListFragment = supportFragmentManager.findFragmentById(R.id.fragment) as PostListFragment

        val postListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                post!!.id = dataSnapshot.key!!
                println(post.id)
                for (child in dataSnapshot.child("comments").children) {
                    post!!.comments!!.put(child.key!!, child.getValue(Comment::class.java)!!)
                }
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                post!!.id = dataSnapshot.key!!
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                //val postKey = dataSnapshot.key
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val post = dataSnapshot.getValue(Post::class.java)
                post!!.id = dataSnapshot.key!!
                posts.add(post!!)
                fragment.setPostsObject(posts)
            }
        }
        postsReference.addChildEventListener(postListener)
    }
}
