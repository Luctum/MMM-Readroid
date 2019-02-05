package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import palla_boitard_mubanzo.readroid.models.User

class ProfileInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference
        val username = findViewById<EditText>(R.id.username)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener{
            if(!username.text.toString().isEmpty()){
                registerUsername(username)
            } else {
                username.error = "Le champs doit être remplis"
            }
        }

    }

    fun registerUsername(username : EditText){
        //Create an user in database
        println(username.text.toString())
        database.child("users").child(auth.currentUser!!.uid).setValue(User(username.text.toString(), auth.currentUser!!.email))
        //Redirect to the front page
        val intent = Intent(this, FrontPageActivity::class.java)
        startActivity(intent)
    }
}
