package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.front_page.*
import palla_boitard_mubanzo.readroid.models.Comment
import palla_boitard_mubanzo.readroid.models.Post
import palla_boitard_mubanzo.readroid.models.User

class FrontPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var profileReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_page)
        setSupportActionBar(toolbar)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference
        val text = findViewById<TextView>(R.id.textLogin)

        //Get the current user
        profileReference = database.child("users").child(auth.currentUser!!.uid)
        val profileListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                text.text = user!!.username
            }
        }
        profileReference.addListenerForSingleValueEvent(profileListener)

        //TODO Clean
        val posts : MutableList<Post> = mutableListOf()
        posts.add(Post( User("Josh", "josh@gmail.com"), "12aaa443213", "swag","19/03/2018"))
        posts.add(Post( User("Abbie", "josh@gmail.com"), "12443213", "roulette","13/05/1925"))
        posts.add(Post( User("Gramdoul", "josh@gmail.com"), "1244321ezae3", "saucisse","12/02/2019"))

        val fragment:PostListFragment = supportFragmentManager.findFragmentById(R.id.postFragment) as PostListFragment
        //fragment.setPostsObject(posts)
    }

}
