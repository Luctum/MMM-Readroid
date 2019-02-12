package palla_boitard_mubanzo.readroid.models

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post (val content: String?, val author : User?, val comments: MutableList<Comment>?, val timestamp: String?)

class FireBasePostHandler: FireBaseHandler<Post> {
    override fun getReference(ref: String): DatabaseReference {
        return FirebaseDatabase.getInstance().reference.child(ref)
    }

    override fun getChild(ref: String, child: String): DatabaseReference {
        return this.getReference(ref).child(child)
    }

    override fun create(ref: String, child: String?, obj: Post) {
        this.getChild(ref, child.toString()).setValue(obj)
    }
}