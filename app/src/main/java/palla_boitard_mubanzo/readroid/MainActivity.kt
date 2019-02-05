package palla_boitard_mubanzo.readroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        this.auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        val passwordField = findViewById<EditText>(R.id.password);
        val mailField = findViewById<EditText>(R.id.mail);
        val buttonLogin = findViewById<Button>(R.id.loginButton);
        buttonLogin.setOnClickListener{
            //TODO check field not empty/null
            displayInfo(mailField.text.toString() + "", passwordField.text.toString() + "")
        }
    }

    fun displayInfo(mail: String, password: String) {
        auth.signInWithEmailAndPassword(mail, password+"")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "FAILED", Toast.LENGTH_LONG).show();
                }
            }
    }
}
