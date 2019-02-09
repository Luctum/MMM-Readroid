package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import palla_boitard_mubanzo.readroid.models.FireBaseUserHandler
import palla_boitard_mubanzo.readroid.models.User

class ProfileInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var fireBaseHandler: FireBaseUserHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        this.auth = FirebaseAuth.getInstance()
        this.fireBaseHandler = FireBaseUserHandler()
        val username = findViewById<EditText>(R.id.username)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            if(!username.text.toString().isEmpty()){
                registerUsername(username)
            } else {
                username.error = "Le champs doit être remplis"
            }
        }

    }

    private fun registerUsername(username : EditText){
        //Create an user in database
        println(username.text.toString())
        val name = username.text.toString()
        val mail = auth.currentUser!!.email
        val uuid = auth.currentUser!!.uid
        val user = User(name,mail)
        this.fireBaseHandler.create("users", uuid, user)
        //Redirect to the front page
        val intent = Intent(this, FrontPageActivity::class.java)
        startActivity(intent)
    }
}
