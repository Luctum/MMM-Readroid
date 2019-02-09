package palla_boitard_mubanzo.readroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import palla_boitard_mubanzo.readroid.models.FireBasePostHandler

class PostActivity : AppCompatActivity() {

    private lateinit var fireBaseHandler: FireBasePostHandler

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_layout)
        this.fireBaseHandler = FireBasePostHandler()
        var content = findViewById<TextView>(R.id.postcontent)
        val comment = findViewById<Button>(R.id.commentbtn)
        val author = findViewById<TextView>(R.id.author)
    }

}