package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import palla_boitard_mubanzo.readroid.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference

        setContentView(R.layout.activity_main)
        val passwordField = findViewById<EditText>(R.id.password)
        val mailField = findViewById<EditText>(R.id.mail)
        val buttonLogin = findViewById<Button>(R.id.loginButton)
        val buttonSignup = findViewById<Button>(R.id.signupButton)

        buttonLogin.setOnClickListener{
            if(!mailField.text.toString().isNullOrEmpty() && !passwordField.text.toString().isNullOrEmpty()){
                login(mailField, passwordField)
            } else {
                mailField.error = "Tous les champs doivent être complétés"
            }
        }

        buttonSignup.setOnClickListener{
            if(!mailField.text.toString().isNullOrEmpty() && !passwordField.text.toString().isNullOrEmpty()){
                login(mailField, passwordField, true)
            } else {
                mailField.error = "Tous les champs doivent être complétés"
            }
        }
    }

    fun login(mail: EditText, password: EditText, signup: Boolean = false) {
        if(signup){
            auth.createUserWithEmailAndPassword(mail.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Clean fields errors
                        mail.error = null
                        sucessLogin()
                    } else {
                        mail.error = "Une erreur est survenue"
                    }
                }
        }
        else {
            auth.signInWithEmailAndPassword(mail.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Clean fields errors
                        mail.error = null
                        sucessLogin()
                    } else {
                        mail.error = "Identifiant ou Mot de passe incorrects"
                    }
                }
        }
    }

    fun sucessLogin(){
        //Redirect to the front page or on the username selection
        val intentProfile = Intent(this, ProfileInfoActivity::class.java)
        val intentFront = Intent(this, FrontPageActivity::class.java)

        val profileReference: DatabaseReference = database.child("users")
        val profileListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                if(dataSnapshot.hasChild(auth.currentUser!!.uid)){
                    startActivity(intentFront)
                } else {
                    startActivity(intentProfile)
                }
                finish()
            }
        }
        profileReference.addListenerForSingleValueEvent(profileListener)
    }

}
