package palla_boitard_mubanzo.readroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import palla_boitard_mubanzo.readroid.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
// ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.auth = FirebaseAuth.getInstance()
        this.database = FirebaseDatabase.getInstance().reference

        setContentView(R.layout.activity_main)
        val passwordField = findViewById<EditText>(R.id.password)
        val mailField = findViewById<EditText>(R.id.mail)
        val buttonLogin = findViewById<Button>(R.id.loginButton)
        if(auth.currentUser != null){
            val intent = Intent(this, FrontPageActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener{
            if(!mailField.text.toString().isNullOrEmpty() && !passwordField.text.toString().isNullOrEmpty()){
                login(mailField, passwordField)
            } else {
                mailField.error = "Tous les champs doivent être complétés"
            }
        }
    }

    fun login(mail: EditText, password: EditText) {
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

    fun sucessLogin(){
        //Create an user in database
        //database.child("users").child(auth.currentUser!!.uid).setValue(User("Jerome","Albert"))
        //Redirect to the front page
        val intent = Intent(this, ProfileInfoActivity::class.java)
        startActivity(intent)
    }

}
