package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.front_page.*
import palla_boitard_mubanzo.readroid.models.User

class FrontPageActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.front_page)
        setSupportActionBar(toolbar)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference
        val text = findViewById<TextView>(R.id.textLogin)
        text.text = auth.currentUser!!.email
    }

}
